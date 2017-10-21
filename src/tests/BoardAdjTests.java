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
	
	//TODO: Implement tests for 

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
