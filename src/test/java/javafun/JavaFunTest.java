package javafun;

import static org.junit.Assert.*;

import org.junit.Test;

import javafun.JavaFun;

public class JavaFunTest {
	
	private JavaFun javaFun = new JavaFun();
	
	@Test
	public void testGetSmallestSumSuccess() {
		int[] chain = new int[] { 1, 2, 3, 9, 6, 4, 7 };
		assertEquals(3, javaFun.getSmallestSum(chain));
		
		chain = new int[] { 9, 9, 8, 1, 1 };
		assertEquals(2, javaFun.getSmallestSum(chain));
	}
	
	@Test
	public void testGetSmallestSumFailed() {
		
		// 0 is an invalid value
		int[] chain = new int[] { 1, 3, 4, 0 };
		try {
			javaFun.getSmallestSum(chain);
			fail("IllegelArgumentException expected");
		} catch(IllegalArgumentException e) {
			// nop
		}
		
		// MAX_VALUE will cause IntegerOverflow
		chain = new int[] { 1, Integer.MAX_VALUE };
		try {
			javaFun.getSmallestSum(chain);
			fail("IllegelArgumentException expected");
		} catch(IllegalArgumentException e) {
			// nop
		}
		
		// negative values are not allowed
		chain = new int[] { 1, 2, 3, 4, 5, -7 };
		try {
			javaFun.getSmallestSum(chain);
			fail("IllegelArgumentException expected");
		} catch(IllegalArgumentException e) {
			// nop
		}
	}
}	