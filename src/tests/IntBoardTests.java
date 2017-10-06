package tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import experiment.BoardCell;
import experiment.IntBoard;

/**
 * @author AdamN
 *
 */
/**
 * @author AdamN
 *
 */
public class IntBoardTests {

	private IntBoard board;

	@Before
	public void setUp() {
		System.out.println("In @Before");
		board = new IntBoard();
	}

	@Test
	public void testTopLeftCorner() {
		//tests adjacency list for [0][0]
		BoardCell cell = board.getCell(0, 0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());
	}

	@Test
	public void testBottomRightCorner() {
		//tests adjacency list for [3][3]
		BoardCell cell = board.getCell(3, 3);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertEquals(2, testList.size());
	}

	@Test
	public void testRightEdge() {
		//tests adjacency list for [1][3]
		fail("Not yet implemented");
	}

	@Test
	public void testLeftEdge() {
		//tests adjacency list for [3][0]
		fail("Not yet implemented");
	}

	@Test
	public void test2ndRow2ndCol() {
		//tests adjacency list for [1][1], remember zero indexing
		fail("Not yet implemented");
	}

	@Test
	public void test3rdRow3rdCol() {
		//tests adjacency list for [2][2]
		fail("Not yet implemented");
	}

	//Need six other test methods
	//Template below

	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/

}
