#define _GNU_SOURCE
#include <sys/types.h>
#include <stdio.h>
#include <stdarg.h>
#include <time.h>
#include <errno.h>
#include <unistd.h>
#include <signal.h>
#include <stdlib.h>
#include <stdint.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/fcntl.h>
#include <sys/stat.h>
#include <netdb.h>
#include <sys/select.h>
#include <arpa/inet.h>
#include <netinet/tcp.h>
#include <pthread.h>

#define BUFSIZE 65536
#define IPSIZE 4
#define ARRAY_SIZE(x) (sizeof(x) / sizeof(x[0]))
#define ARRAY_INIT    {0}

unsigned short int port = 1080;		//默认端口为1080
int daemon_mode = 0;				//守护进程
int auth_type;						//认证类型
char *arg_username;
char *arg_password;
FILE *log_file;						//日志输出文件
pthread_mutex_t lock;				//互斥锁
//socket版本
enum socks {
	RESERVED = 0x00,
	VERSION4 = 0x04,
	VERSION5 = 0x05
};
//认证类型
enum socks_auth_methods {
	NOAUTH = 0x00,
	USERPASS = 0x02,
	NOMETHOD = 0xff
};
//用户名+密码认证状态
enum socks_auth_userpass {
	AUTH_OK = 0x00,
	AUTH_VERSION = 0x01,
	AUTH_FAIL = 0xff
};

enum socks_command {
	CONNECT = 0x01
};

enum socks_command_type {
	IP = 0x01,
	DOMAIN = 0x03
};

enum socks_status {
	OK = 0x00,
	FAILED = 0x05
};
//不定长参数，向日志输出消息
void log_message(const char *message, ...)
{
	if (daemon_mode) {
		return;
	}

	char vbuffer[255];
	va_list args;
	va_start(args, message);
	vsnprintf(vbuffer, ARRAY_SIZE(vbuffer), message, args);
	va_end(args);

	time_t now;
	time(&now);
	char *date = ctime(&now);
	date[strlen(date) - 1] = '\0';

	//获取当前线程自身pid
	pthread_t self = pthread_self();

	if (errno != 0) {
		pthread_mutex_lock(&lock);
		fprintf(log_file, "[%s][%lu] Critical: %s - %s\n", date, self,
			vbuffer, strerror(errno));
		errno = 0;
		pthread_mutex_unlock(&lock);
	} else {
		fprintf(log_file, "[%s][%lu] Info: %s\n", date, self, vbuffer);
	}

	//把输出缓冲区中的东西打印到stdout上
	fflush(log_file);
}
//用来读取消息的函数
int readn(int fd, void *buf, int n)
{

	//把fd所指的文件传送count个字节到buf所指的内存中
	//返回回值为实际读取到的字节数, 如果返回0, 表示已到达文件尾或是无可读取的数据
	//若参数count 为0, 则read()不会有作用并返回0
	int nread, left = n;
	while (left > 0) {
		if ((nread = read(fd, buf, left)) == -1) {
			if (errno == EINTR || errno == EAGAIN) {
				continue;
			}
		} else {
			if (nread == 0) {
				return 0;
			} else {
				left -= nread;
				buf += nread;
			}
		}
	}
	return n;
}
//用来写消息的函数
int writen(int fd, void *buf, int n)
{

	//把参数buf所指的内存写入count个字节到参数放到所指的文件内
	//如果顺利write()会返回实际写入的字节数。当有错误发生时则返回-1，错误代码存入errno中
	int nwrite, left = n;
	while (left > 0) {
		if ((nwrite = write(fd, buf, left)) == -1) {
			if (errno == EINTR || errno == EAGAIN) {
				continue;
			}
		} else {
			if (nwrite == n) {
				return 0;
			} else {
				left -= nwrite;
				buf += nwrite;
			}
		}
	}
	return n;
}
//用于关闭socket和线程
void app_thread_exit(int ret, int fd)
{
	close(fd);
	pthread_exit((void *)&ret);
}
//主要连接函数
int app_connect(int type, void *buf, unsigned short int portnum)
{
	int fd;
	struct sockaddr_in remote;
	char address[16];

	memset(address, 0, ARRAY_SIZE(address));

	if (type == IP) {
		char *ip = (char *)buf;
		snprintf(address, ARRAY_SIZE(address), "%hhu.%hhu.%hhu.%hhu",
			 ip[0], ip[1], ip[2], ip[3]);
		memset(&remote, 0, sizeof(remote));
		remote.sin_family = AF_INET;
		remote.sin_addr.s_addr = inet_addr(address);
		remote.sin_port = htons(portnum);

		fd = socket(AF_INET, SOCK_STREAM, 0);

		//connect(SOCKET s, const struct sockaddr * name, int namelen)
		//建立与指定socket的连接
		//name是要连接的套接字的指针
		//namelen是sockaddr结构体的长度
		if (connect(fd, (struct sockaddr *)&remote, sizeof(remote)) < 0) {
			log_message("connect() in app_connect");
			close(fd);
			return -1;
		}

		return fd;
	} else if (type == DOMAIN) {
		char portaddr[6];
		struct addrinfo *res;
		snprintf(portaddr, ARRAY_SIZE(portaddr), "%d", portnum);
		log_message("getaddrinfo: %s %s", (char *)buf, portaddr);

		//关键代码：getaddrinfo(const char *hostname, const char *service, const struct addrinfo *hints, struct addrinfo **result)
		//处理名字到地址以及服务到端口这两种转换
		//hostname：主机名/地址串（如ipv4的点分十进制、ipv6的16进制串）
		//service：要么是端口号，要么是已有的服务名称，比如http、ftp
		//hints：空指针，或者是某个指向addrinfo的指针
		//result：本函数通过result指针参数返回一个指向addrinfo结构体链表的指针
		int ret = getaddrinfo((char *)buf, portaddr, NULL, &res);
		if (ret == EAI_NODATA) {
			return -1;
		} else if (ret == 0) {
			struct addrinfo *r;
			for (r = res; r != NULL; r = r->ai_next) {
				fd = socket(r->ai_family, r->ai_socktype,
					    r->ai_protocol);
                if (fd == -1) {
                    continue;
                }
				ret = connect(fd, r->ai_addr, r->ai_addrlen);
				if (ret == 0) {
					freeaddrinfo(res);
					return fd;
                } else {
                    close(fd);
                }
			}
		}
		freeaddrinfo(res);
		return -1;
	}

    return -1;
}
//版本控制
int socks_invitation(int fd, int *version)
{
	char init[2];
	int nread = readn(fd, (void *)init, ARRAY_SIZE(init));
	if (nread == 2 && init[0] != VERSION5 && init[0] != VERSION4) {
        log_message("They send us %hhX %hhX", init[0], init[1]);
		log_message("Incompatible version!");
		app_thread_exit(0, fd);
	}
	log_message("Initial %hhX %hhX", init[0], init[1]);
	*version = init[0];
	return init[1];
}

