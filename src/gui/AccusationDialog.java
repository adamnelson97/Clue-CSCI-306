package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import clueGame.Solution;

/**
 * <h1>AccusationDialog</h1>
 * Custom dialog for the human player to make an accusation.
 * @author Adam Nelson, Youjun Lee
 * @since 2017-12-04
 * @see JDialog
 *
 */
@SuppressWarnings("serial")
public class AccusationDialog extends JDialog {

	private Solution accusation;
	private JComboBox<String> room;
	private JComboBox<String> person;
	private JComboBox<String> weapon;
	private boolean submitted;

	/**
	 * Default constructor.
	 */
	public AccusationDialog() {
		super();
		accusation = new Solution(); //Initialize solution

		JPanel panel = new JPanel();
		setContentPane(panel);
		setSize(300, 350);
		setResizable(true);
		setModal(true);
		setTitle("Make a Guess");

		panel.setLayout(new GridLayout(4,2));

		//Add label for room.
		panel.add(new JLabel("Room"));

		//Add combo box to select the room.
		room = roomBox();
		panel.add(room);

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
	 * Creates a JComboBox with options for each room.
	 * @return JComboBox The combo box.
	 */
	private JComboBox<String> roomBox() {
		JComboBox<String> room = new JComboBox<String>();
		room.addItem("Art Room");
		room.addItem("Ballroom");
		room.addItem("Conservatory");
		room.addItem("Kitchen");
		room.addItem("Library");
		room.addItem("Master Bedroom");
		room.addItem("Servant Quarters");
		room.addItem("Trophy Room");
		room.addItem("Theatre");
		return room;

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
		AccusationDialog dialog;
		public CancelButtonListener(AccusationDialog dialog) {
			this.dialog = dialog;
		}
		public void actionPerformed(ActionEvent arg0) {
			submitted = false;
			dialog.dispose();

		}

	}

	private class SubmitButtonListener implements ActionListener {
		AccusationDialog dialog;
		public SubmitButtonListener(AccusationDialog dialog) {
			this.dialog = dialog;
		}
		public void actionPerformed(ActionEvent e) {
			accusation.room = (String) room.getSelectedItem();
			accusation.person = (String) person.getSelectedItem();
			accusation.weapon = (String) weapon.getSelectedItem();
			submitted = true;
			dialog.dispose();
		}
	}

	public Solution getAccusation() {
		return accusation;
	}

	public boolean isSubmitted() {
		return submitted;
	}
}
