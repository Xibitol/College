ORG A0
DB FB; 0
DB 0B; 1
DB B7; 2
DB 9F; 3
DB 4F; 4
DB DD; 5
DB FD; 6
DB 8B; 7
DB FF; 8
DB DF; 9

DB FA; 0
DB 0A; 1
DB B6; 2
DB 9E; 3
DB 4E; 4
DB DC; 5

ORG 00
CLO

INST2: MOV DL,FF
INST3: INC DL
MOV AL,01
OUT 02
JMP AfficheDizaine
SuiteDizaine: CMP DL,06
JZ INST2

INST4: MOV CL,00
INST5: JMP AfficheUnite
SuiteUnite: INC CL
CMP CL,0A
JZ INST3
JMP INST5
halt

AfficheUnite:
	MOV BL,A0
	ADD BL,CL
	MOV AL,[BL]
	OUT 02
	JMP SuiteUnite

AfficheDizaine:
	MOV BL,AA
	ADD BL,DL
	MOV AL,[BL]
	OUT 02
	JMP SuiteDizaine

end