// declare package name
package cluster;

// import Point class
import point.Point;
import java.util.ArrayList;

// main cluster class.  Used to store all data for instantiated clusters
public class Cluster {
	/* PROPERTIES */
	// Used to store centroid
	Point centroid;
	
	ArrayList<Point> clusterPoints;
	
	/* CONSTRUCTORS */
	// default constructor
	public Cluster() {
		// Set centroid to (0, 0)
		centroid = new Point(0, 0);
		// Instantiate new ArrayList to store our points
		clusterPoints = new ArrayList<Point>();
		clusterPoints.add(centroid);
	}
	// primary constructor
	public Cluster(Point startingCentroid){
		centroid = startingCentroid;
		// Instantiate new ArrayList to store our points
		clusterPoints = new ArrayList<Point>();
		clusterPoints.add(centroid);
	}

}
