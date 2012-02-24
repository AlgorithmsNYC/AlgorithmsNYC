__author__ = 'eb'

# Development Cases
#colors = [['green', 'green', 'green'],['green', 'red', 'red'],['green', 'green', 'green']]
#measurements = ['red', 'red']
#motions = [[0,0], [0, 1]]
#sensor_right = 0.8
#p_move = 0.5

# Final Test Case
colors = [['red', 'green', 'green', 'red' , 'red'],
    ['red', 'red', 'green', 'red', 'red'],
    ['red', 'red', 'green', 'green', 'red'],
    ['red', 'red', 'red', 'red', 'red']]
measurements = ['green', 'green', 'green' ,'green', 'green']
motions = [[0,0],[0,1],[1,0],[1,0],[0,1]]
sensor_right = 0.7
p_move = 0.8

def show(p):
    for i in range(len(p)):
        print p[i]

#DO NOT USE IMPORT
#ENTER CODE BELOW HERE
#ANY CODE ABOVE WILL CAUSE
#HOMEWORK TO BE GRADED
#INCORRECT

def nextSenseNonNormalizedProb(prob, Z, row, col):
    hit = (Z == colors[row][col])
    nextProb = prob[row][col] * (hit * sensor_right + (1-hit) * (1.0-sensor_right))
    return nextProb

def sense(prob, Z):
    nonNormalQ = [[nextSenseNonNormalizedProb(prob, Z, row, col) for col in range(len(prob[row]))] for row in range(len(prob))]
    s = sum([sum(v) for v in nonNormalQ])
    q = [[nonNormalQ[row][col]/s for col in range(len(prob[row]))] for row in range(len(prob))]
    return q

def nextMoveProb(prob, U, row, col):
    fromRow = ((row-U[0]) % len(prob))
    fromCol = ((col-U[1]) % len(prob[row]))
    pMoveSuccess = (p_move, 1.0)[(sum(U) == 0)] #probability of move success is always 1.0 when no move is attempted
    nextProb = pMoveSuccess * prob[fromRow][fromCol] # probability coming from a successful move
    nextProb += (1.0 - pMoveSuccess) * prob[row][col] # probability coming from a non-successful move
    return nextProb

def move(prob, U):
    q = [[nextMoveProb(prob, U, row, col) for col in range(len(prob[row]))] for row in range(len(prob))]
    return q

rows = len(colors)
cols = len(colors[0])
uniformProb = 1.0 / (rows * cols)

p = [[uniformProb for j in range(cols)] for i in range(rows)]

for i in range(len(measurements)):
    print "\nMove: ",  motions[i]
    p = move(p, motions[i])
    show(p)
    print "\nSense: ",  motions[i]
    p = sense(p, measurements[i])
    show(p)

print "\nFinal Answer"
#Your probability array must be printed
#with the following code.
show(p)


