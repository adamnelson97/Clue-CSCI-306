/*
 * Class: BadConfigFormatException
 * 
 * Authors: Nathaniel Fuller, Adam Nelson, Youjun Lee
 * 
 * Purpose: An exception to be thrown when reading data from an improperly formatted config file.
 * 
 */

package clueGame;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * <h1>BadConfigFormatException</h1>
 * This is a custom exception that tells the user if a legend file or board
 * layout file has a problem.
 * 
 * @author Adam Nelson, Nathaniel Fuller, Youjun Lee
 * @version 1.0
 * @since 2017-10-09
 *
 */
public class BadConfigFormatException extends Exception {
	// -- Variables --

	// -- Constructors --
	
	/**
	 * Constructor with generic message.
	 */
	public BadConfigFormatException() {
		super("Error: Bad Configuration Files");
	}

	/**
	 * Constructor that prints a custom error message.
	 * @param s This string is determined in the method the exception is called from.
	 */
	public BadConfigFormatException(String s) {
		super(s);
		PrintWriter writer;
		try {
			writer = new PrintWriter("BadConfigErrorLog.txt");
			writer.println(s);
			writer.close();
		} catch (FileNotFoundException e) {}	
	}
}