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
 * Description:  Welcome to my program!  You're looking at my main file here.  This file processes the file inputs and parses them.
 * Once it has the information, it instantiates a point object for each point, which is then fed to my plot class.  The plot class doesn't 
 * do much except store our points(it would conceivably be useful if we wanted to actually show the clusters to the user).  In any case, the plot
 * has its own plot processor (a separate class), which creates and validates our clusters.  I separated the plot processor because I wanted to 
 * maintain separation of concerns, and it made sense to me to store results in a distinct results log.  Our plot processor then gives us all the results
 * you'd expect from a program like this while storing a log of everything it does!
 * 
 * In terms of extra functionality, I made my clustering algorithm automatically stop once the clusters have stabilized.
 * 
 * Sources: Parameterized testing was pulled from the junit docs. All other material was pulled from the lecture notes and assignment description.
 * 
 * Thank you for grading my project!
 */

// IMPORTS
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import point.Point;
import plot.Plot;

// MAIN CLASS
public class Main {
	/* METHODS */
	// method to read points from a file
	public static ArrayList<Point> readPoints(String filePath) throws Exception{
		ArrayList<Point> filePoints = new ArrayList<Point>();  // arraylist to store our points
		try {
			// create a file object
			File file = new File(filePath);
			BufferedReader reader = new BufferedReader(new FileReader (file)); // create a buffered reader
			String line = null;
			while ((line = reader.readLine()) != null) { // while there are more lines to read...
				String[] vals = line.split(",");
				Point newPoint = new Point(vals);
				filePoints.add(newPoint);
			}
			
			reader.close(); // close our reader
		} catch (FileNotFoundException e) { // throw if file not found
			e.printStackTrace();
			throw e;
		} catch (IOException e) { 			// throw if error reading lines
			e.printStackTrace();
			throw e;
		} catch (Exception e) { 			// catch-all exception
			e.printStackTrace();
			throw e;
		}
		return filePoints; // return a list of our points
	}
	
	public static void main(String[] args) throws Exception{
		ArrayList<Point> coords = readPoints(args[0]); 	//instantiate list of points from our points.txt file
		Plot newPlot = new Plot(coords);				// create a plot based on our points
		newPlot.processor.createKMeansClusters(Integer.parseInt(args[1]), Integer.parseInt(args[2])); //create clusters using our plot processor
		// end of main
	}

}