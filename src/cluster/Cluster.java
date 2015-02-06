// declare package name
package cluster;

// import Point class
import point.Point;
import java.util.ArrayList;

// main cluster class.  Used to store all data for instantiated clusters
public class Cluster {
	/* PROPERTIES */
	Point centroid; 					//Used to store centroid
	ArrayList<Point> clusterPoints; 	// ArrayList to store points
	
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
	
	/* METHODS */
	// Getter for centroid
	public Point getCentroid(){
		return centroid;
	}
	
	// add a single point to the cluster
	public void addPoint(Point pointToAdd){
		// push new point to ArrayList
		clusterPoints.add(pointToAdd);
	}
	
	// add multiple points to the cluster
	public void addPoints(ArrayList<Point> points){
		// iterate through array list and add all points to the cluster
		for(Point newPoint: points){
			clusterPoints.add(newPoint);
		}
	}
	
	public String getCoordsAsString(){
		String coords = "";
		for( Point coord: clusterPoints ){
			coords += "(" + coord.getX() + ", " + coord.getY() + ") ";
		}
		return coords;
	}
}
