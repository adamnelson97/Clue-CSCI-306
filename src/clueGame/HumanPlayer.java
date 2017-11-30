package clueGame;

import java.awt.Color;

/**
 * <h1>HumanPlayer</h1>
 * This class is an extension of Player that makes it easier
 * to identify the user.
 * @author Adam Nelson, Youjun Lee
 * @since 2017-11-15
 * @see Player
 *
 */
public class HumanPlayer extends Player {

	private boolean completedTurn; //Tracks whether the user has completed their turn.
	
	/**
	 * Default constructor.
	 * @param playerName The name of the character.
	 * @param color The character's color.
	 * @param row The character's starting row.
	 * @param column The character's starting column.
	 */
	public HumanPlayer(String playerName, Color color, int row, int column) {
		super(playerName, color, row, column);
	}

	public boolean isCompletedTurn() {
		return completedTurn;
	}

	public void setCompletedTurn(boolean completedTurn) {
		this.completedTurn = completedTurn;
	}
	
	/**
	 * Updates the player's location and ends their turn.
	 * @param cell The cell with the player's new location.
	 */
	public void completeTurn(BoardCell cell) {
		this.setCompletedTurn(true);
		this.setLoc(cell);
	}
}
