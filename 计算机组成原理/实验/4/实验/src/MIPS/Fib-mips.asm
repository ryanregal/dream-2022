.text
main:
	li $t0,7 #斐波那契的n=7
	li $t1,0 #初始化f(0)=0
	li $t2,1 #初始化f(1)=1
	li $t4,1 #初始化f(2)=1
	li $t3,2 #int i=2	
        sw $t1,0($zero)  #将f(0)置于缓存中
  	blt $t0,2,out    #如果输入n<2，直接跳转out
  	sw $t2,4($zero)  #将f(1)置于缓存中
 	blt $t0,3,out    #如果输入n<3，直接跳转out
	sw $t2,8($zero)  #将f(2)置于缓存中
  	blt $t0,4,out    #如果输入n<4，直接跳转out
loop:
 	bgt $t3,7,out    #如果i>7，直接跳转out
	move $t1,$t2       #f(n-2)=f(n-1)
	move $t2,$t4       #f(n-1)=f(n)
	add $t4,$t1,$t2    #f(n)=f(n-1)+f(n-2),n从3开始
 	addi $t3,$t3,1     #i++,i第一次变成3
	sll $t0, $t3, 2    #偏移量 $t0 =i*4 sll是左移
	add $t0,$zero,$t0  #实际地址 = 偏移量$t0 + 基准地址
	sw $t4,0($t0)      #将f(n)写入到实际地址
	j loop
out:     #=========结果==========#
	li $v0,10 #结束程序
	syscall  #执行

