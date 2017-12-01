package gui;

import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

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
	 */
	public SuggestionDialog() {
		super();
		JPanel panel = new JPanel();
		setContentPane(panel);
		setSize(400, 450);
		setResizable(true);
		setTitle("Make a guess");
		
		panel.setLayout(new GridLayout(4,2));
		//Add label for room.
		
		//Add non-editable JTextField for the current room.
		
		//Add label for the person guess.
		
		//Add combo box to select the person.
		
		//Add label for the weapon guess.
		
		//Add combo box to select the weapon.
		
		//Add button to submit suggestion.
		
		//Add button to cancel.
		
	}

}
