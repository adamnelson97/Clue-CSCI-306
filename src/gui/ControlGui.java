package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

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
		setLayout(new GridLayout(2,3));
		
		//Add JLabel and JTextField for "Whose Turn"
		add(whoseTurn());
		//Add JButton for "Next player"
		
		//Add JButton for "Make an accusation"
		
		//Add JLabel Roll with JTextField and border titled "Die"
		
		//Add JLabel Guess with JTextField and border titled "Guess"
		
		//Add JLabel Response with JTextField and border titled "Guess Result"
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

}
