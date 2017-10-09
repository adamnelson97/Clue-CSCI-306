/*
 * Class: BoardCell
 * 
 * Authors: Nathaniel Fuller, Adam Nelson
 * 
 * Purpose: A class to represent a single 'tile' on the board for the game 'Clue'.
 * 
 */

package clueGame;

public class BoardCell {
	// -- Variables --
	private int row;
	private int column;
	private char initial;
	private DoorDirection door;
	
	// -- Constructors --
	public BoardCell() {
		row = 0;
		column = 0;
	}
	public BoardCell(int x, int y, char i) { // If the csv has one character for the cell
		row = x;
		column = y;
		initial = i;
		door = DoorDirection.NONE;
	}
	public BoardCell(int x, int y, char i, char d) { // If the csv has two characters for the cell
		row = x;
		column = y;
		initial = i;
		switch(d) {
		case 'U':
			door = DoorDirection.UP;
			break;
		case 'D':
			door = DoorDirection.DOWN;
			break;
		case 'L':
			door = DoorDirection.LEFT;
			break;
		case 'R':
			door = DoorDirection.RIGHT;
			break;
		default:
			door = DoorDirection.NONE;
			break;
		}
	}
	
	// -- Methods --
	public boolean isWalkway() {
		if(initial == 'W') {
			return true;
		}
		return false;
	}
	
	public boolean isRoom() {
		if(initial != 'W' && initial != 'X') {
			return true;
		}
		return false;
	}
	
	public boolean isDoorway() {
		if(door == DoorDirection.NONE) {
			return false;
		}
		return true;
	}
	
	public DoorDirection getDoorDirection() {
		return door;
	}
	
	// -- Getters & Setters
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public char getInitial() {
		return initial;
	}
}