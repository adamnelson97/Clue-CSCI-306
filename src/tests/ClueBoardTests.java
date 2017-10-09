package tests;

import static org.junit.Assert.*;

import clueGame.*;
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
	
	@Test
	public void testDoorwayDirections() {
		// Test doorways in each direction, as well as cells with no doorways.
		BoardCell door = board.getCellAt(4, 2); // Kitchen door (Down)
		assertEquals(DoorDirection.DOWN, door.getDoorDirection());
		door = board.getCellAt(14, 2); // Ballroom door (Left)
		assertEquals(DoorDirection.LEFT, door.getDoorDirection());
		door = board.getCellAt(3,22); // Library door (Right)
		assertEquals(DoorDirection.RIGHT, door.getDoorDirection());
		door = board.getCellAt(17, 20); // Master Bedroom door (Up)
		assertEquals(DoorDirection.UP, door.getDoorDirection());
		door = board.getCellAt(0, 10); // Cell in Servant's Quarters (None)
		assertEquals(DoorDirection.NONE, door.getDoorDirection());
		door = board.getCellAt(19, 11); // Cell in Art Room (None)
		assertEquals(DoorDirection.NONE, door.getDoorDirection());
	}
	
	@Test
	public void testRoomInitials() {
		// Test a cell from each room to make sure they have the proper initials.
		BoardCell cell = board.getCellAt(0, 0); // Kitchen (K)
		assertEquals('K', cell.getInitial());
		cell = board.getCellAt(10, 0); // Conservatory (C)
		assertEquals('C', cell.getInitial());
		cell = board.getCellAt(19, 0); // Ballroom (B)
		assertEquals('B', cell.getInitial());
		cell = board.getCellAt(0, 12); // Servant's Quarters (Q)
		assertEquals('Q', cell.getInitial());
		cell = board.getCellAt(19, 11); // Art Room (A)
		assertEquals('A', cell.getInitial());
		cell = board.getCellAt(19, 16); // Trophy Room (R)
		assertEquals('R', cell.getInitial());
		cell = board.getCellAt(0, 19); // Library (L)
		assertEquals('L', cell.getInitial());
		cell = board.getCellAt(11, 19); // Theatre (T)
		assertEquals('T', cell.getInitial());
		cell = board.getCellAt(19, 19); // Master Bedroom (M)
		assertEquals('M', cell.getInitial());
	}

} //End of Class
