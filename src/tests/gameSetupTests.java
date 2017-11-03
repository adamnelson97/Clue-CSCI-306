package tests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
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
	//TODO Test dealing the cards.
	//Each player (human and computer) should have roughly the same number of cards

	//TODO Test selecting a target location (Computer Player)

	//TODO Test checking an accusation (Board)

	//TODO Test creating a suggestion (Computer Player)

	//TODO Test disproving a suggestion (Player)

	//TODO Test asking players in order to disprove a suggestion (Board)

}
