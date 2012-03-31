/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Marcello
 */
public class CommonFunctions {
    
    public static int partition(int[] array, int start, int end) {
        
        int n = end + 1 - start;

        int pivotIndex = start + (new Random().nextInt(n));
        int pivotValue = array[pivotIndex];
        
        System.out.print("-pivotIndex=" + pivotIndex + ", pivotValue=" + pivotValue);
        
        CommonFunctions.swap(array, pivotIndex, end);
                
        int slow = start - 1;
        for(int fast = start; fast < end; fast++)
            if(array[fast] < pivotValue)
                CommonFunctions.swap(array, ++slow, fast);

        CommonFunctions.swap(array, ++slow, end);

        
        System.out.println(", tmpArray=" + Arrays.toString(array));
        
        return slow;
        
    }
    
    
    public static void swap(int[] array, int index1, int index2) {    
        
        int tmp = array[index1];
        
        array[index1] = array[index2];
        
        array[index2] = tmp;
    
    }
}
