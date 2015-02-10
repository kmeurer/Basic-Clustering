import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

/*
 *  Simple test suite used to test file input
 */

public class MainTest {

	// Ensure that file input function can process data
	@Test
	public void fileInputTest() throws Exception {
		// should be able to extract points from file
		assertEquals(Main.readPoints("test.txt").size(), 5);	
	}
	
	// ensure that it properly throws an exception when the file is not found
	@Test(expected = FileNotFoundException.class)
	public void fileNotFoundExceptionTest() throws Exception {
		Main.readPoints("nonexistent.txt");
	}
}
