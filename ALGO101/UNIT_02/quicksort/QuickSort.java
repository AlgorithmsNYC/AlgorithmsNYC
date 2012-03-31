/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package quicksort;

import java.util.Arrays;
import util.CommonFunctions;

/**
 *
 * @author Marcello
 */
public class QuickSort {
    
    public static void main(String[] args) {
        
        int[] input = new int[]{9,4,7,1,5,2,0,3,8,6};
        
        System.out.println("Input=" + Arrays.toString(input));
        quickSort(input);
        System.out.println("Output=" + Arrays.toString(input));
        
    }
    
    public static void quickSort(int[] input) {
        
        quickSort(input, 0, input.length - 1);
                
    }
    
    private static void quickSort(int[] input, int left, int right) {
        
        if(left < right) {
            
            int pivotIndex = CommonFunctions.partition(input, left, right);

            quickSort(input, left, pivotIndex - 1);
            
            quickSort(input, pivotIndex + 1, right);

        }
        
    }

}
