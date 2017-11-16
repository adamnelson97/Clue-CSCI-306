package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.Board;
import clueGame.Card;
import clueGame.Card.CardType;

/**
 * <h1>BoardGui</h1>
 * BoardGui is the upper section of the main GUI
 * used to play the game. In includes panels displaying what cards
 * the player currently possesses, as well as the game board.
 * @author Adam Nelson, Youjun Lee
 * @since 2017-11-10
 *
 */
@SuppressWarnings("serial")
public class BoardGui extends JPanel {
	private BoardGui gui; //Self referencing object.
	private static Board board; //The game board
	
	/**
	 * Default Constructor for BoardGui.
	 * When this is called, it automatically adds all subpanels.
	 */	
	public BoardGui(Board board) {
		gui = this;
		this.board = board; //Passes the game board from GuiMain into the BoardGui.
		add(humanHand(), BorderLayout.EAST);
		add(board, BorderLayout.CENTER);
	}
	
	/**
	 * Creates a panel showing what cards the user has in their hand.
	 * @return JPanel The panel with all the cards the user has.
	 */
	private JPanel humanHand() {
		//Create master panel for all cards.
		JPanel masterPanel = new JPanel();
		//Create panels for each type of card.
		JPanel people = this.cardPanel("People", CardType.PERSON);
		JPanel rooms = this.cardPanel("Rooms", CardType.ROOM);
		JPanel weapons = this.cardPanel("Weapons", CardType.WEAPON);
		setLayout(new GridLayout(0,1));
		add(people);
		add(rooms);
		add(weapons);
		setBorder(new TitledBorder(new EtchedBorder(), "My Cards"));
		return masterPanel;
	}
	
	/**
	 * Creates a panel for a specific card type.
	 * @param name The card type that is being displayed.
	 * @param type The specified card type.
	 * @return JPanel The panel showing every card the user has of that type.
	 */
	private JPanel cardPanel(String name, CardType type) {
		JPanel panel = new JPanel();
		JTextArea cards = new JTextArea(3,15); //The player has a maximum of 3 cards.
		String displayText = "";
		for (Card c : board.getHuman().getHand()) {
			if (c.getCardType() == type) displayText += c.getCardName() + "\n";
		}
		cards.setText(displayText);
		cards.setEditable(false);
		//Add the display field to the panel
		panel.add(cards);
		panel.setBorder(new TitledBorder(new EtchedBorder(), name));
		return panel;
	}
}
