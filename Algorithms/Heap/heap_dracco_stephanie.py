a = [0, 16, 14, 10, 8, 7, 9, 3, 2, 4, 1]

def parent(a,i):
    return i / 2

def left(a,i):
    return 2*i

def right(a,i):
    return 2*i +1

if right(a,2)!=5:
    raise Exception('Test failed')


if left(a,2)!=4:
    raise Exception('Test failed')

if parent(a, 3) != 1:
    raise Exception('Test failed')

if parent(a, 9) != 4:
    raise Exception('Test failed')


a = [0,16,4,10,14,7,9,3,2,8,1]
a_length=11

def max_heapify(a,i,a_length):
    l=left(a,i)
    r=right(a,i)

    if l<=a_length and a[l]>a[i]:
        largest=l
    else:
        largest=i

    if r<=a_length and a[r]>a[largest]:
        largest=r

    if largest!=i:
        temp=a[i]
        a[i]=a[largest]
        a[largest]=temp
        max_heapify(a,largest,a_length)

max_heapify(a,2,a_length)

if a != [0,16,14,10,8,7,9,3,2,4,1]:
    raise Exception('Test failed')

def build_max_heap(a):
    a_length=len(a)-1
    for i in reversed(range(1,a_length/2+1)):
        max_heapify(a,i,a_length)


a = [0,4,1,3,2,16,9,10,14,8,7]

build_max_heap(a)

if a != [0,16,14,10,8,7,9,3,2,4,1]:
    raise Exception('Test failed')

a = [0,16,14,10,8,7,9,3,2,4,1]

def heapsort(a):
    build_max_heap(a)
    a_length = len(a) - 1
    for i in reversed(range(2,len(a))):
        temp=a[1]
        a[1]=a[i]
        a[i]=temp
        a_length = a_length-1
        max_heapify(a,1,a_length)

print a
heapsort(a)
print a



if a != [0,1,2,3,4,7,8,9,10,14,16]:
    raise Exception('Test failed')
