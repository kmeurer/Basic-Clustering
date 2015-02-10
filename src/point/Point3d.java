package point;

public class Point3d extends Point {
	/* PROPERTIES */
	// inherits x and y from Point
	private double z;
	
	/* Constructors */
	public Point3d(){
		x = 0;
		y = 0;
		z = 0;
	}
	
	public Point3d(double xVal, double yVal, double zVal) {
		x = xVal;
		y = yVal;
		z = zVal;
	}
	
	/* METHODS */
	// inherits getX() and getY() from Point
	// get z value
	public double getZ(){
		return z;
	}
	
	// set z value
	public double setZ(double newZ){
		z = newZ;
		return z;
	}
	
	// METHOD OVERRIDES
	// Print coord
	public void print(){
		System.out.println("(" + x + ", " + y + ", " + z + ")");
	}
	
	// Get coord as String
	public String getPointAsString(){
		return "(" + x + ", " + y + ", " + z + ")";
	}
	
	// return an array of our coordinates [x, y, z]
	public double[] getCoords(){
		double[] coords = {x, y, z}; // create an array to return
		return coords;
	}
	
	// isEqual: boolean function that returns true if two points have equal values
	public boolean isEqual(Point3d testPoint){
		if( testPoint.getX() == x && testPoint.getY() == y && testPoint.getZ() == z ){
			return true;
		}
		return false;
	}
	
	// distanceFrom: returns a double of the distance between two integers
	public double distanceFrom(Point3d point2){
		// returns the result of the Euclidian distance eqn: sqrt((x1 - x2)^2 + (y1 - y2)^2)
		double xVals = x - point2.getX();  // takes up more space but clearer
		double yVals = y - point2.getY();
		double zVals = z - point2.getZ();
		return Math.sqrt( Math.pow(xVals, 2) + Math.pow(yVals, 2) + Math.pow(zVals, 2));
	}
	
}
