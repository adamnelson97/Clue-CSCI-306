/*
 * Class: BoardCell
 * 
 * Authors: Nathaniel Fuller, Adam Nelson, Youjun Lee
 * 
 * Purpose: A class to represent a single 'tile' on the board for the game 'Clue'.
 * 
 */

package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;

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

	private final static int WIDTH = 25;
	private final static int HEIGHT = 25;

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
	 * @throws BadConfigFormatException Throws if the door direction is invalid
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
			door = DoorDirection.NAME;
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

	public void draw(Graphics g, Map<Character, String> rooms) {
		if (this.isRoom()) {
			g.setColor(Color.GRAY);
			g.fillRect(column * WIDTH, row * HEIGHT, WIDTH, HEIGHT);
			//Check to see if the cell is a door
			if (this.isDoorway()) {
				g.setColor(Color.BLUE);
				switch (this.getDoorDirection()) {
				case UP:
					g.fillRect(column * WIDTH, row * HEIGHT, WIDTH, HEIGHT / 5); break;
				case DOWN:
					g.fillRect(column * WIDTH, (row + 1) * HEIGHT - HEIGHT / 5, WIDTH, HEIGHT / 5); break;
				case LEFT:
					g.fillRect(column * WIDTH, row * HEIGHT, WIDTH / 5, HEIGHT); break;
				case RIGHT:
					g.fillRect((column + 1) * WIDTH - WIDTH / 5, row * HEIGHT, WIDTH / 5, HEIGHT); break;
				case NAME:
					g.setColor(Color.BLUE);
					g.drawString(rooms.get(this.getInitial()).toUpperCase(), column * (WIDTH), row * HEIGHT);
					break;
				case NONE:
					break;
				}
			}
		}
		else if (!this.isWalkway()) {
			g.setColor(Color.GRAY);
			g.fillRect(column * WIDTH, row * HEIGHT, WIDTH, HEIGHT);
		}
		else {
			g.setColor(Color.ORANGE);
			g.fillRect(column * WIDTH, row * HEIGHT, WIDTH, HEIGHT);
			g.setColor(Color.BLACK);
			g.drawRect(column * WIDTH,  row * HEIGHT, WIDTH, HEIGHT);
		}
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


	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", column=" + column + "]";
	}


	public static int getWidth() {
		return WIDTH;
	}


	public static int getHeight() {
		return HEIGHT;
	}
}