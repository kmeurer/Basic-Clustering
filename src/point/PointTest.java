package point;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import point.Point;


public class PointTest {
	// declare a testPoint variable for testing
	Point testPoint;
	
	// instantiate test point for each test
	@Before
	public void setUp() {
		// Declare a point to test our point class
		testPoint = new Point(1, 3);
	}
	
	// test constructors
	@Test
	public void constructorTest(){
		// create point at 0, 0
		Point emptyPoint = new Point();
		// create point at 2, 8
		Point specificPoint = new Point(2, 8);
		assertEquals(emptyPoint.getX(), 0, 0);
		assertEquals(specificPoint.getY(), 8, 0);
	}
	
	// test all getter functions
	@Test
	public void gettersTest(){
		// getter for X test
		assertEquals(testPoint.getX(), 1, 0);
		// getter for Y test
		assertEquals(testPoint.getY(), 3, 0);
		// getter for both coords test
		int[] testCoords = {1, 3};
		assertArrayEquals(testPoint.getCoords(), testCoords);
	}

	// test setter functions
	@Test
	public void settersTest(){
		// it should return the value that it is set to
		assertEquals(testPoint.setY(5), 5, 0);
		// it should save changes to coordinate values
		assertEquals(testPoint.getY(), 5, 0);
		// it should return the value that it is set to
		assertEquals(testPoint.setX(7), 7, 0);
		// it should save changes to coordinate values
		assertEquals(testPoint.getX(), 7, 0);
	}
	
	// is Equal tests: tests whether the point is equal function works (Note: test of values are equal, not refs)
	@Test
	public void isEqualTest(){
		// test point is (1, 3)
		// Test with unequal points
		Point unequalPoint1 = new Point(1, 4); // diff y val
		assertFalse(testPoint.isEqual(unequalPoint1));
		Point unequalPoint2 = new Point(2, 3); // diff x val
		assertFalse(testPoint.isEqual(unequalPoint2));
		Point unequalPoint3 = new Point(2, 9); // diff both vals
		assertFalse(testPoint.isEqual(unequalPoint3));
		
		// Test with equal points
		Point equalPoint = new Point(1, 3);
		assertTrue(testPoint.isEqual(equalPoint));
	}
	
	
}
