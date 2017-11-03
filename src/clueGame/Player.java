package clueGame;

import java.awt.Color;
import java.util.ArrayList;

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
	
	//Constructors
	public Player(String playerName, Color color, int row, int column) {
		this.playerName = playerName;
		this.color = color;
		this.row = row;
		this.column = column;
		hand = new ArrayList<Card>();
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
