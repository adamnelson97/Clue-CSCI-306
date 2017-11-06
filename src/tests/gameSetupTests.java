package tests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.ComputerPlayer;
import clueGame.Player;

/*
 * Authors: Adam Nelson and Youjun Lee
 * Purpose: Test loading the different characters and cards, and dealing the cards.
 */
public class gameSetupTests {

	public static final int LEGEND_SIZE = 11;
	public static final int NUM_ROWS = 25;
	public static final int NUM_COLUMNS = 20;

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


	//Test loading 1st, 3rd, and last player from config file
	//Test that each player's color and starting location is correct.
	@Test
	public void testLoadPlayers() {
		assertEquals(6, board.getPlayers().size());
		//Creates a new player object, then tests the set for that object
		assertEquals("Mrs. White", board.getPlayers().get("Mrs. White").getPlayerName());
		assertEquals(Color.WHITE, board.getPlayers().get("Mrs. White").getColor());
		assertEquals(6, board.getPlayers().get("Mrs. White").getRow());
		assertEquals(1, board.getPlayers().get("Mrs. White").getColumn());

		assertEquals("Miss Scarlett", board.getPlayers().get("Miss Scarlett").getPlayerName());
		assertEquals(Color.RED, board.getPlayers().get("Miss Scarlett").getColor());
		assertEquals(0, board.getPlayers().get("Miss Scarlett").getRow());
		assertEquals(6, board.getPlayers().get("Miss Scarlett").getColumn());

		assertEquals("Mrs. Peacock", board.getPlayers().get("Mrs. Peacock").getPlayerName());
		assertEquals(Color.BLUE, board.getPlayers().get("Mrs. Peacock").getColor());
		assertEquals(18, board.getPlayers().get("Mrs. Peacock").getRow());
		assertEquals(19, board.getPlayers().get("Mrs. Peacock").getColumn());
	} 


	//Test for correct total number of cards
	//Test for correct number of each type of card
	//Test existence of one specific weapon, room, and person
	@Test
	public void testWeaponDeck() {
		assertEquals(6, board.getWeapons().size());
		assertTrue(board.getWeapons().containsKey("Knife"));
	}

	@Test
	public void testRoomDeck() {
		assertEquals(9, board.getRooms().size());
		assertTrue(board.getRooms().containsKey("Art Room"));
	}

	@Test
	public void testPlayerDeck() {
		assertEquals(6, board.getPlayerCards().size());
		assertTrue(board.getPlayerCards().containsKey("Colonel Mustard"));
	}


	//TESTS BELOW THIS POINT HAVE DETAILED SUGGESTIONS IN CluePlayer.pdf
	//Test dealing the cards.
	@Test
	public void testPlayerHands() {
		assertEquals(3, board.getPlayers().get("Miss Scarlett").getHand().size());
		assertEquals(3, board.getPlayers().get("Professor Plum").getHand().size());
		assertEquals(3, board.getPlayers().get("Mrs. Peacock").getHand().size());
		assertEquals(3, board.getPlayers().get("Mr. Green").getHand().size());
		assertEquals(3, board.getPlayers().get("Colonel Mustard").getHand().size());
		assertEquals(3, board.getPlayers().get("Mrs. White").getHand().size());
	}

	//Double check cards have not been dealt twice
	@Test
	public void doubleCheckDealing() {
		Map<String, Card> cards = board.getDeck();
		assertEquals(21, board.getDeck().size());
		int num_doubles = 0;

		for (String x : cards.keySet()) {
			for (String y : cards.keySet()) {
				if (cards.get(x).equals(cards.get(y))) {
					num_doubles++;
				}
			}
		}
		assertEquals(21, num_doubles);
	}

	//TODO Test selecting a target location (Computer Player)
	@Test
	public void testTargetRandomSelection() {
		ComputerPlayer player = new ComputerPlayer();
		//Location has 3 targets
		board.calcTargets(18, 4, 1);
		boolean loc_17_4 = false;
		boolean loc_19_4 = false;
		boolean loc_18_5 = false;
		// Run the test a large number of times
		for (int i=0; i<50; i++) {
			BoardCell selected = player.pickLocation(board.getTargets());
			if (selected == board.getCellAt(17, 17))
				loc_17_4 = true;
			else if (selected == board.getCellAt(16, 18))
				loc_19_4 = true;
			else if (selected == board.getCellAt(18, 18))
				loc_18_5 = true;
			else
				fail("Invalid target selected");
		}
		// Ensure each target was selected at least once
		assertTrue(loc_17_4);
		assertTrue(loc_19_4);
		assertTrue(loc_18_5);

		//Location has 5 targets plus a room
		board.calcTargets(19, 17, 3);
		boolean loc_17_17 = false;
		boolean loc_18_16 = false;
		boolean loc_18_18 = false;
		boolean loc_19_15 = false;
		boolean loc_19_19 = false;
		boolean loc_20_17 = false;
		// Run the test a large number of times
		for (int i=0; i<100; i++) {
			BoardCell selected = player.pickLocation(board.getTargets());
			if (selected == board.getCellAt(17, 17))
				loc_17_17 = true;
			else if (selected == board.getCellAt(18, 16))
				loc_18_16 = true;
			else if (selected == board.getCellAt(18, 18))
				loc_18_18 = true;
			else if (selected == board.getCellAt(19, 15))
				loc_19_15 = true;
			else if (selected == board.getCellAt(19, 19))
				loc_19_19 = true;
			else if (selected == board.getCellAt(20, 17))
				loc_20_17 = true;
			else
				fail("Invalid target selected");
		}
		// Ensure each target was selected at least once
		assertTrue(loc_17_17);
		assertTrue(loc_18_16);
		assertTrue(loc_18_18);
		assertTrue(loc_19_15);
		assertTrue(loc_19_19);
		assertTrue(loc_20_17);
	}

	//TODO Test checking an accusation (Board)

	//TODO Test creating a suggestion (Computer Player)

	//TODO Test disproving a suggestion (Player)

	//TODO Test asking players in order to disprove a suggestion (Board)

}
