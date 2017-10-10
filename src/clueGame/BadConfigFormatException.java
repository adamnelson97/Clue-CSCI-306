/*
 * Class: BadConfigFormatException
 * 
 * Authors: Nathaniel Fuller, Adam Nelson
 * 
 * Purpose: An exception to be thrown when reading data from an improperly formatted config file.
 * 
 */

package clueGame;

import java.io.IOException;
import java.io.PrintWriter;

public class BadConfigFormatException extends Exception {
	// -- Variables --

	// -- Constructors --
	public BadConfigFormatException() {
		super("Error: Bad Configuration Files");
	}

	public BadConfigFormatException(String s) throws IOException {
		super(s);
		PrintWriter writer = new PrintWriter("BadConfigErrorLog.txt");
		writer.println(s);
		writer.close();
	}
}