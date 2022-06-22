#  BASIC

## C++关键字

| asm        | do           | if               | return      | typedef      |
| ---------- | ------------ | ---------------- | ----------- | ------------ |
| auto       | double       | inline           | short       | typeid       |
| bool       | dynamic_cast | int              | sighed      | typename     |
| break      | else         | long             | sizeof      | union        |
| case       | enum         | mutable          | static      | unsighed     |
| catch      | explicit     | namespace        | static_cast | using        |
| char       | export       | new              | struct      | virtual      |
| class      | extern       | operator         | switch      | void         |
| const      | false        | private          | template    | **volatile** |
| const_cast | float        | protected        | this        | **wchar_t**  |
| continue   | for          | public           | throw       | while        |
| default    | friend       | register         | true        |              |
| delete     | goto         | reinterpret_cast | try         |              |

## 2 数据类型

1. 基本数据类型
2. 构造数据类型：数组 结构体 指针
3. 抽象数据类型

类型名前加上类型修饰符可构成基本类型的派生类型：long  double、unsigned、short [int]

#### 2.1 整型

无符号整数：123U、098U（后面有U）

| 数据类型  | 占用空间                                             | 取值范围                         |
| --------- | ---------------------------------------------------- | -------------------------------- |
| short     | 2 byte                                               | -2^15 ~ 2^15-1（-32768 ~ 32767） |
| int       | 4 byte                                               | -2^31 ~ 2^31-1                   |
| long      | Windows - 4 byte, Linux - 4 byte(32位)，8 byte(64位) | -2^31 ~ 2^31-1                   |
| long long | 8 byte                                               | -2^63 ~ 2^63-1                   |

#### 2.2 实型

| 数据类型 | 占用空间 | 有效数字范围    |
| -------- | -------- | --------------- |
| float    | 4 byte   | 7位有效数字     |
| double   | 8 byte   | 15~16位有效数字 |

- 默认情况下输出一个小数，显示6位有效数字（float和double都是）

`float f = 3.1415926f; //输出3.14159 `

- 避免对两个浮点数进行 == 和 != 比较：

  判断相等 --- `fabs(x-y) < 1e-6`

  判断不相等 --- `fabs(x-y) > 1e-6`

#### 2.3 字符型

输出字符的ASCII码值：

`cout << (int)ch << endl;`

##### ASCII码表

- 0~31---控制字符；32~126---可打印的字符
- A---65；a---97；0---48

![img](https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fupload-images.jianshu.io%2Fupload_images%2F16672994-005c0c71665a4dfb.png&refer=http%3A%2F%2Fupload-images.jianshu.io&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644799698&t=5735dc4a5df8b588e8f4a726e680a543)

##### 转义字符

| 转义字符 | 含义                             | ASCII码值（十进制） |
| -------- | -------------------------------- | ------------------- |
| \a       | 警报                             | 007                 |
| \b       | 退格（BS），将当前位置移到前一列 | 008                 |
| \f       | 换页（FF）                       | 012                 |
| \n       | 换行（LF）                       | 010                 |
| \r       | 回车（CR），回到本行开头         | 013                 |
| \t       | 水平制表（HT）                   | 009                 |
| \v       | 垂直制表（VT）                   | 011                 |
| \\\      |                                  | 092                 |
| \\'      |                                  | 039                 |
| \\"      |                                  | 034                 |
| \?       |                                  | 063                 |
| \0       | 数字0                            | 000                 |
| \ddd     |                                  | 3位8进制数          |
| \xhh     |                                  | 3位16进制           |

`cout << "aaa\t" << endl;`

\t 一共占8个字符，前面aaa占了3个，所以输出后aaa后面还有5个空格字符

#### 2.4 字符串型

1. `char str[] = "hello";` 

2.  `string str = "hello";`

   要包括头文件 `#include <string>`

- 可以直接用 == 判断两字符串是否相等

区分 cin、cin.getline()、getline()

```c++
	string movieTitle;

	//cin >> movieTitle;
	//cin.ignore();
	//cout << movieTitle << endl;

	getline(cin, movieTitle);
	cout << movieTitle << endl;

	getline(cin, movieTitle);
	cout << movieTitle << endl;

	char movieTitle1[10];
	//char* movieTitle1;
	cin.getline(movieTitle1, 64);//cin.getline作用于字符数组
	//getline(cin, movieTitle1); getline作用于string类
	cout << movieTitle1;
	cout << "aa";

	/*cin >> movieTitle;
	cin.ignore();
	cout << movieTitle << endl;*/
```



#### 2.5 布尔类型

```c++
bool flag;
cin >> flag;//若输入100，则flag值为1，因为只要非0就为真
```

```c++
    int x = 10;
    bool z = x;//非0--1
    cout << z << endl;//1
    cout << z+x;//11
```

#### 2.6 常量和变量

常量：

1. 字面常量：通过直接写出常量值来使用的常量 如10

