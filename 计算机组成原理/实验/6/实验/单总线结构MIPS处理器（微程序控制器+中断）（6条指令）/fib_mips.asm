#MIPS绋嬪簭    璁＄畻璐规尝閭ｅ鏁板垪                           n=10锛堝彲浠ユ敼鍙橈級锛岃绠楀ソ鐨刵+1涓暟锛?0銆?1銆?1銆?2銆?3銆?5銆?8銆?13銆?21銆?34銆佲?︹?︼級淇濆瓨鍦ㄥ湴鍧?涓?0銆?4銆?8銆?12銆?...鐨勬暟鎹瓨鍌ㄥ櫒涓?

main:
                addi $s2,$zero,10               		# $s2=n=10        

	addi $s1,$zero,0			# $s1=0 		
	sw $s1,3072($zero)                                       # 0 -> (0)

                addi $s1,$zero,1			# $s1=1 	
	sw $s1,3076($zero)			# 1 -> (4)

	sw $s1,3080($zero)			# 1 -> (8)

                addi $s3,$zero,2			# $s3 = 2
                addi $s4,$zero,1			# $s4 = 1
                addi $s5,$zero,1			# $s5 = 1
loop:
                add $s6,$s4,$s5			# $s4+$s5 -> $s6	
                addi $s4,$s5,0			# $s5 -> $s4	
                addi $s5,$s6,0			# $s6 -> $s5	
                addi $s3,$s3,1			# $s3+1 -> $s3	
                add $s7,$s3,$s3			# $s3+$s3 -> $s7
                add $s0,$s7,$s7			# $s7+$s7 -> $s0	
                sw $s6,3072($s0)                		# $s6 -> 0($s0)	
	beq $s2,$s3,end			# if $s2 = $s3 goto end	
                beq $zero,$zero,loop				# goto loop
end:
	beq $zero,$zero,end                			# 绋嬪簭閫?鍑?
IRQ0:                                                       #IRQ0中断服务程序的入口地址：1024   =   400H    		RAM对应100            
	addi $sp,$zero,3840    	#push registers  需要保留中断程序用到的寄存器                 $s0    $s1       
  	sw $s0,0($sp)
  	sw $s1,4($sp)

  	addi $s1,$zero,3328              #RAM对应340
  	lw $s0,0($s1)
  	addi $s0,$s0,1
  	sw $s0,0($s1)

  	lw $s1,4($sp)   		#pop registers
  	lw $s0,0($sp)
  	eret
IRQ1: 				#IRQ0中断服务程序的入口地址：1536     =   600H  		RAM对应180            
	addi $sp,$zero,3840    	#push registers  需要保留中断程序用到的寄存器                 $s0    $s1       
  	sw $s0,0($sp)
  	sw $s1,4($sp)

  	addi $s1,$zero,3392              #RAM对应350
  	lw $s0,0($s1)
  	addi $s0,$s0,1
  	sw $s0,0($s1)

  	lw $s1,4($sp)   		#pop registers
  	lw $s0,0($sp)
  	eret
IRQ2: 				#IRQ0中断服务程序的入口地址：2048       =  800H		RAM对应200            
	addi $sp,$zero,3840    	#push registers  需要保留中断程序用到的寄存器      	$s0    $s1        
  	sw $s0,0($sp)
  	sw $s1,4($sp)

  	addi $s1,$zero,3456             #RAM对应360
  	lw $s0,0($s1)
  	addi $s0,$s0,1
  	sw $s0,0($s1)

  	lw $s1,4($sp)   		#pop registers
  	lw $s0,0($sp)
  	eret

IRQ3: 				#IRQ0中断服务程序的入口地址：2560       =  A00H		RAM对应280            
	addi $sp,$zero,3840    	#push registers  需要保留中断程序用到的寄存器              $s0    $s1          
  	sw $s0,0($sp)
  	sw $s1,4($sp)

  	addi $s1,$zero,3520               #RAM对应370
  	lw $s0,0($s1)
  	addi $s0,$s0,1
  	sw $s0,0($s1)

  	lw $s1,4($sp)   		#pop registers
  	lw $s0,0($sp)
  	eret  	