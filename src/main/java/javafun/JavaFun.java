package javafun;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javaslang.collection.List;

public class JavaFun {
	public static void main(String[] args) {
		int[] chain = new int[] { 2, 1, 3, 6 };
		System.out.println(new JavaFun().getSmallestSumWithoutNeighbors(chain)); // What should this print?
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
			if(pairSum <= 0){
				throw new IllegalArgumentException();
			};
			
			return pairSum;
		} else {
			throw new IllegalArgumentException("Size of list too small");
		}
	}
	
	/**
	 * Finds the smallest pair sum in given integer list in O(n logn)
	 * 
	 * @param chain List of Integers
	 * @throws IllegalArgumentException if given list's size is lower than two or negative values are included.
	 * @return Smallest sum
	 */
	public int getSmallestSumWithoutNeighbors(int[] chain) {
		// at least 3 values are needed
		if (chain.length == 3) {
			checkForOverflow(chain[0], chain[2]);
			return chain[0] + chain[2];
		}
		if (chain.length > 3) {
			
			// Tree map sorts by key automatically
			Map<Integer, Integer> mapValueToIndex = new TreeMap<>();
			
			// O(n logn)
			for(int i = 0; i<chain.length; ++i) {
				mapValueToIndex.put(chain[i], i);
			}
			
			Iterator<Integer> iter = mapValueToIndex.keySet().iterator();
			Integer first = iter.next();
			if(first <= 0) {
				throw new IllegalArgumentException("List includes negative value.");
			}

			//
			// Lookup smallest pair sum without neighbors
			//
			
			// Lookup the first possible smallest sum
			Integer next = iter.next();
			while(Math.abs(mapValueToIndex.get(next) - mapValueToIndex.get(first)) <= 1) {
				next = iter.next();
			}
			// TODO: if this sum is NOT the smallest sum we have to ignore the overflow here
			checkForOverflow(first,  next);
			int firstPossibleSmallestSum = first + next;
			
			// lookup the second possible smallest sum starting
			iter = mapValueToIndex.keySet().iterator();
			iter.next();
			first = iter.next();
			next = iter.next();
			while(Math.abs(mapValueToIndex.get(next) - mapValueToIndex.get(first)) <= 1) {
				next = iter.next();
			}
			checkForOverflow(first,  next);
			int secondPossibleSmallestSum = first + next;
			
			if(firstPossibleSmallestSum < secondPossibleSmallestSum) {
				return firstPossibleSmallestSum;
			} else {
				return secondPossibleSmallestSum;
			}
		} else {
			throw new IllegalArgumentException("Size of list too small");
		}
	}
	
	/**
	 * Checks for integer overflow
	 * 
	 * @param a Positive (>0) integer
	 * @param b Positive (>0) integer
	 * @throws IllegalArgumentException if overflow is detected
	 */
	private void checkForOverflow(int a, int b) {
		if( (a+b) <= 0 ) {
				throw new IllegalArgumentException("Integer overflow detected!");
		}
	}
}
