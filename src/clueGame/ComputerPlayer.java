package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import clueGame.Card.CardType;

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
	private BoardCell lastRoom; //Tracks the last room the CP visited for targeting purposes.

	//Constructors

	/**
	 * Default constructor.
	 */
	public ComputerPlayer() {
		super();
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
	public Solution createSuggestion(BoardCell location, Set<Card> dealtCards) {
		Solution suggestion = new Solution();

		ArrayList<Card> people = new ArrayList<Card>();
		ArrayList<Card> weapons = new ArrayList<Card>();

		//Adds unseen cards to the respective arrays
		for (Card c : dealtCards) {
			boolean addCard = true;
			for (int i = 0; i < hand.size(); i++) {
				if (c.getCardName().equals(hand.get(i).getCardName())) {
					addCard = false; //Card has same name as a card revealed to the player, so it won't be added.
				}
			}
			if (addCard) {
				//Adds card to correct array list based on type.
				switch(c.getCardType()) {
				case PERSON: people.add(c); break;
				case WEAPON: weapons.add(c); break;
				case ROOM: break;
				}
			}
		}

		//Randomly chooses an unseen person
		Random rand = new Random();
		suggestion.person = people.get(rand.nextInt(people.size())).getCardName();
		//Randomly chooses a person
		suggestion.weapon = weapons.get(rand.nextInt(weapons.size())).getCardName();

		char i = location.getInitial();
		switch(i) {
		case 'A': suggestion.room = "Art Room"; break;
		case 'B': suggestion.room = "Ballroom"; break;
		case 'C': suggestion.room = "Conservatory"; break;
		case 'K': suggestion.room = "Kitchen"; break;
		case 'L': suggestion.room = "Library"; break;
		case 'M': suggestion.room = "Master Bedrom"; break;
		case 'Q': suggestion.room = "Servant Quarters"; break;
		case 'R': suggestion.room = "Trophy Room"; break;
		case 'T': suggestion.room = "Theatre"; break;
		}

		return suggestion;		
	}

	/**
	 * Resets the last visited room for testing purposes only.
	 */
	public void resetRoom() {
		lastRoom = new BoardCell();
	}
}
