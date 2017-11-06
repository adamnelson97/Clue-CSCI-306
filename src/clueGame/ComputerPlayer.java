package clueGame;

import java.awt.Color;
import java.util.Random;
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
	private BoardCell lastRoom; //Tracks the last room the CP visited for targeting purposes

	//Constructors

	/**
	 * Default constructor.
	 */
	public ComputerPlayer() {
		this.lastRoom = new BoardCell(); //Stores a default cell for the "first room."
	}

	/**
	 * Generates a ComputerPlayer from a config file.
	 * @param name Name of the player.
	 * @param col The player's color.
	 * @param r The starting row for the player.
	 * @param c The starting column for the player.
	 */
	public ComputerPlayer(String name, Color col, int r, int c) {
		super(name, col, r, c);
		this.lastRoom = new BoardCell(); //Stores a default cell for the "first room."
	}

	//Methods


	/**
	 * Randomly selects a destination from the list of viable targets.
	 * @param targets The set of all possible destinations.
	 * @return BoardCell The randomly chosen destination.
	 */
	public BoardCell pickLocation(Set<BoardCell> targets) {
		for (BoardCell cell : targets) {
			if (cell.isDoorway()) {
				//If the target is a room, determine if the CP just left that room
				if (!(cell.getColumn() == lastRoom.getColumn() && cell.getRow() == lastRoom.getRow())) {
					lastRoom = cell; //Stores the new last visit room.
					//System.out.println("New Room: " + lastRoom.toString());
					return cell; //Returns the target if it is a new room.
				}
			}
		}

		//If no rooms are present in the set:
		BoardCell[] targs = targets.toArray(new BoardCell[targets.size()]); //Puts the contents of the set into an array
		Random rand = new Random();
		return targs[rand.nextInt(targs.length)]; //Randomly returns a cell from the array
	}

	/**
	 * The computer randomly makes an accusation based off the cards it has
	 * in its hand and cards that it has already seen.
	 */
	public Solution makeAccusation() {
		//TODO complete makeAccusation method
		return null;
	}

	/**
	 * The computer randomly makes a suggestion based off the cards it has
	 * in its hand and cards that it has already seen.
	 */
	public void createSuggestion() {
		//TODO complete createSuggestion method
		return;
	}

	/**
	 * Resets the last visited room for testing purposes only.
	 */
	public void resetRoom() {
		lastRoom = new BoardCell();
	}
}
