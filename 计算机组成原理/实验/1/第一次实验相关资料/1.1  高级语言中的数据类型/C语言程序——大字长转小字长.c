#include "stdio.h"

//输出字符的十六进制编码 
void hex_out(char a){
	const char HEX[]="0123456789ABCDEF";
	printf("%c%c",HEX[(a&0xF0)>>4],HEX[a&0xF]);
}
//输出2字节变量的机器码 
void out_2byte(char *addr){
	hex_out (*(addr +1));
	hex_out (*(addr +0));
}
//输出4字节变量的机器码 
void out_4byte(char *addr){
	hex_out (*(addr +3));
	hex_out (*(addr +2));
	hex_out (*(addr +1));
	hex_out (*(addr +0));
}

int main(){
    int i=0xFFFF1001;  
    short s; unsigned short us;

    s=i;
    us=i;

    out_4byte(&i);
    printf(" = i = %d \n",i);

    out_2byte(&s);
    printf(" = s = %d \n",s);

    out_2byte(&us);
    printf(" = us = %u \n",us);
}



