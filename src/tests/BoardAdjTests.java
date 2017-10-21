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
	
	//TODO: Implement tests for 

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
