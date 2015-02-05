package plot;

import java.util.ArrayList;
import point.Point;
import java.lang.Math;

import cluster.Cluster;

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
	
	// 
	public ArrayList<Cluster> CreateKMeansCluster(int numOfClusters, int numOfIterations){
		// Create an arrayList to store all of our clusters
		ArrayList<Cluster> clusters = new ArrayList<Cluster>();
		// Iterate to create the specified number of clusters, checking to see if we have already used the centroid
		for( int i = 0; i < numOfClusters; i++ ){
			Point centroid = plot.getCoords().get((int) Math.floor(Math.random()));
			Cluster newCluster = new Cluster(centroid);
			clusters.add(newCluster);
		}
		return new ArrayList<Cluster>();
	}
	
	
	
}
