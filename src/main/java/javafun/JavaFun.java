package javafun;

import javaslang.collection.List;

public class JavaFun {
	public static void main(String[] args) {
		int[] chain = new int[] { 1, Integer.MAX_VALUE };
		System.out.println(new JavaFun().getSmallestSum(chain)); // What should this print?
	}
		
	/**
	 * Finds the smallest pair sum in given int list in O(n logn)
	 * 
	 * @param chain List of Integers
	 * @throws IllegalArgumentException if given list's size is lower than two or negative values are included.
	 * @return Smallest sum
	 */
	public int getSmallestSum(int[] chain) {
		// at least 2 values are needed
		if (chain.length >= 2) {
			
			// O(n logn)
			List<Integer> chainArray = List.ofAll(chain).sort();
			
			// 0 and negative values are not allowed
			if (chainArray.get(0) <= 0) {
				throw new IllegalArgumentException("List includes negative value.");
			}

			int pairSum = chainArray.get(0) + chainArray.get(1);
			
			// check for integer overflow
			if(pairSum < 0){
				throw new IllegalArgumentException();
			};
			
			return pairSum;
		} else {
			throw new IllegalArgumentException("Size of list too small");
		}
	}
}
