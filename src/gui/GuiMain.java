package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

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
public class GuiMain extends JFrame {
	private GuiMain gui; //Self referencing frame.

	/**
	 * Method creates a JFrame for the GUI, and adds
	 * Control GUI and Board GUI panels to the frame.
	 */
	public GuiMain() {
		gui = this;
		//Set size and default close operation.
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Set name of window
		setTitle("Clue Game");

		//Add the ControlGui.
		add(new ControlGui(), BorderLayout.SOUTH);
		//Add the BoardGui.
		add(new BoardGui(), BorderLayout.NORTH);

		//Create the Menu Bar and add a Detective Notes Option.
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
	}
	
	//List the MenuBar menus here.
	private JMenu detectiveMenu() {
		JMenu detective = new JMenu("Detective Notes");
		//Add items to the menu.
		detective.add(detectiveNotesItem());
		return detective;
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
