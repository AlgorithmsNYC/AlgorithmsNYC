maze = [
    [ 1, 1, 1, 1, 1, 1, 1],
    [ 1, 0, 1, 0, 0, 0, 1],
    [ 1, 0, 1, 0, 0, 0, 1],
    [ 1, 0, 0, 0, 0, 0, 1],
    [ 1, 1, 1, 1, 1, 1, 1],
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

frontier = []
visited = []

pos = start

while pos != goal
  x,y = pos

  # find all legal moves
  directions.each do |dir|
    x1 = pos[0] + dir[0]
    y1 = pos[1] + dir[1]
    if maze[y1][x1] == 0 and not visited.member?([x1, y1])
      frontier.push [x1, y1]
      distance_from_start[y1][x1] = distance_from_start[y][x] + 1
    end
  end

  #dequeue next move
  x2, y2 = frontier.shift

  visited.push pos

  pos = [x2,y2]
end

distance_from_start.each { |r| puts r.inspect }


#[0, 0, 0, 0, 0, 0, 0]
#[0, 0, 0, 6, 0, 0, 0]
#[0, 1, 0, 5, 6, 0, 0]
#[0, 2, 3, 4, 5, 6, 0]
#[0, 0, 0, 0, 0, 0, 0]