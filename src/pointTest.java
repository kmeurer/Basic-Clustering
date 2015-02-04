import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class pointTest {
	// declare a testPoint variable for testing
	point testPoint;
	
	@Before
	public void setUp() {
		// Declare a point to test our point class
		testPoint = new point(1, 3);
	}
	
	// test constructors
	@Test
	public void constructorTest(){
		// create point at 0, 0
		point emptyPoint = new point();
		// create point at 2, 8
		point specificPoint = new point(2, 8);
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

	// test all setter functions
	@Test
	public void settersTest(){
		// it should return the value that it is set to
		assertEquals(testPoint.setY(5), 5, 0);
		// it should save changes to coordinate values
		assertEquals(testPoint.getY(), 5, 0);
	}
}
