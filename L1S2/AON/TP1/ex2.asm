	CLO
	MOV AL, 15
	MOV BL, 40
	MOV CL, 50
	MOV DL, 60
Boucle:
	INC AL
	MOV [A0], AL  ; le contenu de AL est recopié dans la mémoire d'adresse A0
	MOV BL, [40]  ; BL <- contenu de la mémoire d'adresse 40
	MOV [CL], AL  ; CL est utilisé ici comme registre d'adresse : son contenu est une adresse
	MOV BL, [CL]  ; BL <- contenu de la mémoire dont l'adresse est le contenu de CL : CL pointe sur la donnée à mettre dans BL
	JMP boucle    ; boucle infinie (utiliser STOP pour mettre fin au programme)
	END