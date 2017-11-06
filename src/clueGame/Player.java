package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * <h1>Player</h1>
 * This class stores all the information relevant to the players in the game.
 * @author Adam Nelson, Youjun Lee
 * @version 1.0
 * @since 2017-11-02
 *
 */
public class Player {

	//Variables
	private String playerName;
	private int row;
	private int column;
	private Color color;
	private ArrayList<Card> hand; //All the cards dealt to the player
	Set<Card> seen; //Tracks all the cards revealed to the player.

	//Constructors
	
	/**
	 * Default constructor.
	 */
	public Player() {
		
	}
	/**
	 * Parameterized Constructor that creates a new player from a data file, and
	 * initializes an empty hand.
	 * @param playerName The name of the player.
	 * @param color The player's identifying color.
	 * @param row The row of the player's starting location.
	 * @param column The column of the player's starting location.
	 */
	public Player(String playerName, Color color, int row, int column) {
		this.playerName = playerName;
		this.color = color;
		this.row = row;
		this.column = column;
		this.hand = new ArrayList<Card>();
		this.seen = new HashSet<Card>();
	}
	
	//Methods
	
	/**
	 * Reveals a suggested card to the player making the suggestion.
	 * @param suggestion The proposed suggestion, e.g. PERSON did it with the WEAPON in the ROOM.
	 * @return Card A suggested card that the player contains in their hand.
	 */
	public Card disproveSuggestion(Solution suggestion) {
		//TODO Complete disproveSuggestion method
		return null;
	}
	
	/**
	 * Adds a card to the player's hand.
	 * @param c The card to be added.
	 */
	public void addCard(Card c) {
		hand.add(c);
		seen.add(c);
	}
	
	//Getters for Testing
	
	public Color getColor() {
		return color;
	}

	public String getPlayerName() {
		return playerName;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}

	@Override
	public String toString() {
		return "Player [playerName=" + playerName + ", row=" + row + ", column=" + column + ", color=" + color + "]";
	}
	
	
}
