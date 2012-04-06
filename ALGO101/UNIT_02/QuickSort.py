__author__ = 'mike_selender'

import random

#Adapted from pseudocode at http://en.wikipedia.org/wiki/Quick_sort
#left is the index of the leftmost element of the array
#right is the index of the rightmost element of the array (inclusive)
#number of elements in subarray = right-left+1

def swap_array(input,x, y):
    hold = input[x]
    input[x] = input[y]
    input[y] = hold

def partition(input, leftIndex, rightIndex, pivotIndex):

    pivotValue = input[pivotIndex]
    swap_array(input, pivotIndex, rightIndex)
    storeIndex = leftIndex
    for i in range(leftIndex, rightIndex):
        if input[i] < pivotValue:
            swap_array(input, i, storeIndex)
            storeIndex += 1
    swap_array(input, storeIndex, rightIndex)
    return storeIndex

def QuickSort(input, leftIndex, rightIndex):

    if leftIndex < rightIndex:
        pivotIndex = random.randrange(leftIndex, rightIndex)
        pivotNewIndex = partition(input, leftIndex, rightIndex, pivotIndex)
        QuickSort(input, leftIndex, pivotNewIndex - 1)
        QuickSort(input, (pivotNewIndex + 1), rightIndex)

#input = [3, 8, 2, 10]
input = [5, 4, 7, 8, 2, 3, 1, 6, 9]
#print partition(input, 0, len(input) - 1, 1)
QuickSort(input, 0, len(input) - 1)
print input



