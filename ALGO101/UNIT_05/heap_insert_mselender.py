__author__ = 'mike_selender'

def insert_to_heap(heap, node):
    heap.append(node)
    index = len(heap) - 1
    while index > 0:
        indexp = index_of_parent(index)
        if heap[index] < heap[indexp]:
            swap(heap, index, indexp)
            index = indexp
        else:
            break

def swap(heap, index, indexp):
        hold = heap[index]
        heap[index] = heap[indexp]
        heap[indexp] = hold

def index_of_parent(index):
    return (index - 1) / 2


heap = [4, 5, 8, 11, 20]

print heap
insert_to_heap(heap, 5)
print heap