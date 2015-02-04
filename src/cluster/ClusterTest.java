package cluster;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import point.Point;

public class ClusterTest {
	// Create a test cluster to be used for testing functionality
	Cluster testCluster;
	
	@Before
	public void setUp() throws Exception {
		Point testCentroid = new Point(3, 6);
		testCluster = new Cluster(testCentroid);
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
