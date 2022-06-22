
	ori a0,zero,10
	ori a1,zero,0 
	ori a2,zero,1
	ori a3,zero,0
	ori a4,zero,0 
	sw a1,0(zero)
	sw a2,4(zero)
	ori a5,zero,8
	ori a6,zero,4	
	ori s2,zero,1	
loop:
	
	add a3,a1,a2	
	sw a3 0(a5)
	add a5,a5,a6		
	add a1,a2,zero	
	add a2,a3,zero	
	add a4,a4,s2	
	beq a4,a0,finish	
	jal zero,loop	
finish:
	jal zero,finish
