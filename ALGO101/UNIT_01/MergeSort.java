/**
 * 
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author kalyan
 *
 */
public class MergeSort {
	
	public static void main(String args[]){
		
		ArrayList<Integer> sorterdList = new ArrayList<Integer>();
		List<Integer> intList = new ArrayList<Integer>();
		
		intList.add(0, new Integer(5));
		intList.add(1, new Integer(2));
		intList.add(2, new Integer(3));
		intList.add(3, new Integer(7));
		intList.add(3, new Integer(8));
		
		
		for(int i = 0;i<intList.size();i++)
			System.out.println("unsorterdList: "+i+"value"+intList.get(i));
		
		sorterdList = mSort((ArrayList<Integer>) intList);
		for(int i = 0;i<sorterdList.size();i++)
		System.out.println("iteration: "+i+"value"+sorterdList.get(i));
		
		
	}
	
	public static ArrayList<Integer> mSort(ArrayList<Integer> listToBeSorted){
		 ArrayList<Integer> left = new ArrayList<Integer>();
		 ArrayList<Integer> right = new ArrayList<Integer>();
		 int mediumLen = 0;
		// if list size is negative or zero
		if(listToBeSorted != null && listToBeSorted.size()<=1){
			
			return listToBeSorted;
		}else{
			
			//determine mid point
			mediumLen = listToBeSorted.size()/2;
			
			System.out.println("medium length:"+mediumLen);
			
			
			//add anything less than medium index to left array
			for(int i=0;i<mediumLen;i++){
				System.out.println("add to left array iteration:"+i+"value being added:"+listToBeSorted.get(i));
				left.add(listToBeSorted.get(i));
			}
			
			//add anything greater than or equal to right array
			for(int i=mediumLen;i<listToBeSorted.size();i++){
				System.out.println("add to right array iteration:"+i+"value being added"+listToBeSorted.get(i));
				right.add(listToBeSorted.get(i));
			}
			
			System.out.println("left array size:"+left.size());
			System.out.println("right array size:"+right.size());
			
			left = mSort(left);
			right = mSort(right);
			
			
		}
		
		return merge(left,right);
	}
	
	public static ArrayList<Integer> merge(ArrayList<Integer> leftArrList ,ArrayList<Integer> rightArrList){
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		while(leftArrList.size()>0 || rightArrList.size()>0){
			if(leftArrList.size()>0 && rightArrList.size()>0){
				if(leftArrList.get(0)<=rightArrList.get(0)){
					//append first of  left to result
					result.add(leftArrList.get(0));
					
					//append rest to left
					leftArrList.remove(0);
					
				}else{
					
					//append first of right to result
					result.add(rightArrList.get(0));
					
					//append rest to right
					rightArrList.remove(0);
					
					
				}
			}else if(leftArrList.size()>0){
				//append first(left) to result
				result.add(leftArrList.get(0));
	            //left = rest(left)
				leftArrList.remove(0);
				
			}else if(rightArrList.size()>0){
				
				//append first(right) to result
				result.add(rightArrList.get(0));
	            //right = rest(right)
				rightArrList.remove(0);
				
			}
		}
		
		return result;
	}

}
