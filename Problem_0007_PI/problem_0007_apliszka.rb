# Trivial Monte Carlo pi estimation

n_square = 0.0
n_circle = 0.0

while(true)
  x = rand
  y = rand
  n_square += 1
  n_circle += 1 if((x*x+y*y) <= 1)
  if(n_square % 100000 == 0 and n_circle > 0)
    pi = 4*(n_circle)/(n_square )
    puts "n=%14i  pi=%14f  err=%14f" % [n_square, pi, pi - Math::PI]
  end
end
