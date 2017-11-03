package clueGame;

import java.awt.Color;

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
	
	//Constructors
	public Player(String playerName, Color color, int row, int column) {
		this.playerName = playerName;
		this.color = color;
		this.row = row;
		this.column = column;
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
}
