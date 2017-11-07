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
	 * @param card The card being compared against.
	 * @return boolean Whether the two cards are equal.
	 */
	public boolean equals(Card card) {
		if (cardName.equals(card.getCardName())) {
			if (cardType == card.getCardType()) return true;
		}
		return false; //Returns false if either of the attributes does not match.
	}

	
	//Getters
	
	public String getCardName() {
		return cardName;
	}

	public CardType getCardType() {
		return cardType;
	}

	@Override
	public String toString() {
		return "Card [cardName=" + cardName + ", cardType=" + cardType + "]";
	}
	
	

}
