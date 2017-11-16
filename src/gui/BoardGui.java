package gui;

import java.awt.GridLayout;

import javax.swing.JPanel;
import clueGame.Board;
import clueGame.BoardCell;

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
	 * @param theBoard The game board.
	 */	
	public BoardGui(Board theBoard) {
		gui = this;
		board = theBoard; //Passes the game board from GuiMain into the BoardGui.
		setLayout(new GridLayout(1,2));
		add(board);
		setSize(board.getNumColumns() * BoardCell.getWidth(), board.getNumRows() * BoardCell.getHeight());
		board.repaint();
	}
	
}
