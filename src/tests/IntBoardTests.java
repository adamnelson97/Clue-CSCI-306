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

	/*
	 * Board:
	 * [0,0][0,1][0,2][0,3]
	 * [1,0][1,1][1,2][1,3]
	 * [2,0][2,1][2,2][2,3]
	 * [3,0][3,1][3,2][3,3]
	 */

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
		BoardCell cell = board.getCell(1, 3);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(0, 3)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertEquals(3, testList.size());
	}

	@Test
	public void testLeftEdge() {
		//tests adjacency list for [2][0]
		BoardCell cell = board.getCell(2, 0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(3, 0)));
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertEquals(3, testList.size());
	}

	@Test
	public void test2ndRow2ndCol() {
		//tests adjacency list for [1][1], remember zero indexing
		BoardCell cell = board.getCell(1, 1);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertEquals(4, testList.size());
	}

	@Test
	public void test3rdRow3rdCol() {
		//tests adjacency list for [2][2]
		BoardCell cell = board.getCell(2, 2);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertEquals(4, testList.size());
	}

	//Need six other test methods
	//Template below

	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/

}
