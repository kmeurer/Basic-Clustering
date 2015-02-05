package plot;

import java.util.ArrayList;
import point.Point;
import cluster.Cluster;
import java.lang.Math;

public class PlotProcessor {
	/* PROPERTIES */
	Plot plot;
	ArrayList<String> resultsLog;
	
	/* CONSTRUCTOR(S) */
	// constructor takes a single plot and includes an overview in the results log
	public PlotProcessor(Plot unprocessedPlot) {
		// define our plot
		plot = unprocessedPlot;
		// create a results log that will allow us to reference prior tests. Useful if we wanted to run algorithm multiple times and output/compare results
		resultsLog = new ArrayList<String>();
	}
	
	// K-MEANS CLUSTERING ALGORITHM
	public ArrayList<Cluster> CreateKMeansCluster(int numOfClusters, int numOfIterations) throws Exception{
		if(numOfClusters > plot.getCoords().size()){
			throw new Exception("You have not supplied enough coordinates to create that many clusters.");
		}
		
		ArrayList<Cluster> clusters = new ArrayList<Cluster>();	// Create an arrayList to store our clusters
		ArrayList<Point> centroids = new ArrayList<Point>(); 	// Create an arrayList to store centroids (used to not repeat centroids)
		
		// Iterate to create the specified number of clusters, checking to see if we have already used the centroid
		for( int i = 0; i < numOfClusters; i++ ){
			boolean centroidTaken = true; // create bool to track if our chosen point is already taken	
			// Randomly select a centroid from our coordinates
			Point centroid = plot.getCoords().get((int) Math.floor(Math.random()));
			
			// While the centroid is taken, we test.  On first iteration. assume taken and test to be sure
			while(centroidTaken){
				centroidTaken = false; // set bool to false
				// iterate through our list of taken centroids
				for(Point takenCentroid: centroids){
					// check if our selected centroid matches any that are already taken
					if(centroid.isEqual(takenCentroid)){
						centroidTaken = true;
						centroid = plot.getCoords().get((int) Math.floor(Math.random()));
					}
				}
			}
			
			// Our centroid is not taken, so we can create a cluster with the specified centroid and add the centroid to our list of taken points
			Cluster newCluster = new Cluster(centroid);
			clusters.add(newCluster);	// add cluster
			centroids.add(centroid); 	// add centroid
		}
		return new ArrayList<Cluster>();
	}
	
	
	
}
