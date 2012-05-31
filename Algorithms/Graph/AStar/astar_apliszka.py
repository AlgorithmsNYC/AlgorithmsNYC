maze = [
    [ 1, 1, 1, 1, 1, 1, 1],
    [ 1, 0, 1, 0, 0, 0, 1],
    [ 1, 0, 1, 0, 0, 0, 1],
    [ 1, 0, 0, 0, 0, 0, 1],
    [ 1, 1, 1, 1, 1, 1, 1],
]

distance_to_goal = [
    [10, 9, 8, 7, 6, 5, 4],
    [ 9, 8, 7, 6, 5, 4, 3],
    [ 8, 7, 6, 5, 4, 3, 2],
    [ 7, 6, 5, 4, 3, 2, 1],
    [ 6, 5, 4, 3, 2, 1, 0],
]

distance_from_start = [
    [ 0, 0, 0, 0, 0, 0, 0],
    [ 0, 0, 0, 0, 0, 0, 0],
    [ 0, 0, 0, 0, 0, 0, 0],
    [ 0, 0, 0, 0, 0, 0, 0],
    [ 0, 0, 0, 0, 0, 0, 0],
]

directions = [
    [ 0, 1],
    [ 1, 0],
    [ 0,-1],
    [-1, 0],
]

start = [ 1, 1]
goal =  [ 5, 3]

position = start

frontier = []
visited = [start]

while position != goal:
    x, y = position

    # find all legal moves
    for direction in directions:
        x1 = position[0] + direction[0]
        y1 = position[1] + direction[1]
        distance = distance_from_start[y][x]  + 1 + distance_to_goal[y1][x1]
        if maze[y1][x1] == 0 and [x1,y1] not in visited:
            frontier.append([distance, [x1, y1]])

    # choose the best move
    frontier.sort()
    frontier.reverse()
    distance, [x2, y2] = frontier.pop()

    # move the robot to the new position
    distance_from_start[y2][x2] = distance_from_start[y][x] + 1
    position = [x2, y2]
    visited.append(position)

print visited

for move in visited:
    maze[move[1]][move[0]] = 8

print
for row in maze:
    print row
