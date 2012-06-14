#V = [0, 1, 2, 3]

# [weight, vertex1, vertex2]
#E = [
#    [1,0,1], [5,0,3],
#    [2,1,2], [3,1,3],
#    [4,2,3]
#]

#MIT p 632
V = [0, 1, 2, 3, 4, 5, 6, 7, 8]

E = [
    [4, 0, 1], [8, 0, 7],
    [8, 1, 2], [11, 1, 7],
    [7, 2, 3], [4, 2, 5], [2, 2, 8],
    [9, 3, 4], [14, 3, 5],
    [10, 4, 5],
    [2, 5, 6],
    [1, 6, 7], [6, 6, 8],
    [7, 7, 8]
]

MST = []
Q = E

Q.sort()

forest = []
for v in V:
    forest.append([v])

print Q

while Q and len(MST) < len(V) - 1:
    e = Q.pop(0)
    for s in forest:
        if e[1] in s:
            s1 = s
        if e[2] in s:
            s2 = s
    if s1 != s2:
        MST.append(e)
        forest.remove(s1)
        forest.remove(s2)
        forest.append(s1+s2)
    print forest

print MST

for e in MST:
    print e[1], '-', e[2]
