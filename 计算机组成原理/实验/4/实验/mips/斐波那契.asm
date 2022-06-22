.data
	prompt:.asciiz "please input a value:"
	result:.asciiz "result:"
.text
main:
	li $v0,4
	la $a0,prompt
	syscall
	li $v0,5
	syscall
	move $t0,$v0
	beq $t0,1,input_1
	beq $t0,2,input_2
	
	li $t1,0
	li $t2,1
	li $t4,1
	li $t3,4 #2+2
loop:
	bgt $t3,$t0,out
	move $t1,$t2
	move $t2,$t4
	add $t4,$t1,$t2
	
	addi $t3,$t3,1
	b loop
input_1:
	li $t4,0
	b out
input_2:
	li $t4,1
	b out
out:
	li $v0,4
	la $a0,result
	syscall
	li $v0,1
	move $a0,$t4
	syscall
	
	li $v0,10
	syscall

	
