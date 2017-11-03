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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.*;

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
public class Board {
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
	private String weaponConfigFile = "WeaponLegend.txt";

	private Map<String, Player> players; //Set of all 6 players in the game
	private Set<Card> rooms; //Set of all 9 rooms in the game
	private Set<Card> weapons; //Set of all 6 weapons in the game


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
		rooms = new HashSet<Card>();
		weapons = new HashSet<Card>();
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
	}

	/**
	 * This loads the legend for the different room types. Throws an exception
	 * if the file is not properly formatted or if the file cannot be found.
	 * 
	 * @throws BadConfigFormatException Throws various formatting exceptions
	 */
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
	public void loadConfigFiles() {
		//TODO complete loadConfigFiles method
		FileReader playerCfg = null;
		players.clear();

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
			Player x = new Player(line[0], color, Integer.parseInt(line[2]), Integer.parseInt(line[3]));
			//System.out.println(x.toString());
			players.put(line[0], x);
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
	public void selectAnswer() {
		//TODO complete selectAnswer method
	}

	/**
	 * Sends a suggestion to each player and checks for possible cards.
	 * @return Card Returns a card within the suggestion, revealing it to the player who guessed.
	 */
	public Card handleSuggestion() {
		//TODO complete handleSuggestion method
		return null;
	}

	/**
	 * Checks an accusation against the set solution to see if the player has correctly
	 * guessed and therefore won.
	 * @param accusation The player's guess.
	 * @return boolean Whether the player accurately guessed the solution.
	 */
	public boolean checkAccusation(Solution accusation) {
		//TODO complete checkAccusation method
		return false;
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
}