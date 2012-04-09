/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesort;

import java.util.Arrays;

/**
 *
 * @author Marcello
 */
public class MergeSort {
    
    public static void main(String[] args) {
        int[] input = new int[]{9,4,7,2,6,0,3,1,8,5};
        
        System.out.println("Input=" + Arrays.toString(input));

        mergeSort(input);
        
        System.out.println("Output=" + Arrays.toString(input));
    }
    
    public static void mergeSort( int[] array ) {

        mergeSort( array, new int[array.length], 0, array.length - 1 );

    }
	
    private static void mergeSort( int[] array, int[] tmpArray, int left, int right ) {
        if( left < right ) {
     
            int center = left + (right - left) / 2;//( left + right ) / 2;
            
            mergeSort( array, tmpArray, left, center );
            
            mergeSort( array, tmpArray, center + 1, right );
            
            merge( array, tmpArray, left, center + 1, right );
        }
    }
    
    private static void merge( int[] array, int[] tmpArray, int leftPos, int rightPos, int rightEnd ) {
							   
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;
		
        //Main loop
        while( leftPos <= leftEnd && rightPos <= rightEnd ) {
            if( array[ leftPos ] <= array[ rightPos ] )
                tmpArray[ tmpPos++ ] = array[ leftPos++ ];
            else
                tmpArray[ tmpPos++ ] = array[ rightPos++ ];
        }
        
        //Copy rest of first half
        while( leftPos <= leftEnd )
            tmpArray[ tmpPos++ ] = array[ leftPos++ ];

        //Copy rest of right half
        while( rightPos <= rightEnd )
            tmpArray[ tmpPos++ ] = array[ rightPos++ ];

        //Copy tmpArray back into inputArray
        for( int i = 0; i < numElements; i++, rightEnd-- )
            array[ rightEnd ] = tmpArray[ rightEnd ];
    
    }
    
}
