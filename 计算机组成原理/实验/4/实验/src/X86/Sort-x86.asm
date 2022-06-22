
	;设置堆栈
	mov eax , cs
	mov ss  , eax 
	mov esp , 0x7c00

	;设置下es为0x7c00
	mov eax , 0x7c00
	mov es  , eax  

	;设置ds为段描述符表起始地址
	mov eax , [es:program_gdt+0x2]
	xor edx , edx 
	mov ebx , 16
	div ebx 
	mov ds  , eax 
	mov ebx , edx 

	;设置全局描述符表
	;0号插槽
	mov dword[ebx+0x00],0x00
	mov dword[ebx+0x04],0x00
	;1号描述符：代码段
	mov dword[ebx+0x08],0x7c0001ff  ;主引导扇区起始地址：0x7c00；段界限长度为：512字节(段界限=长度-1)
	mov dword[ebx+0x0c],0x00409800  ;G=0 D=1:该段内指令偏移地址和操作数为32位(用eip)；p=1：一般虚拟内存中使用；
							   ;DPL=00 最高特权：表示该代码段可以访问任何权限段内的数据；
							   ;s=1：表示代码段/数据段（堆栈段也是数据段）(若为0，则表示系统段)
							   ;x=1：表示该段只执行，不可读和写(代码段要能执行的,但默认是一定不能写入的)
                                               ;【x：执行c特权代码间调用r读a已访问位】
	;2号描述符；代码段的别名
	mov dword[ebx+0x10],0x7c0001ff
	mov dword[ebx+0x14],0x00409a00  ;因为代码段默认是要能执行的，上面的代码段是不能读取的，这里设置个可以读取的代码段别名
							   
	;3号描述符：数据段；因为代码段不能写入，所以用数据段来写入数据
	mov dword[ebx+0x18],0x0000ffff  ;数据段起始地址：0x0000；段界限长度为4G:fffff+1 = 100000 = 2^20=1MB，
					                ;因为G=0,则(2^20)*(4kb)=4G
	mov dword[ebx+0x1c],0x00cf9200  ;G=0,4kb为偏移量单位；w=1：表示该数据段能写
					                ;（数据段不能执行，默认是可以读的）【xewa】
  
	;4号描述符：堆栈段
	mov dword[ebx+0x20],0x00007a00  ;ss=0x00000 esp=0x7a00
	mov dword[ebx+0x24],0x00409600  ;ew=11:表示向下扩展，可写

	;5号描述符：显存段
	mov dword[ebx+0x28],0x8000ffff  ;显存起始地址：0xb8000；0xb8000~0xbffff
	mov dword[ebx+0x2c],0x0040920b

	mov eax , (6*8-1)
	mov [es:program_gdt],eax
	lgdt [es:program_gdt]

	;开启快速A20
	mov dx , 0x92
	in  al , dx 
	or  al , 0x2
	out dx , al 

	;设置进入保护模式
	cli
	mov eax , cr0
	or  eax , 0x1
	mov cr0 , eax 

	;清除流水线，进入保护模式
	jmp dword 0x0008:flush
		[bits 32]
flush:
	
	;设置堆栈
	mov eax , 0x0020
	mov ss  , eax 
	mov esp , 0x7c00
	
	;段寄存器检查规则：ss：可读可写；cs：可执行；其他的段寄存器一定要可读（否则检查不通过）；
	;代码段不能读取，所以用代码段的别名段读取要排序对的数据
	mov eax , 0x0010 ;可读的代码段（即：可以用作代码段也可以用作其他段寄存器）
	mov fs  , eax 
	
	;用显存段来显示数据
	mov eax , 0x0028
	mov es  , eax 

	;开始显示未排序的array
	mov esi , array
	mov edi , 0x00
	mov ecx , (separate - array)
	call print_string
	
	;打印下分割线
	mov esi , separate
	mov ecx , (program_gdt - separate)
	call print_string
	
	;开始冒泡排序
	mov eax , 0x0018
	mov ds  , eax 
	mov ebx  , array
	mov ecx , (separate - array -1)
	call Bubble_sort
	
	;打印排序号的array
	mov esi , array
	mov ecx , (separate - array)
	call print_string

halt:
	hlt		
	
;=============Bubble sort====================
;输入参数：ds:ebx  ecx
Bubble_sort:	
	push esi
@1:
	xor esi , esi
	push ecx 
@2:
	mov ax , [0x7c00 + ebx+esi] 
	cmp al , ah 
	jg @3
	xchg al , ah 
	mov [0x7c00 + ebx+esi],ax
@3:
	inc esi
	loop @2

	pop ecx 
	loop @1
	
	pop esi
	ret
;Bubble_sort end 
	
;============print_string=====================
;输入参数 fs:esi  es:edi ecx
;输出 esi指向字符末尾，edi 指向下个要显示的地址 
print_string: 
	push ecx 
	push fs
	push es
	
show_array:
	mov al , [fs:esi]
	mov [es:edi] , al
	add edi , 2
	inc esi
	loop show_array

	pop es 
	pop fs 
	pop ecx 
	ret
;print_string	

;=============data=====================
	array db '9102837564pzlaoskdmrnebq'
	separate db '   ====   '
	
	program_gdt dw 0x00
		    dd 0x00007e00

	times 510-($-$$) db 0x00
					 dw 0xaa55
