package gui;

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
	
	
	public static void main(String[] args) {
		//Create JFrame window
		JFrame frame = new JFrame();
		//Set size and default close operation
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Set name of window
		frame.setTitle("Clue Game");
		//Make window visible
		frame.setVisible(true);
	}
} //End of Class
