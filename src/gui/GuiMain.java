package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * <h1>GuiMain</h1>
 * This class creates a JFrame, calls constructors
 * for the Control GUI and Board GUI and then begins the game.
 * @author Adam Nelson, Youjun Lee
 * @since 2017-11-10
 * @see ControlGui
 *
 */
public class GuiMain {
	
	/**
	 * Main method creates a JFrame for the GUI, and adds
	 * Control GUI and Board GUI panels to the frame.
	 * @param args No arguments used.
	 */
	public static void main(String[] args) {
		//Create JFrame window
		JFrame frame = new JFrame();
		//Set size and default close operation
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Set name of window
		frame.setTitle("Clue Game");
		
		//Add the ControlGui
		frame.add(new ControlGui(), BorderLayout.SOUTH);
		
		
		
		//Make window visible
		frame.setVisible(true);
	}
} //End of Class
