.data
A1:
.word 12, 34, 17, 5, 89, 15, 9, 22, 16, 19, 21
.text
main:
ADDIU $r2, $r0, 11 # 外层循环计数 i
LOOP1:
ADDIU $r3, $r0, A1 # 数组基址
ADDIU $r4, $r0, 0 # 内层循环计数 j
LOOP2:
LW $r5, 0($r3) # 载入 A1[j]
LW $r6, 4($r3) # 载入 A1[j+1]
# 前瞻执行
ADDIU $r4, $r4, 1 # j += 1
DSUB $r7, $r5, $r6 # A1[j] - A1[j+1]
DSUB $r8, $r2, $r4 # i - j
BLTZ $r7, NOSWAP #A1[j] < A1[j+1]，跳转到 NOSWAP
SW $r5, 4($r3) #A1[j] > A1[j+1], 交换
SW $r6, 0($r3)
NOSWAP:
ADDIU $r3, $r3, 4
BGTZ $r8, LOOP2 #i - j > 0, 继续内层循环
ADDIU $r2, $r2, -1 # i -= 1
BNE $r2, $r0, LOOP1 # 继续外层循环
TEQ $r0, $r0