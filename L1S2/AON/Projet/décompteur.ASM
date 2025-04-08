; ---------------------------------------------------
; Registers meaning:
; AL = Output (Not saved)
; BL = Count/Counter (Only decremented/incremented)
; CL and DL = For calculus
; ---------------------------------------------------
; Data-only memory
org A0

digits:
	db FA; 0
	db 0A; 1
	db B6; 2
	db 9E; 3
	db 4E; 4
	db DC; 5
	db FC; 6
	db 8A; 7
	db FE; 8
	db DE; 9

; Functions-only memory
org 1A

display:
	push AL
	div AL, 10
	add AL, A0
	mov AL, [AL]
	out 02
	
	pop AL
	mod AL, 10
	add AL, A0
	mov AL, [AL]
	add AL, 01
	out 02

	ret

org 38
clear:
	mov AL, 01
	out 02
	mov AL, 00
	out 02

	ret

org 43
blink:
	call 38; [clear]
	
	mov AL, 00
	call 1A; [display]
	
	dec BL
	cmp BL, 00
	jnz blink

	ret

org 52
remOverflow:
	; Calculus: AL -= (AL mod 16)/9*((AL mod 16) mod 9)
	push BL
	pop CL
	mod CL, 10
	push CL
	div CL, 09
	pop DL
	mod DL, 09
	mul DL, CL
	sub BL, DL

	ret

; Program-only memory
org 00

call 38; [clear]

mov BL, 54; IMPLEMENT INPUT FROM KEYBOARD
decounterLoop:
	call 52; [remOverflow]

	push BL
	pop AL
	call 1A; [display]

	dec BL
	cmp BL, 00
	jns decounterLoop

mov BL, 05
call 43; [blink]

end