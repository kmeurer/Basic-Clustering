// IMPORTS
import java.util.ArrayList;

import cluster.Cluster;
import point.Point;
import plot.Plot;
import plot.PlotProcessor;

// MAIN CLASS
public class Main {

	public static void main(String[] args) throws Exception{
		Point p1 = new Point(1, 2);
		Point p2 = new Point(4, 1);
		Point p3 = new Point(2, 5);
		Point p4 = new Point(6, 2);
		Point p5 = new Point(1, 7);
		Point p6 = new Point(14, 8);
		ArrayList<Point> coords = new ArrayList<Point>();
		coords.add(p1);
		coords.add(p2);
		coords.add(p3);
		coords.add(p4);
		coords.add(p5);
		coords.add(p6);
		Plot newPlot = new Plot(coords);
		PlotProcessor processor1 = new PlotProcessor(newPlot);
		processor1.createKMeansClusters(3, 9);
	}

}