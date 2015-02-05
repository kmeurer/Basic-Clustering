package point;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import point.Point;

@RunWith(Parameterized.class)
public class PointTest {
	// Establish Parameters for point testing. Format {x1, y1, x2, y2, distance}
	// Sourced from docs: https://github.com/junit-team/junit/wiki/Parameterized-tests and class notes
	@Parameters
    public static Collection<Object[]> getTestParameters() {
        return Arrays.asList(new Object[][] {     
                 { 0, 0, 1, 0, 1 }, 	// x1, y1, x2, y2, distance
                 { 5, 6, 8, 2, 5 }, 	// x1, y1, x2, y2, distance
                 { 15, 8, 6, 22, 16.64 }	// x1, y1, x2, y2, distance
           });
    }
    
	// declare parameters and  testPoint variables for parameterized Testing
	int x1;
	int y1;
	int x2;
	int y2;
	double distance;
    Point testPoint1;
	Point testPoint2;
	
	// Test Constructor: assigns parameters to variables
	public PointTest(int x_1, int y_1, int x_2, int y_2, double dist){
		x1 = x_1;
		y1 = y_1;
		x2 = x_2;
		y2 = y_2;
		distance = dist;
	}
	
	// reinstantiate test point for each test
	@Before
	public void setUp() {
		// Declare a point to test our point class
		testPoint1 = new Point(x1, y1);
		testPoint2 = new Point(x2, y2);
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
		assertEquals(testPoint1.getX(), x1, 0);
		// getter for Y test
		assertEquals(testPoint1.getY(), y1, 0);
		// getter for both coords test
		int[] testCoords = {x1, y1};
		assertArrayEquals(testPoint1.getCoords(), testCoords);
	}

	// test setter functions
	@Test
	public void settersTest(){
		// it should return the value that it is set to
		assertEquals(testPoint1.setY(5), 5, 0);
		// it should save changes to coordinate values
		assertEquals(testPoint1.getY(), 5, 0);
		// it should return the value that it is set to
		assertEquals(testPoint1.setX(7), 7, 0);
		// it should save changes to coordinate values
		assertEquals(testPoint1.getX(), 7, 0);
	}
	
	// is Equal tests: tests whether the point is equal function works (Note: test of values are equal, not refs)
	@Test
	public void isEqualTest(){
		// Test with unequal points
		Point unequalPoint1 = new Point(1, 4); // diff y val
		assertFalse(testPoint1.isEqual(unequalPoint1));
		Point unequalPoint2 = new Point(2, 3); // diff x val
		assertFalse(testPoint1.isEqual(unequalPoint2));
		Point unequalPoint3 = new Point(2, 9); // diff both vals
		assertFalse(testPoint1.isEqual(unequalPoint3));
		
		// Test with equal points
		Point equalPoint = new Point(x1, y1);
		assertTrue(testPoint1.isEqual(equalPoint));
	}
	
	// tests distanceFrom: Should employ the euclidian distance equation to return the distance
	@Test
	public void distanceFromTest(){
		// distance should be the same regardless of which point is the argument
		System.out.println(distance);
		assertEquals(testPoint1.distanceFrom(testPoint2), distance, .01);
		assertEquals(testPoint2.distanceFrom(testPoint1), distance, .01);
		// distance should be zero when compared to self
		assertEquals(testPoint2.distanceFrom(testPoint2), 0, 0);
		
	}
	
	
}
