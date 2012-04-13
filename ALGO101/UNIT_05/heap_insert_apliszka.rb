# Trivial implementation of heap insert function

def swap(a,i,j)
  a[i], a[j] = a[j], a[i]
end

def insert(heap, x)
  heap[heap.size] = x

  i = heap.size - 1

  while i > 0 do
    p = (i-1)/2
    break if heap[p] < heap[i]
    swap(heap, p, i)
    i = p
  end
end

heap = [4,5,8,11,20]

p heap
insert(heap, 1)
insert(heap, 2)
insert(heap, 3)
p heap