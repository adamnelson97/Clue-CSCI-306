package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
public class ControlGui extends JPanel {
	
	/**
	 * Default Constructor for ControlGui.
	 * When this is called, it automatically adds all subpanels.
	 */
	public ControlGui() {
		//Set GridLayout
		setLayout(new GridLayout(2,1));
		
		//Create a panel to store the turn indicator and two buttons
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,3));
		
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
		bottomPanel.setLayout(new GridLayout(1,3));
		
		//Add JLabel Roll with JTextField and border titled "Die"
		bottomPanel.add(dieRoll());
		
		//Add JLabel Guess with JTextField and border titled "Guess"
		
		
		//Add JLabel Response with JTextField and border titled "Guess Result"
		
		
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
		return panel;
	}
	//Add any necessary action listeners for the GUI below.

}
