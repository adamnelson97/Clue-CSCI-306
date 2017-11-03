package clueGame;

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
