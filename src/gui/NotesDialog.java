package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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
@SuppressWarnings("serial")
public class NotesDialog extends JDialog {

	//https://examples.javacodegeeks.com/desktop-java/swing/jdialog/java-jdialog-example/
	/**
	 * Default constructor.
	 */
	public NotesDialog() {
		super();
		JPanel panel = new JPanel();
		setContentPane(panel);
		setSize(600,600);
		setResizable(true);
		setTitle("Detective Notes");
		//Create a panel to store radio buttons and combo boxes on.
		panel.setLayout(new GridLayout(3,2));

		//Get check boxes for People.
		panel.add(getCheckPeople());
		//Get combo box for People.
		panel.add(getComboPeople());
		//Get check boxes for Rooms.
		panel.add(getCheckRooms());
		//Get combo box for Rooms.
		panel.add(getComboRooms());
		//Get check boxes for Weapons.
		panel.add(getCheckWeapons());
		//Get combo box for Weapons.
		panel.add(getComboWeapons());

		//We want to retain the changes the player makes.
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		//setVisible(true);
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

	/**
	 * Creates a panel with a combo box for the player to store
	 * their best guess of who did it.
	 * @return JPanel Combo box of people.
	 */
	private JPanel getComboPeople() {
		JPanel panel = new JPanel();		
		JComboBox<String> people = new JComboBox<String>();
		people.addItem("Unsure");
		people.addItem("Miss Scarlett");
		people.addItem("Professor Plum");
		people.addItem("Mrs. Peacock");
		people.addItem("Mr. Green");
		people.addItem("Colonel Mustard");
		people.addItem("Mrs. White");
		panel.add(people);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Person Guess"));
		return panel;
	}

	/**
	 * Creates a panel with check boxes for each revealed Room card.
	 * @return JPanel Grid of check boxes for each room.
	 */
	private JPanel getCheckRooms() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5,2));
		//Add the rooms to the panel.
		panel.add(new JCheckBox("Art Room"));
		panel.add(new JCheckBox("Ballroom"));
		panel.add(new JCheckBox("Conservatory"));
		panel.add(new JCheckBox("Kitchen"));
		panel.add(new JCheckBox("Library"));
		panel.add(new JCheckBox("Master Bedroom"));
		panel.add(new JCheckBox("Servant Quarters"));
		panel.add(new JCheckBox("Trophy Room"));
		panel.add(new JCheckBox("Theatre"));
		panel.setBorder(new TitledBorder(new EtchedBorder(), "People"));
		return panel;
	}

	/**
	 * Creates a panel with a combo box for the player to store
	 * their best guess of where it happened.
	 * @return JPanel Combo box of rooms.
	 */
	private JPanel getComboRooms() {
		JPanel panel = new JPanel();		
		JComboBox<String> rooms = new JComboBox<String>();
		rooms.addItem("Unsure");
		rooms.addItem("Art Room");
		rooms.addItem("Ballroom");
		rooms.addItem("Conservatory");
		rooms.addItem("Kitchen");
		rooms.addItem("Library");
		rooms.addItem("Master Bedroom");
		rooms.addItem("Servant Quarters");
		rooms.addItem("Trophy Room");
		rooms.addItem("Theatre");
		panel.add(rooms);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Room Guess"));
		return panel;
	}
	
	/**
	 * Creates a panel with check boxes for each revealed Weapon card.
	 * @return JPanel Grid of check boxes for each weapon.
	 */
	private JPanel getCheckWeapons() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,2));
		//Add the weapons to the panel.
		panel.add(new JCheckBox("Candlestick"));
		panel.add(new JCheckBox("Knife"));
		panel.add(new JCheckBox("Pipe"));
		panel.add(new JCheckBox("Revolver"));
		panel.add(new JCheckBox("Rope"));
		panel.add(new JCheckBox("Wrench"));
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
		return panel;
	}
	
	/**
	 * Creates a panel with a combo box for the player to store
	 * their best guess of what it was done with.
	 * @return JPanel Combo box of weapons.
	 */
	private JPanel getComboWeapons() {
		JPanel panel = new JPanel();		
		JComboBox<String> weapons = new JComboBox<String>();
		weapons.addItem("Unsure");
		weapons.addItem("Candlestick");
		weapons.addItem("Knife");
		weapons.addItem("Pipe");
		weapons.addItem("Revolver");
		weapons.addItem("Rope");
		weapons.addItem("Wrench");
		panel.add(weapons, BorderLayout.CENTER);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Weapon Guess"));
		return panel;
	}
	
} //End of Class