2. 符号常量：通过常量定义给常量取一个名字并指定一个类型，在通过常量名来使用这些常量

   有两种方式定义：

   ​	#define

   ​	[const](https://blog.csdn.net/weixin_35284593/article/details/117147586?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522164872891116780271549198%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=164872891116780271549198&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_ecpm_v1~rank_v31_ecpm-2-117147586.142^v5^pc_search_insert_es_download,143^v6^control&utm_term=%E4%BB%80%E4%B9%88%E6%83%85%E5%86%B5%E4%B8%8B%EF%BC%8C%E7%A8%8B%E5%BA%8F%E5%91%98%E4%BC%9A%E6%8A%8A%E6%95%B0%E6%8D%AE%E6%88%90%E5%91%98%E5%AE%9A%E4%B9%89%E6%88%90constC%2B%2B&spm=1018.2226.3001.4187)：必须在定义时赋初值

   上述方式区别：除形式外 const定义的符号常量有类型 占用存储单元

变量三要素：变量类型、变量值、变量空间地址

##### 域解析运算符与全局变量

```c++
int count1=100;//全局变量
int main()
{  int count1=10;
   int count3=50;
   cout<<count1<<endl;//打印局部变量10
   cout<<::count1<<endl;//打印全局变量100
 {  int count1=20;
    int count2=30;
    cout<<count1<<endl;//打印20
    cout<<::count1<<endl;//仍打印全局变量100
    count1+=3;
 }
    cout<<count1<<endl;
    return 0;  }

```

#### 2.7 数组和指针

1. void sort(int x[],int n); 等价于 void sort(int *p,int n)
2. 函数名、数组名是指针常量

<img src="D:\Users\NElk\AppData\Roaming\Typora\typora_images\image-20220224215549274.png" alt="image-20220224215549274" style="zoom: 50%;" />

##### 指向函数的指针

采用函数名作为参数，通过一个**公用接口调用不同的函数** 

```c++
int max(int,int);
int min(int,int);
int add(int,int);
int process(int x,int y,int (*fun)(int,int));

process(a,b,max);
process(a,b,mix)
```

# C++

## 1  内存分区模型

C++在执行时，将内存大方向分为4个区域：

- 代码区：存放函数体的二进制代码，由操作系统进行管理
- 全局区：存放全局变量、静态变量、常量
- 栈区：编译器自动分配释放，存放函数的参数值、局部变量
- 堆区：程序员分配或释放，若程序员不释放，程序结束时由操作系统回收

存储类型：

- 自动存储：在栈区
- 静态存储：在静态区全局区
- 动态存储：在堆区

### 1.1 程序运行前

程序编译后生成了exe可执行程序，未执行该程序前分为两个区域：

- 代码区：存放CPU执行的机器指令

  ​			代码区是共享的(目的：对于频繁被执行的程序，只需在内存中有一份代码即可)

  ​			代码区只读(防止程序意外修改其指令)

- 全局区：全局变量、静态变量、常量---字符串常量和const修饰的全局变量(全局常量)

  ​			该区域的数据在程序结束后由操作系统释放

### 1.2 程序运行后

- 栈区：局部变量存放在栈区，函数执行完后栈区数据自动释放，故不能返回函数中局部变量的地址，否则会变成野指针
- 堆区：利用new在堆区开辟内存

```c++
int* p = new int (10);
cout << *p << endl;//输出10
```

### 1.3 new和delete

开辟数组：`int* p = new[10];//整型数组中有10个元素`

释放空间：`delete[] p//要加一个[]告诉系统释放的是个数组`

## 2 引用

引用==不是一个变量，本身不占内存空间==；但是，引用具有值，也具有地址值，与被绑定的变量相同；

意义：

1. 复制实参的局限性：当需要以大型对象作为实参传递时，**复制**对象所付出的**时间和存储**空间代价过大；
2. 当需要在**函数中修改实参的值**时，单向值传递，无法实现修改；
3. 当无法实现对象的复制时；

引用比指针的优势：引用作为参数可以提高效率；像指针那样工作，而使用方式如一般变量，具有更好的可读性和直观性。

```c++
int a = 10;
int &b = a;//a的别名是b，a的引用是b，b只可以作为a的别名，不能再作为别的变量的别名
int c = 3;
b = c;//正确，这不是在更改b，而是在给b赋值
```

- 引用必须初始化，一旦初始化后就不可更改（指针指向还可以变 引用不可变）
- 更改变量的别名，变量的值也被改变

#### 引用做函数形参

可直接修改实参

函数参数传递分类：

1. 值传递：形参为普通变量；实质：传递实参的数据值
2. 地址传递：形参为指针变量；实质：传递实参的地址，在子函数中的指针变量改变能传回调用函数中（共用一段存储空间）
3. 引用传递：形参为变量的引用；实质：传递的是实参**变量本身**，实现了“一个变量两个名字”，允许子函数**访问**调用函数中的变量

```c++
int a[]={2,5,3,8,6,9,5,7};
int main()
{ void fun(int b[],int n,int &d);
   int x,y;//x y 分别计数
   fun(a,8,x);  //关注实参
   fun(a+3,5,y);
   cout<<x+y<<endl;
   return 0; }
void fun(int b[],int n,int &d) 
{  d=0;//y==0
    for (int i=0;i<n;i+=2)
       d+=b[i];  
}
//在函数体中改变主函数变量值
```



#### 引用做函数返回值

函数返回值时，会生成一个没有名字的**临时变量**(变量类型与返回值类型一致），后该临时变量再将值赋值给a变量；

而引用返回值时，不生成值的副本，而是返回值直接赋值给变量b：避免了临时变量的产生，若s是一个用户自定义类型时，直接带来了程序执行**效率和空间**利用的优点

不要返回局部变量的引用！

```c++
int& test{
	int a = 10;
	return a;
}
int &res = test();
cout << res << endl;
//第一次打印结果正确，因为编译器做了保留
//第二次再打印，结果就是乱码，因为局部变量出函数就被释放，引用局部变量的别名，不知其内存空间是什么，非法
```

函数的调用可以作为左值

```c++
int& test{
	static int a = 10;//静态局部变量，放在全局区，程序结束后由系统释放
	return a;
}
int &res = test();
test() = 100;//test()就是a的引用，相当于修改a，res也被修改为100
```

#### 引用的本质

在C++内部是一个指针常量（指针的指向不可改变，指针指向的值可以改变）

#### 常量引用

```c++
int a = 10; 
int &b = 10;//错误，引用必须引用的是一块合法的内存空间
```

```c++
const int &b = 10;//常量引用
//正确，编译器自动将代码改为 int tem = 10;int &b = tem;
b = 10;
//错误，加上const之后变为只读，不可修改
```

常量引用常用于修饰形参，防止误操作

#### 数组的引用

```c++
void test(int (&arr)[5]){//正确！
    //正确：int* arr 或 int arr[] 都可以在函数体内改变数组的值
    //错误：int (&arr)[] 报错
    for (int i = 0; i < 5; i++){
        arr[i]+=1;
    }
}

int arr[5] = {1,2,3,4,5};
test(arr);
```



## 3 函数

### 3.1 函数默认参数

```C++
int test(int a, int b = 10, int c = 20){
    return a + b + c;
}
```

`test(100);//正确，a=100，b=10，c=20`

`test(100,50);//正确，如果自己传入数据，则实参代替默认形参的值`

- 如果形参表的某个形参有了默认值，则该形参后的没一个形参都必须有默认值

```C++
int test(int a, int b = 10, int c){
    return a + b + c;
}//错误，c必须也有默认值
```

- 函数声明和该函数定义只能有一处能有默认参数，声明处定义了默认参数，定义处就不能重新定义默认参数；定义处定义了默认参数，函数声明处就不能再定义

  函数有声明时，默认的参数值应设置在函数的说明语句中，而不是函数的定义中；如果没有函数声明时，默认的参数值可设置在函数的定义中

### 3.2 占位参数

形参列表里可以有占位参数，调用函数时必须填补该位置

```c++
int test(int a, int b, int ){
    return a + b;
}
test(10, 10);//错误，占位参数必须有值
test(10, 10, 10);//正确
```

占位参数可以有默认参数

```c++
int test(int a, int b, int = 10){
    return a + b;
}
test(10, 10);//正确，因为占位参数有了默认值
```

### 3.3 函数重载

函数名可以相同，提高复用性

可以函数重载的条件：

1. 同一个作用域下
2. 函数名称相同
3. 函数参数或者**类型不同**，或者**个数不同**，或者**顺序不同**
4. 注意：仅返回值不同的函数不可以作为函数重载！

重载函数的选择：

1. 先查找的是**严格匹配**的；

2. 再查找通过**类型转换**可以匹配的；（见书p40-整型提升）

3. 最后是通过**用户的强制类型转换**达到匹配的

```c++
void print(double x);
void print(char x);

print(1);//匹配失败，因为int可以转成char和double都行
```

```c++
int pow(int a, int b);
double pow(double a, double b);

pow(2.0, 1);//失败
```



- 引用作为重载的条件：采用引用参数不能区分函数重载

```c++
void test(int &a){
    ;
}
void test(const int &a){
    ;
}
//可作为函数重载的条件，参数类型不同
int a = 10;
test(a);//调用第一个test，因为a是个变量可读可写，第二个const int &a只可读不可写
test(10);//10是个常量，调用第二个test
```

```c++
void test(int &a){
    ;
}
void test(int a){
    ;
}
//从形参上看：类型不一致
//编译器角度下：参数x与double x原型和double &x原型均匹配，编译器无法确定究竟使用哪个函数
```

- 引用作为函数形参的默认参数

```c++
void test(int &a = 10){//错误，语法错误，引用只能指向合法的内存空间
    ;
}
void test(const int &a = 10){//正确
    ;
}
```

- 函数重载与默认参数

```c++
void test(int a, int b = 10){
    ;
}
void test(int a){
    ;
}
test(10);//错误，编译器不知道调用哪一个，出现二义性
test(10, 20);//正确，很明确，调用第一个
```

### 3.4 内联函数

**注意事项**：

1. 内联函数的函数体内**不允许**出现**循环语句和开关语句**等大语句。如果内联函数的函数体内含有这些语句时，编译系统将它**按普通函数**处理；

2. 内联函数的函数体不宜过大。通常以1~5行为宜。过大会增加源程序的代码量；

3. 在类结构中，在**类体内定义的成员函数**都是内联函数；

4. 内联函数名有**文件作用域**，在一个文件中定义的内联函数对另一个文件是不可见的

   由于编译器不对内联函数生成独立的代码，而是用函数体替换内联函数调用，因此在调用内联函数时一定要见到内联函数的定义，所以在使用同一个内联函数的**各个源文件**都要有对内联函数的定义

   而各个源文件定义的同名内联函数属于不同的函数，为同一个内联函数各个定义之间的不一致，通常把**内联函数的定义放在某个头文件中**

内联函数使用的场合：优化程序、提高效率，把函数短小而又频繁调用的函数声明为内联函数

函数体适当小，这样就使嵌入工作容易进行，不会破坏原调用主体；程序中特别是在**循环中反复执行该函数**，这样就使嵌入的效率相对较高。

```c++
inline int cube(int);
void main()
{ for (int i=1;i<=10;i++)
    { int p=cube(i);//循环中多次调用
      cout<<i<<‘*’<<i<<‘*’<<i<<‘=‘<<p<<endl;
    }
}
inline int cube(int n)
{ return n*n*n;
}
```

内联函数与宏定义：

```c++
#define pow(x) x*x

inline int pow(int x){
    return x*x;
}

pow(1+1);
//注意！！！宏定义是把x用 1+1 代替，变成了 1+1*1+1！！！
```

### 3.5 匿名函数

[]        //未定义变量.试图在Lambda内使用任何外部变量都是错误的.
[x, &y]   //x 按值捕获, y 按引用捕获.
[&]       //用到的任何外部变量都隐式按引用捕获
[=]       //用到的任何外部变量都隐式按值捕获
[&, x]    //x显式地按值捕获. 其它变量按引用捕获
[=, &z]   //z按引用捕获. 其它变量按值捕获
https://blog.csdn.net/zhang14916/article/details/101058089

## 4 类和对象

C++面向对象的三大特性：封装、继承、多态

类：具有相同性质的对象

### 4.1 封装

设计一个类

```c++
#include <iostream>
using namespace std;
#include <string> //用string类型时要包括该头文件
//设计一个学生类
class Student{
//访问权限
public: //公共权限，类内和类外都可以调用
    //类中的属性和行为，统一称为成员
    //属性：一些变量
    string m_Name;
    int m_ID;
    
    //行为：一些函数
    void showInfo(){
        cout << "姓名：" << m_Name << " 学号：" << m_ID << endl;
    }
};//记得加这个分号！！！

int main(){
    //实例化对象：创建一个具体的对象
    Student s1;
    //给类中成员变量赋值的方法1：
    s1.m_Name = "张三";
    //调用成员函数
    s1.showInfo();
    
    system("pause");
    return 0;
}
```

给类中成员变量赋值的另一方法：利用成员函数赋值

```c++
class Student{
public:
    string m_Name;
    int m_ID;
    
    void showInfo(){
        cout << "姓名：" << m_Name << " 学号：" << m_ID << endl;
    }
    //给变量赋值
    void setInfo(string name, int ID){
        m_Name = name;
        m_ID = ID;
    }
};//加分号！！！

int main(){
    Student s1;
    s1.setInfo("张三", 10);//传参
}
```

#### 4.1.1 访问权限

1. `public`公共权限：成员变量在类内和类外均可以被访问
2. `protected`保护权限：类内可以，类外不行；继承中子类可以访问父类的保护内容
3. `private`私有权限：类内可以，类外不行；继承中子类不可以访问父类的私有内容

struct 和 class 的区别：struct默认权限为公共，class**默认为私有**

#### 4.1.2 成员属性私有化

成员变量私有化，但可通过<u>公共的成员函数</u>为对外接口，从类外读或写成员变量

私有化的优点：① 可以自己控制读写权限；② 对于写数据，可以检测数据的有效性

```c++
class Student{
private:
    string m_Name;
    int m_ID;
public:
     //给变量赋值，可写
    void setInfo(string name, int ID){
        m_Name = name;
        if( ID > 100 ){//体现优点②
            cout << "ID输入有误！" << endl;
            return;
        }
        m_ID = ID;
    }
    void showInfo(){//可读
        cout << "姓名：" << m_Name << " 学号：" << m_ID << endl;
    }
    string getName(){
        return m_Name;
    }
};//加分号！！！

int main(){
    Student s1;
    s1.setInfo("张三", 10);//传参
    cout << "姓名为：" << s1.getName << endl;
}
```

或在类内声明成员函数，类外来定义：

```c++
//类外定义：
void Student::showInfo(){//注意这里的写法
    cout << "姓名：" << m_Name << " 学号：" << m_ID << endl;
}
```



### 4.2 对象的初始化和清理

#### 4.2.1 构造函数和析构函数

这两个函数会被编译器自动调用，完成对象的初始化和清理；不提供构造和析构函数，编译器会提供，但编译器提供的构造和析构函数是空实现

构造函数：创建对象时为对象的成员属性赋值

析构函数：对象销毁前系统自动调用，执行一些清理工作

**构造函数**：`类名(){ }`

1. 没有返回值也不写void
2. 函数名称与类名同名
3. 构造函数可以有参数，故可以函数重载
4. 程序在调用对象时会自动调用构造函数，且只会调用一次

**析构函数**：`~类名(){ }`

1. 没有返回值也不写void
2. 前面有个~，名字与类名相同
3. 不可有参数，不可重载
4. 程序在对象销毁前会自动调用，且只会调用一次

<u>前面的访问权限都是public！！！</u>

```c++
class Person{
public:
    Person{
        cout << "无参构造函数" << endl;
    }
    Person(int a){
        age = a;
        cout << "有参构造函数" << endl;
    }
    //拷贝构造函数：顾名思义，拷贝一份类，形参必须有const和&
    Person(const Person &p){
        age = p.age;
        cout << "拷贝构造函数" << endl;
    }
    
    //析构函数
    ~Person(){
        cout << "析构函数" << endl;
    }
    
    int age;
}

int main(){
    //1. 括号法进行调用
    Person p1;//调用默认构造函数，注意此时不要加空括号()，否则编译器会当做一个函数声明
    Person p2(10);//调用有参构造函数
    Person p3(p2);//调用拷贝构造函数
    
    //2. 显示法进行调用
    Person p1;
    Person p2 = Person(10);
    Person p3 = Person(p2);
    //其中Person(10)叫做匿名对象，其特点是当前执行结束后，系统会立刻回收掉该对象
    Person(p2);
    //不要用拷贝构造函数初始化匿名对象，编译器认为 Person(p2); === Person p2; 会认为 p2 重定义
    
    //3. 隐式转换法调用
    Person p4 = 10;//编译器把该行隐式转换为 Person p4 = Person(10);
    
    return 0;
}
```

类外定义构造函数：

```c++
class Person{
public:
    Person();
private:
    int mA;
};

Person::Person(){//注意此处的写法！
    mA = 100;
}
```



#### 4.2.2 拷贝函数的调用时机

1. 使用一个已经创建完毕的对象来初始化一个新对象

2. 值传递的方式给函数参数传值

   ```c++
   void doWork(Person p){//这里的p是拷贝函数
       ;
   }
   void test(){
       Person p;
       doWork(p);
   }
   ```

3. 以值方式返回局部对象

   ```c++
   Person doWork(void){
       Person p;
       return p;//返回的不是p，而是拷贝了一份p，调用拷贝构造函数
   }
   void test(){
       Person p = doWork();
   }
   ```

##### 拷贝构造函数与赋值运算符

在默认情况下（用户没有定义，但是也没有显式的删除），编译器会自动的隐式生成一个拷贝构造函数和赋值运算符。但用户可以使用`delete`来指定不生成拷贝构造函数和赋值运算符，这样的对象就不能通过值传递，也不能进行赋值运算。

```cpp
class Person
{
public:
    // 显式的删除了拷贝构造函数和赋值运算符，在需要调用拷贝构造函数或者赋值运算符的地方，会提示无法调用该函数，它是已删除的函数
	Person(const Person& p) = delete;
	Person& operator=(const Person& p) = delete;
private:
	int age;
	string name;
};
```

#### 4.2.3 构造函数调用规则

默认情况下，编译器至少给一个类添加3个函数：默认构造函数、默认析构函数、默认拷贝构造函数(默认对属性进行值拷贝，不需自己写代码来值拷贝)

构造函数调用规则：

- 如果用户定义有参构造函数，则编译器不再提供默认无参构造函数(则需要自己写一个默认构造函数，否则没有默认构造函数还调用 `Person p;` 就会报错，因为该句代码是要调用默认无参构造函数，但没有默认无参构造函数可以调用)，但会提供默认拷贝构造函数
- 如果用户定义拷贝构造函数，编译器不再提供其他构造函数

#### 4.2.4 深拷贝与浅拷贝

浅拷贝：简单的赋值拷贝操作

深拷贝：在堆区重新申请空间，进行拷贝操作，避免了浅拷贝堆区指针而引起的内存重复释放的问题

如果属性有在堆区开辟，要自己提供拷贝构造函数，防止系统默认的浅拷贝带来的问题

```c++
class Person{
public:
    Person(){
    }
    Person(int age, int height){
        m_age = age;
        m_height = new int(height);
    }
    Person(const Person &p){
        m_age = p.m_age;
        //m_height = p.m_height;//系统默认的浅拷贝，由于拷贝的是指针，原函数与拷贝函数的两个指针指向同一块内存，在析构函数中释放空间时，会造成同一块内存的重复释放
        m_height = new int(*p.height);//深拷贝，自己在堆区新开辟一个内存，两个指针指向堆区不同内存，不会重复释放
    }
    ~Person(){//析构函数的作用之一：释放堆区用户自己开辟的空间
        if(m_height != NULL){
            delete m_height;
        }
    }
    int m_age;
    int* m_height;
};
```

#### 4.2.5 初始化列表

```c++
class Person{
private:
    int m_a;
    int m_b;
    double m_c;
public:
    Person(int a, int b, double c):m_a(a), m_b(b), m_c(c){}
};
```

[必须用初始化列表的情况](https://blog.csdn.net/weixin_43692030/article/details/99292437)：

1. **类成员为const**，因为常量只能初始化不能赋值
2. **类成员为引用**，因为引用必须在定义时初始化且不能被赋值
3. 没有默认构造函数的类类型，因为使用初始化列表可以不必调用默认构造函数来初始化，而是直接调用拷贝构造函数初始化
4. 类存在继承关系，**子类必须在初始化列表中调用基类的构造函数**

```c++
class Demo{
	const int m_a;
	int &m_b;
    Demo(int a,int& b):m_a(a),m_b(b){}
    ~Demo(){}
};
```

```c++
// 子类调用父类构造函数
class Base
{
    public:
        Base(int a) : val(a) {}
    private:
        int val;
};
 
class A : public Base
{
    public:
        A(int v) : p(v), Base(v) {} // 调用父类构造函数
        void print_val() { cout << "class A:" << p << endl;}
    private:
        int p;
};
int main()
{
    int m = 45;
    A b(m);
    b.print_val();
}

// 子类调用父类构造函数
　class animal  
　{  
　public:  
　　　animal(int height, int weight)  
　　　{  
　　　　　cout<<"animal construct"<<endl;  
　　　}  
　　　…  
　};  
　class fish:public animal  
　{  
　public:  
　　　fish():animal(400,300)  //此处！！！
　　　{  
　　　　　cout<<"fish construct"<<endl;  
　　　}  
　　　…  
　};
```



#### 4.2.6 类对象作为类成员

当一个类中包含另一个类对象时，该类会先调用另一个类对象的构造函数，再调用本类中的构造函数来构造本类中的其他成员变量

```c++
class Phone{
public:
    string m_Logo;
    Phone(string logo){
        m_Logo = logo;
    }
};

class Person{
public:
    string m_Name;
    Phone m_Phone;//先构造另一个类的对象
    Person(string name, string logo):m_Name(name), m_Logo(logo){}//再构造本类中的对象
    
    void dispInfo(){
        cout << m_Name << endl << m_Logo <<endl;//可以直接调用另一个类对象中的成员
    }
};
```

#### 4.2.7 静态成员

静态成员：就是在成员变量和成员函数前加上关键字 static

静态成员变量

- 所有对象共享一份数据
- 在编译阶段分配内存
- 类内声明，类外初始化

静态成员函数

- 所有对象共享同一个函数：即不属于具体的某一个对象，所以不需要创建具体的对象也可以直接**通过类名访问**静态成员函数
- 静态成员函数只能访问静态成员变量

```c++
class Person{
public:
    static void func(){
        m_A = 100;
    }
    static m_A;
};
//类外访问静态成员变量
int Person::m_A = 10;

//两种访问静态成员函数的方式
//1. 通过对象访问
Person p;
p.func();
//2. 通过类名访问
Person::func();//不属于具体的某一个对象，所以可直接通过类名访问
//类外不能访问private静态成员函数
```

### 4.3 C++对象模型和 this 指针

#### 4.3.1 成员变量和成员函数分开存储

`Person p; sizeof(p)`Person 类中什么也没有，则空对象 p 的大小仍为一个字节，空对象也占一个字节空间是为了区分空对象占内存的位置

类的成员变量和成员函数分开存储：只有非静态成员变量才属于类的对象上，静态成员函数、静态成员变量、非静态成员函数都不在类的对象上，都只有一份，每个对象都可以用这一份

```c++
class Person{
public:
    int a;
    static int b;
    void func(){
        cout << this->a << endl;
    }
};
Person p;
sizeof(p);//大小为4，只有非静态成员变量a在对象p上
```

#### 4.3.2 this指针

本质：指针常量，指针指向不可以变，指针指向的值可以改变

1. 当形参和成员变量同名时，用`this->成员变量名`来和形参区分
2. 每一个非静态或静态成员函数只会产生一份函数实例，即多个同类型对象会共用同一段代码，这一代码块通过this指针区分是哪个对象调用的自己
3. 在类的非静态成员函数中返回对象本身，可用 `return *this`

```c++
class Person{
    Person(int age){
        //age = age;//错误，形参与成员变量同名时，会认为这两个age都是形参而没有进行成员变量的赋值
        this->age = age;
    }
    
    Person& AddAge(Person p){//返回值类型必须是引用，否则返回的不是该对象本身，而是一个拷贝对象
        this->age += p.age;
        return *this;
    }
    int age;//所以为了防止形参和成员变量名字一样，一般成员变量命名前加一个m-表示member
};
Person p1(10);
Person p2(5);
p2.AddAge(p1);//返回值是p2自己本身
p2.AddAge(p1).AddAge(p1).AddAge(p1);//所以可以链式调用该函数实现连加
```

##### 空指针访问成员函数

```c++
class Person{
public:
    void showInfo(){
        cout << "person1" << endl;
    }
    void showAge(){
        cout << mAge << endl;//这里相当于 this->mAge
    }
    int mAge;
};

Person* p = NULL;
p->showInfo();//虽然p是空指针，但这一行可以打印，运行正常
p->showAge();//不能打印这一行，程序崩溃，因为this为空，没有对象和对象中的成员变量mAge
```

#### 4.3.3 const修饰成员函数-常函数

常函数：

- 成员函数**后**加 const 即为常函数
- 常函数内不可修改成员属性
- 成员属性声明时加关键字 mutable 后，在常函数中依然可以修改

常对象：

- 声明对象前加 const 即为常对象
- 常对象只能调用常函数（如果可以调用普通成员函数，则该函数中可以修改成员变量的值，这就通过常对象间接改变了成员变量，这是不允许的）
- 常对象不可修改普通成员变量的值，但可以修改静态变量的值

```c++
class Person{
    void showPerson() const{//在成员函数后加const修饰的是this指针
//this本来是 Person * const this 指针常量，在成员函数后加了const则变成 const Person* const this 指针指向的值也不可以改变了
        //mA = 100;//相当于 this->mA 不可以修改
        mB = 100;//可以修改
    }
    int mA;
    mutable int mB;
};

const Person p;//常对象
p.mA = 100;//错误，不可修改
p.mB = 100;//可以修改
```

### 4.4 友元

关键字：`friend`

目的就是让一个函数或一个类访问另一个类中的私有成员

#### 4.4.1 	函数作友元

```c++
#include <iostream>
using namespace std;

class House {
    friend void goodFriend(House* biulding);
    //在类内声明该全局函数是类的友元函数，可以访问其私有成员变量
public:
    House() {
        mSittingRoom = "客厅";
        mBedRoom = "卧室";
    }
public:
    string mSittingRoom;
private:
    string mBedRoom;
};

void goodFriend(House* biulding) {
    cout << biulding->mBedRoom;//友元可以以访问私有变量
    cout << biulding->mSittingRoom;
}

int main() {
    House biulding;
    goodFriend(&biulding);
}
```

#### 4.4.2 类做友元

```c++
class Biulding{
    friend class gFriend;//声明类为友元，类可以访问该类中的私有变量
    friend void gFriend::Visit();//或声明另一个类中的成员函数为友元
    
public:
    Biulding();//类内声明构造函数，在类外定义
    string mSittingRoom;
private:
    string mBedRoom;
};

class gFriend{
public:
    gFriend();
    void Visit();
private:
    Biulding* biuld;
};

//构造函数定义：
Biulding::Biulding(){
    mSittingRoom = "客厅";
    mBedRoom = "卧室";
}
gFriend::gFriend(){
    biuld = new Biulding;
}

//成员函数定义：
void Visit(){
    cout << biuld->mBedRoom;//必须声明了友元，否则这一行不可访问
}
```

### 4.5 运算符重载

对已有的运算符重新定义，赋予其另外一种功能以适应不同的数据类型

**不能被重载的运算符**：长度运算符`sizeof`、条件运算符`: ?`、成员选择符`.`和域解析运算符`::`

**只能以成员函数重载**：箭头运算符`->`、下标运算符`[ ]`、函数调用运算符`( )`、赋值运算符`=`

-  重载不能改变运算符的优先级和结合性

- 重载不会改变运算符的用法，原有几个操作数、操作数在左边还是在右边，这些都不会改变。例如`~`号右边只有一个操作数，`+`号总是出现在两个操作数之间，重载后也必须如此。

- 运算符重载函数不能有默认的参数，否则就改变了运算符操作数的个数

- 运算符重载函数既可以作为类的成员函数，也可以作为全局函数。

  将运算符重载函数作为类的成员函数时，二元运算符的参数只有一个，一元运算符不需要参数。之所以少一个参数，是因为这个参数是this指针隐含给出，作为左操作数；形参是右操作数

  将运算符重载函数作为全局函数时，二元操作符就需要两个参数，一元操作符需要一个参数，而且其中必须有一个参数是对象，好让编译器区分这是程序员自定义的运算符，防止程序员修改用于内置类型的运算符的性质。

  运算符重载函数作为全局函数时，一般都需要在类中将该函数声明为友元函数

[对于成员函数形式的重载 “+”](https://blog.csdn.net/qq_44861675/article/details/105126559)
对于b = a + 1.1;，被转换为b = a.operator+(1.1);，1.1 也是被转换为complex(1.1)；
对于b = 2.2 + a;，被转换为b = (2.2).operator+(a);，这很显然是不正确的，进而编译报错；
以全局函数的形式重载 +，是为了保证 + 运算符的操作数能够`被对称的处理`；换句话说，小数（double）在 + 左边和右边都是正确的；
this指针隐含传递给函数作为左操作数，形参表中的参数作为运算符的右操作数

#### 类型转换函数

类类型向基本数据类型转化：operator

```C++
//类型转换函数
//1. 必须是成员函数
//2. 没有返回值类型
//3. 在函数体内必须用return 语句以传值方式返回一个目标类型的变量(对象)
//4. 没有形参
//5. 一般情况下，不要使用它， 因为它违反了常规思维方式
```

```c++
class Complex
{
public:
   Complex( ){real=0;imag=0;}
   Complex(double r,double i){real=r;imag=i;}
   operator double( ) {return real;} //类型转换函数
private:
   double real;
   double imag;
};
int main( )
{
   Complex c1(3,4),c2(5,-10),c3;
   double d;
   d=2.5+c1;//要求将一个double数据与Complex类数据相加
   cout<<d<<endl;
   return 0;
}
```

|                    |                                                              | 原因                                                         |
| ------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 只能以成员函数重载 | 箭头运算符 `->`<br />下标运算符 `[ ]`<br />赋值运算符 `=`<br />函数调用运算符 `( )` | 运算符重载函数规定是类成员函数时，<br />类的**this指针会被绑定到运算符的左侧运算对象**，<br />成员运算符函数的显示参数比运算符对象总数少一个，<br />即**左操作数必须是该类类型的参数**；<br />如果是全局函数，则可能出现非法情况，如6=c |
| 只能以普通函数重载 | `<<` `>>`                                                    | 原因同上，<<和>>左操作数不能是类对象，<br />而是istream和ostream的对象cin和cout |



#### 4.5.1 加号运算符重载

实现两个类相加

> 1. 对内置的数据类型的表达式的运算 不可以用运算符重载去改变
> 2. 不要滥用运算符重载(即不要把对加法的运算重载为两个自定义类型相减)

```c++
class Person{
public:
    Person(){}
    Person(int a, int b){
        this->mA = a;
        this->mB = b;
    }
    //1. 成员函数实现运算符重载
    Person operator+(const Person& p){//返回值不是引用了 而是一个类 是因为返回后要创建一个新对象
        Person tmp;
        tmp.mA = this->mA + p.mA;
        tmp.mB = this->mB + p.mB;
        return tmp;
    }
    int mA;
    int mB;
};

//2. 全局函数实现运算符重载
Person operator+(const Person& p1, const Person& p2){
    Person tmp;
    tmp.mA = p1.mA + p2.mA;
    tmp.mB = p1.mB + p2.mB;
    return tmp;
}

//函数可以重载
Person operator+(const Person& p, int num){
    Person tmp;
    tmp.mA = p.mA + num;
    tmp.mB = p.mB + num;
    return tmp;
}

void test(){
    Person p1;
    Person p2;
    Person p3 = p1 + p2;//运算符重载
    Person p4 = p3 + 100;
}
```

#### 4.5.2 >>和<<的重载

作用：配合友元 

可输出自定义的数据

```c++
//重载运算符<< 使之能直接输出自定义类型一个类
class Person{
    friend ostream& operator<< (ostream &cout, const Person &p);
    friend istream & operator>>(istream & in , complex &a);
//5. 成员变量一般是私有，又不能用成员函数重载<<，所有就用友元声明
public:
    Person(int a, int b){
        ma = a;
        mb = b;
    }
private:
    int ma;
    int mb;
};
//1. 一般用全局在函数重载<<，再在类中声明友元
//2. cout是ostream类下的一个对象 ostream下的对象只能有一个，所以形参和返回值必须是引用 否则就会拷贝一个新对象
ostream& operator<< (ostream &cout, const Person &p){
    cout << "p.ma = " << p.ma << ", p.mb = " << p.mb;
    return cout;
//4. 如果不返回ostream 则test中只能cout << p;后面输出<< endl会报错 因为只有返回了ostream才能继续调用<<进行输出
}

istream & operator>>(istream &in, complex &A){
    in >> A.m_real >> A.m_imag;
    return in;
}

void test(){
    Person p(10, 10);
    cout << p << endl;//链式编程
}
```

#### 4.5.3 递增运算符重载

前置++、后置++的重载

```c++
class MyInteger{
    friend ostream& operator<<(ostream& out, const MyInterger &my);
    
public:
    MyInteger(){
        mnum = 0;
    }
    
    //前置++重载
    MyInterger& operator++(){//1. 返回值必须是引用 返回自身 否则链式编程时实际上mnum的值没有改变
        mnum++;
        return *this;
    }
    
    //后置++重载
    MyInterger operator++(int){//2. 不允许只有返回值不同的函数重载，所以加一个占位参数，让编译器知道在做后置++的重载
        Myinterger tmp = *this;
        // 或：Myinteger tmp(*this); 拷贝构造
        mnum++;
        return tmp;
        //3. 返回值不能是引用 因为返回的是函数局部变量tmp 出函数后被销毁，此时返回tmp本身 则非法访问
    }
    
private:
    int mnum;
};

//重载<<
ostream& operator<<(ostream& out, const MyInterger &my){
    out << my.mnum;
    return out;
}

void test(){
    MyInterger myint;
    cout << ++myint << endl;
    cout << myint++ << endl;
}
```

#### 4.5.4 赋值运算符重载

c++编译器至少给一个类添加4个函数：

1. 默认构造：无参 函数体为空
2. 默认析构：无参 函数体为空
3. 默认拷贝构造函数：对属性进行值拷贝
4. 赋值运算符 operator= ：对属性进行值拷贝---浅拷贝，即对两个类赋值时可以直接 Person p2 = p1;

赋值运算符重载：为防止浅拷贝带来的内存重复释放

```c++
class Person{
public:
    Person(int a){
        m_age = new int(a);
    }
    // 只能在成员函数中重载
    Person& operator=(const Person& p){
        //重载=：应先判断原本是否有属性在堆区 即this对象有没有被初始化指针属性 如果有 应先释放该内存 再开辟新内存
        if(m_age != NULL){
            delete m_age;
            m_age = NULL;
        }
        m_age = new int(*p.m_age);
        return *this;//返回自身 即返回值是引用 以便链式编程
    }
    
    ~Person(){
        if(m_age != NULL){
            delete m_age;
            m_age = NULL;
        }
    }
    
    int* m_age;
}

void test(){
    Person p1(10);
    Person p2(20);
    Person p3 = p2 = p1;//如果重载=的返回值不是本身 则无法进行链式编程 p3= 处会报错
}
```

#### 4.5.5 关系运算符的重载

== <= 等的重载

```C++
//重载==运算符：返回值是bool
bool operator==(const Complex &c1, const Complex &c2){
    if( c1.m_real == c2.m_real && c1.m_imag == c2.m_imag ){
        return true;
    }else{
        return false;
    }
}

//重载!=运算符
bool operator!=(const Complex &c1, const Complex &c2){
    if( c1.m_real != c2.m_real || c1.m_imag != c2.m_imag ){
        return true;
    }else{
        return false;
    }
}

// +=、-=、*=、/=可用成员函数重载
//重载+=运算符
Complex & Complex::operator+=(const Complex &c){
    this->m_real += c.m_real;
    this->m_imag += c.m_imag;
    return *this;
}
```



#### 4.5.6 ()和[ ]的重载

（）和 [ ] 重载不能用友元函数，只能用成员函数

重载后的使用方式类似函数的调用 因此成为仿函数

仿函数没有固定写法 非常灵活

```c++
class Restart{
public:
    void operator() (string text){
        cout << text << endl;
    }
    
    int operator() (int a, int b){
        return a + b;
    }
};

void test(){
    Restart r;
    r("hello");//这里的()就是重载后的函数调用
    int res = r(10, 30);
    //res=40
    
    //匿名对象调用
    cout << Restart()(100,19);
    //看到 类名加一个空括号 就意识到是一个匿名对象 执行完这一行就被销毁
}
```

```C++
返回值类型 & operator[ ] (参数); // 该方式，[ ]不仅可以访问元素，还可以修改元素
const 返回值类型 & operator[ ] (参数) const; // [ ]只能访问而不能修改元素
// 实际开发中，我们应该同时提供以上两种形式，这样做是为了适应 const 对象，因为通过 const 对象只能调用 const 成员函数，如果不提供第二种形式，那么将无法访问 const 对象的任何元素

class Array{
public:
    Array(int length = 0);
    ~Array();
public:
    int & operator[](int i);
    const int & operator[](int i) const;
public:
    int length() const { return m_length; }
    void display() const;
private:
    int m_length;  //数组长度
    int *m_p;  //指向数组内存的指针
};
Array::Array(int length): m_length(length){
    if(length == 0){
        m_p = NULL;
    }else{
        m_p = new int[length];
    }
}
Array::~Array(){
    delete[] m_p;
}
int& Array::operator[](int i){
    return m_p[i];
}
const int & Array::operator[](int i) const{
    return m_p[i];
}
void Array::display() const{
    for(int i = 0; i < m_length; i++){
        if(i == m_length - 1){
            cout<<m_p[i]<<endl;
        }else{
            cout<<m_p[i]<<", ";
        }
    }
}
int main(){
    int n;
    cin>>n;
    Array A(n);// 构造对象
    for(int i = 0, len = A.length(); i < len; i++){
        A[i] = i * 5;
    }
    A.display();
   
    const Array B(n);
    cout<<B[n-1]<<endl;  //访问最后一个元素
   
    return 0;
}
```



#### 4.5.7 new和delete的重载

以成员函数的形式重载 new 运算符：

`void * className::operator new( size_t size ){ }`

以全局函数的形式重载 new 运算符：

`void * operator new( size_t size ){ }`

两种重载形式的返回值**都是`void *`类型**，并且**都有一个参数，为`size_t`类型**。在重载 new 或 new[] 时，无论是作为成员函数还是作为全局函数，它的**第一个参数必须是 size_t 类型**。

size_t 表示的是要分配空间的大小，对于 new[] 的重载函数而言，size_t 则表示所需要分配的所有空间的总和。

> size_t 在头文件 <cstdio> 中被定义为`typedef unsigned int size_t;`，也就是无符号整型。

重载函数可以有其他参数，但**都必须有默认值**，并且第一个参数的类型必须是 size_t。

delete 运算符也有两种重载形式。以类的成员函数的形式进行重载：

`void className::operator delete( void *ptr){ }`

以全局函数的形式进行重载：

`void operator delete( void *ptr){ }`

两种重载形式的**返回值都是 void 类型**，并且**都必须有一个 void 类型的指针作为参数**，该指针指向需要释放的内存空间。

当我们以类的成员函数的形式重载了new 和 delete 操作符，其使用方法如下：

```
C * c = new C;  //分配内存空间
delete c;  //释放内存空间
```

如果类中没有定义 new 和 delete 的重载函数，那么会自动调用内建的 new 和 delete 运算符。

1. 局部重载new和delete（可以使用成员函数和友元函数两种方式重载）
      使用new分配某个重载了new的累的对象空间时，先调用new的重载函数，再调用该类的构造函数，如果该类的构造函数有参数要求，则必须给出对应的实参。
      使用了delete释放某个重载了delete的累的对象空间时，先调用类的析构函数，然后再调用重载的delete函数

   ```C++
   class Sample
   {
   private:
       int x,y,z;
   public:
       Sample(int a,int b,int c);
       ~Sample()
       {
           cout << "Destructing\n";
       }
       void *operator new(size_t size);
       void operator delete(void *p);
       friend ostream & operator <<(ostream &stream,Sample obj);
   };
   
   Sample::Sample(int a,int b,int c)
   {
       cout << "Constructing\n";
       x = a;
       y = b;
       z = c;
   }
   
   void *Sample::operator new(size_t size)
   {
       cout << "in Sample new\n";
       return malloc(size);// 用malloc重载！
   }
   
   void Sample::operator delete(void *p)
   {
       cout << "in Sample delete\n" ;
       free(p);// free！！
   }
   
   ostream &operator <<(ostream &os,Sample obj)
   {
       os << obj.x << ",";
       os << obj.y << ",";
       os << obj.z << "\n";
       return os;
   }
   
   int main(int argc,char *argv[])
   {
       Sample *p = new Sample(1,2,3);
       Sample *p1 = new Sample(4,5,6);
       if(!p || !p1)
       {
           cout << "Allocation failure" << endl;
           return 1;
       }
       cout << *p << *p1;
       delete p;
       delete p1;
       int *pnum;
       pnum = new int;
       *pnum = 0;
       cout << "num = " << *pnum << endl;
       delete pnum;
       cout << "Application Run Successfully!" << endl;
       return 0;
   }
   ```

   

2. 全局重载new和delete
      可以在任何类说明之外重在new和delete，使它们成为全局的。当new和delete被重载为全局时，C++原来的new与delete被忽略，并且重载的运算符用于所有类型（包括标准型和用户定义类型）的分配要求。

   ```C++
   class Sample
   {
   private:
       int x,y,z;
   public:
       Sample(int a,int b,int c);
       ~Sample()
       {
           cout << "Destructing\n";
       }
       friend ostream & operator <<(ostream &stream,Sample obj);
   };
   
   Sample::Sample(int a,int b,int c)
   {
       cout << "Constructing\n";
       x = a;
       y = b;
       z = c;
   }
   
   void *operator new(size_t size)
   {
       cout << "in Sample new\n";
       return malloc(size);
   }
   
   void operator delete(void *p)
   {
       cout << "in Sample delete\n" ;
       free(p);
   }
   
   ostream &operator <<(ostream &os,Sample obj)
   {
       os << obj.x << ",";
       os << obj.y << ",";
       os << obj.z << "\n";
       return os;
   }
   
   int main(int argc,char *argv[])
   {
       Sample *p = new Sample(1,2,3);
       Sample *p1 = new Sample(4,5,6);
       if(!p || !p1)
       {
           cout << "Allocation failure" << endl;
           return 1;
       }
       cout << *p << *p1;
       delete p;
       delete p1;
       int *pnum;
       pnum = new int;
       *pnum = 0;
       cout << "num = " << *pnum << endl;
       delete pnum;
       cout << "Application Run Successfully!" << endl;
       return 0;
   }
   ```

   

### 4.6 继承

https://www.cnblogs.com/cscshi/p/15350328.html

是面向对象的三大特性之一

有些类与类之间有特殊关系，定义这些类时，下级成员除了拥有上一级的共性 还有自己的特性，此时就可用继承来减少重复代码

子类(派生类)、父类(基类)

```c++
class BasePage{
public:
    void head(){
        cout << "标题页面" << endl;
    }
    void footer(){
        cout << "底部页面" << endl;
    }
    void left(){
        cout << "侧边分类页面" << endl;
    }
}

class Java: public BasePage{//以公共方式父类
public:
    void self(){
        cout << "Java视频页面" << endl;
    }
}

void test(){
    Java j;
    j.self();
    j.head();
    j.left();//调用父类中的成员
}
```

- 继承多个父类：`class Son:public Base1, protected Base2...`

  多继承中若父类中出现同名情况 子类使用时要加作用域(最好少用多继承 避免出现父类重名情况)

#### 4.6.1 继承方式

公共继承、保护继承、私有继承

<img src="C:\Users\NElk\AppData\Roaming\Typora\typora-user-images\image-20220129205625848.png" alt="image-20220129205625848" style="zoom: 67%;" />

规则：

1. 父类中的私有成员：不论以何种方式继承父类 都不可以访问
2. 以 public 继承父类：父类中的 public、protected 属性在子类中不变，子类不可访问父类中的 private
3. 以 protected 继承父类：父类中的 public、protected 属性在子类中全是 protected，子类不可访问父类中的 private
4. 以 private 继承父类：父类中的 public、protected 属性在子类中全是 private，子类不可访问父类中的 private

从父类继承来的都到了子类中，子类sizeof 包括父类成员及自己的成员；父类中不可访问的成员只是不可访问 仍被继承下来了

#### 4.6.2 继承中构造和析构顺序

创建一个子类对象时，由于继承了父类，该子类对象也会调用父类中的构造和析构函数：先调用父类构造-->再是子类构造-->子类析构-->父类析构

#### 4.6.3 父类子类中同名成员

```c++
class Base{
public:
    Base(){
        mA=100;
    }
    void func(){
        mA=0;
    }
    void func(int a){
        mA=a;
    }
    int mA;
    static int mB;
}
Base::mB=100;//静态成员变量：类内定义 类外初始化

class Son: public Base{
public:
    Son(){
        mA=200;
    }
    void func(){
        mA=0;
    }
    int mA;
}

void test(){
	Son s;
    s.mA = 2;//访问子类中的同名成员
    s.Base::mA = 9;//访问父类中的同名成员：加上作用域
    s.func();
    s.Base::func(10);
    //s.func(10);
    //如果子类中没有func函数 则这行代码可以访问到父类中的func(a)函数；子类中有func 则这行会报错
    //因为若父子类有同名函数 子类的同名函数会隐藏掉父类 所有 同名函数，即使是可重载函数，此时访问父类同名函数必须加作用域
    
    //通过类名直接访问子类中的父类成员变量
    cout << Son::Base::mB;//前后两个冒号不一样！第一个：通过类名访问；第二个：指明作用域
}
```

```C++
// 基类
class Base
{
public:
    void func();
    void func(int);
};

// 派生类
class Derived: public Base
{
public:
    void func(string);
    void func(bool);
};

int main()
{
    Derived d;
    d.func("test"); // 派生类 Derived 域中匹配
    d.func(true);   // 派生类 Derived 域中匹配
    d.func();   // 编译错误，在派生类中找到了同名函数，因此不会再去基类匹配，但派生类中无法匹配
    d.func(10); // 编译错误，在派生类中找到了同名函数，因此不会再去基类匹配，但派生类中无法匹配
    d.Base::func();
    d.Base::func(100);
    return 0;
}

```



#### 4.6.4 菱形继承

B C继承了A，D又继承了B C，产生问题：

1. B C同样继承了A的数据 当D使用数据时，会产生二义性：需要声明作用域 用的到底是B中A的数据还是C中A的数据

2. D继承了来自B C的两份A数据，其实只需要一份：利用虚继承解决这个问题，B C继承A之前加关键字 `class B:virtual public A` 此时A称为**虚基类** 

   <img src="C:\Users\NElk\AppData\Roaming\Typora\typora-user-images\image-20220131095325634.png" alt="image-20220131095325634"  />

   图中 vbptr 为**虚基类指针** 指向各自对应的 vbtable，该 table 中记录了一个**偏移量** vbptr所在位置加上该偏移量就能找到本可继承的数据

### 4.7 多态

静态多态：函数重载、运算符重载属于静态多态 复用函数名

动态多态：派生类、虚函数实现运行时多态

静态、动态多态区别：

1. 静态多态的函数地址早绑定 - 编译阶段确定函数地址
2. 动态多态的函数地址晚绑定 - 运行阶段确定函数地址

- 动态多态使用场景：**通过父类的指针或引用 执行子类的对象**
- 使用动态多态需满足的条件：有继承关系；子类重写父类的虚函数

```c++
class Animal{
public:
    virtual void Speak(){//虚函数
//此处若不加virtual 则会打印动物说话 因为地址早就绑定了 但目的是想打印猫猫说话 所以要加virtual使函数地址晚绑定 - 运行阶段确定函数地址
//使用虚函数 则编译器在编译的时候就不能确定调用哪个函数了 需要通过传进来的实参确定
        cout<<"动物会说话";
    }
}
//没加virtual关键字之前：Animal中只有一个非静态成员函数 计算sizeof时相当于一个空类 sizeof==1
//加了virtual之后：有了个vfptr指针 sizeof==4

class Cat: public Animal{
public:
    void Speak(){//子类重写父类的虚函数，此处也可以前加virtual
//子类继承了父类 故子类的虚函数表中本记录的是父类中虚函数的偏移地址 当子类重写父类的虚函数时：子类虚函数表中的父类虚函数偏移地址 会替换成子类中重写的虚函数偏移地址 故后面实参是cat 就在Cat虚函数表中找到了重写虚函数
        cout<<"猫猫会说话";
    }
}

void doSpeak(Animal &ani){//通过父类的指针或引用 执行子类的对象
    animal.speak();
}

void test(){
    Cat cat;
    doSpeak(cat);//虽然类是Cat 但仍可被父类Animal引用接收
}
```

多态优点：代码组织结构清晰；可读性强；利于前期和后期的扩展及维护

```c++
class AbstractCalculator{
public:
    virtual int getRes(){
        return 0;
    }
    int ma;
    int mb;
}

//加法
class AddCalcu: public AbstractCalculator{
public:
    int getRes(){
        return ma + mb;
    }
}

//减法
class SubCalcu: public AbstractCalculator{
public:
    int getRes(){
        return ma - mb;
    }
}

void test(){
    AbstractCalculator *abc = new AddCalcu;//用父类指针指向子类对象
	cin >> abc->ma >> abc->mb;
    cout << abc->getRes();//abc是加法对象 执行Add中的getRes
    delete abc;//在堆区创建的 记得手动释放
}
```

#### 虚函数、虚基类

[二义性问题](https://blog.csdn.net/buknow/article/details/80436010)

- 虚函数必须是**基类的非静态成员函数**
- 类中的virtual函数，要么设为纯虚函数，要么有定义，否则无法生成虚函数表

- 虚函数可以类外定义，类外定义不需要加virtual
- 虚函数一般不声明为内联函数，但是声明为内联函数也不会引起错误
- 声明为纯虚函数，则类为抽象类，无法实例化；想要实例化有虚函数的类，必须对虚函数进行定义
- 基类定义为虚函数，则子类同名函数也为虚函数，无论是否有virtual关键字修饰(一般声明时加virtual，便于阅读)

- 
  凡是**基类定义有虚函数**，则**基类需要定义虚析构函数**(根据上一条法则，虚析构函数要么有定义，要么纯虚，一般不设为纯虚，可以定义空白)
- 虚函数通过虚表实现，虚表是类实例化时生成在对象中的(虚表地址)，所以如果一个类能够实例化，则其虚函数必须有定义，如果不想定义虚函数，只能声明为纯虚函数，留给子类定义

```C++
	#include <iostream>
	using namespace std;

	class Base1 {
		public:
		public:
		virtual void play();
	};
	
	void Base1::play()
	{
		cout << "Base1::play()" << endl;
	}
	
	class Base2:
		public Base1
	{
		virtual void play();
	};
	
	void Base2::play() {
		cout << "Base2::play()" << endl;
	}
	
	class Derived :
		public Base2
	{
		virtual void play();
	};
	
	void Derived::play() {
		cout << "Derived:: play()" << endl;
	}
	
	void fun(Base1* ba) {  //声明一个基类的指针
		ba->play();
	}
	
	int main()
	{
		Base1 ba1;
		Base2 ba2;
		Derived de;
		//分别用不同的对象指针来调用 fun 函数
		fun(&ba1);
		fun(&ba2);
		fun(&de);
		return 0;
	}

```

```C++
// 虚基类
class Base//声明基类base 
{
	public:
		Base(int sa)
		{
			a=sa;
			cout<<"Constructing Base"<<endl;
		}
	private:
		int a;
};
class Base1:virtual public Base//声明类base是base1的虚基类 
{
	public:
		Base1(int sa,int sb):Base(sa)//在此必须缀上对类base的构造函数的调用 
		{
			b=sb;
			cout<<"Constructing Base1"<<endl;
		}
	private:
		int b;
};
class Base2:virtual public Base//声明base是base2的虚基类 
{
	public:
		Base2(int sa,int sc):Base(sa)//在此2必须缀上对类base构造函数的调用 
		{
			c=sc;
			cout<<"Constructing Base2"<<endl;
		}
	private:
		int c;
};
class Derived:public Base1,public Base2//derived是base1和base2的共同派生类是base的间接派生类 
{
	public:
		Derived(int sa,int sb,int sc,int sd):Base(sa),Base1(sa,sb),Base2(sa,sc)//在此必须缀上对类base的构造函数的调用 
		{
			d=sd;
			cout<<"Constructing Derived"<<endl;
		}
	private:
		int d;
};
int main()
{
	Derived obj(2,4,6,8);
	return 0;
}

```



#### 4.7.1 纯虚函数和抽象类

多态中 由上述例子可知 通常父类中的纯虚函数是毫无意义的 主要是调用子类重写的内容，因此可将父类中虚函数改为**纯虚函数**，当类中有了纯虚函数 该类也称**抽象类**

抽象类特点：

1. 无法实例化对象
2. 子类必须重写抽象类中的纯虚函数，否则也属于抽象类

纯虚函数语法：`virtual 返回值类型 函数名 (参数列表) = 0;`

```c++
class Base{//抽象类
public:
    virtual void func()=0;
}

class Son: public Base{
public:
    virtual void func(){
        ;
    }//子类必须重写抽象类中的纯虚函数
}

void test(){
    //Base s;//会报错！抽象类无法实例化对象
    Base * s = new Son;//可以 用父类指针指向子类
}
```

```c++
class AbstractDrink{
public:
    virtual void Boil()=0;
    virtual void Brew()=0;
    virtual void PourInCup()=0;
    virtual void PutSth()=0;
    
    void makeDrink(){
        Boil();
        Brew();
        PurInCup();
        PutSth();
}
    
class Tea: public AbstractDrink{
public:
    virtual void Boil(){
    	...
    }
    virtual void Brew(){
    	...
    }
    virtual void PourInCup(){
    	...
    }
    virtual void PutSth(){
    	...
    }
}
    
void makeTea(AbstractDrink* abs){
    abs->makeDrink();
    delete abs;
}
    
void test(){
    makeTea(new Tea);//注意这里的用法！！！
}
```

#### 4.7.2 虚析构和纯虚析构

多态使用时 若子类中**有属性开辟到堆区** 则**父指针在释放时无法调用到子类的**析构代码（内存泄漏）---解决方式：将**父类中的析构函数改为虚析构或纯虚析**构

若**子类中没有堆区 则可以不写父类中的纯虚析构或虚析构**

虚析构、纯虚析构共性：

1. 可以解决父类指针释放子类对象
2. 都需要有具体函数实现

区别：若是纯虚析构，则该类为抽象类 无法实例化对象

虚析构：`virtual ~类名(){}`

纯虚析构：`virtual ~类名()=0;`

```c++
class Animal{
public:
    virtual void Speak()=0;
    
    Animal(){
        cout<<"Animal构造调用";
    }
    
    //虚析构写法：
    //virtual ~Animal(){
    //    cout<<"Animal析构调用";
    //}
    
    //纯虚析构：
    virtual ~Animal()=0;//类内声明
}
Animal::~Animal(){
    cout<<"Animal析构调用";
}//类外定义 虚析构和纯虚析构必须都有函数体内容

class Cat: public Animal{
public:
    Cat(string name){
        m_name = new sting(name);//在构造函数中开辟堆区数据并赋值
        cout<<"Cat构造";
    }
    
    virtual void Speak(){
        cout<<*m_name<<"喵喵";
    }
    
    ~Cat(){
        if(m_name != NULL){
            delete m_name;
            m_name=NULL;
        }
        cout<<"Cat析构";
    }
    string * m_name;
}

void test(){
    Animal * cat = new Cat("Tom");
    cat->Speak();
    delete cat;//若没有虚或纯虚析构 则不会调用Cat析构 则m_name指向的堆区内存不会被释放--内存泄漏
}
```

#### 4.7.3 多态案例：电脑组装

电脑零件有CPU、显卡、内存条；将每个零件封装成抽象基类，并且有不同厂商提供不同零件；创建电脑类 提供让电脑工作的函数(CPU进行计算 显卡显示 内存条存储) 并调用每个零件的接口；测试时组装三台不同电脑进行工作

```c++
#include <iostream>
using namespace std;

//CPU基类
class CPU {
public:
	virtual void calculate() = 0;
};

//显卡基类
class Card {
public:
	virtual void display() = 0;
};

//内存条基类
class Memo {
public:
	virtual void storage() = 0;
};

//厂家Intel-子类
class Intel :public CPU, public Card, public Memo {
	void calculate() {
		cout << "CPU - Intel --- Working ! " << endl;
	}

	void display() {
		cout << "Card - Intel --- Working ! " << endl;
	}

	void storage() {
		cout << "Memo - Intel --- Working ! " << endl;
	}
};

//厂家Lenovo-子类
class Lenovo :public CPU, public Card, public Memo {
	void calculate() {
		cout << "CPU - Lenovo --- Working ! " << endl;
	}

	void display() {
		cout << "Card - Lenovo --- Working ! " << endl;
	}

	void storage() {
		cout << "Memo - Lenovo --- Working ! " << endl;
	}
};

class Computer {
private:
	CPU* m_cpu;
	Card* m_card;
	Memo* m_memo;

public:
	Computer(CPU* cpu, Card* card, Memo* memo) {
		m_cpu = cpu;
		m_card = card;
		m_memo = memo;
	}

	//电脑工作
	void doWork() {
		m_cpu->calculate();
		m_card->display();
		m_memo->storage();
	}

	~Computer() {
		cout << "destroy the computer" << endl;
		if (m_cpu != NULL) {
			cout << "delete cpu" << endl;
			delete m_cpu;
			m_cpu = NULL;
		}
		if (m_card != NULL) {
			cout << "delete card" << endl;
			//delete m_card;
			m_card = NULL;
		}
		if (m_memo != NULL) {
			cout << "delete memo" << endl;
			//delete m_memo;
			//m_memo = NULL;
             //这里为什么会重复释放啊？！！！ 开辟的内存哪里重合了吗？？？
		}
	}
};

void test() {
	CPU* cpu = new Intel;
	Card* card = new Lenovo;
	Memo* memo = new Intel;

	Computer* compu = new Computer(cpu, card, memo);
	compu->doWork();

	delete compu;

}

int main() {

	test();
	
	system("pause");

	return 0;
}
```