char *socks5_auth_get_user(int fd)
{
	unsigned char size;
	readn(fd, (void *)&size, sizeof(size));

	char *user = (char *)malloc(sizeof(char) * size + 1);
	readn(fd, (void *)user, (int)size);
	user[size] = 0;

	return user;
}

char *socks5_auth_get_pass(int fd)
{
	unsigned char size;
	readn(fd, (void *)&size, sizeof(size));

	char *pass = (char *)malloc(sizeof(char) * size + 1);
	readn(fd, (void *)pass, (int)size);
	pass[size] = 0;

	return pass;
}

int socks5_auth_userpass(int fd)
{
	char answer[2] = { VERSION5, USERPASS };
	writen(fd, (void *)answer, ARRAY_SIZE(answer));
	char resp;
	readn(fd, (void *)&resp, sizeof(resp));
	log_message("auth %hhX", resp);
	char *username = socks5_auth_get_user(fd);
	char *password = socks5_auth_get_pass(fd);
	log_message("l: %s p: %s", username, password);
	if (strcmp(arg_username, username) == 0
	    && strcmp(arg_password, password) == 0) {
		char answer[2] = { AUTH_VERSION, AUTH_OK };
		writen(fd, (void *)answer, ARRAY_SIZE(answer));
		free(username);
		free(password);
		return 0;
	} else {
		char answer[2] = { AUTH_VERSION, AUTH_FAIL };
		writen(fd, (void *)answer, ARRAY_SIZE(answer));
		free(username);
		free(password);
		return 1;
	}
}
//选择SOCK5 + 无需认证
int socks5_auth_noauth(int fd)
{
	char answer[2] = { VERSION5, NOAUTH };
	writen(fd, (void *)answer, ARRAY_SIZE(answer));
	return 0;
}

void socks5_auth_notsupported(int fd)
{
	char answer[2] = { VERSION5, NOMETHOD };
	writen(fd, (void *)answer, ARRAY_SIZE(answer));
}

