package plot;

import java.util.ArrayList;
import point.Point;
import cluster.Cluster;
import java.lang.Math;

public class PlotProcessor {
	/* PROPERTIES */
	private Plot plot;
	private ArrayList<String> resultsLog;
	
	/* CONSTRUCTOR(S) */
	// constructor takes a single plot and includes an overview in the results log
	public PlotProcessor(Plot unprocessedPlot) {
		// define our plot
		plot = unprocessedPlot;
		// create a results log that will allow us to reference prior tests. Useful if we wanted to run algorithm multiple times and output/compare results
		resultsLog = new ArrayList<String>();
	}
	
	/* METHODS */
	// K-MEANS CLUSTERING ALGORITHM
	// Create Clusters
	private ArrayList<Cluster> createKMeansClusters(int numOfClusters, int numOfIterations) throws Exception{
		// check to make sure parameters are valid
		if(numOfClusters > plot.getCoords().size()){
			throw new Exception("You have not supplied enough coordinates to create that many clusters.");
		}
		
		// write to results log and print starting statement.
		String startingMsg = "K means cluster creation for plot: Creating " + numOfClusters + " clusters over " + numOfIterations + " iterations.";
		resultsLog.add(startingMsg);
		System.out.println(startingMsg);
		
		ArrayList<Cluster> clusters = new ArrayList<Cluster>();	// Create an arrayList to store our clusters
		ArrayList<Point> centroids = new ArrayList<Point>(); 	// Create an arrayList to store centroids (used to not repeat centroids)
		
		// PART 1: Create Clusters by randomly selecting centroids from plot coordinates
		// Iterate to create the specified number of clusters, checking to see if we have already used the centroid
		for( int i = 0; i < numOfClusters; i++ ){
			// create bool to track if our chosen point is already taken
			boolean centroidTaken = true;
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
			Cluster newCluster = new Cluster(centroid); // create cluster
			clusters.add(newCluster);	// add cluster
			centroids.add(centroid); 	// add centroid
		}
		
		// print and write to results log the initial centroids
		String initialCentroidMsg = "Initial Centroids Selected: ";
		for(Point initialCentroid: centroids){
			initialCentroidMsg += "(" + initialCentroid.getX() + ", " + initialCentroid.getY() + ")";
		}
		resultsLog.add(initialCentroidMsg);
		System.out.println(initialCentroidMsg);
		
		
		// PART 2: Add other coordinates to clusters based on minimum distance
		// iterate through all of our coords.  For each coord...
		for(Point coord: plot.getCoords()){
			// if our point is the centroid of the first cluster, we do nothing and jump to next iteration
			if(coord == clusters.get(0).getCentroid()){
				continue;
			} 
			double minDistance = coord.distanceFrom(clusters.get(0).getCentroid());
			  
			// compare point to centroid of each Cluster
			for( int i = 1; i < clusters.size(); i++){
				
			}
		}
		
		// RETURN CLUSTERS
		return new ArrayList<Cluster>();
	}
	
	private double validateKMeansClusters(ArrayList<Cluster> clusters){
		return 1.0;
	}
	
//	// Public function to call the create and validate
//		public ArrayList<Cluster> calculateKMeans(int numOfClusters, int numOfIterations){
//			ArrayList<Cluster> clusters = createKMeansClusters(numOfClusters, numOfIterations);
//			return clusters;
//		}
	
	
	
}
