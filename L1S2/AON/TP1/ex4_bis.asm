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

; ---- FUNCTIONS ----
org 40
display:
	push CL
	pop AL

	; First digit
	div AL, 0A
	push AL ; Save for after

	add AL, A0 ; numbers
	mov AL, [AL]
	out 02
	
	; Second digit
	push CL
	pop AL
	pop DL
	mul DL, 0A
	sub AL, DL

	add AL, A0 ; numbers
	mov AL, [AL]
	add AL, 01
	out 02

	ret	
	
; ---- Program ----
org 00 ; Where program-only mem is

; Reset displays
mov AL, 00
out 02
mov AL, 01
out 02

mov BL, 63 ; MAX : 09(10)
mov CL, 00 ; Counter init

call 40

jmp count_cond
count:
	inc CL
	call 40
	count_cond:
		cmp BL, CL
		jnz count

end