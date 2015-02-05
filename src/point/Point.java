package point;

import java.lang.Math;

public class Point {
	/* CLASS PROPERTIES */
	private int x; // x-value
	private int y; // y-value
	
	/* CONSTRUCTORS */
	// default constructor sets point to (0, 0)
	public Point() {
		x = 0;
		y = 0;
	}
	
	// common constructor takes an x and a y value
	public Point(int xVal, int yVal){
		// set x and y values
		x = xVal;
		y = yVal;
	}
	
	/* CLASS METHODS */
	// Print coord
	public String getPointAsString(){
		return "(" + x + ", " + y + ")";
	}
	
	// getter for x
	public int getX(){
		return x;
	}
	
	// getter for y
	public int getY(){
		return y;
	}
	
	// return a tuple of both coordinates [x, y]
	public int[] getCoords(){
		int[] coords = {x, y}; // create an array to return
		return coords;
	}
	
	// setter for x
	public int setX(int newX){
		x = newX;
		return x; // return new value
	}
	
	// setter for y
	public int setY(int newY){
		y = newY;
		return y; // return new value
	}
	
	// isEqual: boolean function that returns true if two points have equal values
	public boolean isEqual(Point testPoint){
		if( testPoint.getX() == x && testPoint.getY() == y ){
			return true;
		}
		return false;
	}
	
	// distanceFrom: returns a double of the distance between two integers
	public double distanceFrom(Point point2){
		// returns the result of the Euclidian distance eqn: sqrt((x1 - x2)^2 + (y1 - y2)^2)
		double xVals = x - point2.getX();  // takes up more space but clearer
		double yVals = y - point2.getY();
		return Math.sqrt( Math.pow(xVals, 2) + Math.pow(yVals, 2) );
	}

}
