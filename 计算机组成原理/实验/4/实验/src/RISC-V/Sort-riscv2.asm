
main:
#十个数13 4 2 1 15 9 7 8 6 11
#内存中的值初始化
ori s0,zero,13
sw s0,0(zero)
ori s0,zero,4
sw s0,4(zero)
ori s0,zero,2
sw s0,8(zero)
ori s0,zero,1
sw s0,12(zero)
ori s0,zero,15
sw s0,16(zero)
ori s0,zero,9
sw s0,20(zero)
ori s0,zero,7
sw s0,24(zero)
ori s0,zero,8
sw s0,28(zero)
ori s0,zero,6
sw s0,32(zero)
ori s0,zero,11
sw s0,36(zero)

ori s0,zero,1		#s0=1，外层循环判断条件变量
ori s1,zero,10		#s1=10，外层循环结束条件	
ori s2,zero,1 		#s2=1,内层循环判断条件变量
ori s3,zero,0		#s3用来表示地址
ori a0,zero,1		#a0=1,常量，用于循环过程中变量的递增
ori t0,zero,4		#t0=4，常量，用于循环过程中地址的递增
loopout:
	beq s0,s1,finish	#外层循环退出条件s0=s1
	ori s2,zero,1		#每次外层循环刚开始，设置s2=1
	ori s3,zero,0		#每次外层循环刚开始，设置s3=0，从一对元素开始判断
	loopin:	
		lw s4,0(s3)
		lw s5,4(s3)
		slt s6,s5,s4	#如果s5<s4,那么s6=1,否则s6=0,为1需要交换值
		bne s6,zero,next	#如果s6=0，不需要交换，直接跳转向next，否则顺序执行
		#交换值的部分
		add a1,s4,zero	#a1=s4
		add s4,s5,zero	#s4=s5
		add s5,a1,zero	#s5=a1
	next:
		#送回内存
		sw s4,0(s3)		
		sw s5,4(s3)
		add s3,s3,t0		#地址加4
		add s2,s2,a0		#变量加1
		add  s7,s0,s2		#s7=s0+s2    s7=j+i
		#j+i>n,退出
		slt t1,s1,s7		#s1<s7,t1=1,否则t1=0，t1=1退出循环
		beq t1,zero,loopin	#等于1，继续内层循环，否则外层变量加1，继续外层循环
		add s0,s0,a0
		jal zero,loopout
finish:
	jal zero,finish
