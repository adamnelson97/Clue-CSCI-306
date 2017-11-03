package tests;

import static org.junit.Assert.*;

import java.awt.Color;
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
		board.loadConfigFiles(); //Loads players and weapons into board
	}
	

	//TODO Test loading 1st, 3rd, and last player from config file
	@Test
	public void testLoadPlayers() {
		Set<Player> players = board.getPlayers();
		//Creates a new player object, then tests the set for that object
		Player x = new Player("Miss Scarlett", Color.RED, 0, 6);
		assertTrue(players.contains(x));
		x = new Player("Professor Plum", Color.PINK, 8, 19);
		assertTrue(players.contains(x));
		x = new Player("Mrs. Peacock", Color.BLUE, 18, 19);
		assertTrue(players.contains(x));
		x = new Player("Mr. Green", Color.GREEN, 24, 13);
		assertTrue(players.contains(x));
		x = new Player("Colonel Mustard", Color.YELLOW, 24, 5);
		assertTrue(players.contains(x));
		x = new Player("Mrs. White", Color.WHITE, 6, 1);
		assertTrue(players.contains(x));
	}

	//TODO Determine if Human player will be determined by format or chosen by player

	//TODO Test loading the deck of cards
	//Test for correct total number of cards
	//Test for correct number of each type of card
	//Test existence of one specific weapon, room, and person

	//TESTS BELOW THIS POINT HAVE DETAILED SUGGESTIONS IN CluePlayer.pdf
	//TODO Test dealing the cards.
	//Each player (human and computer) should have roughly the same number of cards

	//TODO Test selecting a target location (Computer Player)

	//TODO Test checking an accusation (Board)

	//TODO Test creating a suggestion (Computer Player)

	//TODO Test disproving a suggestion (Player)

	//TODO Test asking players in order to disprove a suggestion (Board)

}
