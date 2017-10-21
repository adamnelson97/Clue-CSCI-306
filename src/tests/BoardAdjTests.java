package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import clueGame.Board;
import clueGame.BoardCell;

public class BoardAdjTests {

	//Constructor
	private static Board board;
	private Set<BoardCell> adj;
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance and initialize it		
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueGameLayout.csv", "ClueGameLegend.txt");		
		board.initialize();
	}
	
	// Tests for only walkways adjacent
	@Test
	public void testWalkways() {
		adj = board.getAdjList(4, 5);
		// Check if adj contains exactly 4 cells
		assertEquals(adj.size(), 4);
		
		// Check if they are the correct cells
		assertTrue(adj.contains(board.getCellAt(3, 5)));
		assertTrue(adj.contains(board.getCellAt(5, 5)));
		assertTrue(adj.contains(board.getCellAt(4, 4)));
		assertTrue(adj.contains(board.getCellAt(4, 6)));
	}
	
	// Tests for locations within rooms
	@Test
	public void testInsideRoom() {
		adj = board.getAdjList(10, 0);
		
		// Check if empty
		assertTrue(adj.isEmpty());
		
		adj = board.getAdjList(11, 19);
		
		// Check if empty
		assertTrue(adj.isEmpty());
	}
	
	// Tests for edges
	@Test
	public void testBoardEdges() {
		adj = board.getAdjList(0, 6); // Test top edge
		
		assertEquals(adj.size(), 2);
		
		assertTrue(adj.contains(board.getCellAt(0, 5)));
		assertTrue(adj.contains(board.getCellAt(1, 5)));
		
		adj = board.getAdjList(14, 0); // Test left edge
		
		assertEquals(adj.size(), 2);
		
		assertTrue(adj.contains(board.getCellAt(15, 0)));
		assertTrue(adj.contains(board.getCellAt(1, 14)));
		
		adj = board.getAdjList(8, 19); // Test right edge
		
		assertEquals(adj.size(), 2);
		
		assertTrue(adj.contains(board.getCellAt(7, 19)));
		assertTrue(adj.contains(board.getCellAt(8, 18)));
		
		adj = board.getAdjList(24, 13); // Test bottom edge
		
		assertEquals(adj.size(), 3);
		
		assertTrue(adj.contains(board.getCellAt(24, 12)));
		assertTrue(adj.contains(board.getCellAt(24, 14)));
		assertTrue(adj.contains(board.getCellAt(23, 13)));
	}
	
	// Tests for locations next to rooms
	@Test
	public void testRoomEdges() {
		adj = board.getAdjList(13, 5); // Test location adjacent to Servant's Quarters
		
		assertEquals(adj.size(), 3);
		
		assertTrue(adj.contains(board.getCellAt(12, 5)));
		assertTrue(adj.contains(board.getCellAt(13, 6)));
		assertTrue(adj.contains(board.getCellAt(14, 5)));
		
		adj = board.getAdjList(15, 14); // Test location adjacent to Trophy Room
		
		assertEquals(adj.size(), 3);
		
		assertTrue(adj.contains(board.getCellAt(14, 14)));
		assertTrue(adj.contains(board.getCellAt(15, 13)));
		assertTrue(adj.contains(board.getCellAt(16, 14)));
	}
	
	// Tests for locations next to doorways
	@Test
	public void testDoorwayEntry() {
		adj = board.getAdjList(6, 1); // Test 'UP' door
		
		assertEquals(adj.size(), 4);
		
		assertTrue(adj.contains(board.getCellAt(5, 1)));
		assertTrue(adj.contains(board.getCellAt(6, 0)));
		assertTrue(adj.contains(board.getCellAt(6, 2)));
		assertTrue(adj.contains(board.getCellAt(7, 1)));
		
		adj = board.getAdjList(7, 16); // Test 'DOWN' door
		
		assertEquals(adj.size(), 4);

		assertTrue(adj.contains(board.getCellAt(6, 16)));
		assertTrue(adj.contains(board.getCellAt(7, 15)));
		assertTrue(adj.contains(board.getCellAt(7, 17)));
		assertTrue(adj.contains(board.getCellAt(8, 16)));
		
		adj = board.getAdjList(3, 13); // Test 'LEFT' door
		
		assertEquals(adj.size(), 4);

		assertTrue(adj.contains(board.getCellAt(2, 13)));
		assertTrue(adj.contains(board.getCellAt(3, 12)));
		assertTrue(adj.contains(board.getCellAt(3, 14)));
		assertTrue(adj.contains(board.getCellAt(4, 13)));
		
		adj = board.getAdjList(23, 4); // Test 'RIGHT' door
		
		assertEquals(adj.size(), 4);

		assertTrue(adj.contains(board.getCellAt(22, 4)));
		assertTrue(adj.contains(board.getCellAt(23, 3)));
		assertTrue(adj.contains(board.getCellAt(23, 5)));
		assertTrue(adj.contains(board.getCellAt(24, 4)));
	}
	
	// Tests for doorways
	@Test
	public void testDoorwayExit() {
		adj = board.getAdjList(16, 1); // Test 'UP' doorway
		
		assertEquals(adj.size(), 1);

		assertTrue(adj.contains(board.getCellAt(15, 1)));
		
		adj = board.getAdjList(16, 16); // Test 'DOWN' doorway
		
		assertEquals(adj.size(), 1);

		assertTrue(adj.contains(board.getCellAt(17, 16)));
		
		adj = board.getAdjList(3, 14); // Test 'LEFT' doorway
		
		assertEquals(adj.size(), 1);

		assertTrue(adj.contains(board.getCellAt(3, 13)));
		
		adj = board.getAdjList(23, 3); // Test 'RIGHT' doorway
		
		assertEquals(adj.size(), 1);

		assertTrue(adj.contains(board.getCellAt(23, 4)));
	}

	// TODO: Implement tests for movement (1,3,5,6 spaces)
	@Test
	public void testTargetsOneStep() {
		board.calcTargets(10, 5, 1);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(9, 5)));
		assertTrue(targets.contains(board.getCellAt(11, 5)));
		assertTrue(targets.contains(board.getCellAt(10, 6)));
		
		board.calcTargets(18, 19, 1);
		targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(17, 19)));
		assertTrue(targets.contains(board.getCellAt(19, 19)));
		assertTrue(targets.contains(board.getCellAt(18, 18)));
		
		board.calcTargets(24, 5, 1);
		targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(24, 4)));
		assertTrue(targets.contains(board.getCellAt(24, 6)));
		assertTrue(targets.contains(board.getCellAt(23, 5)));
	}
	
	@Test
	public void testTargetsThreeStep() {
		
	}
	
	@Test
	public void testTargetsFiveStep() {
		
	}
	
	@Test
	public void testTargetsSixStep() {
		
	}
	
	// TODO: Implement tests for entering a room
	@Test
	public void testTargetsIntoRoom() {
		
	}
	
	@Test
	public void testTargetsIntoRoomShortcut() {
		
	}
	
	// TODO: Implement tests for leaving a room
	@Test
	public void testRoomExit() {
		
	}
}