void socks5_auth(int fd, int methods_count)
{
	int supported = 0;
	int num = methods_count;
	for (int i = 0; i < num; i++) {
		char type;
		readn(fd, (void *)&type, 1);
		log_message("Method AUTH %hhX", type);
		if (type == auth_type) {
			supported = 1;
		}
	}
	if (supported == 0) {
		socks5_auth_notsupported(fd);
		app_thread_exit(1, fd);
	}
	int ret = 0;
	switch (auth_type) {
	case NOAUTH:
		ret = socks5_auth_noauth(fd);
		break;
	case USERPASS:
		ret = socks5_auth_userpass(fd);
		break;
	}
	if (ret == 0) {
		return;
	} else {
		app_thread_exit(1, fd);
	}
}
//选择SOCK5情况下，读取指令
int socks5_command(int fd)
{
	char command[4];
	readn(fd, (void *)command, ARRAY_SIZE(command));
	log_message("Command %hhX %hhX %hhX %hhX", command[0], command[1],
		    command[2], command[3]);
	return command[3];
}
//读取端口号
unsigned short int socks_read_port(int fd)
{
	unsigned short int p;
	readn(fd, (void *)&p, sizeof(p));
	log_message("Port %hu", ntohs(p));
	return p;
}
//读取ip
char *socks_ip_read(int fd)
{
	char *ip = (char *)malloc(sizeof(char) * IPSIZE);
	readn(fd, (void *)ip, IPSIZE);
	log_message("IP %hhu.%hhu.%hhu.%hhu", ip[0], ip[1], ip[2], ip[3]);
	return ip;
}
//选择SOCK5情况下，根据ip做出回应
void socks5_ip_send_response(int fd, char *ip, unsigned short int port)
{
	char response[4] = { VERSION5, OK, RESERVED, IP };
	writen(fd, (void *)response, ARRAY_SIZE(response));
	writen(fd, (void *)ip, IPSIZE);
	writen(fd, (void *)&port, sizeof(port));
}
//选择SOCK5情况下，读取域名
char *socks5_domain_read(int fd, unsigned char *size)
{
	unsigned char s;
	readn(fd, (void *)&s, sizeof(s));
	char *address = (char *)malloc((sizeof(char) * s) + 1);
	readn(fd, (void *)address, (int)s);
	address[s] = 0;
	log_message("Address %s", address);
	*size = s;
	return address;
}

void socks5_domain_send_response(int fd, char *domain, unsigned char size,
				 unsigned short int port)
{
	char response[4] = { VERSION5, OK, RESERVED, DOMAIN };
	writen(fd, (void *)response, ARRAY_SIZE(response));
	writen(fd, (void *)&size, sizeof(size));
	writen(fd, (void *)domain, size * sizeof(char));
	writen(fd, (void *)&port, sizeof(port));
}

int socks4_is_4a(char *ip)
{
	return (ip[0] == 0 && ip[1] == 0 && ip[2] == 0 && ip[3] != 0);
}

int socks4_read_nstring(int fd, char *buf, int size)
{
	char sym = 0;
	int nread = 0;
	int i = 0;

	while (i < size) {
		nread = recv(fd, &sym, sizeof(char), 0);

		if (nread <= 0) {
			break;
		} else {
			buf[i] = sym;
			i++;
		}

		if (sym == 0) {
			break;
		}
	}

	return i;
}

