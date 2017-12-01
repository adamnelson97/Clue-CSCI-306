package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Solution;

/**
 * <h1>SuggestionDialog</h1>
 * Custom dialog for the human player to make a suggestion.
 * @author Adam Nelson, Youjun Lee
 * @since 2017-12-01
 * @see JDialog
 *
 */
@SuppressWarnings("serial")
public class SuggestionDialog extends JDialog {
	
	private Solution suggestion; //The player's suggestion to be returned.
	private JComboBox<String> person;
	private JComboBox<String> weapon;
	
	/**
	 * Default constructor.
	 * @param currentLoc The current cell the player is on.
	 * @param theBoard The game board.
	 */
	public SuggestionDialog(BoardCell currentLoc, Board theBoard) {
		super();
		suggestion = new Solution(); //Initialize solution
		
		JPanel panel = new JPanel();
		setContentPane(panel);
		setSize(300, 350);
		setResizable(true);
		/*
		 * setModal prohibits the program from continuing until the dialog window
		 * is closed by either the submit or cancel button.
		 */
		setModal(true);
		setTitle("Make a Guess");
		
		panel.setLayout(new GridLayout(4,2));
		
		//Add label for room.
		panel.add(new JLabel("Your room"));
		
		//Add non-editable JTextField for the current room.
		JTextField room = new JTextField(10);
		room.setEditable(false); //The player should not be able to type in this area.
		//Retrieve the name of the room from the initial of the current cell.
		room.setText(theBoard.getLegend().get(currentLoc.getInitial()));
		panel.add(room);
		suggestion.room = room.getText(); //Stores the current room into the suggestion.
		
		//Add label for the person guess.
		panel.add(new JLabel("Person"));
		
		//Add combo box to select the person.
		person = personBox();
		panel.add(person);
		
		//Add label for the weapon guess.
		panel.add(new JLabel("Weapon"));
		
		//Add combo box to select the weapon.
		weapon = weaponBox();
		panel.add(weapon);
		
		//Add button to submit suggestion.
		JButton submit = new JButton("Submit");
		submit.addActionListener(new SubmitButtonListener(this));
		panel.add(submit);
		
		//Add button to cancel.
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new CancelButtonListener(this));
		panel.add(cancel);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		/*
		 * Make the dialog visible after creation, since it is specifically called
		 * from the HumanPlayer class.
		 */
		setVisible(true);
	}
	
	/**
	 * Creates a JComboBox with options for each player.
	 * @return JComboBox The combo box.
	 */
	private JComboBox<String> personBox() {
		JComboBox<String> person = new JComboBox<String>();
		person.addItem("Miss Scarlett");
		person.addItem("Professor Plum");
		person.addItem("Mrs. Peacock");
		person.addItem("Mr. Green");
		person.addItem("Colonel Mustard");
		person.addItem("Mrs. White");
		return person;
	}
	
	/**
	 * Creates a JComboBox with options for each weapon.
	 * @return JComboBox The combo box.
	 */
	private JComboBox<String> weaponBox() {
		JComboBox<String> weapon = new JComboBox<String>();
		weapon.addItem("Candlestick");
		weapon.addItem("Knife");
		weapon.addItem("Pipe");
		weapon.addItem("Revolver");
		weapon.addItem("Rope");
		weapon.addItem("Wrench");
		return weapon;
	}

	//Action Listeners
	
	private class CancelButtonListener implements ActionListener {
		SuggestionDialog dialog;
		public CancelButtonListener(SuggestionDialog dialog) {
			this.dialog = dialog;
		}
		public void actionPerformed(ActionEvent arg0) {
			dialog.dispose();
			
		}
		
	}
	
	private class SubmitButtonListener implements ActionListener {
		SuggestionDialog dialog;
		public SubmitButtonListener(SuggestionDialog dialog) {
			this.dialog = dialog;
		}public void actionPerformed(ActionEvent e) {
			suggestion.person = (String) person.getSelectedItem();
			suggestion.weapon = (String) weapon.getSelectedItem();
			dialog.dispose();
		}
	}

	public Solution getSuggestion() {
		return suggestion;
	}
}
