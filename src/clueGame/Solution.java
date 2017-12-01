package clueGame;

/**
 * <h1>Solution</h1>
 * This class stores the solution to the game.
 * @author Adam Nelson, Youjun Lee
 * @version 1.0
 * @since 2017-11-02
 *
 */
public class Solution {

	//Variables
	public String person;
	public String room;
	public String weapon;
	
	//Constructors
	
	/**
	 * Default constructor.
	 */
	public Solution() {
		//Nothing should need to go here.
	}
	
	/**
	 * Parameterized constructor.
	 * @param person The person who committed the crime.
	 * @param weapon The weapon the person used.
	 * @param room The location the crime took place in.
	 */
	public Solution(String person, String weapon, String room) {
		this.person = person;
		this.weapon = weapon;
		this.room = room;
	}

	@Override
	public String toString() {
		return "Solution [person=" + person + ", room=" + room + ", weapon="
				+ weapon + "]";
	}
}
