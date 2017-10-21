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
		board.calcTargets(10, 5, 3);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(7, targets.size());
		assertTrue(targets.contains(board.getCellAt(10, 6)));
		assertTrue(targets.contains(board.getCellAt(9, 5)));
		assertTrue(targets.contains(board.getCellAt(11, 5)));
		assertTrue(targets.contains(board.getCellAt(7, 5)));
		assertTrue(targets.contains(board.getCellAt(13, 5)));
		assertTrue(targets.contains(board.getCellAt(8, 6)));
		assertTrue(targets.contains(board.getCellAt(12, 6)));

		board.calcTargets(18, 19, 3);
		targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCellAt(17, 19)));
		assertTrue(targets.contains(board.getCellAt(19, 19)));
		assertTrue(targets.contains(board.getCellAt(18, 18)));
		assertTrue(targets.contains(board.getCellAt(18, 16)));
		assertTrue(targets.contains(board.getCellAt(17, 17)));
		assertTrue(targets.contains(board.getCellAt(17, 19)));

		board.calcTargets(24, 5, 3);
		targets = board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCellAt(24, 4)));
		assertTrue(targets.contains(board.getCellAt(23, 5)));
		assertTrue(targets.contains(board.getCellAt(22, 4)));
		assertTrue(targets.contains(board.getCellAt(21, 5)));
	}

	@Test
	public void testTargetsFiveStep() {
		board.calcTargets(10, 5, 3);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(13, targets.size());
		assertTrue(targets.contains(board.getCellAt(10, 6)));
		assertTrue(targets.contains(board.getCellAt(9, 5)));
		assertTrue(targets.contains(board.getCellAt(11, 5)));
		assertTrue(targets.contains(board.getCellAt(7, 5)));
		assertTrue(targets.contains(board.getCellAt(13, 5)));
		assertTrue(targets.contains(board.getCellAt(8, 6)));
		assertTrue(targets.contains(board.getCellAt(12, 6)));
		assertTrue(targets.contains(board.getCellAt(14, 4)));
		assertTrue(targets.contains(board.getCellAt(15, 5)));
		assertTrue(targets.contains(board.getCellAt(14, 6)));
		assertTrue(targets.contains(board.getCellAt(6, 4)));
		assertTrue(targets.contains(board.getCellAt(5, 6)));
		assertTrue(targets.contains(board.getCellAt(6, 6)));

		board.calcTargets(18, 19, 3);
		targets = board.getTargets();
		assertEquals(9, targets.size());
		assertTrue(targets.contains(board.getCellAt(17, 19)));
		assertTrue(targets.contains(board.getCellAt(19, 19)));
		assertTrue(targets.contains(board.getCellAt(18, 18)));
		assertTrue(targets.contains(board.getCellAt(18, 16)));
		assertTrue(targets.contains(board.getCellAt(17, 17)));
		assertTrue(targets.contains(board.getCellAt(17, 19)));
		assertTrue(targets.contains(board.getCellAt(17, 15)));
		assertTrue(targets.contains(board.getCellAt(19, 15)));
		assertTrue(targets.contains(board.getCellAt(18, 14)));

		board.calcTargets(24, 5, 3);
		targets = board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCellAt(24, 4)));
		assertTrue(targets.contains(board.getCellAt(23, 5)));
		assertTrue(targets.contains(board.getCellAt(22, 4)));
		assertTrue(targets.contains(board.getCellAt(21, 5)));
		assertTrue(targets.contains(board.getCellAt(20, 4)));
		assertTrue(targets.contains(board.getCellAt(19, 5)));
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
