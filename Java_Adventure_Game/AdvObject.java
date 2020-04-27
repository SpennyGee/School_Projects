/*
 * File: AdvObject.java
 * --------------------
 * This file defines a class that models an object in the
 * Adventure game.
 */

import java.io.*;
import java.util.Scanner;

/* Class: AdvObject */
/**
 * This class defines an object in the Adventure game.  An object is
 * characterized by the following properties:
 *
 * <ul>
 * <li>Its name, which is the noun used to refer to the object
 * <li>Its description, which is a string giving a short description
 * <li>The room number in which the object initially lives
 * </li>
 *
 * The external format of the objects file is described in the
 * assignment handout.  The comments on the methods exported by
 * this class show how to use the initialized data structure.
 */
public class AdvObject {

/* Method: getName() */
/**
 * Returns the object name, which is the word used to refer to it.
 *
 * @usage String name = obj.getName();
 * @return The name of the object
 */
	public String getName() {
		
		return name;
	}

/* Method: getDescription() */
/**
 * Returns the one-line description of the object.  This description
 * should start with an article, as in "a set of keys" or "an emerald
 * the size of a plover's egg."
 *
 * @usage String name = obj.getDescription();
 * @return The description of the object
 */
	public String getDescription() {
		return description;
	}


/* Method: getInitialLocation() */
/**
 * Returns the initial location of the object.
 *
 * @usage int roomNumber = obj.getInitialLocation();
 * @return The room number in which the object initially resides
 */
	public int getInitialLocation() {
		return initialRoom;
	}

	/* Method: readFromFile(scan) */
	/**
	 * Reads the data for this object from the Scanner scan, which must have
	 * been opened by the caller. This method returns the object if the object
	 * initialization is successful; if there are no more objects to read,
	 * readFromFile returns null
	 * 
	 * @usage AdvObject object = AdvObject.readFromFile(scan);
	 * @param scan
	 *            A Scanner open on the objects data file
	 * @return the object if an object is successfully read; null at end of file
	 */
	public static AdvObject readFromFile(Scanner scan) {
		
		// The object for return
		AdvObject object = new AdvObject();
		// A field to handle lines as Strings
		String line;
		
		// Strip out any empty lines before the Object's name
		while ((line = scan.nextLine()).isEmpty()) { }
		
		// First line that contains text should have the Object's name
		object.name = line.trim();
		
		// Second line that contains text should have the Object's description
		object.description = scan.nextLine().trim();
		
		// Third line should be the object's initial room placement as described by an integer
		object.initialRoom = scan.nextInt();
		
		// We have everything we need, so return the object
		return object;
	}

/* Private instance variables */
	
	// The object's name
	private String name;
	
	// The object's one-line description
	private String description;
	
	// The room number of the object's initial placement
	private int initialRoom;
}
