w = [[0,1,4,0,0], [1,0,2,6,0], [4,0,0,3,1], [0,6,3,0,0], [0,0,1,0,0]] # V*V
key = [0, 100, 100, 100, 100, 100] # V
parent = [-1, -1, -1, -1, -1] # V
Q = [0, 1, 2, 3, 4] # V

while Q.size > 0 # O(V)
  Q.sort! { |b,a| key[a] <=> key[b]} # O(V log V)?
  u = Q.pop
  puts "Edge: #{parent[u]} - #{u}" if parent[u] >= 0
  0.upto(4).each do |v| # O(V)
    if w[u][v] > 0
      if Q.member?(v) and w[u][v] < key[v]
        key[v] = w[u][v]
        parent[v] = u
      end
    end
  end
end

