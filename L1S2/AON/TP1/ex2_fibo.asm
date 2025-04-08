; Data
org A0 ; Where data-only mem is

term:
	db 0F ; Min is 1

resultp:
	db 90
	
; Program
org 00 ; Where program-only mem is

mov AL, 01 ; F(n-1)
mov BL, 00 ; F(n-2)

mov DL, [A1] ; [resultp]
mov [DL], BL ; Shows first term (n=0).

add DL, 1
mov [DL], AL ; Shows second term (n=1).

mov CL, 02 ; n
display:
	; Calculates next term
	push AL
	add AL, BL
	pop BL

	; Shows the calculated term
	push CL
	mov DL, [A1] ; [resultp]
	add CL, DL
	mov [CL], AL
	pop CL

	; Continues or stops
	add CL, 01
	cmp CL, [A0] ; [term]
	jz display
	js display

end