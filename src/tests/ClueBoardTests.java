package tests;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;

public class ClueBoardTests {

	public static final int LEGEND_SIZE = 11;
	public static final int NUM_ROWS = 20;
	public static final int NUM_COLUMNS = 25;
	
	private static Board board;
	
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueGameLayout.csv", "ClueGameLegend.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
	}
	
	@Test //Test that the rooms were properly loaded according to the legend
	public void testRooms() {
		Map<Character, String> legend = board.getLegend();
		assertEquals(LEGEND_SIZE, legend.size());
		assertEquals("Art Room", legend.get('A'));
		assertEquals("Kitchen", legend.get('K'));
		assertEquals("Master Bedroom", legend.get('M'));
		assertEquals("Closet", legend.get('X'));
		assertEquals("Walkway", legend.get('W'));
	}
	
	@Test //Test that the correct dimensions were loaded
	public void testBoardDim() {
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());
	}
	
	

} //End of Class