void socks4_send_response(int fd, int status)
{
	char resp[8] = {0x00, (char)status, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
	writen(fd, (void *)resp, ARRAY_SIZE(resp));
}

void app_socket_pipe(int fd0, int fd1)
{
	int maxfd, ret;
	fd_set rd_set;
	size_t nread;
	char buffer_r[BUFSIZE];

    log_message("Connecting two sockets");

	maxfd = (fd0 > fd1) ? fd0 : fd1;
	while (1) {
		FD_ZERO(&rd_set);
		FD_SET(fd0, &rd_set);
		FD_SET(fd1, &rd_set);
		ret = select(maxfd + 1, &rd_set, NULL, NULL, NULL);

		if (ret < 0 && errno == EINTR) {
			continue;
		}

		if (FD_ISSET(fd0, &rd_set)) {
			nread = recv(fd0, buffer_r, BUFSIZE, 0);
			if (nread <= 0)
				break;
			send(fd1, (const void *)buffer_r, nread, 0);
		}

		if (FD_ISSET(fd1, &rd_set)) {
			nread = recv(fd1, buffer_r, BUFSIZE, 0);
			if (nread <= 0)
				break;
			send(fd0, (const void *)buffer_r, nread, 0);
		}
	}
}
//app_loop中每个创建的线程从此开始执行
void *app_thread_process(void *fd)
{
	int net_fd = *(int *)fd;
	int version = 0;
	int inet_fd = -1;
	char methods = socks_invitation(net_fd, &version);

	//从net_fd中读取选择的版本
	//一般我们选用SOCK5
	//火狐浏览器中可以选择SOCK4

	switch (version) {
	case VERSION5: {
			socks5_auth(net_fd, methods);
			int command = socks5_command(net_fd);

			if (command == IP) {
				char *ip = socks_ip_read(net_fd);
				unsigned short int p = socks_read_port(net_fd);

				inet_fd = app_connect(IP, (void *)ip, ntohs(p));
				if (inet_fd == -1) {
					app_thread_exit(1, net_fd);
				}
				socks5_ip_send_response(net_fd, ip, p);
				free(ip);
				break;
			} else if (command == DOMAIN) {
				unsigned char size;
				char *address = socks5_domain_read(net_fd, &size);
				unsigned short int p = socks_read_port(net_fd);

				inet_fd = app_connect(DOMAIN, (void *)address, ntohs(p));
				if (inet_fd == -1) {
					app_thread_exit(1, net_fd);
				}
				socks5_domain_send_response(net_fd, address, size, p);
				free(address);
				break;
			} else {
				app_thread_exit(1, net_fd);
			}
		}
		case VERSION4: {
			if (methods == 1) {
				char ident[255];
				unsigned short int p = socks_read_port(net_fd);
				char *ip = socks_ip_read(net_fd);
				socks4_read_nstring(net_fd, ident, sizeof(ident));

				if (socks4_is_4a(ip)) {
					char domain[255];
					socks4_read_nstring(net_fd, domain, sizeof(domain));
					log_message("Socks4A: ident:%s; domain:%s;", ident, domain);
					inet_fd = app_connect(DOMAIN, (void *)domain, ntohs(p));
				} else {
					log_message("Socks4: connect by ip & port");
					inet_fd = app_connect(IP, (void *)ip, ntohs(p));
				}

				if (inet_fd != -1) {
					socks4_send_response(net_fd, 0x5a);
				} else {
					socks4_send_response(net_fd, 0x5b);
					free(ip);
					app_thread_exit(1, net_fd);
				}

				free(ip);
            } else {
                log_message("Unsupported mode");
            }
			break;
		}
	}

	app_socket_pipe(inet_fd, net_fd);
	close(inet_fd);
	app_thread_exit(0, net_fd);

    return NULL;
}

int app_loop()
{
	int sock_fd, net_fd;
	int optval = 1;
	//sockaddr_in用来处理网络通信的地址，同类型的还有sockaddr
	//含有四个变量：sin_family		地址族
	//			  sin_port      16位TCP/UDP端口号
	//			  sin_addr      32位ip地址（这个是一个结构体，内部还有一个s_addr，表示32位ipv4地址）
	//            sin_zero[8]   不使用
	//一般都是用sockaddr_in来定义和赋值socket
	//sockaddr用来函数传参

	struct sockaddr_in local, remote;

	//存储远端的地址长度，用socklen_t是为了做accept的第三个参数
	socklen_t remotelen;

	//socket函数原型：socket(int domain, int type, int protocol)
	//创建一个协议族为domain、协议类型为type、协议编号为protocol的套接字文件描述符
	//AF_INET是ipv4协议
	//SOCK_STREAM是TCP连接
	//如果一个协议只有一种特定类型，那么第三个参数为0

	//创建流式套接字
	//如果失败会返回-1，然后提示socket()，然后退出程序
	if ((sock_fd = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
		log_message("socket()");
		exit(1);
	}
	//setsockopt用于对套接字进行设置
	//这里是设置在closesocket后继续重用该socket
	if (setsockopt
	    (sock_fd, SOL_SOCKET, SO_REUSEADDR, (char *)&optval,
	     sizeof(optval)) < 0) {
		log_message("setsockopt()");
		exit(1);
	}

	memset(&local, 0, sizeof(local));
	local.sin_family = AF_INET;

	//htonl			用来把一个32位数从主机字节顺序转换为网络字节顺序
	//INADDR_ANY	指定地址为0.0.0.0的地址，表示不确定的地址，或“所有地址”、“任意地址”
	local.sin_addr.s_addr = htonl(INADDR_ANY);
	//htons			类似于htonl，将主机字节顺序转为网络字节顺序
	//与htonl的不同在于：htonl()--"Host to Network Long"
	//				   htons()--"Host to Network Short"

	local.sin_port = htons(port);
	//给套接口分配一个本地名字来为套接口建立本地绑定
	//即将本地地址和套接口绑定
	if (bind(sock_fd, (struct sockaddr *)&local, sizeof(local)) < 0) {
		log_message("bind()");
		exit(1);
	}

	//创建一个套接口并监听申请的连接
	//第二个参数是等待连接队列的最大长度
	if (listen(sock_fd, 25) < 0) {
		log_message("listen()");
		exit(1);
	}

	remotelen = sizeof(remote);
	memset(&remote, 0, sizeof(remote));

	log_message("Listening port %d...", port);

	pthread_t worker;
	while (1) 
	{		
		//本函数会阻塞等待直到有客户端请求到达,返回一个新的套接字，代表新的连接，相当于这个客户端的socket
		if ((net_fd =
		     accept(sock_fd, (struct sockaddr *)&remote,
			    &remotelen)) < 0) {
			log_message("accept()");
			exit(1);
		}
        int one = 1;
		//设置服务器socket
        setsockopt(sock_fd, SOL_TCP, TCP_NODELAY, &one, sizeof(one));
		//pthread_create是linux等操作系统创建线程的函数，功能是创建线程
		//net_fd是上面新创建的套接字
		if (pthread_create
		    (&worker, NULL, &app_thread_process,
		     (void *)&net_fd) == 0) {
		//将子线程从主线程分离出来，子线程结束后，资源自动回收
			pthread_detach(worker);
		} else {
			log_message("pthread_create()");
		}
	}
}

void daemonize()
{
	pid_t pid;
	int x;

	pid = fork();
	//把当前运行的程序分为两个完全一样的进程，每个进程都启动一个从代码的同一位置开始执行的进程
	//不需要参数并返回一个int
	//如果成功，返回两次两个值，子进程返回0，父进程返回子进程pid；出错返回-1

	if (pid < 0) {
		exit(EXIT_FAILURE);
	}

	if (pid > 0) {
		exit(EXIT_SUCCESS);
	}

	if (setsid() < 0) {
		exit(EXIT_FAILURE);
	}

	signal(SIGCHLD, SIG_IGN);
	signal(SIGHUP, SIG_IGN);

	pid = fork();

	if (pid < 0) {
		exit(EXIT_FAILURE);
	}

	if (pid > 0) {
		exit(EXIT_SUCCESS);
	}

	umask(0);
	chdir("/");

	for (x = sysconf(_SC_OPEN_MAX); x >= 0; x--) {
		close(x);
	}
}

void usage(char *app)
{
	printf
	    ("USAGE: %s [-h][-n PORT][-a AUTHTYPE][-u USERNAME][-p PASSWORD][-l LOGFILE]\n",
	     app);
	printf("AUTHTYPE: 0 for NOAUTH, 2 for USERPASS\n");
	printf
	    ("By default: port is 1080, authtype is no auth, logfile is stdout\n");
	exit(1);
}

int main(int argc, char *argv[])
{
	int ret;							
	log_file = stdout;					
	auth_type = NOAUTH;					
	arg_username = "user";				
	arg_password = "pass";				
	pthread_mutex_init(&lock, NULL);	

	signal(SIGPIPE, SIG_IGN);

	while ((ret = getopt(argc, argv, "n:u:p:l:a:hd")) != -1) {
		switch (ret) {
		case 'd':{
				daemon_mode = 1;
				daemonize();
				break;
			}
		case 'n':{
				port = atoi(optarg) & 0xffff;
				break;
			}
		case 'u':{
				arg_username = strdup(optarg);
				break;
			}
		case 'p':{
				arg_password = strdup(optarg);
				break;
			}
		case 'l':{
				freopen(optarg, "wa", log_file);
				break;
			}
		case 'a':{
				auth_type = atoi(optarg);
				break;
			}
		case 'h':
		default:
			usage(argv[0]);
		}
	}
	log_message("Starting with authtype %X", auth_type);
	if (auth_type != NOAUTH) {
		log_message("Username is %s, password is %s", arg_username,
			    arg_password);
	}
	app_loop();
	return 0;
}

