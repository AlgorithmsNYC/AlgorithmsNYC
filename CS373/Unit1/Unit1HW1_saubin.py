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

## global vars
p = []

## functions
def showFormated(p):
    for i in range(len(p)):
        print '|'
        for j in range(len(p[i])):
           print '{0:.3}'.format(p[i][j])

def initUniformP():
    q = []
    nbPos = len(colors)*len(colors[0])
    for i in range(len(colors)):
        line = []
        for j in range(len(colors[0])):
            line.append(1./nbPos)
        q.append(line)
    return q  
    
def initUniformZero():
    q = []
    nbPos = len(colors)*len(colors[0])
    for i in range(len(colors)):
        line = []
        for j in range(len(colors[0])):
            line.append(0.0)
        q.append(line)
    return q        
    
def move(p, movePair):
    pExact = p_move
    pUndershoot = 1 - p_move
    q = []
    for i in range(len(p)):
        qline=[]
        for j in range(len(p[i])):
           s = pExact * p[(i-movePair[0]) % len(p)][(j-movePair[1]) % len(p[i])]
           s += pUndershoot * p[i][j]
           qline.append(s)
        q.append(qline)
    return q
    
def sense(p, col):
    pHit = sensor_right
    pMiss = 1-sensor_right
    q=[]
    s=0.0
    for i in range(len(p)):
        qline=[]
        for j in range(len(p[i])):
            hit = (col == colors[i][j])
            qline.append(p[i][j] * (hit * pHit + (1-hit) * pMiss))
        q.append(qline)
        s+=sum(qline)
        
    #print 'q` before'
    #print s
    #showFormated(q)
    
    for i in range(len(p)):
        for j in range(len(p[i])):
            q[i][j]=q[i][j]/s
    
    return q

### main part 

p = initUniformP()
#p = initUniformZero()
#p[1][2]=1.0
#showFormated(p)

#testmeasurements = ['green', 'red']
#testmotions = [[0,0],[1,0]]
#measurements = testmeasurements
#motions = testmotions

#main iteration over the moves and measurements
for k in range(len(measurements)):
    p = move(p, motions[k])
 #   showFormated(p)
    p = sense(p, measurements[k])
 
#showFormated(p)

#Your probability array must be printed 
#with the following code.
show(p)





