/*
 * Class: DoorDirection
 * 
 * Authors: Nathaniel Fuller, Adam Nelson, Youjun Lee
 * 
 * Purpose: An enumerated type to indicate which direction a door is facing for the purposes of our 'Clue' game.
 * 
 */

package clueGame;

/**
 * <h1>Door Direction</h1>
 * Enumerated type for identifying which way a door
 * opens up on the board.
 * 
 * @author Adam Nelson, Nathaniel Fuller, Youjun Lee
 * @version 1.0
 * @since 2017-10-09
 *
 */
public enum DoorDirection {
	/**
	 * There are four possible directions specifying how the player
	 * is adjacent to a door.
	 */
	UP, DOWN, LEFT, RIGHT, NAME, NONE;
	
	
}