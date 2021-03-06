/*
 * POINT CLASS:  Stores two dimensional points.  Also useful for calculating euclidian distance between points
 */
package point;

import java.lang.Math;

public class Point {
	/* PROPERTIES */
	// using doubles for precision.  Made them protected in case we wanted to inherit
	protected double x; // x-value
	protected double y; // y-value
	
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
	
	public Point(double xVal, double yVal){
		// set x and y values
		x = xVal;
		y = yVal;
	}
	
	public Point(String[] vals){
		x = Double.parseDouble(vals[0]);
		y = Double.parseDouble(vals[1]);
	}
	
	/* CLASS METHODS */
	// Print coord
	public void print(){
		System.out.println("(" + x + ", " + y + ")");
	}
	
	// Get coord as String
	public String getPointAsString(){
		return "(" + x + ", " + y + ")";
	}
	
	// getter for x
	public double getX(){
		return x;
	}
	
	// getter for y
	public double getY(){
		return y;
	}
	
	// return a tuple of both coordinates [x, y]
	public double[] getCoords(){
		double[] coords = {x, y}; // create an array to return
		return coords;
	}
	
	// setter for x
	public double setX(double newX){
		x = newX;
		return x; // return new value
	}
	
	// setter for y
	public double setY(double newY){
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
