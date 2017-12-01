package gui;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clueGame.Board;
import clueGame.BoardCell;

/**
 * <h1>SuggestionDialog</h1>
 * Custom dialog for the human player to make a suggestion.
 * @author Adam Nelson, Youjun Lee
 * @since 2017-12-01
 * @see JDialog
 *
 */
public class SuggestionDialog extends JDialog {
	
	/**
	 * Default constructor.
	 * @param currentLoc The current cell the player is on.
	 * @param theBoard The game board.
	 */
	public SuggestionDialog(BoardCell currentLoc, Board theBoard) {
		super();
		JPanel panel = new JPanel();
		setContentPane(panel);
		setSize(400, 450);
		setResizable(true);
		setTitle("Make a guess");
		
		panel.setLayout(new GridLayout(4,2));
		
		//Add label for room.
		panel.add(new JLabel("Your room"));
		
		//Add non-editable JTextField for the current room.
		JTextField room = new JTextField(10);
		room.setEditable(false); //The player should not be able to type in this area.
		//Retrieve the name of the room from the initial of the current cell.
		room.setText(theBoard.getLegend().get(currentLoc.getInitial()));
		panel.add(room);
		
		//Add label for the person guess.
		panel.add(new JLabel("Person"));
		
		//Add combo box to select the person.
		JComboBox<String> person = personBox();
		panel.add(person);
		
		//Add label for the weapon guess.
		panel.add(new JLabel("Weapon"));
		
		//Add combo box to select the weapon.
		JComboBox weapon = new JComboBox();
		panel.add(weapon);
		
		//Add button to submit suggestion.
		
		//Add button to cancel.
		
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
		
	}

}
