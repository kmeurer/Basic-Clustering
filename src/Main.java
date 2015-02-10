/*
 * Project 1
 * Name: Kevin Meurer
 * E-Mail: kevin.a.meurer@gmail.com
 * Instructor: Singh
 * COSC 150
 * 
 * Honor Statement: In accordance with the class policies and Georgetown's Honor Code. I certify that, with the exceptions
 * of the lecture notes and those items noted below, I have neither given nor received any assistance on this project
 * 
 * Description:  Welcome to my program!  You're looking at my main runner here.  This file processes the file inputs and parses them.
 * Once it has the information, it instantiates a point object for each point, which is then fed to my plot class.  The plot class doesn't 
 * do much except store our points(it would conceivably be useful if we wanted to actually show the clusters to the user).  In any case, the plot
 * is passed as an argument to our plotProcessor, which creates and validates our clusters.  I separated the plot processor because I wanted to 
 * maintain separation of concerns, and it made sense to me to store results in a distinct results log.  Our p;ot processor then gives us all the results
 * you'd expect from a program like this while storing a log of everything it does!
 * 
 * Thank you for grading my project!
 */


// IMPORTS
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import point.Point;
import plot.Plot;

// MAIN CLASS
public class Main {
	
	private static ArrayList<Point> readPoints(String filePath){
		ArrayList<Point> filePoints = new ArrayList<Point>();  // arraylist to store our points
		try {
			// create a file object
			File file = new File(filePath);
			BufferedReader reader = new BufferedReader(new FileReader (file)); // 
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] vals = line.split(",");
				Point newPoint = new Point(vals);
				filePoints.add(newPoint);
			}
			
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePoints;	
	}
	
	public static void main(String[] args) throws Exception{
		ArrayList<Point> coords = readPoints(args[0]);
		Plot newPlot = new Plot(coords);
		newPlot.processor.createKMeansClusters(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
	}

}