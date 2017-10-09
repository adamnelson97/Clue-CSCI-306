/*
 * Class: BadConfigFormatException
 * 
 * Authors: Nathaniel Fuller, Adam Nelson
 * 
 * Purpose: An exception to be thrown when reading data from an improperly formatted config file.
 * 
 */

package clueGame;

public class BadConfigFormatException extends Exception {
	// -- Variables --
	
	// -- Constructors --
	public BadConfigFormatException() {
		super("Error: Bad Configuration Files");
	}
	
	public BadConfigFormatException(String s) {
		
	}
}