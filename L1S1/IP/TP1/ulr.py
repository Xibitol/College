import turtle
import math as m

unit = 20
w, h, spacing = 3, 5, 1

def letterSpace():
    turtle.penup()
    turtle.forward(unit*spacing)

def u(firstLetter: bool = False):
    if not firstLetter: letterSpace()
    turtle.pendown()
    
    turtle.forward(unit*w/3)
    turtle.right(90)
    turtle.forward(unit*h/5*4)
    turtle.left(90)
    turtle.forward(unit*w/3)
    turtle.left(90)
    turtle.forward(unit*h/5*4)
    turtle.right(90)
    turtle.forward(unit*w/3)
    
    turtle.right(90)
    turtle.forward(unit*h)
    turtle.right(90)
    turtle.forward(unit*w)
    turtle.right(90)
    turtle.forward(unit*h)
    turtle.right(90)
    
    turtle.penup()
    turtle.forward(unit*w)

def l(firstLetter: bool = False):
    if not firstLetter: letterSpace()
    turtle.pendown()
    
    turtle.forward(unit*w/3)
    turtle.right(90)
    turtle.forward(unit*h/5*4)
    turtle.left(90)
    turtle.forward(unit*w/3*2)
    turtle.right(90)
    turtle.forward(unit*h/5)
    turtle.right(90)
    turtle.forward(unit*w)
    turtle.right(90)
    turtle.forward(unit*h)
    turtle.right(90)
    
    turtle.penup()
    turtle.forward(unit*w)

def r(firstLetter: bool = False):
    if not firstLetter: letterSpace()
    turtle.pendown()
    
    turtle.forward(unit*w)
    turtle.right(90)
    turtle.forward(unit*h/5*3)
    turtle.right(90)
    turtle.forward(unit*w/6)
    
    d = m.sqrt((w/6)**2 + (h/5*2)**2)
    a = (m.acos(w/6/d))*180/m.pi
    turtle.left(180 - a)
    turtle.forward(unit*d)
    turtle.right(180 - a)
    turtle.forward(unit*w/3)
    turtle.right(a)
    turtle.forward(unit*d)
    turtle.left(a)
    
    turtle.forward(unit*w/6)
    turtle.left(90)
    turtle.forward(unit*h/5*2)
    turtle.right(90)
    turtle.forward(unit*w/3)
    turtle.right(90)
    turtle.forward(unit*h)
    turtle.right(90)
    
    turtle.penup()
    turtle.forward(unit*w/3)
    turtle.right(90)
    turtle.forward(unit*h/5)
    turtle.left(90)
    turtle.pendown()
    for k in range(2):
        turtle.forward(unit*w/3)
        turtle.right(90)
        turtle.forward(unit*h/5)
        turtle.right(90)
    turtle.penup()
    turtle.left(180)
    turtle.forward(unit*w/3)
    turtle.right(90)
    turtle.forward(unit*h/5)
    turtle.right(90)
    
    turtle.penup()
    turtle.forward(unit*w)

if __name__ == "__main__":
    turtle.penup()
    turtle.left(180)
    turtle.forward(unit*(w*3 + spacing*2)/2)
    turtle.right(90)
    turtle.forward(unit*h/2)
    turtle.right(90)
    
    u(True)
    l()
    r()