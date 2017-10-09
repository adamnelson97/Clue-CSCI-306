/*
 * Class: BoardCell
 * 
 * Authors: Nathaniel Fuller, Adam Nelson
 * 
 * Purpose: A class to represent a single 'tile' on the board for the game 'Clue'.
 * 
 */

package clueGame;

class BoardCell {
	// -- Variables --
	private int row;
	private int column;
	private char initial;
	
	// -- Constructors --
	public BoardCell() {
		// TODO: Implement constructor for BoardCell
	}
	public BoardCell(int r, int c, char i) {
		// TODO: Implement constructor for BoardCell
	}
	
	// -- Methods --
	public boolean isWalkway() {
		// TODO: Implement isWalkway to return whether the BoardCell is a walkway.
		return false;
	}
	
	public boolean isRoom() {
		// TODO: Implement isRoom to return whether the BoardCell is a room.
		return false;
	}
	
	public boolean isDoorway() {
		// TODO: Implement isDoorway to return whether the BoardCell is a doorway.
		return false;
	}
}