package cluster;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import point.Point;

public class ClusterTest {
	// Create a test cluster to be used for testing functionality
	Cluster testCluster;
	Point testCentroid;
	
	@Before
	public void setUp() throws Exception {
		testCentroid = new Point(3, 6);
		testCluster = new Cluster(testCentroid);
	}
	
	// Test constructors
	@Test
	public void constructorTests() {
		Cluster test1 = new Cluster();
		Point testPoint = new Point();
		Cluster test2 = new Cluster(testPoint);
		assertEquals(test2.getCentroid(), testPoint);
	}
	
	// Getters Test
	@Test
	public void gettersTests(){
		assertEquals(testCluster.getCentroid(), testCentroid);
	}

}
