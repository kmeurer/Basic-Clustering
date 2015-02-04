package plot;

//import Point class
import point.Point;
import cluster.Cluster;
import java.util.ArrayList;

public class Plot {
	ArrayList<Point> coordinates;
	
	public Plot() {
		coordinates = new ArrayList<Point>();
	}
	
	public Plot(ArrayList<Point> coords) {
		coordinates = coords;
	}
	
	public String print(){
		String printStmt = "Plot of " + coordinates.size() + "coordinates. \n ";
		for(Point coord: coordinates){
			printStmt += coord.print() + "\n";
		}
		return printStmt;
	}

}
