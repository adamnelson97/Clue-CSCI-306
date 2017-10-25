/*
 * Class: Board
 * 
 * Authors: Nathaniel Fuller, Adam Nelson, Youjun Lee
 * 
 * Purpose: To represent the board used for the game 'Clue' including movement, possible player locations, rooms, and doorways.
 * 
 */

package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
	// -- Variables --
	private int numRows;
	private int numColumns;
	public final static int MAX_BOARD_SIZE = 50;
	private BoardCell[][] board;
	private Map<Character, String> legend;
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> targets;
	private Set<BoardCell> visited;
	private String boardConfigFile = "ClueGameLayout.csv";
	private String roomConfigFile = "ClueGameLegend.txt";

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
	}

	// -- Methods --
	
	/**
	 * This sets up the board by calling functions to load the corresponding
	 * configuration files and then calculate the adjacency sets for each cell.
	 */
	public void initialize() {
		try {
			loadRoomConfig();
		} catch (BadConfigFormatException e) {}
		try {
			loadBoardConfig();
		} catch (BadConfigFormatException e) {}
		
		// Populate adjMatrix
		calcAdjacencies();
	}

	/**
	 * This loads the legend for the different room types. Throws an exception
	 * if the file is not properly formatted or if the file cannot be found.
	 * 
	 * @throws BadConfigFormatException
	 */
	public void loadRoomConfig() throws BadConfigFormatException {
		FileReader roomCfg = null;
		legend.clear();

		try {
			roomCfg = new FileReader(roomConfigFile);
		} catch (FileNotFoundException e) {System.out.println("Room Config File not found."); }

		Scanner in = new Scanner(roomCfg);
		char letter; //Ex: 'A'
		String name; //Ex: 'Art Room'
		String temp; //Entire line that will be manipulated, ex: A, Art Room, Card
		int secComma; //Index location of second comma, i.e. follows the name of the room

		while (in.hasNextLine()) {
			temp = in.nextLine(); //Takes in entire line, ex: A, Art Room, Card
			if (!temp.endsWith("Card") && !temp.endsWith("Other")) {
				throw new BadConfigFormatException("Room is not type Card or Other");
			}

			letter = temp.charAt(0); //Stores the first character as the room symbol, ex: A
			secComma = temp.indexOf(',', 2); //Char at index should be a comma, so the next one follows the name of the room
			name = temp.substring(3, secComma);
			legend.put(letter, name); //Adds room to the legend
		}
		in.close();
	}

	/**
	 * This loads the data of the board, and throws an exception if the data is not
	 * formatted properly or if the file cannot be found.
	 * 
	 * @throws BadConfigFormatException
	 */
	public void loadBoardConfig() throws BadConfigFormatException {
		FileReader boardCfg = null;

		try {
			boardCfg = new FileReader(boardConfigFile);
		} catch (FileNotFoundException e) {System.out.println("Board Config File not found."); }

		//First, get dimensions of csv file so the board array can be initialized
		Scanner in = new Scanner(boardCfg);
		int expectedCommas = 0;
		
		ArrayList<String> lines = new ArrayList<String>(); // Create a data structure to store the lines from boardCfg
		String currLine = in.nextLine();
		// Read all lines from the file and add them to lines.
		lines.add(currLine);
		while(in.hasNextLine()) {
			currLine = in.nextLine();
			lines.add(currLine);
		}

		in.close();
		
		//Get the num of columns in the first row. All other rows need to have this many columns
		int numCommas = 0;
		for (int i = 0; i < lines.get(0).length(); i++) {
			if (currLine.charAt(i) == ',') {
				numCommas++; //Increments for each instance of a comma
			}
		}
		expectedCommas = numCommas; //One less comma than total columns
		
		//Test all other rows to ensure they are the same size
		for(int i = 0; i < lines.size(); i++) {
			numCommas = 0;
			for (int j = 0; j < lines.get(i).length(); j++) {
				if (lines.get(i).charAt(j) == ',') {
					numCommas++; //Increments for each instance of a comma
				}
			}
			if (numCommas != expectedCommas) {
				throw new BadConfigFormatException("Number of rows and columns is not consistent.");
			}
		}
		
		//If this point is reached, the rows all have the same number of columns
		numColumns = expectedCommas + 1; //There is one less comma then total columns
		numRows = lines.size();
		
		board = new BoardCell[numRows][numColumns];
		
		int currStrPos;
		int currIndex;
		int nextComma;
		String tileData;
		// Iterate through all read lines
		for(int i = 0; i < lines.size(); i++) {
			currStrPos = 0;
			currIndex = 0;
			// As long as there are unprocessed characters in the string
			while(currIndex < board[i].length) {
				nextComma = lines.get(i).indexOf(',', currStrPos);
				if(nextComma != -1) {
					tileData = lines.get(i).substring(currStrPos, nextComma); // Get the character(s) between commas
				}
				else {
					tileData = lines.get(i).substring(currStrPos); // If there are no more commas, get the rest of the string.
				}
				// Depending on how many characters are there, call the appropriate constructor, or throw the exception.
				if(legend.containsKey(tileData.charAt(0))) {
					if(tileData.length() == 1) {
						board[i][currIndex] = new BoardCell(i, currIndex, tileData.charAt(0));
						currIndex++;
					}
					else if(tileData.length() == 2) {
						board[i][currIndex] = new BoardCell(i, currIndex, tileData.charAt(0), tileData.charAt(1));
						currIndex++;
					}
					else {
						throw new BadConfigFormatException("Entry at position " + Integer.toString(i) + "," + Integer.toString(currIndex) + " has too many defining characters.");
					}
					currStrPos = nextComma + 1; // Increment the current position in the string.
				}
				else {
					throw new BadConfigFormatException("Entry at position " + Integer.toString(i) + ","  + Integer.toString(currIndex) + " has an invalid character.");
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
	
	// Old function accepting old parameters. Delete if determined superfluous & obsolete.
	
	/*public void calcTargets(BoardCell cell, int pathLength) {
		//Stores possible target cells in targets
		Set<BoardCell> adj = getAdjList(cell);
		visited.add(cell);
		// Iterate through all adjacent cells
		for(BoardCell c : adj) {
			// If the cell hasn't been visited
			if(!visited.contains(c)) {
				// If the path is length 1, add the cell to targets.
				if(pathLength == 1) {
					targets.add(c);
				}
				// Otherwise, add the adjacent cell to visited, call the function from that cell with a shorter pathLength.
				// Then, after that has completed, remove the cell from visited.
				else {
					calcTargets(c, (pathLength-1));
					visited.remove(c);
				}
			}
		}
	}*/
	
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

	// -- Getters and Setters --

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
	
	// Old function accepting old parameters. Delete if determined superfluous & obsolete.
	
	/*public Set<BoardCell> getAdjList(BoardCell cell) {
		return adjMatrix.get(cell); //Stores the adjacency list for a cell in a new list
	}*/
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
}