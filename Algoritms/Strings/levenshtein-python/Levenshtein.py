'''
Created on May 24, 2012

@author: dstengle
'''

import string
import sys


def editDistance(xString, yString):
    
    
    # build a pretty matrix with the words laid out
    # the word costs start at 1, not zero, so this must be accounted for
    # when converting from the matrix to a position in the word string
    matrix = []
    # put xString in the first row of the matrix padded with two spaces
    matrix.append([x for x in '  '+xString])
    # put the cost row for xString in padded by one space
    matrix.append([' ']+[x for x in range(0, len(xString)+1)])
    # add all of the y rows of the form ychar ycost zeros * x length
    matrixBody = []
    for y in range(1, len(yString)+1):
        matrixBody.append([yString[y-1]] + [y] + [0]*len(xString))
    matrix.extend(matrixBody)
#    matrix.extend([[ychar]+[y]+[0]*(len(xString)) for y, ychar in izip(range(1, len(yString)+1), yString) ])
#    print matrix
    
    # Rule eval at a cell
    # from the left = matrix[x-1][y] + 1
    # from above matrix[x][y-1] + 1    
    # from diag:
    # matrix[x-1][y-1] + if stringX[x] == stringY[y]: 0 else 1
    
    # We are starting our indexing off by two to account for the
    # word and the cost
    for x in range(2,len(xString)+2):
        for y in range(2, len(yString)+2):
#            print "x %s y %s" % (y,x)
            left = matrix[y][x-1] + 1
            above = matrix[y-1][x] + 1
            diag = matrix[y-1][x-1]
            if xString[x-2] != yString[y-2]:
                diag = diag+1
            matrix[y][x] = min([left, above,diag])    
    
    for y in matrix:
        print string.join([str(x) for x in y], ' ')
    
    return matrix[len(yString)+1][len(xString)+1]

if __name__ == '__main__':
    print "Edit distance is: "+str(editDistance(sys.argv[1], sys.argv[2]))