from Queue import Queue
start_pos = [1, 1]
goal = [5, 3]
maze = [[1, 1, 1, 1, 1, 1, 1],
        [1, 0, 1, 0, 0, 0, 1],
        [1, 0, 1, 0, 0, 0, 1],
        [1, 0, 0, 0, 1, 0, 1],
        [1, 1, 1, 1, 1, 1, 1]]

distance_from_start = [[0 for _x in xrange(7)] for _y in xrange(5)]
position = start_pos
available_moves = [[0, 1], [1, 0], [0, -1], [-1, 0]]
frontier = Queue()
visited = []

while position != goal:
    x, y = position
    for move in available_moves:
        x1 = x + move[0]
        y1 = y + move[1]
        if maze[y1][x1] == 0 and [x1, y1] not in visited:
            frontier.put([x1, y1])
            distance_from_start[y1][x1] = distance_from_start[y][x] + 1
            
    x2, y2 = frontier.get()
    visited.append(position)
    position = [x2, y2]

for row in distance_from_start:
    print row
        
