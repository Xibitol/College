; Data
org A0 ; Where data-only mem is

hello:
	db "Hello!"
	db 00
	
; Program
org 00 ; Where program-only mem is

mov AL, A0 ; [hello]; Manipulated pointer
mov BL, C0 ; Video ram poiter

display:
	mov DL, [AL]
	mov [BL], DL
	add AL, 01
	add BL, 01

	mov DL, [AL]
	cmp DL, 00
	jnz display

end