package clueGame;

import gui.SuggestionDialog;

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
		completedTurn = true; //Initially true so the game can begin.
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
	 * @param theBoard The game board.
	 */
	public void completeTurn(BoardCell cell, Board theBoard) {
		this.setCompletedTurn(true); //Indicates the player has moved to a new cell.
		this.setLoc(cell); //Actually moves the player to the new cell.

		/*
		 * If the player has moved to a room, a dialog needs to open for them to
		 * make a suggestion, and the board must then disprove it.
		 */
		if (cell.isRoom()) {
			SuggestionDialog dialog = new SuggestionDialog(cell, theBoard);
			if (dialog.isSubmitted()) { //If the player actually submitted a suggestion:
				suggestion = dialog.getSuggestion(); //Stores the user's suggestion.
				//System.out.println(suggestion.toString()); //Debugging
				//Update control panel after human makes suggestion.
				theBoard.control.setGuessText(getSuggestion().text());
				Card revealedCard = theBoard.handleSuggestion(this, suggestion);
				if (revealedCard != null) {
					theBoard.control.setGuessResultText(revealedCard.getCardName()); //Updates the display with the name of the revealed card.
					addSeen(revealedCard); //Adds the revealed card to the list of seen cards for that player.
				}
				else theBoard.control.setGuessResultText("");
				theBoard.getPlayers().get(suggestion.person).setLoc(cell); //Moves the suggested player to the human player's location.
			}
		}
	}

}
