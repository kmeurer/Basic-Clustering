
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
	

}
