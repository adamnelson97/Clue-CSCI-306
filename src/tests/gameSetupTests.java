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
import clueGame.Solution;
import clueGame.Card.CardType;

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

	//Test selecting a target location (Computer Player)
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
			if (selected == board.getCellAt(17, 4))
				loc_17_4 = true;
			else if (selected == board.getCellAt(19, 4))
				loc_19_4 = true;
			else if (selected == board.getCellAt(18, 5))
				loc_18_5 = true;
			else
				fail("Invalid target selected");
		}
		// Ensure each target was selected at least once
		assertTrue(loc_17_4);
		assertTrue(loc_19_4);
		assertTrue(loc_18_5);

		//Location has 5 targets plus a room that has NOT just been visited
		board.calcTargets(19, 17, 2);
		boolean loc_17_17 = false;
		boolean loc_18_16 = false;
		boolean loc_18_18 = false;
		boolean loc_19_15 = false;
		boolean loc_19_19 = false;
		boolean loc_20_17 = false;
		// Run the test a large number of times
		for (int i=0; i<100; i++) {
			player.resetRoom();
			BoardCell selected = player.pickLocation(board.getTargets());
			//System.out.println("Selected: " + selected.toString());
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
		assertFalse(loc_17_17);
		assertFalse(loc_18_16);
		assertFalse(loc_18_18);
		assertFalse(loc_19_15);
		assertFalse(loc_19_19);
		assertTrue(loc_20_17);

		//Location has 5 targets plus a room that HAS just been visited
		board.calcTargets(19, 17, 2);
		loc_17_17 = false;
		loc_18_16 = false;
		loc_18_18 = false;
		loc_19_15 = false;
		loc_19_19 = false;
		loc_20_17 = false;
		// Run the test a large number of times
		for (int i=0; i<100; i++) {
			BoardCell selected = player.pickLocation(board.getTargets());
			//System.out.println("Selected: " + selected.toString());
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

	//Test checking an accusation (Board)
	@Test
	public void testAccusation() {
		board.setSolution(new Solution("Miss Scarlett", "Knife", "Library"));
		
		//Check correct accusation
		boolean correctAccusation = board.checkAccusation(new Solution("Miss Scarlett", "Knife", "Library"));
		assertTrue(correctAccusation);
		//Check incorrect person
		boolean wrongPerson = board.checkAccusation(new Solution("Mrs. Peacock", "Knife", "Library"));
		assertFalse(wrongPerson);
		//Check incorrect weapon
		boolean wrongWeapon = board.checkAccusation(new Solution("Miss Scarlett", "Pipe", "Library"));
		assertFalse(wrongWeapon);
		//Check incorrect room
		boolean wrongRoom = board.checkAccusation(new Solution("Miss Scarlett", "Knife", "Theatre"));
		assertFalse(wrongRoom);		
	}
	
	//Test creating a suggestion (Computer Player)
	@Test
	public void testCreateSuggestion() {
		ComputerPlayer player = new ComputerPlayer();
		//Give player cards, these should not be suggested.
		player.addCard(new Card("Miss Scarlett", CardType.PERSON));
		player.addCard(new Card("Knife", CardType.WEAPON));
		player.addCard(new Card("Mrs. Peacock", CardType.PERSON));
		player.addCard(new Card("Pipe", CardType.WEAPON));
		
		Set<Card> dealtCards = new HashSet<Card>(); //Total collection of cards in play
		dealtCards.add(new Card("Miss Scarlett", CardType.PERSON));
		dealtCards.add(new Card("Knife", CardType.WEAPON));
		dealtCards.add(new Card("Mrs. Peacock", CardType.PERSON));
		dealtCards.add(new Card("Pipe", CardType.WEAPON));
		//Add other cards the player will not have seen.
		dealtCards.add(new Card("Mr. Green", CardType.PERSON));
		dealtCards.add(new Card("Revolver", CardType.WEAPON));
		
		Solution suggestion = player.createSuggestion(board, board.getCellAt(0, 19), dealtCards);
		assertEquals("Mr. Green", suggestion.person);
		assertEquals("Revolver", suggestion.weapon);
		assertEquals("Ballroom", suggestion.room);
	}

	//Test disproving a suggestion (Player)
	@Test
	public void testDisproveSuggestion() {
		Solution suggestion = new Solution("Miss Scarlett", "Knife", "Library");
		ComputerPlayer player1 = new ComputerPlayer(); //This player will not have any of the cards.
		ComputerPlayer player2 = new ComputerPlayer(); //This player will have Miss Scarlett.
		ComputerPlayer player3 = new ComputerPlayer(); //This player will have Knife and Library.
		
		player2.addCard(new Card("Miss Scarlett", CardType.PERSON));
		player3.addCard(new Card("Knife", CardType.WEAPON));
		player3.addCard(new Card("Library", CardType.ROOM));
		
		Card disproveCard = player1.disproveSuggestion(suggestion); //Should return null
		assertEquals(null, disproveCard);
		disproveCard = player2.disproveSuggestion(suggestion); //Should return Miss Scarlett
		assertEquals("Miss Scarlett", disproveCard.getCardName());
		
		//Last player has two possible cards, so a loop will test for both possibilities.
		boolean weaponCard = false;
		boolean roomCard = false;
		for (int i = 0; i < 100; i++) {
			disproveCard = player3.disproveSuggestion(suggestion);
			if (disproveCard.getCardName().equals("Knife")) {
				weaponCard = true;
			}
			if (disproveCard.getCardName().equals("Library")) {
				roomCard = true;
			}
		}
		assertTrue(weaponCard);
		assertTrue(roomCard);
		
	}

	//TODO Test asking players in order to disprove a suggestion (Board)
	@Test
	public void testHandleSuggestion() {
		Solution suggestion = new Solution("Santa Claus", "Candy Cane", "North Pole"); //Solution cannot be disproven as the cards don't exist.
		Player player = new Player("Miss Scarlett", Color.RED, 0, 0); //This is the player making the suggestion.
		Card disproveCard = board.handleSuggestion(player, suggestion); //Should return null because no player has any of those cards
		assertEquals(null, disproveCard);
		
		//Test a suggestion that only the suggesting player can disprove
		suggestion = new Solution("Colonel Mustard", "Wrench", "Kitchen");
		disproveCard = board.handleSuggestion(player, suggestion); //Should still return null
		assertEquals(null, disproveCard);
		
		//Test a suggestion that player 1 and 2 can disprove, make sure card comes from player 1
		/*
		 * Disprove order:
		 * Miss Scarlett
		 * Professor Plum
		 * Mrs. Peacock
		 * Mr. Green
		 * Colonel Mustard
		 * Mrs. White
		 */
		board.getPlayers().get("Professor Plum").addCard(new Card("Wrench", CardType.WEAPON));
		board.getPlayers().get("Mrs. Peacock").addCard(new Card("Kitchen", CardType.ROOM));
		disproveCard = board.handleSuggestion(player, suggestion); //Should return Wrench, not Kitchen
		assertEquals("Wrench", disproveCard.getCardName());

	}

}
