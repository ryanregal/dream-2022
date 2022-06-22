.text                           #代码段
.globl main                     #程序从此开始

main:                           #主程序
      add  $s1,  $zero ,0     #加载基准内存地址
      add  $s0, $zero, 1     # $s0 就是int i = 1
read: 
      li $t0,10  #n=10
      sw $t0, 0($zero)# 存入数据
      li $t0,9
      sw $t0, 4($zero)
      li $t0,1
      sw $t0, 8($zero)
       li $t0,2
      sw $t0, 12($zero)
       li $t0,5
      sw $t0, 16($zero)
       li $t0,10
      sw $t0, 20($zero)
       li $t0,8
      sw $t0, 24($zero)
       li $t0,4
      sw $t0, 28($zero)     
       li $t0,7
      sw $t0, 32($zero)
       li $t0,6
      sw $t0, 36($zero)
       li $t0,3
      sw $t0, 40($zero)

oLop:
                                #外层循环开始
      addi $s2, $zero, 10        #$s2 就是 int j = 10
iLop:
                                    #内层循环开始 
          sll $t0, $s2, 2           #偏移量j*4，初始是40
          add $t1, $s1, $t0         #A[j]的实际内存地址
          addi $t2, $t1, -4         #A[j-1]的实际内存地址
          lw $t3, 0($t1)            #$t3=A[j]的值
          lw $t4, 0($t2)            #$t4=A[j-1]的值
          slt $t5, $t3, $t4         #若A[j-1] < A[j]
          beq $t5, $zero, afterSwap #为真交换，否则跳过
#swap
          lw $t6, 0($t1)           # tmp=A[j]
          sw $t4, 0($t1)            # A[j] = A[j-1]
          sw $t6, 0($t2)            # A[j-1]=tmp
afterSwap:
          addi $s2, $s2, -1          # j = j - 1
          slt $t0, $s0, $s2         #若i < j
          bne $t0, $zero, iLop      #继续内层循环
          addi $s0, $s0, 1          # i = i + 1
          slti $t0, $s0, 9         #若i<10
          bne  $t0, $zero, oLop     #则继续外层循环 
                                #外层循环结束
          li $v0, 10                #停
          syscall                   #机 
