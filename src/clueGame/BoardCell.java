/*
 * Class: BoardCell
 * 
 * Authors: Nathaniel Fuller, Adam Nelson, Youjun Lee
 * 
 * Purpose: A class to represent a single 'tile' on the board for the game 'Clue'.
 * 
 */

package clueGame;

/**
 * <h1>BoardCell</h1>
 * This class stores the location of a cell, the type of space
 * it is, and whether or not the cell has a door attached.
 * 
 * @author Adam Nelson, Nathaniel Fuller, Youjun Lee
 * @version 1.0
 * @since 2017-10-09
 */
public class BoardCell {
	// -- Variables --
	private int row;
	private int column;
	private char initial;
	private DoorDirection door;

	//Constructors

	/**
	 * Default constructor.
	 */
	public BoardCell() {
		row = 0;
		column = 0;
		initial = ' ';
		door = DoorDirection.NONE;
	}


	/**
	 * Constructor that contains the location of a cell and what type of space
	 * the cell is (room, walkway, other).
	 * @param x The x-axis location of the cell.
	 * @param y The y-axis location of the cell.
	 * @param i The type of space that the cell is.
	 */
	public BoardCell(int x, int y, char i) { // If the csv has one character for the cell
		row = x;
		column = y;
		initial = i;
		door = DoorDirection.NONE;
	}

	/**
	 * Constructor that contains the location of a cell, the type of space, and
	 * what direction a door is facing for that cell.
	 * @param x The x-axis location of the cell.
	 * @param y The y-axis location of the cell.
	 * @param i The type of space that the cell is.
	 * @param d What direction the door is adjacent to.
	 * @throws BadConfigFormatException 
	 */
	public BoardCell(int x, int y, char i, char d) throws BadConfigFormatException { // If the csv has two characters for the cell
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
		case 'N':
			door = DoorDirection.NONE;
			break;
		default:
			throw new BadConfigFormatException("Error: Invalid Door Direction: " + d);
		}
	}

	//Methods

	/**
	 * @return boolean Whether a space is a walkway or not.
	 */
	public boolean isWalkway() {
		return initial == 'W';
	}

	/**
	 * Any space that is not a walkway or the closet is an accessible room.
	 * @return boolean Whether a space is a room or not.
	 */
	public boolean isRoom() {
		return (initial != 'W' && initial != 'X');
	}

	/**
	 * 
	 * @return boolean Whether a space contains a door or not.
	 */
	public boolean isDoorway() {
		return door != DoorDirection.NONE;
	}

	

	//Getters & Setters

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public char getInitial() {
		return initial;
	}
	
	public DoorDirection getDoorDirection() {
		return door;
	}
}