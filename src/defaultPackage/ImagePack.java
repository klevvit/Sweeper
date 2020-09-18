/**
 * @author Kovalenko Lev
*/
/**
 * Copyright Kovalenko Lev (Sweeper) 2016. All rights reserved.
 */
package defaultPackage;

import java.util.InvalidPropertiesFormatException;

public class ImagePack {
	private final String myPath;
	private final String myName;
	private final String myAuthor;
	private boolean isFinished = true;
	public static final char splitter = '|';

	public ImagePack(String name, String author, String path) {
		myName = name;
		myAuthor = author;
		myPath = path;
	}

	public ImagePack(String name, String author, String path, boolean isFull) {
		this(name, author, path);
		isFinished = isFull;

	}

	public ImagePack(String nameAuthorPath) throws InvalidPropertiesFormatException {
		String[] a = nameAuthorPath.split(Character.toString(splitter));
		if (a.length == 3) {
			myName = a[0];
			myAuthor = a[1];
			myPath = a[2];
		} else {
			throw new InvalidPropertiesFormatException(a.length - 1 + " splits found, 2 splits excepted.");
		}
	}

	public String toString() {
		String packName = myName + " ";
		if (myAuthor != "") {
			packName += "| Author: " + myAuthor + " ";
		}
		if (!isFinished) {
			packName += "(not finished yet)";
		}
		return packName;
	}

	// getters

	public String getName() {
		return myName;
	}

	public String getAuthor() {
		return myAuthor;
	}

	public String getPath() {
		return myPath;
	}
}
