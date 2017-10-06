package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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

	@Before
	public void setUp() {
		System.out.println("In @Before");
		IntBoard board = new IntBoard();
	}

	@Test
	public void testTopLeftCorner() {
		//tests adjacency list for [0][0]
		fail("Not yet implemented");
	}

	@Test
	public void testBottomRightCorner() {
		//tests adjacency list for [3][3]
		fail("Not yet implemented");
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
