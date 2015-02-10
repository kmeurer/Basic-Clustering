package plot;

import static org.junit.Assert.*;

import point.Point;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class PlotTest {

	// create a plot and a list of coords
	ArrayList<Point> coords;
	Plot testPlot;
	
	@Before
	public void setUp() throws Exception {
		coords = new ArrayList<Point>();
		coords.add(new Point(10, 12));
		coords.add(new Point(5, 3));
		testPlot = new Plot(coords);
	}

	@Test
	public void gettersTest() {
		assertEquals(coords, testPlot.getCoords());
	}

}
