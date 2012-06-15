public class EditDistance_Rahul {

	
	private static final String DELIM = "\t|";

	public static void main(String[] args) {
		String w1 = "cat";
		String w2 = "brat";
		
		int rowNum = w1.length() +1 ;
		int colNum = w2.length() + 1;
		int arr[][] = new int[rowNum][colNum];
		
		for (int row = 0; row <= w1.length(); row++) {
			arr[row][0] = row;
 		}
		for (int col = 0 ; col <= w2.length(); col++) {
			arr[0][col] = col;
		}
		
		for (int row = 1; row <= w1.length(); row++) {
			for (int col = 1; col <= w2.length(); col++) {
				int costDown = arr[row-1][col] + 1;
				int costSide = arr[row][col-1] +1;
				int diag = (w1.charAt(row-1) == w2.charAt(col-1) ? arr[row-1][col-1] : arr[row-1][col-1] + 1);
				arr[row][col] = Math.min(diag,Math.min(costSide, costDown));
			}
		}
		printArr(w1, w2, arr, rowNum,colNum);
	}

	private static void printArr(String w1, String w2, int[][] arr, int rowNum, int colNum) {
		for (int row = -1; row < rowNum; row++) {
			for (int col = -1; col < colNum; col++) {
				if(row == -1  ) { 
					if(col < 1)
						System.out.print("-" + DELIM) ;
					else 
						System.out.print(w2.charAt(col-1) + DELIM);
				} else if (col == -1 ) {
					if(row < 1) 
						System.out.print("-" + DELIM) ;
					else 
						System.out.print(w1.charAt(row-1) + DELIM) ;
				}  else {
					System.out.print(arr[row][col]  + DELIM);
				}
			}
			System.out.println();
		}
	}
}

