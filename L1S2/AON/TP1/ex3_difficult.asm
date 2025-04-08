mov CL, BF ; Reversed SP/file pointer; SP register at start
mov DL, C0 ; VRAM pointer

read:
	in 00 ; Stored in AL

	cmp AL, 0D ; Carriage Return
	jz write_cond

	push AL
	jmp read

write:	
	mov BL, [CL]
	mov [DL], BL

	sub CL, 01 ; Back because is resversed
	add DL, 01 ; Advance in VRAM
	
	write_cond:
		mov BL, [CL]
		cmp BL, 00
		jnz write

end