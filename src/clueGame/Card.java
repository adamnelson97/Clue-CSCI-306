package clueGame;

/**
 * <h1>Card</h1>
 * This class stores the information for a single card,
 * which can be a person, weapon, or room.
 * @author Adam Nelson, Youjun Lee
 * @version 1.0
 * @since 2017-11-02
 *
 */
public class Card {

	//Variables
	private String cardName;
	public enum CardType {
		PERSON, WEAPON, ROOM;
	}
	private CardType cardType;

	//Constructors

	/**
	 * Parameterized constructor that creates a Card from a data file.
	 * @param cardName The name of the Card.
	 * @param cardType The type of the card: Person, Weapon, or Room.
	 */
	public Card(String cardName, CardType cardType) {
		this.cardName = cardName;
		this.cardType = cardType;
	}
	
	//Methods

	/**
	 * Comparator that determines if two cards are equal.
	 * @return boolean Whether the two cards are equal.
	 */
	public boolean equals() {
		//TODO complete equals method
		return false;
	}

	
	//Getters
	
	public String getCardName() {
		return cardName;
	}

	public CardType getCardType() {
		return cardType;
	}

}
