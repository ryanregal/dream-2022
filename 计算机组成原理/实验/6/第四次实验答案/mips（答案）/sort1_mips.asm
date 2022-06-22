#MIPS程序    冒泡法排序（降序排列，从大到小）                       #先将10个数（8、1、5、2、7、9、6、4、3、10）存放到地址为0开始的数据存储器中，然后对这10个数进行排序。

 .text
main:
 	addi $s0,$zero,8                        #第1个数=8（可以修改）保存到(0)
 	sw $s0,0($zero)
 	addi $s0,$zero,1                        #第2个数=1（可以修改）保存到(4)
 	sw $s0,4($zero)
 	addi $s0,$zero,5                        #第3个数=5（可以修改）保存到(8)
 	sw $s0,8($zero)
 	addi $s0,$zero,2                        #第4个数=2（可以修改）保存到(12)
	sw $s0,12($zero)
 	addi $s0,$zero,7                        #第5个数=7（可以修改）保存到(16)
	sw $s0,16($zero)
 	addi $s0,$zero,9                        #第6个数=9（可以修改）保存到(20)
 	sw $s0,20($zero)
 	addi $s0,$zero,6                        #第7个数=6（可以修改）保存到(24)
 	sw $s0,24($zero)
 	addi $s0,$zero,4                        #第8个数=4（可以修改）保存到(28)
 	sw $s0,28($zero)
 	addi $s0,$zero,3                        #第9个数=3（可以修改）保存到(32)
 	sw $s0,32($zero)
 	addi $s0,$zero,10                      #第10个数=10（可以修改）保存到(36)
 	sw $s0,36($zero)
 	addi $s0,$zero,0          	 #$s0=0                      排序区间开始地址
 	addi $s1,$zero,36  		 #$s1=36=10*4-4      排序区间结束地址         10个数        如果不是20个数，这里要修改，例如20个数，这里修改为76
sort_loop:
 	lw $s3,0($s0)                     	#$s3=($s0)
	lw $s4,0($s1)                	#$s4=($s1)
 	slt $t0,$s3,$s4                	#如果$s3<$s4，则置$t0=1；否则，置$t0=0
 	beq $t0,$zero,sort_next   	#如果$t0=0，则转sort_nent       降序排序         从大到小
 	sw $s3,0($s1)                	#交换($s0)和($s1)
	sw $s4,0($s0)                   	#交换($s0)和($s1)
sort_next:
	addi $s1,$s1,-4                        # $s1-4 -> $s1
 	bne $s0,$s1,sort_loop             #如果$s0不等于$s1，则转sort_loop
  	addi $s0,$s0,4                         #$s0+4 -> $s0
	addi $s1,$zero,36                   	#$s1=36=10*4-4   排序区间结束地址                10个数             如果不是10个数，这里要修改，例如20个数，这里修改为76
	bne $s0,$s1,sort_loop 	#如果$s0不等于$s1，则转sort_loop
	addi   $v0,$zero,10         	# 10号系统调用
	syscall                  		# 程序退出
