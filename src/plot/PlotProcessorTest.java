package plot;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import point.Point;
import cluster.Cluster;

@RunWith(Parameterized.class)
public class PlotProcessorTest {
	// Establish Parameters for cluster testing. Format {centroidX, centroidY, x2, y2, x3, y3, x4, y4, max-intra-distance, meanX, meanY}
	@Parameters
    public static Collection<Object[]> getTestParameters() {
        return Arrays.asList(new Object[][] {     
             { 10, 7, 1, 4, 10, 9, 11, 17, 13, 4, 17, 2 }, 	// x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6
             { 5, -2, 4, 1, 5, 17, 10, 16, 2, 9, 6, 37 }, 	// x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6
             { 57, 8, 3, 22, 16, 12, 14, 1, -7, 6, 1, 4 }	// x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6
       });
    }
	
	// Create a test cluster to be used for testing functionality
	Point p1;
	Point p2;
	Point p3;
	Point p4;
	Point p5;
	Point p6;
	ArrayList<Point> coords;
	Plot testPlot;
	
	// Test Constructor: assigns parameters to variables
	public PlotProcessorTest(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4, double x5, double y5, double x6, double y6){
		p1 = new Point(x1, y1);
		p2 = new Point(x2, y2);
		p3 = new Point(x3, y3);
		p4 = new Point(x4, y4);
		p5 = new Point(x5, y5);
		p6 = new Point(x6, y6);
		// store our coords
		coords = new ArrayList<Point>();
		coords.add(p1);
		coords.add(p2);
		coords.add(p3);
		coords.add(p4);
		coords.add(p5);
		coords.add(p6);
		// create a plot from our coords.  Also instantiates a plot processor
		testPlot = new Plot(coords);
	}
	
	@Before
	public void setUp() {
		testPlot.processor.clearResultsLog();
	}
	
	// tests an exception that is thrown when the user inputs more clusters than we have points
	@Test(expected = Exception.class)
	public void clusterValidationExceptionTest() throws Exception {
		testPlot.processor.createKMeansClusters(7, 3);
	}
	
	// Tests an exception that is thrown when we do not have enough distinct centroids to create clusters 
	@Test(expected = Exception.class)
	public void distinctCentroidExceptionTest() throws Exception {
		ArrayList<Point> testCoords = new ArrayList<Point>();
		testCoords.add(new Point(1,2));
		testCoords.add(new Point(1,2));
		Plot simplePlot = new Plot(testCoords);
		simplePlot.processor.createKMeansClusters(2, 5);
	}
	
	// Tests to be sure that the function returns the number of clusters requested
	@Test
	public void clusterCountTest() {
		try {
			assertEquals(testPlot.processor.createKMeansClusters(4, 4).size(), 4);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Tests to be sure that the validator returns an accurate largest intra-cluster distance
	@Test
	public void clusterValidationTest() {
		try {
			ArrayList<Cluster> clusters = testPlot.processor.createKMeansClusters(3, 4);
			double functionLargestDist = testPlot.processor.validateClusters(clusters); // get largest according to the function;
			double actualLargestDist = 0;
			for( Cluster clust: clusters ){
				if ( clust.getMaxIntraClusterDistance() > actualLargestDist){
					actualLargestDist = clust.getMaxIntraClusterDistance();
				}
			}
			assertEquals(actualLargestDist, functionLargestDist, 0.001);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
