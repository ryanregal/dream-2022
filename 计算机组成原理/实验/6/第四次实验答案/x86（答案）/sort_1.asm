;x86（32位）汇编语言程序                   排序（升序排列，从小到大）               

    .486                                    			; create 32 bit code
    .model flat, stdcall                    		; 32 bit memory model
    option casemap :none                    		; case sensitive
 
    include \masm32\macros\macros.asm       	

    includelib \masm32\lib\masm32.lib
    includelib \masm32\lib\gdi32.lib
    includelib \masm32\lib\user32.lib
    includelib \masm32\lib\kernel32.lib
    includelib \masm32\lib\wsock32.lib
    includelib \masm32\lib\msvcrt.lib

    include \masm32\include\msvcrt.inc
    include \masm32\include\masm32.inc
    include \masm32\include\gdi32.inc
    include \masm32\include\user32.inc
    include \masm32\include\kernel32.inc

.data								
	arr dd 10,2,3,2,5,21,32,12,43,12,33,43,54,66,87,89,00,4,2,6		; data to be sort      20个原始数据，数据可以改变，排好后，还放在这里    
	len1 byte ? 						
	len2 byte ? 						
	fmt byte '%d ',0

.code

main:        ;-----------------------------------------------显示原始数据-------------------------------------------------
	mov len1,lengthof arr                         ;获取数据长度                                                                
	mov ebx,offset arr                               ;获取数据的起始地址
	xor ecx,ecx
	mov al,len1
prt1:	
	movsx ebx,al
	cmp ecx,ebx
	jnb fina1
	mov edx, arr[(type arr)*ecx]
	pushad
	invoke crt_printf,offset fmt,edx              ;显示一个数据
	popad
	inc ecx
	jmp prt1
fina1:
    	print chr$(" ",13,10)                                 ;显示回车、换行           
	print chr$(" ",13,10)                                 ;显示回车、换行

;-----------------------------------------------------------排序-----------------------------------------------

	mov len1,lengthof arr                           ;数据长度  ->  len1
	mov ebx,offset arr                                 ;数据偏移地址(首地址)  ->   ebx
	mov al,0h                                              ; al=0			 	
lp:	
	cmp al,len1
	jnb done                                               ; 排序结束，转done				

	mov ah, 1h	                             ; ah=1		
inner:				
	mov cl,len1                                          ; cl <- len1=数据长度
	mov len2,cl                                          ; len2 <- cl
	sub len2,al                                           ; len2 <- len2-al
	cmp ah,len2                                         ; 比较ah 和 len2
	jnb last                                                 ; 跳出内循环
	movsx esi,ah 		            ; esi <- ah
	mov bl,ah                                            ; bl = ah
	sub bl,1                                               ; bl <1 bl-1
	movsx edi,bl                                       ; edi <- bl
	mov ecx, arr[(type arr)*esi]                ;ecx <- [esi]
	mov edx, arr[(type arr)*edi]               ;edx <_ [edi]
	cmp ecx,edx                                       ;比较ecx和edx
	jnb follow 		           ;大于等于则转           升序排列，从小到大	
	mov edx,arr[(type arr)*esi]                ;edx <- [esi]
	xchg edx,arr[(type arr)*edi]               ;edx与[edi]交换
	xchg edx,arr[(type arr)*esi]               ;edx与[esi]交换
follow:	
	inc ah 	                                          ; ah +1 -> ah			
	jmp inner                                          ; 转内循环
last:	
	inc al                                                 ; al+1 -> al 				
	jmp lp                                               ;转外循环
done:	
;--------------------------------------显示排序后的数据------------------------------------
	xor ecx,ecx                                     
	mov al,len1
prt:	
	movsx ebx,al
	cmp ecx,ebx
	jnb fina
	mov edx, arr[(type arr)*ecx]
	pushad
	invoke crt_printf,offset fmt,edx
	popad
	inc ecx
	jmp prt
fina:	
	exit

end main 				