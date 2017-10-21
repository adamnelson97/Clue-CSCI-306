package tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import clueGame.Board;

public class BoardAdjTests {

	//Constructor
	private static Board board;
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance and initialize it		
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueGameLayout.csv", "ClueGameLegend.txt");		
		board.initialize();
	}
	
	// TODO: Implement tests for only walkways adjacent
	@Test
	public void testWalkways() {
		
	}
	
	// TODO: Implement tests for locations within rooms
	@Test
	public void testInsideRoom() {
		
	}
	
	// TODO: Implement tests for edges
	@Test
	public void testBoardEdges() {
		
	}
	
	// TODO: Implement tests for locations next to rooms
	@Test
	public void testRoomEdges() {
		
	}
	
	// TODO: Implement tests for locations next to doorways
	@Test
	public void testDoorwayEntry() {
		
	}
	
	// TODO: Implement tests for doorways
	@Test
	public void testDoorwayExit() {
		
	}

	@Test
	public void testTargetsOneStep() {
		
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
	
	@Test
	public void testTargetsIntoRoom() {
		
	}
	
	@Test
	public void testTargetsIntoRoomShortcut() {
		
	}
	
	@Test
	public void testRoomExit() {
		
	}
}
