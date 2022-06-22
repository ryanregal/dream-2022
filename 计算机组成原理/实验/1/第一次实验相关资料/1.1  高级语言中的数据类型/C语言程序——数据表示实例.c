#include "stdio.h"
union
{
	int i;  unsigned int ui; float f;
	short s; unsigned short us;
	char c; unsigned char uc;
}t;

//输出字符的十六进制编码 
void hex_out(char a){
	const char HEX[]="0123456789ABCDEF";//HEX数组 
	//0f代表11110000，f代表1111
	printf("%c%c",HEX[(a&0xF0)>>4],HEX[a&0xF]);
}

void out_1byte(char *addr){
	hex_out (*(addr +0));
}

void out_2byte(char *addr){
	hex_out (*(addr +1));
	hex_out (*(addr +0));
}

//可用于输出4字节变量的机器码 
void out_4byte(char *addr){
	hex_out (*(addr +3));//输出指针变量的值，指针本质是内存地址，无符号数 
	hex_out (*(addr +2));//假设采用小端方式储存 
	hex_out (*(addr +1));
	hex_out (*(addr +0));
}

main(){
	t.i=0xC77FFFFF;

	out_4byte(&t.i);//i是int类型的 
	printf(" = %d \n",t.i);

	out_4byte(&t.ui);//ui是unsigned int 
	printf(" = %u \n",t.ui);

	out_4byte(&t.f);//f是float 
	printf(" = %f \n",t.f);

	out_2byte(&t.s);//s是short 
	printf(" = %d \n",t.s);

	out_2byte(&t.us);//us是unsigned short 
	printf(" = %u \n",t.us);

	out_1byte(&t.c);//c是char 
	printf(" = %d \n",t.c);

	out_1byte(&t.uc);//uc是unsigned char 
	printf(" = %d \n",t.uc);
}

