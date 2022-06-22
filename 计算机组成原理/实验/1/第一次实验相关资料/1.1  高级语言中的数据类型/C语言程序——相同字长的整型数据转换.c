#include "stdio.h"
//输出字符的十六进制编码 
void hex_out(char a){
	const char HEX[]="0123456789ABCDEF";
	printf("%c%c",HEX[(a&0xF0)>>4],HEX[a&0xF]);
}
//输出1字节变量的机器码 
void out_1byte(char *addr){
	hex_out (*(addr +0));
}

int main(){
    unsigned char uc1=255,uc;
    char c1=-127,c;

    c=(char)uc1;

    out_1byte(&uc1);
    printf(" = uc1 = %u \n",uc1);

    out_1byte(&c);
    printf(" = c = %d \n",c);

    uc=c1;

    out_1byte(&c1);
    printf(" = c1 = %d \n",c1);

    out_1byte(&uc);
    printf(" = uc = %u \n",uc);
}

