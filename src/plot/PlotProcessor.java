/*
 * PLOT PROCESSOR:  Runs all forms of analysis on our plot.  Takes a plot in its constructor and stores the plot's coordinates.
 * The primary function here is the createKMeansClusters function, which executes our algorithm
 */

package plot;

import java.util.ArrayList;
import point.Point;
import cluster.Cluster;
import java.lang.Math;

public class PlotProcessor {
	/* PROPERTIES */
	private ArrayList<Point> plotCoords;
	private ArrayList<String> resultsLog;
	
	/* CONSTRUCTOR(S) */
	// constructor takes a single plot and includes an overview in the results log
	public PlotProcessor(Plot unprocessedPlot) {
		// define our plot
		plotCoords = unprocessedPlot.getCoords();
		// create a results log that will allow us to reference prior tests. Useful if we wanted to run algorithm multiple times and output/compare results
		resultsLog = new ArrayList<String>();
	}
	
	/* METHODS */
	/*
	 *  K-MEANS CLUSTERING ALGORITHM
	 */
	// Main Function: Create Clusters
	public ArrayList<Cluster> createKMeansClusters(int numOfClusters, int numOfIterations) throws Exception{
		// PART 0: Validate Parameters and declare necessary variables
		if(numOfClusters > plotCoords.size()){
			throw new Exception("You have not supplied enough coordinates to create that many clusters.");
		}
		
		// write to results log and print starting statement.
		String startingMsg = "\n\nK means cluster creation for plot: Creating " + numOfClusters + " clusters over " + numOfIterations + " iterations.";
		resultsLog.add(startingMsg);
		System.out.println(startingMsg);

		
		ArrayList<Cluster> clusters = new ArrayList<Cluster>();				// Create an arrayList to store our clusters
		ArrayList<Point> centroids = new ArrayList<Point>(); 				// Create an arrayList to store centroids (used to not repeat centroids)
		ArrayList<Point> coords = new ArrayList<Point>(plotCoords); 	// Make a copy of our plot coordinates
		
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
			
			int count = 0; // used to handle exceptions related to infinite looping
			
			// While the selected centroid is taken, we test.  On first iteration. assume taken and test to be sure
			while(centroidTaken){
				count++; // increment count
				if(count > coords.size() * 100){ // if we have gone through all options 100 times, statistically it would infinitely loop
					throw new Exception("Not enough distinct centroids to complete clustering");
				}
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
		 * 			Here, we recalculate the centroid using the built in cluster function and reassign it as the centroid
		 */
		// create a clusterCopy so we can check for stabilization
		boolean clusterStabilized = true;
		for(int i = 1; i < numOfIterations; i++){
			// set clusterStabilized as true
			clusterStabilized = true;
			
			// FOR EACH CLUSTER: Calculate mean point, assign as centroid, clear other coords
			for(Cluster cluster: clusters){
				Point mean = cluster.getClusterMean();	// get cluster mean
				if(mean.isEqual(cluster.getCentroid()) == false){ // if our new centroid is different than the old centroid, not stabilized yet
					clusterStabilized = false;
				}
				cluster.setCentroid(mean);				// set centroid as mean of all points
				cluster.clearClusterPoints();			// clear cluster points
			}
			
			// assign all of our coordinates to our clusters (because it is all of them, we use the coords directly from the plot)
			assignCoordsToNearestCluster(plotCoords, clusters);
			
			// Add to report log and print results of each iteration
			// Add reporting to the results Log
			String iterationNMsg = "Iteration " + (i + 1) + ":\nNew Centroids: ";
			for(Cluster cluster: clusters){
				iterationNMsg += cluster.getCentroid().getPointAsString() + " ";
			}
			iterationNMsg += "\nNew Clusters: \n";
			for(int j = 0; j < clusters.size(); j++){
				iterationNMsg += "\tCluster " + (j + 1) + ": " + clusters.get(j).getCoordsAsString() + "\n";
			}
			resultsLog.add(iterationNMsg);
			System.out.println(iterationNMsg);
			// if the cluster is stabilized, we print this and break the loop of iterations
			if(clusterStabilized){
				resultsLog.add("Cluster Stabilization Detected.  Terminating after " + (i + 1) + " iterations.\n");
				System.out.println("Cluster Stabilization Detected.  Terminating after " + (i + 1) + " iterations.\n");
				break;
			}
		}
		
		// Add to results log
		resultsLog.add("Clustering complete.  Beginning cluster validation using maximum intra-cluster distance.");
		System.out.println("Clustering complete.  Beginning cluster validation using maximum intra-cluster distance.");
		
		/*
		 * PART 4: Validate clusters using maximum intra-cluster distance method.  The logic for this is captured in a separate function
		 */
		validateClusters(clusters);
		
		// RETURN CLUSTERS
		return clusters;
	}
	
	/*
	 *  Assign a group of points to a group of clusters based on minimum distance.  Function takes an arraylist of Coordinates and an arraylist of clusters
	 */
	public void assignCoordsToNearestCluster(ArrayList<Point> coordinates, ArrayList<Cluster> clusters){
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
	
	// Calculates and outputs the maximum intra-cluster distance for a given cluster.
	public double validateClusters(ArrayList<Cluster> clusters){
		double maxDistance = 0; 								// used to store max intracluster distance amongst all our clusters
		String validationMsg = "Maximum intra-cluster distances: \n";	// create a message to output
		// calculate maximum intra-cluster distance for each cluster, print it, and store them
		for( int i = 0; i < clusters.size(); i++){
			// calculate max distance
			double dist = clusters.get(i).getMaxIntraClusterDistance();	// get maximum intra cluster distance
			if( dist > maxDistance){
				maxDistance = dist;										// update our max distance if this cluster has a greater max distance
			}
			// add result to validation message
			validationMsg += "\tCluster " + (i + 1) + ": " + clusters.get(i).getCoordsAsString() + "\n\tMax Distance: " + dist + "\n\n";
		}
		
		// add result to our reporter
		validationMsg += "Maximum intra-cluster distance amongst all clusters: " + maxDistance;
		
		// update our resultsLog and print
		resultsLog.add(validationMsg);
		System.out.println(validationMsg);
		// return maximum intra-cluster distance
		return maxDistance;
	}
	
	// Prints results log.  Useful if we wanted to create clusters multiple times and store the results
	public void printResultsLog(){
		for(String result: resultsLog){
			System.out.println(result);
		}
	}
	
	// clear the results log
	public void clearResultsLog(){
		resultsLog.clear();
	}
}
