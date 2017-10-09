package tests;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

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
	
	@Test //ensure there is the correct number of doors according to the layout using a hardcoded value
	public void testNumDoors() {
		int numDoors = 0;
		BoardCell cell;
		
		for (int i = 0; i < board.getNumRows(); i++) {
			for (int j = 0; j < board.getNumColumns(); j++) {
				cell = board.getCellAt(i, j);
				if (cell.isDoorway()) numDoors++;
			}
		}
		assertEquals(17, numDoors); //Tests that the number of doors is correct after each cell has been checked
	}
	

} //End of Class
