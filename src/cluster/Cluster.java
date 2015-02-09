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
	
	// Setter for centroid
	public Point setCentroid(Point newCentroid){
		centroid = newCentroid;
		return centroid;
	}
	
	// getter for all coords
	public ArrayList<Point> getCoords(){
		return clusterPoints;
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
	
	// Calculates the mean of all points in the cluster
	public Point getClusterMean(){
		double xMean = 0;  // value used to store sum of all x values and ultimately the mean of all x values
		double yMean = 0;  // value used to store sum of all y values and ultimately the mean of all y values
		for( Point coord: clusterPoints ){
			// store sum of all x values and y values
			xMean += coord.getX();
			yMean += coord.getY();
		}
		xMean /= clusterPoints.size(); // divide sum by number of elements
		yMean /= clusterPoints.size(); // divide sum by number of elements
		Point mean = new Point(xMean, yMean);
		return mean;
	}
	
	public double getMaxIntraClusterDistance(){
		double maxDist = 0;
		double currentDist = 0; 	// allocate memory space for current distance
		Point currentPoint; 		// allocate memory space for iteration current point to avoid recreating the variable
		// compare each point to all the other points in our array, starting at i + 1, storing the maximum intra-cluster distance
		for( int i = 0; i < clusterPoints.size() - 1; i++ ){
			currentPoint = clusterPoints.get(i); // get current point
			// compare each point to all the other points
			for( int j = i + 1; j< clusterPoints.size(); j++ ){
				// store distance between this point and the next
				currentDist = currentPoint.distanceFrom(clusterPoints.get(j));
				if(currentDist > maxDist){
					maxDist = currentDist;
				}
			}
		}
		// return maximum distance
		return maxDist;
	}
	
	// Removes all points from a given cluster.  Leaves centroid the same(the defining characteristic of a centroid)
	public void clearClusterPoints(){
		// Clear our cluster points
		clusterPoints.clear();
	}
	
	// returns all of the cluster coordinates as a string.  Used for printing.
	public String getCoordsAsString(){
		String coords = "";
		for( Point coord: clusterPoints ){
			coords += "(" + coord.getX() + ", " + coord.getY() + ") ";
		}
		return coords;
	}
}
