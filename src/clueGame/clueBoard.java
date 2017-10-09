/*
 * Class: Board
 * 
 * Authors: Nathaniel Fuller, Adam Nelson
 * 
 * Purpose: To represent the board used for the game 'Clue' including movement, possible player locations, rooms, and doorways.
 * 
 */

package clueGame;

import java.util.*;

class Board {
	// -- Variables --
	private int numRows;
	private int numColumns;
	public static int MAX_BOARD_SIZE = 50;
	private BoardCell[][] board;
	private Map<Character, String> legend;
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> targets;
	private String boardConfigFile;
	private String roomConfigFile;
	// Variable used for the singleton pattern
	private static Board theInstance = new Board();

	// -- Constructors --
	
	// Private constructor to ensure only one can be created
	private Board() {}
	
	// -- Methods --
	public void initialize() {
		// TODO: Implement Initialize to perform necessary one-time operations on creation.
	}
	
	public void loadRoomConfig() {
		// TODO: Implement loadRoomConfig to read data from roomConfigFile.
	}
	
	public void loadBoardConfig() {
		// TODO: Implement loadBoardConfig to read data from boardConfigFile.
	}
	
	public void calcAdjacencies() {
		// TODO: Implement calcAdjacencies to correctly add values to adjMatrix
	}
	
	public void calcTargets(BoardCell cell, int pathLength) {
		// TODO: Implement calcTargets to calculate all possible movement options and set that value to targets.
	}
	
	// -- Getters and Setters
	
	// This method returns the only Board.
	public static Board getInstance() {
		return theInstance;
	}
}