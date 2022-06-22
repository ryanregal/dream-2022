#RISC-V冒泡方法排序程序                将n个数据进行排序（从大到小，降序排列）        
#                                                       n存放在地址为0的数据存储器中            
#                                                       n个数据存放在地址为4、8、12、4*n的数据存储器中             
#                                                       排列好的数据仍然放在地址为4、8、12、4*n的数据存储器中     

main:
	lw	a1,0(zero)  		#待排序的数字个数n存在0号存储单元处
	add	a2,a1,zero  		#i=n
	ori  	a4,zero,1  		#x4=1
	ori  	a5,zero,4  		#x5=4
	ori  	a6,zero,-1  		#x6=-1
loop1:
	beq  	a2,a4,finish  		#if i=1 则结束
	ori  	a3,zero,1  		#j=1
	ori  	a7,zero,4                                 
	ori  	s8,zero,8
loop2:
	sltu  	s11,a3,a2 		# if j<i then 读取两个元素比较
	beq  	s11,zero,loop3
	lw 	s9,0(a7)     		#读取第j个元素
	lw 	s10,0(s8)    		#读取第j+1个元素
	sltu 	s11,s10,s9 		#降序排列，从大到小
	beq 	s11,a4,loop4                            
	sw 	s10,0(a7)  		#交换存储
	sw 	s9,0(s8)   			#交换存储
	jal 	zero,loop4
loop3:
   	add 	a2,a2,a6
   	jal 	zero,loop1
loop4:
   	add 	a3,a3,a4 			# j=j+1
   	add 	a7,a7,a5
   	add 	s8,s8,a5
   	jal 	zero,loop2
finish:
	jal 	zero,finish

