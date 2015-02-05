package plot;

//import Point class
import point.Point;
import java.util.ArrayList;

public class Plot {
	ArrayList<Point> coordinates; // used to store plot coordinates
	PlotProcessor processor; // used to perform all analytics and log results
	
	public Plot() {
		coordinates = new ArrayList<Point>();
	}
	
	public Plot(ArrayList<Point> coords) {
		coordinates = coords;
	}
	
	// Used to print an overview of our plot
	public String getOverview(){
		// Create a print Statement to return
		String printStmt = "Plot of " + coordinates.size() + "coordinates.\n ";
		// list coordinates
		for(Point coord: coordinates){
			printStmt += coord.getPointAsString() + "\n";
		}
		// return statement to be printed
		return printStmt;
	}

}
