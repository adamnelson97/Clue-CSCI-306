/*
 * Class: BoardCell
 * 
 * Authors: Nathaniel Fuller, Adam Nelson
 * 
 * Purpose: A class to represent a single tile and its coordinates for ClueGame
 * 
 */

package experiment;

public class BoardCell {
	
	// -- Variables --
	private int row;
	private int column;
	
	// -- Constructors --
	public BoardCell() {
		row = 0;
		column = 0;
	}
	public BoardCell(int x, int y) {
		row = x;
		column = y;
	}
	
	// -- Getters and Setters
	public int getRow() {
		return row;
	}
	public int getCol() {
		return column;
	}
}
