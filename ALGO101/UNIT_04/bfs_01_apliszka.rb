label = ['s','a','b','c','d','e']
G = [[1,2],[0,3],[0,3,4],[1,2,4,5],[2,3,5],[3,4]]
explored = [0,0,0,0,0,0]
Q = []

Q.push(0)
explored[0] = 1

while not Q.empty?
  a = Q.shift
  puts label[a]
  G[a].each do |n|
    if explored[n] == 0
      explored[n] = 1
      Q.push n
    end
  end
end