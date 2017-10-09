/*
 * Class: Board
 * 
 * Authors: Nathaniel Fuller, Adam Nelson
 * 
 * Purpose: To represent the board used for the game 'Clue' including movement, possible player locations, rooms, and doorways.
 * 
 */

package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Board {
	// -- Variables --
	private int numRows;
	private int numColumns;
	public static int MAX_BOARD_SIZE = 50;
	private BoardCell[][] board;
	private Map<Character, String> legend;
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> targets;
	private Set<BoardCell> visited;
	private String boardConfigFile;
	private String roomConfigFile;
	
	// Variable used for the singleton pattern
	private static Board theInstance = new Board();

	// -- Constructors --
	
	// Private constructor to ensure only one can be created
	private Board() {
		// Set up empty data structures
		legend = new HashMap<Character, String>();
		adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();
		targets = new HashSet<BoardCell>();
		visited = new HashSet<BoardCell>();
	}
	
	// -- Methods --
	public void initialize() throws BadConfigFormatException {
		// TODO: figure out how to input the file names
		//setConfigFiles(boardCfg, roomCfg);
		loadRoomConfig();
		loadBoardConfig();
	}
	
	public void loadRoomConfig() throws BadConfigFormatException {
		// TODO: Implement loadRoomConfig to read data from roomConfigFile.
		FileReader roomCfg = null;
		
		try {
			roomCfg = new FileReader(roomConfigFile);
		} catch (FileNotFoundException e) {System.out.println("File not found."); }
		
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
			secComma = temp.indexOf(',', 1); //Char at index should be a comma, so the next one follows the name of the room
			name = temp.substring(3, secComma - 1);
			legend.put(letter, name); //Adds room to the legend
		}
	}
	
	public void loadBoardConfig() throws BadConfigFormatException {
		// TODO: Implement loadBoardConfig to read data from boardConfigFile.
	}
	
	public void calcAdjacencies() {
		// Generates map of all cells and their adjacent cells and stores it in adjMtx
		BoardCell currCell;
		Set<BoardCell> adjSet;
		for(int i = 0; i < numRows; i++) { // Iterate through all cells in grid[][]
			for(int j = 0; j < numColumns; j++) {
				currCell = board[i][j];
				adjSet = new HashSet<BoardCell>();
				// Check to see whether each neighboring cell exists, i.e. Index not out of bounds, and add it to adjSet.
				if(i > 0) { 
					adjSet.add(board[i-1][j]);
				}
				if(i < numRows - 1) {
					adjSet.add(board[i+1][j]);
				}
				if(j > 0) {
					adjSet.add(board[i][j-1]);
				}
				if(j < numColumns - 1) {
					adjSet.add(board[i][j+1]);
				}
				adjMatrix.put(currCell, adjSet);
			}
		}
	}
	
	public void calcTargets(BoardCell cell, int pathLength) {
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
	
	public Set<BoardCell> getAdjList(BoardCell cell) {
		return adjMatrix.get(cell); //Stores the adjacency list for a cell in a new list
	}
	
	public BoardCell getCellAt(int i, int j) {
		return board[i][j]; //returns the BoardCell stored at the specified value
	}
	
	public void setConfigFiles(String boardCfg, String roomCfg) {
		boardConfigFile = boardCfg;
		roomConfigFile = roomCfg;
	}
}