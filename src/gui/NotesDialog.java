package gui;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * <h1>NotesDialog</h1>
 * Custom dialog for storing the cards revealed to the player and
 * the player's best guess.
 * @author Adam Nelson, Youjun Lee
 * @since 2017-11-13
 *
 */
public class NotesDialog extends JDialog {

	//https://examples.javacodegeeks.com/desktop-java/swing/jdialog/java-jdialog-example/
	/**
	 * Default constructor.
	 * @param parent The frame the dialog is displayed on.
	 * @param title The string in the dialog's title bar.
	 */
	public NotesDialog(JFrame parent, String title) {
		super(parent, title);
		//Create a panel to store radio buttons and combo boxes on.
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,2));
		setSize(600,600);
		
		//Get check boxes for People.
		panel.add(getCheckPeople());
		//Get check boxes for Rooms.
		//panel.add(getCheckRooms());
		//Get check boxes for Weapons.
		//panel.add(getCheckWeapons());
		//Get combo box for People.
		//panel.add(getComboPeople());
		//Get combo box for Rooms.
		//panel.add(getComboRooms());
		//Get combo box for Weapons.
		//panel.add(getComboWeapons());
		
		//We want to retain the changes the player makes.
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
	
	/**
	 * Creates a panel with check boxes for each revealed Person card.
	 * @return JPanel Grid of check boxes for each person.
	 */
	private JPanel getCheckPeople() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,2));
		//Add the people to the panel.
		panel.add(new JCheckBox("Miss Scarlett"));
		panel.add(new JCheckBox("Professor Plum"));
		panel.add(new JCheckBox("Mrs. Peacock"));
		panel.add(new JCheckBox("Mr. Green"));
		panel.add(new JCheckBox("Colonel Mustard"));
		panel.add(new JCheckBox("Mrs. White"));
		panel.setBorder(new TitledBorder(new EtchedBorder(), "People"));
		return panel;
	}
}
