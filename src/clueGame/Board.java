/*
 * Class: Board
 * 
 * Authors: Nathaniel Fuller, Adam Nelson, Youjun Lee
 * 
 * Purpose: To represent the board used for the game 'Clue' including movement, possible player locations, rooms, and doorways.
 * 
 */

package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clueGame.Card.CardType;
import gui.AccusationDialog;
import gui.ControlGui;

/**
 * <h1>Board</h1>
 * This is the primary class that establishes the board and the data
 * contained therein. 
 * 
 * @author Adam Nelson, Nathaniel Fuller, Youjun Lee
 * @version 1.0
 * @since 2017-10-09
 *
 */
@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener {
	//Variables
	private int numRows; //The dimensions of the game board
	private int numColumns;
	public final static int MAX_BOARD_SIZE = 50;
	private BoardCell[][] board; //Stores the BoardCells in a 2D grid
	private Map<Character, String> legend; //Stores the room configuration, e.g. Character -> A, String -> Art Room
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> targets;
	private Set<BoardCell> visited;

	private String boardConfigFile = "ClueGameLayout.csv";
	private String roomConfigFile = "ClueGameLegend.txt";
	private String playerConfigFile = "PlayerLegend.txt";
	private String weaponConfigFile = "WeaponsLegend.txt";

	private Map<String, Player> players; //Set of all 6 players in the game
	private HumanPlayer human; //The user.
	private Map<String, Card> playerCards;
	private Map<String, Card> rooms; //Set of all 9 rooms in the game
	private Map<String, Card> weapons; //Set of all 6 weapons in the game
	private Map<String, Card> deck; //The entire deck of playing cards
	private Set<Card> cardDeck; //All dealt cards.

	private Solution solution; //The solution to the game.
	private int turn; //Used to store what player has the next turn in the game.
	ControlGui control; //Used to display information about the current turn/suggestions.

	// Variable used for the singleton pattern
	private static Board theInstance = new Board();

	// -- Constructors --

	/**
	 * Singleton constructor to ensure only one object of the board exists.
	 */
	// Private constructor to ensure only one can be created
	private Board() {
		// Set up empty data structures
		legend = new HashMap<Character, String>();
		adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();
		targets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
		players = new HashMap<String, Player>();
		rooms = new HashMap<String, Card>();
		weapons = new HashMap<String, Card>();
		playerCards = new HashMap<String, Card>();
		deck = new HashMap<String, Card>();
		cardDeck = new HashSet<Card>();
		solution = new Solution();
		turn = 0;
		setSize(getNumColumns() * BoardCell.getWidth(), getNumRows() * BoardCell.getHeight());
	}

	//Methods

	/**
	 * This sets up the board by calling functions to load the corresponding
	 * configuration files and then calculate the adjacency sets for each cell.
	 */
	public void initialize() {
		try {
			loadRoomConfig();
		} catch (BadConfigFormatException e) {
			//The only exception loadRoomConfig throws is for rooms of improper types 
			System.out.println(e);
		}

		try {
			loadBoardConfig();
		} catch (BadConfigFormatException e) {
			System.out.println(e);
		}

		// Populate adjMatrix
		calcAdjacencies();

		loadConfigFiles(); //Populates players and weapons sets
		dealer(); //Creates a solution and deals cards to the players

		//Add the mouse listener to the board.
		addMouseListener(this);
	}

	/**
	 * This loads the legend for the different room types. Throws an exception
	 * if the file is not properly formatted or if the file cannot be found.
	 * 
	 * @throws BadConfigFormatException Throws various formatting exceptions
	 */
	@SuppressWarnings("resource")
	public void loadRoomConfig() throws BadConfigFormatException {
		FileReader roomCfg = null;
		legend.clear();

		try {
			roomCfg = new FileReader(roomConfigFile);
		} catch (FileNotFoundException e) {
			System.out.println("Room Config File not found.");
		}

		Scanner in = new Scanner(roomCfg);
		String temp; //Used to grab the line from the file
		String[] line = new String[3]; //Creates new string array to store data from the file

		while (in.hasNextLine()) {
			temp = in.nextLine(); //Takes in entire line, ex: A, Art Room, Card
			line = temp.split(", "); //Splits line by the commas
			if (line[2].equals("Card") || line[2].equals("Other")) { //Checks for proper room type
				legend.put(line[0].charAt(0), line[1]);
				if (line[2].equals("Card")) {
					rooms.put(line[1], new Card(line[1], CardType.ROOM));
					deck.put(line[1], new Card(line[1], CardType.ROOM));
				}
			}
			else {
				throw new BadConfigFormatException("Room is not type Card or Other. Type is: " + line[2]);
			}
		}
		in.close();
	}

	/**
	 * This loads the data of the board, and throws an exception if the data is not
	 * formatted properly or if the file cannot be found.
	 * 
	 * @throws BadConfigFormatException Throws various formatting exceptions
	 */
	@SuppressWarnings("resource")
	public void loadBoardConfig() throws BadConfigFormatException {
		FileReader boardCfg = null;

		try {
			boardCfg = new FileReader(boardConfigFile);
		} catch (FileNotFoundException e) {
			System.out.println("Board Config File not found.");
		}

		//Open file
		Scanner in = new Scanner(boardCfg);
		ArrayList<String[]> lines = new ArrayList<String[]>(); //Stores each line of the board as a separate string array
		String[] line; //Stores a single line from the file

		//Add each line to the ArrayList
		while (in.hasNextLine()) {
			line = in.nextLine().split(","); //Takes in a line and splits each cell by the commas
			lines.add(line); //Adds the String[] to the ArrayList
		}

		int size = lines.get(0).length; //Pulls the first String[] from the ArrayList and checks its size

		//Check that each line has the same number of columns. Else, throw an exception
		for (int i = 1; i < lines.size(); i++) {
			if (lines.get(i).length != size) {
				throw new BadConfigFormatException("Error: Row 0 has " + size + " columns.\n"+ "Row " + i + " has " + lines.get(i).length + " columns.");
			}
		}

		//Get dimensions of board
		numRows = lines.size();
		numColumns = size;
		//Initialize board
		board = new BoardCell[numRows][numColumns];

		for (int i = 0; i < numRows; i++) { //For each line...
			for (int j = 0; j < numColumns; j++) { //For each cell in that line...
				line = lines.get(i); //Accesses a line of cells

				//Checks that the room is a valid room
				if (!legend.containsKey(line[j].charAt(0))) {
					throw new BadConfigFormatException("Error: Invalid Room character " + line[j].charAt(0) + " at (" + i + ", " + j + ")");
				}

				if (line[j].length() > 1) { //If a cell has a door attached to it...
					//Checks that the door direction is valid
					try {
						board[i][j] = new BoardCell(i, j, line[j].charAt(0), line[j].charAt(1));
					} catch (BadConfigFormatException e) {
						System.out.println(e);
					}
				}
				else { //The cell has no door
					board[i][j] = new BoardCell(i, j, line[j].charAt(0));
				}
			}
		}
	}

	/**
	 * This method creates a set for each cell that contains the cells
	 * it is adjacent to.
	 */
	public void calcAdjacencies() {
		// Generates map of all cells and their adjacent cells and stores it in adjMtx
		BoardCell currCell;
		Set<BoardCell> adjSet;
		for(int i = 0; i < numRows; i++) { // Iterate through all cells in grid[][]
			for(int j = 0; j < numColumns; j++) {
				currCell = board[i][j];
				adjSet = new HashSet<BoardCell>();

				// Do not include adjacencies for cells in rooms or closets
				if(currCell.isWalkway() || currCell.isDoorway()) {
					// Check to see whether each neighboring cell exists, i.e. Index not out of bounds, and add it to adjSet.
					// Additionally, check if the cell is valid, i.e. not a Room, or a properly facing doorway
					if(i > 0 && (currCell.getDoorDirection() == DoorDirection.NONE || currCell.getDoorDirection() == DoorDirection.UP)) {
						if(board[i-1][j].isWalkway() || board[i-1][j].getDoorDirection() == DoorDirection.DOWN) {
							adjSet.add(board[i-1][j]);
						}
					}
					if(i < numRows - 1 && (currCell.getDoorDirection() == DoorDirection.NONE || currCell.getDoorDirection() == DoorDirection.DOWN)) {
						if(board[i+1][j].isWalkway() || board[i+1][j].getDoorDirection() == DoorDirection.UP) {
							adjSet.add(board[i+1][j]);
						}
					}
					if(j > 0 && (currCell.getDoorDirection() == DoorDirection.NONE || currCell.getDoorDirection() == DoorDirection.LEFT)) {
						if(board[i][j-1].isWalkway() || board[i][j-1].getDoorDirection() == DoorDirection.RIGHT) {
							adjSet.add(board[i][j-1]);
						}
					}
					if(j < numColumns - 1 && (currCell.getDoorDirection() == DoorDirection.NONE || currCell.getDoorDirection() == DoorDirection.RIGHT)) {
						if(board[i][j+1].isWalkway() || board[i][j+1].getDoorDirection() == DoorDirection.LEFT) {
							adjSet.add(board[i][j+1]);
						}
					}
				}

				adjMatrix.put(currCell, adjSet);
			}
		}
	}

	/**
	 * This determines possible new destinations for a given path length from
	 * the previous cell.
	 * 
	 * @param cellX Location of the cell on the x-axis.
	 * @param cellY Location of the cell on the y-axis.
	 * @param pathLength The number of unique cells that can be traveled.
	 */
	public void calcTargets(int cellX, int cellY, int pathLength) {
		// If the function is being called for the first time (Since we clear visited after every completed recursion)
		// Clear targets.
		if(visited.isEmpty()) {
			targets.clear();
		}
		BoardCell cell = board[cellX][cellY];
		Set<BoardCell> adj = getAdjList(cellX, cellY);
		visited.add(cell);
		// Iterate through all adjacent cells
		for(BoardCell c : adj) {
			// If the cell hasn't been visited
			if(!visited.contains(c)) {
				// If the path is length 1, OR the cell is a properly oriented doorway, add the cell to targets.
				if(pathLength == 1 || c.isDoorway()) {
					targets.add(c);
					//System.out.println("Adding target: " + c.toString()); //Used for debugging
				}
				// Otherwise, add the adjacent cell to visited, call the function from that cell with a shorter pathLength.
				// Then, after that has completed, remove the cell from visited.
				else {
					calcTargets(c.getRow(), c.getColumn(), (pathLength-1));
					visited.remove(c);
				}
			}
		}
		// If we have completed the recursion and are closing, remove the starting cell from visited
		if(visited.size() == 1 && visited.contains(cell)) {
			visited.clear();
		}
	}

	/**
	 * Loads the Players, and Weapons configuration files.
	 */
	@SuppressWarnings("resource")
	public void loadConfigFiles() {
		//Load the players
		FileReader playerCfg = null;
		players.clear();
		boolean madeHuman = false; //Tracks if a human player has been made yet.

		try {
			playerCfg = new FileReader(playerConfigFile);
		} catch (FileNotFoundException e) {
			System.out.println("Player Config File not found.");
		}

		Scanner in = new Scanner(playerCfg);
		String temp; //Used to grab the line from the file
		String[] line = new String[4]; //Creates new string array to store data from the file
		Color color; //Temporarily stores color object for each player

		while (in.hasNextLine()) {
			temp = in.nextLine(); //Takes in entire line, e.g. Mrs. White,WHITE,6,1
			line = temp.split(","); //Splits line by the commas
			color = convertColor(line[1]);
			if (!madeHuman) {
				human = new HumanPlayer(line[0], color, Integer.parseInt(line[2]), Integer.parseInt(line[3]));
				players.put(line[0], human); //Puts players into map of players
				playerCards.put(line[0], new Card(human.getPlayerName(), CardType.PERSON)); //Puts player into map of cards
				deck.put(line[0], new Card(human.getPlayerName(), CardType.PERSON)); //Puts player into master deck
				madeHuman = true;
			}
			else {
				ComputerPlayer x = new ComputerPlayer(line[0], color, Integer.parseInt(line[2]), Integer.parseInt(line[3]));
				//System.out.println(x.toString());
				players.put(line[0], x); //Puts players into map of players
				playerCards.put(line[0], new Card(x.getPlayerName(), CardType.PERSON)); //Puts player into map of cards
				deck.put(line[0], new Card(x.getPlayerName(), CardType.PERSON)); //Puts player into master deck
			}
		}

		//Load the weapons
		FileReader weaponCfg = null;
		weapons.clear();

		try {
			weaponCfg = new FileReader(weaponConfigFile);
		} catch (FileNotFoundException e) {
			System.out.println("Weapon Config File not found.");
		}

		Scanner wp = new Scanner(weaponCfg);

		while (wp.hasNextLine()) {
			temp = wp.nextLine(); //Stores the weapon name
			weapons.put(temp, new Card(temp, CardType.WEAPON)); //Puts weapon into map of weapons
			deck.put(temp, new Card(temp, CardType.WEAPON)); //Puts weapon into master deck
		}
	}

	/**
	 * Used to convert a string from config file to a color object
	 * @param strColor The string of the desired color
	 * @return Color The color object.
	 */
	public Color convertColor(String strColor) {
		Color color;
		try {
			// We can use reflection to convert the string to a color
			Field field = Class.forName("java.awt.Color").getField(strColor.trim());
			color = (Color)field.get(null);
		} catch (Exception e) {
			color = null; // Not defined
		}
		return color;
	}

	/**
	 * Randomly selects a solution of one Room, Weapon, and Person.
	 */
	public void dealer() {
		ArrayList<Card> roomCards = new ArrayList<Card>(rooms.values());
		ArrayList<Card> peopleCards = new ArrayList<Card>(playerCards.values());
		ArrayList<Card> weaponCards = new ArrayList<Card>(weapons.values());
		Map<String, Card> needsDealt = new HashMap<String, Card>();

		//Shuffle the decks
		Collections.shuffle(roomCards);
		Collections.shuffle(peopleCards);
		Collections.shuffle(weaponCards);

		//Draw three cards for a solution to the game
		solution.person = peopleCards.get(0).getCardName(); //Takes the first randomly selected person card and stores it in the solution
		solution.room = roomCards.get(0).getCardName(); //Takes the first randomly selected room card and stores it in the solution
		solution.weapon = weaponCards.get(0).getCardName(); //Takes the first randomly selected weapon card and stores it in the solution
		System.out.println(solution.toString()); //Debugging
		
		for (int i = 1; i < 6; i++) { //Starting with index 1 since index 0 is the solution
			needsDealt.put(roomCards.get(i).getCardName(), roomCards.get(i));
			needsDealt.put(peopleCards.get(i).getCardName(), peopleCards.get(i));
			needsDealt.put(weaponCards.get(i).getCardName(), weaponCards.get(i));
		}

		for (int i = 6; i < 9; i++) { //Adds remaining room cards
			needsDealt.put(roomCards.get(i).getCardName(), roomCards.get(i));
		}

		List<Card> cards = new ArrayList<Card>(needsDealt.values());

		for (int i = 0; i < 18; i += 6) { //Each player gets exactly 3 cards
			players.get("Miss Scarlett").addCard(cards.get(i));
			players.get("Professor Plum").addCard(cards.get(i+1));
			players.get("Mrs. Peacock").addCard(cards.get(i+2));
			players.get("Mr. Green").addCard(cards.get(i+3));
			players.get("Colonel Mustard").addCard(cards.get(i+4));
			players.get("Mrs. White").addCard(cards.get(i+5));
		}

		//Add the cards into a set to be used by computer players later.
		for (Card c : cards) {
			cardDeck.add(c);
		}

	}

	/**
	 * Sends a suggestion to each player and checks for possible cards.
	 * @param player The player making the suggestion.
	 * @param suggestion The player's suggestion.
	 * @return Card Returns a card within the suggestion, revealing it to the player who guessed.
	 */
	public Card handleSuggestion(Player player, Solution suggestion) {
		//Complete handleSuggestion method
		ArrayList<String> playerOrder = new ArrayList<String>();
		String playerName = player.getPlayerName();
		switch(playerName) {
		case "Miss Scarlett":
			playerOrder.add("Professor Plum");
			playerOrder.add("Mrs. Peacock");
			playerOrder.add("Mr. Green");
			playerOrder.add("Colonel Mustard");
			playerOrder.add("Mrs. White");
			break;
		case "Professor Plum":
			playerOrder.add("Mrs. Peacock");
			playerOrder.add("Mr. Green");
			playerOrder.add("Colonel Mustard");
			playerOrder.add("Mrs. White");
			playerOrder.add("Miss Scarlett");
			break;
		case "Mrs. Peacock":
			playerOrder.add("Mr. Green");
			playerOrder.add("Colonel Mustard");
			playerOrder.add("Mrs. White");
			playerOrder.add("Miss Scarlett");
			playerOrder.add("Professor Plum");
			break;
		case "Mr. Green":
			playerOrder.add("Colonel Mustard");
			playerOrder.add("Mrs. White");
			playerOrder.add("Miss Scarlett");
			playerOrder.add("Professor Plum");
			playerOrder.add("Mrs. Peacock");
			break;
		case "Colonel Mustard":
			playerOrder.add("Mrs. White");
			playerOrder.add("Miss Scarlett");
			playerOrder.add("Professor Plum");
			playerOrder.add("Mrs. Peacock");
			playerOrder.add("Mr. Green");
			break;
		case "Mrs. White":
			playerOrder.add("Miss Scarlett");
			playerOrder.add("Professor Plum");
			playerOrder.add("Mrs. Peacock");
			playerOrder.add("Mr. Green");
			playerOrder.add("Colonel Mustard");
			break;
		}

		//Check through players in order to see if they can disprove
		for (int i = 0; i < playerOrder.size(); i++) {
			Player tempPlayer = players.get(playerOrder.get(i));
			Card disproveCard = tempPlayer.disproveSuggestion(suggestion);
			if (disproveCard != null) return disproveCard;
		}
		return null; //Returns null if no player can disprove the suggestion.
	}

	/**
	 * Checks an accusation against the set solution to see if the player has correctly
	 * guessed and therefore won.
	 * @param accusation The player's guess.
	 * @return boolean Whether the player accurately guessed the solution.
	 */
	public boolean checkAccusation(Solution accusation) {
		//System.out.println(accusation.toString()); //Debugging
		if (!accusation.person.equals(solution.person)) return false;
		if (!accusation.weapon.equals(solution.weapon)) return false;
		if (!accusation.room.equals(solution.room)) return false;
		return true;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				board[i][j].draw(g, legend);
			}
		}

		for (Player p : players.values()) {
			p.draw(g);
		}
	}

	/**
	 * Returns a string containing which player has the next turn.
	 * @return String The player with the next turn.
	 */
	public String whoseTurn() {
		String p = "";
		switch(turn) {
		case 0: p = "Miss Scarlett"; break;
		case 1: p = "Professor Plum"; break;
		case 2: p = "Mrs. Peacock"; break;
		case 3: p = "Mr. Green"; break;
		case 4: p = "Colonel Mustard"; break;
		case 5: p = "Mrs. White"; break;
		}
		if (turn == 5) turn = 0; //Resets turn counter back to the beginning.
		else turn++; //Otherwise increments the turn counter to the next player.
		return p; //Returns who the current player is.
	}

	/**
	 * Moves the current computer player, or has the human player select a new target.
	 */
	public void nextTurn() {
		//Determine if the the previous turn has ended.
		if (turnOver()) {
			//Get the name of the next player;
			String next = whoseTurn();
			Player nextPlayer = getPlayers().get(next);
			//'Roll' the die.
			Random die = new Random();
			int roll = die.nextInt(6) + 1;
			//Determine what the viable targets are for the player this turn.
			calcTargets(nextPlayer.getRow(), nextPlayer.getColumn(), roll); //Updates target destinations for the player.

			//Update the ControlGui display
			control.setRollText(roll);
			control.setTurnText(next);

			//Check to see if the player is a computer or not.
			if (nextPlayer instanceof ComputerPlayer) {
				//If cells have been previously highlighted, reset them.
				highlightTargets(false);

				if (((ComputerPlayer) nextPlayer).isMakeAccusation()) {
					boolean correctAccusation = checkAccusation(((ComputerPlayer) nextPlayer).makeAccusation());
					if (correctAccusation) {
						JOptionPane.showMessageDialog(null, nextPlayer.getPlayerName() + " has won! The answer was: " + ((ComputerPlayer) nextPlayer).getSuggestion().text());
						System.exit(0); //Closes the window since the CP has won.
					}
					else {
						JOptionPane.showMessageDialog(null, nextPlayer.getPlayerName() + " has incorrectly guessed: " + ((ComputerPlayer) nextPlayer).getSuggestion().text());
						((ComputerPlayer) nextPlayer).setMakeAccusation(false); //Tells the CP not to accuse again so they move to a different location next turn.
					}
				}
				else {
					((ComputerPlayer) nextPlayer).makeMove(getTargets()); //CP randomly chooses new location.
					//Have the CP create a suggestion if they entered a room.
					if (nextPlayer.getLocation().isRoom()) {
						//The CP will now generate and set its own suggestion
						nextPlayer.setSuggestion(((ComputerPlayer) nextPlayer).createSuggestion(getInstance(), nextPlayer.getLocation(), cardDeck));

						//Update the control panel with the suggestion.
						control.setGuessText(nextPlayer.getSuggestion().text());

						//Check the suggestion against the other players.
						Card revealedCard = handleSuggestion(nextPlayer, nextPlayer.getSuggestion());
						if (revealedCard != null) {
							control.setGuessResultText(revealedCard.getCardName()); //Updates the display with the name of the revealed card.
							nextPlayer.addSeen(revealedCard); //Adds the revealed card to the list of seen cards for that player.
						}
						else ((ComputerPlayer) nextPlayer).shouldAccuse(); //Has the CP determine if they should make an accusation on their next turn.
						getPlayers().get(nextPlayer.getSuggestion().person).setLoc(nextPlayer.getLocation()); //Moves the suggested player to the CP's location.
					}
					else {
						control.setGuessText("");
						control.setGuessResultText("");
					}
				}
			}

			//Otherwise, the player is the human player and they must manually select a new destination.
			else {
				//Highlight target cells for user.
				highlightTargets(true);
				//Indicate user needs to complete their turn.
				human.setCompletedTurn(false);
			}
			repaint();
		}
		else { //If it is still currently the player's turn:
			JOptionPane.showMessageDialog(null, "You need to finish your turn");
			return;
		}
	}

	/**
	 * Returns if the current turn can be advanced, meaning the human player has
	 * selected a valid target.
	 * @return boolean Whether the game is allowed to advance to the next computer player.
	 */
	public boolean turnOver() {
		return human.isCompletedTurn();
	}


	public void mouseClicked(MouseEvent e) {
		if (this.human.isCompletedTurn()) {
			return; //Do nothing if it is not currently the user's turn.
		}

		//The user clicked a cell. Determine if it is valid or not.
		BoardCell clickedCell = findClickedCell(e.getX(), e.getY());
		if (clickedCell == null) {
			JOptionPane.showMessageDialog(null, "That is not a target");
		}
		else {
			//The user has selected a valid target, so the turn is over.
			this.human.completeTurn(clickedCell, this);
			highlightTargets(false);
			repaint();
		}
	}

	/**
	 * Gets the information for a cell from a mouse click event, and returns if it is
	 * a valid target or not.
	 * @param mouseX The x location of the cell.
	 * @param mouseY The y location of the cell.
	 * @return BoardCell Returns a valid cell, or null if the selection is invalid.
	 */
	public BoardCell findClickedCell(int mouseX, int mouseY) {
		if (this.targets == null) return null; //Returns null for an empty set of targets
		else {
			int column = mouseX / BoardCell.getWidth();
			int row = mouseY / BoardCell.getHeight();
			BoardCell clicked = this.board[row][column]; //Stores the clicked cell.
			if (this.targets.contains(clicked)) return clicked; //Returns the cell if it is a target.
			return null; //If it is not a target, return null so the user must select again.
		}
	}

	/**
	 * Simply turns highlights for target cells on or off.
	 * @param highlight If the cells should be highlighted or not.
	 */
	public void highlightTargets(boolean highlight) {
		for (BoardCell cell : getTargets()) {
			cell.setTarget(highlight);
		}
	}

	/**
	 * Opens a dialog for the user to submit an accusation.
	 */
	public void makeAccusation() {
		if (turnOver()) {
			JOptionPane.showMessageDialog(null, "It is not your turn!");
		}
		else {
			AccusationDialog dialog = new AccusationDialog();
			if (dialog.isSubmitted()) {
				boolean correctAccusation = checkAccusation(dialog.getAccusation());
				if (correctAccusation) {
					JOptionPane.showMessageDialog(null, "Congratulation! You won!");
					System.exit(0);
				}
				else {
					JOptionPane.showMessageDialog(null, "That is incorrect.");
					human.setCompletedTurn(true); //Prevents user from endlessly making accusations.
					highlightTargets(false);
				}
			}
		}
	}

	//Getters and Setters

	// This method returns the only Board.
	public static Board getInstance() {
		return theInstance;
	}

	public Map<Character, String> getLegend() {
		return legend;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public Set<BoardCell> getTargets() {
		return targets; //Getter for targets so the set doesn't have to be created for every get
	}

	public Set<BoardCell> getAdjList(int cellX, int cellY) {
		return adjMatrix.get(board[cellX][cellY]);
	}

	public BoardCell getCellAt(int i, int j) {
		return board[i][j]; //returns the BoardCell stored at the specified value
	}

	/**
	 * Sets configuration files.
	 * @param boardCfg Board Configuration file
	 * @param roomCfg Room Legend Configuration file
	 */
	public void setConfigFiles(String boardCfg, String roomCfg) {
		boardConfigFile = boardCfg;
		roomConfigFile = roomCfg;
	}

	public Map<String, Player> getPlayers() {
		return players;
	}

	public Map<String, Card> getWeapons() {
		return weapons;
	}

	public Map<String, Card> getRooms() {
		return rooms;
	}

	public Map<String, Card> getPlayerCards() {
		return playerCards;
	}

	public Map<String, Card> getDeck() {
		return deck;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}

	public HumanPlayer getHuman() {
		return human;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	public ControlGui getControl() {
		return control;
	}

	public void setControl(ControlGui control) {
		this.control = control;
	}

} //End of Class