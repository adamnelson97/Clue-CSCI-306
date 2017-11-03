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

	public Card(String cardName, int x) {
		this.cardName = cardName;
		switch(x) {
		case 1: cardType = CardType.PERSON; break;
		case 2: cardType = CardType.WEAPON; break;
		case 3: cardType = CardType.ROOM; break;
		}
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

}
