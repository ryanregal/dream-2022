#RISC-V计算累加和程序                      result=1+2+...+n               n存放在主存地址为0的数据存储器中，结果result存放在主存地址为4的数据存储器中单

main:
	lw a3, 0(zero)     		# 读取主存0号单元（地址为0）的值（n）到a3
	ori a5, zero, 1     		# a5内容（循环变量i）为1 
	ori a2, zero, 1     		# a2内容（循环增量）为1
                ori a4, zero, 0                          # a4内容为0
loop:
	add a4, a4, a5     		# 将i加到a4（累加和）
	beq a5, a3, finish  		# 若a5=n，则跳出循环
    	add a5, a5, a2     		# a5=a5+1
    	jal zero, loop		# 无条件跳转到loop执行
finish:
	sw a4, 4(zero)    		# 将累加结果result保存到主存1号单元（地址为4）
end:
	jal zero, end		# 无条件跳转到end执行