__author__ = 'mike_selender'

def Explore(graph, start):
    explored = []
    toexplore = []
    temp = []
    toexplore.append(start)
    while len(toexplore) > 0:
        item = toexplore.pop(0)
        if item not in explored:
            explored.append(item)
            temp = graph[item]
            for t in range(len(temp)):
                if temp[t] not in explored:
                    toexplore.append(temp[t])
    return explored, toexplore


graph = {'S': ['A', 'B'],
         'A': ['S','C'],
         'B': ['S','C'],
         'C': ['A', 'B', 'D', 'E'],
         'D': ['B','C', 'E'],
         'E': ['C', 'D']}


print Explore(graph, 'S')

#print graph['S']



