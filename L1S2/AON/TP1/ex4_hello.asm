; ---- Data ----
org A0 ; Where data-only mem is

letters:
	db 6E ; H
	db F4 ; E
	db 70 ; L
	db 70 ; L
	db FA ; 0
	db 00
	
; ---- Program ----
org 00 ; Where program-only mem is
 
; Reset displays
mov AL, 00
out 02
mov AL, 01
out 02
mov AL, 00

mov CL, A0 ; [letters]
jmp display_repeat

display:
	add AL, 01
	out 02
	sub AL, 01
	
	pop BL
	push AL ; Saves for next interation.
	push BL
	pop AL
	out 02
	pop AL

	add CL, 01
	jmp display_repeat
	
display_repeat:
	push AL
	mov AL, [CL]
	cmp AL, 00
	jnz display

end