package clueGame;

import java.awt.Color;
import java.util.Set;

/**
 * <h1>ComputerPlayer</h1>
 * This class extends Player by adding methods specific to CPUs
 * as opposed to human players.
 * @author Adam Nelson, Youjun Lee
 * @version 1.0
 * @since 2017-11-02
 * @see Player
 *
 */
public class ComputerPlayer extends Player {

	//Variables
	
	//Constructors
	
	/**
	 * Generates a ComputerPlayer from a config file.
	 * @param name Name of the player.
	 * @param col The player's color.
	 * @param r The starting row for the player.
	 * @param c The starting column for the player.
	 */
	public ComputerPlayer(String name, Color col, int r, int c) {
		super(name, col, r, c);
	}
	
	//Methods
	

	/**
	 * Randomly selects a destination from the list of viable targets.
	 * @param targets The set of all possible destinations.
	 * @return BoardCell The randomly chosen destination.
	 */
	public BoardCell pickLocation(Set<BoardCell> targets) {
		//TODO complete pickLocation method
		return null;
	}
	
	/**
	 * The computer randomly makes an accusation based off the cards it has
	 * in its hand and cards that it has already seen.
	 */
	public void makeAccusation() {
		//TODO complete makeAccusation method
		return;
	}
	
	/**
	 * The computer randomly makes a suggestion based off the cards it has
	 * in its hand and cards that it has already seen.
	 */
	public void createSuggestion() {
		//TODO complete createSuggestion method
		return;
	}
}
