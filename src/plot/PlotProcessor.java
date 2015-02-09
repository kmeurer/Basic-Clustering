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
	public ArrayList<Cluster> createKMeansClusters(int numOfClusters, int numOfIterations) throws Exception{
		// PART 0: Validate Parameters and declare necessary variables
		if(numOfClusters > plot.getCoords().size()){
			throw new Exception("You have not supplied enough coordinates to create that many clusters.");
		}
		
		// write to results log and print starting statement.
		String startingMsg = "\n\nK means cluster creation for plot: Creating " + numOfClusters + " clusters over " + numOfIterations + " iterations.";
		resultsLog.add(startingMsg);
		System.out.println(startingMsg);
		
		ArrayList<Cluster> clusters = new ArrayList<Cluster>();				// Create an arrayList to store our clusters
		ArrayList<Point> centroids = new ArrayList<Point>(); 				// Create an arrayList to store centroids (used to not repeat centroids)
		ArrayList<Point> coords = new ArrayList<Point>(plot.getCoords()); 	// Make a copy of our plot coordinates
		
		/*
		 * PART 1: Create Clusters by randomly selecting centroids from plot coordinates
		 */
		// create first centroid and push it
		int randomIdx = (int) Math.floor(Math.random() * coords.size());
		Point firstCentroid = coords.get(randomIdx);
		Cluster firstCluster = new Cluster(firstCentroid); // create cluster
		coords.remove(randomIdx);   // remove coord from list -- it is now already assigned
		clusters.add(firstCluster);	// add cluster
		centroids.add(firstCentroid); 	// add centroid
		
		// Iterate from 1 to create the specified number of clusters, checking to see if we have already used the centroid
		for( int i = 1; i < numOfClusters; i++ ){
			randomIdx = (int) Math.floor(Math.random() * coords.size());	// Randomly select a centroid from our coordinates
			Point centroid = coords.get(randomIdx);	// Get the coordinate at that index
	
			// create bool to track if our chosen point is already taken
			boolean centroidTaken = true;
			
			// While the selected centroid is taken, we test.  On first iteration. assume taken and test to be sure
			while(centroidTaken){
				centroidTaken = false; // set bool to false so we can break if not taken
				// iterate through our list of taken centroids to see if it matches new centroid
				for(Point takenCentroid: centroids){
					// check if our selected centroid matches any that are already taken
					if(centroid.isEqual(takenCentroid)){
						centroidTaken = true;
						centroid = coords.get((int) Math.floor(Math.random()));
						centroid.print();
					} else {
						break;
					}
				} // end of for loop
			} // end of while loop
			
			// Our centroid is not taken, so we can create a cluster with the specified centroid and add the centroid to our list of taken points
			Cluster newCluster = new Cluster(centroid); // create cluster
			coords.remove(randomIdx);   // remove coord from list -- it is now already assigned
			clusters.add(newCluster);	// add cluster
			centroids.add(centroid); 	// add centroid
		} // end of initial centroid selection
		
		// print and write to results log the initial centroids
		String initialCentroidMsg = "\nInitial Centroids Selected: ";
		for(Point initialCentroid: centroids){
			initialCentroidMsg += "(" + initialCentroid.getX() + ", " + initialCentroid.getY() + ") ";
		}
		resultsLog.add(initialCentroidMsg);
		System.out.println(initialCentroidMsg);
		
		/*
		 *  PART 2: Add other coordinates to clusters based on minimum distance
		 */
		// We call our assigning function to add the rest of our points to the clusters. We use our coords arraylist because it has removed all centroids, which are already in the list
		assignCoordsToNearestCluster(coords, clusters);
		
		// Add reporting to the results Log
		String iteration1Msg = "Iteration 1 Clusters: \n";
		for(int i = 0; i < clusters.size(); i++){
			iteration1Msg += "\tCluster " + (i + 1) + ": " + clusters.get(i).getCoordsAsString() + "\n";
		}
		resultsLog.add(iteration1Msg);
		System.out.println(iteration1Msg);
		
		/*
		 * PART 3: Iterate n - 1 number of times across our clusters(stored in the clusters arraylist) to fine-tune our clusters
		 */
		for(int i = 1; i < numOfIterations; i++){
			
		}
		
		
		// RETURN CLUSTERS
		return new ArrayList<Cluster>();
	}
	
	/*
	 *  Assign a group of points to a group of clusters based on minimum distance.  Function takes an arraylist of Coordinates and an arraylist of clusters
	 */
	private void assignCoordsToNearestCluster(ArrayList<Point> coordinates, ArrayList<Cluster> clusters){
		// for every coordinate, we compare the point to the centroid of each cluster
		for(Point coord: coordinates){
			// tuple used to store [minDistance, indexOfClusterWithMinDistance]
			double[] minDistance = {coord.distanceFrom(clusters.get(0).getCentroid()), 0};
			// compare point to centroid of each Cluster, updating minDistance as needed
			for( int i = 1; i < clusters.size(); i++){
				// calculate distance from cluster centroid and point
				double distance = coord.distanceFrom(clusters.get(i).getCentroid());
				// if distance from centroid is less than prior min, set new minDistance
				if( distance < minDistance[0] ){
					minDistance[0] = distance;
					minDistance[1] = i;
				}
			}
			// add point to the cluster with the closest centroid/minimum distance
			clusters.get((int) minDistance[1]).addPoint(coord);
		}
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
