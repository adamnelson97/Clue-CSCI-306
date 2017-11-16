package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import clueGame.Board;

/**
 * <h1>GuiMain</h1>
 * This class creates a JFrame, calls constructors
 * for the Control GUI and Board GUI and then begins the game.
 * @author Adam Nelson, Youjun Lee
 * @since 2017-11-10
 * @see ControlGui
 * @see BoardGui
 * 
 */
@SuppressWarnings("serial")
public class GuiMain extends JFrame {
	private GuiMain gui; //Self referencing frame.
	private NotesDialog notes; //Detective notes custom dialog.
	private static Board board; //The game board.
	private String playerCharacter; //The player the user decides to be.

	/**
	 * Method creates a JFrame for the GUI, and adds
	 * Control GUI and Board GUI panels to the frame.
	 */
	public GuiMain() {
		gui = this;
		notes = new NotesDialog();
		
		//Set up the game board.
		setUp();
		
		//Set size and default close operation.
		setSize(700, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Set name of window
		setTitle("Clue Game");

		//Add the ControlGui.
		add(new ControlGui(board), BorderLayout.SOUTH);
		//Add the BoardGui.
		add(new BoardGui(board), BorderLayout.NORTH);

		//Create the Menu Bar and add a Detective Notes Option.
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(detectiveMenu());

		
	}

	//List the MenuBar menus here.
	private JMenu detectiveMenu() {
		JMenu detective = new JMenu("Detective Notes");
		//Add items to the menu.
		detective.add(detectiveNotesItem(notes));
		return detective;
	}

	//List the menu items here.
	private JMenuItem detectiveNotesItem(NotesDialog note) {
		JMenuItem notesItem = new JMenuItem("Notes");
		//Create the listener.

		notesItem.addActionListener(new NotesItemListener(notes));
		return notesItem;
	}

	//Listeners go here.
	class NotesItemListener implements ActionListener {
		//Have to create instance variable so listener can access the dialog.
		private NotesDialog note;
		
		public NotesItemListener(NotesDialog note) {
			this.note = note;
		}
		public void actionPerformed(ActionEvent e) {
			//Create custom dialog to store notes here
			note.setVisible(true);
		}
	}
	
	//Other Class Methods
	/**
	 * Initializes the game board.
	 */
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueGameLayout.csv", "ClueGameLegend.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
	}

	/**
	 * Main creates a new GuiMain object and opens the window. 
	 * @param args No arguments used.
	 */
	public static void main(String[] args) {
		//Create new GuiMain object.
		GuiMain GUI = new GuiMain();
		//Make window visible.
		GUI.setVisible(true);
	}
} //End of Class
