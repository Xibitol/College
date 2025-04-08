org 80

db 01
db 07


org 00
clo

mov AL,[80]
add AL,30
mov [C0],AL

mov AL,[81]
add AL,30
mov [C1],AL

end