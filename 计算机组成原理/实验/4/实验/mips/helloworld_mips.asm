#MIPS程序      helloworld.asm

.data                           				#数据段开始标�?
	out_string: .asciiz "\nHello, World!\n"

.text                           				#代码段开始标�?
main:
	li $v0,4                      			#4号系统调用，显示字符�?
	la $a0,out_string				#获取字符串的地址
	syscall                      			 #系统调用

	li $v0,10                   			#10号系统调用，程序�?�?
	syscall
