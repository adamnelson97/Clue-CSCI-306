package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.Board;

/**
 * <h1>ControlGui</h1>
 * ControlGui is the lower section of the main GUI
 * used to play the game. It includes buttons to move
 * to the next turn, as well as information such as
 * whose turn it is, what the die roll is, and what
 * a player has guessed.
 * @author Adam Nelson, Youjun Lee
 * @since 2017-11-10
 *
 */
@SuppressWarnings("serial")
public class ControlGui extends JPanel {
	private ControlGui gui; //Self referencing object so action listeners can call the object as a parameter.
	private static Board board; //The game board.
	
	/**
	 * Default Constructor for ControlGui.
	 * When this is called, it automatically adds all subpanels.
	 */
	public ControlGui(Board board) {
		gui = this;
		this.board = board; //Passes the game board from GuiMain ino the ControlGui.
		//Set GridLayout
		setLayout(new GridLayout(2,1));
		
		//Create a panel to store the turn indicator and two buttons
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,0));
		
		//Add JLabel and JTextField for "Whose Turn"
		topPanel.add(whoseTurn());
		
		//Add JButton for "Next player"
		JButton nextPlayerButton = new JButton("Next player");
		//Add action listener for button here later
		topPanel.add(nextPlayerButton);
		
		//Add JButton for "Make an accusation"
		JButton accusationButton = new JButton("Make an accusation");
		//Add action listener for button here later
		topPanel.add(accusationButton);
		
		//Create a panel to store the Die, Guess, and Guess Result boxes
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,0));
		
		//Add JLabel Roll with JTextField and border titled "Die"
		bottomPanel.add(dieRoll());
		
		//Add JLabel Guess with JTextField and border titled "Guess"
		bottomPanel.add(playerGuess());
		
		//Add JLabel Response with JTextField and border titled "Guess Result"
		bottomPanel.add(playerGuessResult());
		
		//Add the two panel sections to the ControlGui panel
		add(topPanel);
		add(bottomPanel);
	}
	
	/**
	 * Method to return a panel stating the which player is currently taking a turn.
	 * @return JPanel A panel containing a label and non-editable text field.
	 */
	private JPanel whoseTurn() {
		JPanel panel = new JPanel();
		JLabel currentPlayer = new JLabel("Whose turn?");
		JTextField playerName = new JTextField(20);
		//The text field only displays information and will not be edited by the player.
		playerName.setEditable(false);
		panel.add(currentPlayer, BorderLayout.NORTH);
		panel.add(playerName, BorderLayout.SOUTH);
		return panel;		
	}
	
	/**
	 * Method to return a panel stating the last roll of the die.
	 * @return JPanel A panel with the last die roll.
	 */
	private JPanel dieRoll() {
		JPanel panel = new JPanel();
		JLabel roll = new JLabel("Roll");
		JTextField rollValue = new JTextField(5);
		rollValue.setEditable(false);
		panel.add(roll, BorderLayout.WEST);
		panel.add(rollValue, BorderLayout.EAST);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Die"));
		return panel;
	}
	
	/**
	 * Method to return a panel containing the guess of the current player.
	 * @return JPanel A panel with the guess of the current player.
	 */
	private JPanel playerGuess() {
		JPanel panel = new JPanel();
		JLabel guess = new JLabel("Guess");
		JTextField playerGuess = new JTextField(30);
		playerGuess.setEditable(false);
		panel.add(guess, BorderLayout.WEST);
		panel.add(playerGuess, BorderLayout.EAST);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Guess"));
		return panel;
	}
	
	/**
	 * Method to return a panel containing the disproving result of the player's suggestion.
	 * @return JPanel A panel displaying the name of the revealed Card.
	 */
	private JPanel playerGuessResult() {
		JPanel panel = new JPanel();
		JLabel response = new JLabel("Response");
		JTextField responseName = new JTextField(20);
		responseName.setEditable(false);
		panel.add(response, BorderLayout.WEST);
		panel.add(responseName, BorderLayout.EAST);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));
		return panel;
	}
	//Add any necessary action listeners for the GUI below.

}
