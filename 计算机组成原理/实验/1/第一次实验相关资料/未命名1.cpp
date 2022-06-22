#include "stdio.h"

void char_hex_out(char a){
	const char HEX[] = "0123456789ABCDEF";
	int index = a&0x0f;
	printf("%c%c",HEX[(a&0xF0)>>4],HEX[a&0x0F]);
}

void four_byte_out(char *addr){
	char_hex_out(*(addr+3));
	char_hex_out(*(addr+2));
	char_hex_out(*(addr+1));
	char_hex_out(*(addr+0));
	printf("\n");
}

main(){
	int a = -1;
	int b = 2147483648;
	int c = -b;
	unsigned int d1 = -13648;
	unsigned int d = -2147483648;
	printf("a=%u=%d=0x%x   \n",a,a,a) ;
	printf("b=%u=%d=0x%x   \n",b,b,b) ;
	printf("c=%u=%d=0x%x   \n",c,c,c) ;
	printf("d=%u=%d=0x%x   \n",d,d,d) ;
	printf("\nd's memory addr=0x%x",&d);	//这里是d不是ui，教材印错了
	printf("\nd's machine code=0x");
	four_byte_out((char *)&d);  //这里是d
	return 0;
}

