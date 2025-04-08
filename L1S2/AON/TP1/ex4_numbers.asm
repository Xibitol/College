; ---- Data ----
org A0 ; Where data-only mem is

numbers:
	db FA ; 0
	db 0A ; 1
	db B6 ; 2
	db 9E ; 3
	db 4E ; 4
	db DC ; 5
	db FC ; 6
	db 8A ; 7
	db FE ; 8
	db DE ; 9
	
; ---- Program ----
org 00 ; Where program-only mem is
 
; Reset displays
mov AL, 00
out 02
mov AL, 01
out 02

mov BL, A0 ; [numbers]
jmp display_repeat

display:
	out 02
	add BL, 01
	jmp display_repeat
	
display_repeat:
	mov AL, [BL]
	cmp AL, 00
	jnz display

end