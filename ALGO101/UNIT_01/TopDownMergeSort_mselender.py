#TopDownMergeSortInPython: 
#A Python implementation of the pseudocode at 
#http://en.wikipedia.org/wiki/Merge_sort


def merge_sort(m):
    if len(m) <=1:
        return m
    left = []
    right = []
    middle = int(len(m) / 2)
    for x in range(0, middle):
        left.append(m[x])
    for x in range(middle, len(m)):
        right.append(m[x])
    left = merge_sort(left)
    right = merge_sort(right)
    return merge(left, right)

def merge(left, right):
    result = []
    while len(left) > 0 or len(right) > 0:
        if len(left) > 0 and len(right) > 0:
            if left[0] <= right[0]:
                result.append(left[0])
                left = left[1:len(left)]
            else:
                result.append(right[0])
                right = right[1:len(right)]
        elif len(left) > 0:
            result.append(left[0])
            left = left[1:len(left)]
        elif len(right) > 0:
            result.append(right[0])
            right = right[1:len(right)]
    return result


input = [5, 4, 7, 8, 2, 3, 1, 6, 9]
print merge_sort(input)