package plot;

//import Point class
import point.Point;

import java.util.ArrayList;

public class Plot {
	ArrayList<Point> coordinates; // used to store plot coordinates
	PlotProcessor processor; // used to perform all analytics and log results
	
	public Plot() {
		coordinates = new ArrayList<Point>();
		processor = new PlotProcessor(this);
	}
	
	public Plot(ArrayList<Point> coords) {
		coordinates = coords;
		processor = new PlotProcessor(this);
	}
	
	// Used to print an overview of our plot
	public void print(){
		// Create a print Statement to return
		System.out.println("Plot of " + coordinates.size() + " coordinates.\n ");
		// list coordinates
		for(Point coord: coordinates){
			coord.print();
		}
	}
	
	public ArrayList<Point> getCoords(){
		return coordinates;
	}
	
}
