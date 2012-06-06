# import pdb

start_pos = [1, 2]
goal = [5, 4]
maze = [[1, 1, 1, 1, 1, 1, 1, 1],
        [1, 1, 0, 0, 0, 0, 0, 1],
        [1, 0, 1, 0, 0, 0, 0, 1],
        [1, 0, 1, 0, 1, 1, 0, 1],
        [1, 0, 0, 0, 1, 0, 0, 1],
        [1, 1, 1, 1, 1, 1, 1, 1]]
distance_to_goal = [[9, 8, 7, 6, 5, 4, 5, 6],
                    [8, 7, 6, 5, 4, 3, 4, 5],
                    [7, 6, 5, 4, 3, 2, 3, 4],
                    [6, 5, 4, 3, 2, 1, 2, 3],
                    [5, 4, 3, 2, 1, 0, 1, 2],
                    [6, 5, 4, 3, 2, 1, 2, 3]]
distance_from_start = [[0 for _x in xrange(8)] for _y in xrange(6)]

# start_pos = [1, 1]
# goal = [5, 3]
# maze = [[1, 1, 1, 1, 1, 1, 1],
#         [1, 0, 1, 0, 0, 0, 1],
#         [1, 0, 1, 0, 0, 0, 1],
#         [1, 0, 0, 0, 1, 0, 1],
#         [1, 1, 1, 1, 1, 1, 1]]
# distance_to_goal = [[8, 7, 6, 5, 4, 3, 4],
#                     [7, 6, 5, 4, 3, 2, 3],
#                     [6, 5, 4, 3, 2, 1, 2],
#                     [5, 4, 3, 2, 1, 0, 1],
#                     [6, 5, 4, 3, 2, 1, 2]]
# distance_from_start = [[0 for _x in xrange(7)] for _y in xrange(5)]


available_moves = [[0, 1], [1, 0], [0, -1], [-1, 0]]
visited = [start_pos]  
frontier = []  # nodes to explore
current_position = start_pos
while current_position != goal:
    for move in available_moves:
        x, y = current_position
        x1 = move[0] + x
        y1 = move[1] + y

        if maze[y1][x1] == 0 and [x1, y1] not in visited:
            distance = distance_from_start[y][x] + 1 + distance_to_goal[y1][x1]
            value = [distance, [x1, y1]]
            frontier.append(value)
            distance_from_start[y1][x1] = distance - distance_to_goal[y1][x1]

    frontier = sorted(frontier)
    frontier.reverse()
    shortest_path = frontier.pop()

    x, y = current_position
    j, z = shortest_path[1]

    distance_from_start[z][j] = shortest_path[0] - distance_to_goal[z][j]
    current_position = shortest_path[1]
    visited.append(shortest_path[1])

for row in distance_from_start:
    print row
    

