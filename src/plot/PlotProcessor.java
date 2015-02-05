package plot;

import java.util.ArrayList;

public class PlotProcessor {
	/* PROPERTIES */
	Plot plot;
	ArrayList<String> resultsLog;
	
	/* CONSTRUCTOR(S) */
	// constructor takes a single plot and includes an overview in the results log
	public PlotProcessor(Plot unprocessedPlot) {
		// define our plot
		plot = unprocessedPlot;
		// create a results log that will allow us to reference prior tests. Useful if we wanted to run algorithm multiple times and compare results
		resultsLog = new ArrayList<String>();
		String overviewStatement = "Results Log for: " + plot.getOverview();
		resultsLog.add(overviewStatement);
	}
	
	
}
