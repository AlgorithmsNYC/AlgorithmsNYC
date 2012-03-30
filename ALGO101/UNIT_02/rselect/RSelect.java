/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rselect;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Marcello
 */
public class RSelect {
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int[] array = new int[]{5, 9, 22, 7, 12, 75, 0, 3, 51, 33};
        int orderStatistic = 5;
        
        int[] sortedArray = array.clone();
        Arrays.sort(sortedArray);
        
        System.out.println("InputArray=" + Arrays.toString(array) + ", OrderStatistic=" + orderStatistic);

        int result = rSelect(array, orderStatistic);
        System.out.println("\nFinalArray=" + Arrays.toString(array) + ", OrderStatistic=" + orderStatistic + ", Result=" + result);
        
        
        System.out.println("\nSortedArray=" + Arrays.toString(sortedArray));

    }

    
    public static int rSelect(int[] array, int orderStatistic) {
        
        if(orderStatistic < 1 || orderStatistic > array.length)
            return -1;
        
        int orderStatisticIndex = orderStatistic - 1;
        
        
        return rSelect(array, 0, array.length - 1, orderStatisticIndex);
        
    }
    
    private static int rSelect(int[] array, int start, int end, int orderStatistic) {
        
        int pivotIndex = partition(array, start, end);
        
        
        if(pivotIndex == orderStatistic) {
            return array[pivotIndex];
        } else if(pivotIndex < orderStatistic) {            
//            eraseOtherSide(array, start, pivotIndex - 1);
            return rSelect(array, pivotIndex + 1, end, orderStatistic);
        } else {
//            eraseOtherSide(array, pivotIndex + 1, end);
            return rSelect(array, start, pivotIndex - 1, orderStatistic);
        }
        
    }
    
    private static int partition(int[] array, int start, int end) {
        
        int n = end + 1 - start;

        int pivotIndex = start + (new Random().nextInt(n));
        int pivotValue = array[pivotIndex];
        
        System.out.print("-pivotIndex=" + pivotIndex + ", pivotValue=" + pivotValue);
        
        swap(array, pivotIndex, end);
                
        int slow = start - 1;
        for(int fast = start; fast < end; fast++)
            if(array[fast] < pivotValue)
                swap(array, ++slow, fast);

        swap(array, ++slow, end);

        
        System.out.println(", tmpArray=" + Arrays.toString(array));
        
        return slow;
        
    }
    
    private static void eraseOtherSide(int[] array, int index1, int index2) {
        for(int i = index1; i <= index2; i++) {
            array[i] = -1;
        }
    }
    
    private static void swap(int[] array, int index1, int index2) {      
        int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

}
