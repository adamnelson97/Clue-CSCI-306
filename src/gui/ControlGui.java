package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;

/**
 * <h1>ControlGui</h1>
 * ControlGui is the lower section of the main GUI
 * used to play the game. It includes buttons to move
 * to the next turn, as well as information such as
 * whose turn it is, what the die roll is, and what
 * a player has guessed.
 * @author Adam Nelson, Youjun Lee
 * @since 2017-11-10
 * @see JPanel
 *
 */
@SuppressWarnings("serial")
public class ControlGui extends JPanel {
	private ControlGui gui; //Self referencing object so action listeners can call the object as a parameter.
	private JButton accusation;
	private JButton nextPlayer;
	private JTextField roll;
	private JTextField turn;
	private JTextField guess;
	private JTextField guessResult;

	/**
	 * Default Constructor for ControlGui.
	 * When this is called, it automatically adds all subpanels.
	 */
	public ControlGui() {
		gui = this;
		//Set GridLayout
		setLayout(new GridLayout(2,1));

		//Create a panel to store the turn indicator and two buttons
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,0));

		JPanel whoseTurnPanel = whoseTurn();
		nextPlayer = new JButton("Next player");
		accusation = new JButton("Make an accusation");
		ButtonListener buttonListener = new ButtonListener();
		nextPlayer.addActionListener(buttonListener);
		accusation.addActionListener(buttonListener);

		//Add components to the top panel
		topPanel.add(whoseTurnPanel);
		topPanel.add(nextPlayer);
		topPanel.add(accusation);
		
		//Create a panel to store the Die, Guess, and Guess Result boxes
		JPanel bottomPanel = new JPanel();
		JPanel dieRollPanel = dieRoll();
		bottomPanel.add(dieRollPanel);
		bottomPanel.add(playerGuess());
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
		this.turn = new JTextField(20);
		this.turn.setEditable(false);
		panel.add(currentPlayer, BorderLayout.NORTH);
		panel.add(this.turn, BorderLayout.SOUTH);
		return panel;		
	}

	/**
	 * Method to return a panel stating the last roll of the die.
	 * @return JPanel A panel with the last die roll.
	 */
	private JPanel dieRoll() {
		JPanel panel = new JPanel();
		JLabel rollLabel = new JLabel("Roll");
		this.roll = new JTextField(3);
		this.roll.setEditable(false);
		panel.add(rollLabel, BorderLayout.WEST);
		panel.add(this.roll, BorderLayout.EAST);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Die"));
		return panel;
	}

	/**
	 * Method to return a panel containing the guess of the current player.
	 * @return JPanel A panel with the guess of the current player.
	 */
	private JPanel playerGuess() {
		JPanel panel = new JPanel();
		JLabel guessLabel = new JLabel("Guess");
		this.guess = new JTextField(20);
		this.guess.setEditable(false);
		panel.add(guessLabel, BorderLayout.NORTH);
		panel.add(this.guess, BorderLayout.EAST);
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
		this.guessResult = new JTextField(10);
		this.guessResult.setEditable(false);
		panel.add(response, BorderLayout.NORTH);
		panel.add(this.guessResult, BorderLayout.EAST);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));
		return panel;
	}
	//Add any necessary action listeners for the GUI below.
	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == nextPlayer) Board.getInstance().nextTurn();
			else Board.getInstance().makeAccusation();
		}
	}

	/**
	 * Updates the JTextField showing the current player's roll.
	 * @param roll The number the player rolled.
	 */
	public void setRollText(int roll) {
		this.roll.setText(Integer.toString(roll));
	}

	/**
	 * Updates the JTextField showing the current player.
	 * @param next The name of the current player.
	 */
	public void setTurnText(String next) {
		this.turn.setText(next);
	}
	
	/**
	 * Updates the JTextField showing the current player's guess.
	 * @param guess The player's guess.
	 */
	public void setGuessText(String guessText) {
		this.guess.setText(guessText);
	}
	
	/**
	 * Updates the JTextField showing the revealed card after a guess..
	 * @param card The revealed card name.
	 */
	public void setGuessResultText(String result) {
		this.guessResult.setText(result);
	}


}
