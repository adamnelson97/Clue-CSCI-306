package gui;

import javax.swing.JPanel;

import clueGame.Board;

/**
 * <h1>BoardGui</h1>
 * BoardGui is the upper section of the main GUI
 * used to play the game. In includes panels displaying what cards
 * the player currently possesses, as well as the game board.
 * @author Adam Nelson, Youjun Lee
 * @since 2017-11-10
 *
 */
@SuppressWarnings("serial")
public class BoardGui extends JPanel {
	private BoardGui gui; //Self referencing object.
	private static Board board; //The game board
	
	/**
	 * Default Constructor for BoardGui.
	 * When this is called, it automatically adds all subpanels.
	 */	
	public BoardGui(Board board) {
		gui = this;
		this.board = board; //Passes the game board from GuiMain into the BoardGui.
	}

}
