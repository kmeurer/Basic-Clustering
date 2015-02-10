package cluster;
import static org.junit.Assert.*;

// local imports
import point.Point;
// util imports
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
// junit imports
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ClusterTest {
	// Establish Parameters for cluster testing. Format {centroidX, centroidY, x2, y2, x3, y3, x4, y4, max-intra-distance, meanX, meanY}
	@Parameters
    public static Collection<Object[]> getTestParameters() {
        return Arrays.asList(new Object[][] {     
             { 10, 7, 1, 4, 10, 9, 11, 17, 16.4, 8, 9.25 }, 	// centroidX, centroidY, x2, y2, x3, y3, x4, y4, max-intra-distance, meanX, meanY
             { 5, -2, 4, 1, 5, 17, 10, 16, 19.0, 6, 8 }, 	// centroidX, centroidY, x2, y2, x3, y3, x4, y4, max-intra-distance, meanX, meanY
             { 57, 8, 3, 22, 16, 12, 14, 1, 55.78, 22.5, 10.75 }	// centroidX, centroidY, x2, y2, x3, y3, x4, y4, max-intra-distance, meanX, meanY
       });
    }
	
	// Create a test cluster to be used for testing functionality
	Cluster testCluster;
	Point testCentroid;
	Point p2;
	Point p3;
	Point p4;
	ArrayList<Point> coords;
	double maxIntraDist; // maximum intra-cluster distance
	double meanX; // average of all x vals
	double meanY; // average of all y vals
	
	// Test Constructor: assigns parameters to variables
	public ClusterTest(double centroidX, double centroidY, double x2, double y2, double x3, double y3, double x4, double y4, double maxIntraDistance, double Xmean, double Ymean){
		meanX = Xmean;
		meanY = Ymean;
		testCentroid = new Point(centroidX, centroidY);
		p2 = new Point(x2, y2);
		p3 = new Point(x3, y3);
		p4 = new Point(x4, y4);
		// store our coords
		coords = new ArrayList<Point>();
		coords.add(testCentroid);
		coords.add(p2);
		coords.add(p3);
		coords.add(p4);
		maxIntraDist = maxIntraDistance;
		testCluster = new Cluster(testCentroid);
	}
	
	// Set Up before each
	@Before
	public void setUp() throws Exception {
		testCluster.clearClusterPoints(); // clear all points but preserve centroid
		testCluster.addPoints(coords); // re-add our points
	}
	
	// Test constructors
	@Test
	public void constructorTests() {
		// It should set a centroid to a specified point if called in the constructor
		Point testPoint = new Point(5, 7);
		Cluster test2 = new Cluster(testPoint);
		assertEquals(test2.getCentroid(), testPoint);
	}
	
	// Getters Test
	@Test
	public void gettersTests(){
		// it should get the centroid when requested
		assertEquals(testCluster.getCentroid(), testCentroid);
	}
	
	// Setters Test
	@Test
	public void settersTests(){
		// create a new point that we will set as the centroid
		Point newCentroid = new Point(10, 15);
		testCluster.setCentroid(newCentroid);
		assertEquals(testCluster.getCentroid(), newCentroid);
	}
	
	// Mean Test
	@Test
	public void meanTest(){
		// calculate the mean of our test cluster
		Point mean = testCluster.getClusterMean();
		// x and y values of mean should match those specified in the parameters
		assertEquals(mean.getX(), meanX, 0);
		assertEquals(mean.getY(), meanY, 0);
	}
	
	// Intra-Cluster Distance Test
	@Test
	public void intraClusterDistanceTest(){
		// get intracluster distance and test to see if it matches our parameter
		assertEquals(testCluster.getMaxIntraClusterDistance(), maxIntraDist, 0.01);
	}
	
}
