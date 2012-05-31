/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package levenshtein;


/**
 *
 * @author Marcello
 */
public class Levenshtein {
    
    public static void main(String[] args) {

        int distance = Levenshtein.lenshteinDistance("cat", "brat");
        
        System.out.println("distance=" + distance);
        
//          brat
//         01234
//        c11234
//        a22223
//        t33332
//
//        distance=2

    }
    
    public static int lenshteinDistance(String w1, String w2) {
        
        int w1len = w1.length() + 1;
        int w2len = w2.length() + 1;
                
        int[][] temp = new int[w1len][w2len];
        
        for(int i = 0; i < w1len; i++) {
            temp[i][0] = i;
        }
        
        for(int j = 0; j < w2len; j++) {
            temp[0][j] = j;
        }


        for(int i = 1; i < w1len; i++) {
            for(int j = 1; j < w2len; j++) {
                
                char c1 = w1.charAt(i - 1);
                char c2 = w2.charAt(j - 1);
                
                int cost = c1 == c2 ? 0 : 1;
                
                temp[i][j] = min(temp[i - 1][j] + 1, temp[i][j - 1] + 1, temp[i - 1][j - 1] + cost);
                
            }
        }

        printArray(temp, w1, w2);
        
        return temp[w1len - 1][w2len - 1];

    }
    
    
    private static void printArray(int[][] array, String w1, String w2) {

        System.out.println("  " + w2);
        for(int i = 0; i < array.length; i++) {
            if(i > 0)
                System.out.print(w1.charAt(i - 1));
            else
                System.out.print(" ");
            for(int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        
    }

    private static int min(int i, int i0, int i1) {

        int min = Math.min(i, i0);

        return Math.min(min, i1);
        
    }
    
}
