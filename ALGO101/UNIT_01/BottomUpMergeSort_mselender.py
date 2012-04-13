__author__ = 'mike_selender'


def BottomUpSort(n, a, b):
    width = 1
    while width < n:
        i = 0
        while i < n:
            BottomUpMerge(a, i, min((i + width), n), min((i + (2 * width)), n), b)
            i = i + (2 * width)
        a = b
        b = []
        width = 2 * width
    return a

def BottomUpMerge(a, left, right, end, b):
    i0 = left
    i1 = right
    j = left
    inversions = 0

    while j < end:
        if i0 < right and (i1 >= end or a[i0] <= a[i1]):
            b.append(a[i0])
            i0 += 1
        else:
            b.append(a[i1])
            i1 += 1
        j += 1
    return inversions

input = [5, 4, 7, 8, 2, 3, 1, 6, 9]
work = []

print BottomUpSort(len(input), input, work)