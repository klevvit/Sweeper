package defaultPackage;

import java.util.InvalidPropertiesFormatException;

/**
 * Class for simple work with different image packs.
 * @author Kovalenko Lev
 * Copyright Kovalenko Lev (Sweeper) 2020. All rights reserved.
 */
public class ImagePack {

    /** path to folder with images */
    private final String path;

	/** image pack name; will be shown to user */
	private final String name;

	/** image pack author name; will be shown to user */
	private final String author;

	/** if false, user will see a remark that package is not finished */
	private boolean isFull = true;

	/** symbols which visually split info parts about package (name, author, etc.) when displaying them to user */
	private static final String SPLITTER = "; ";

	public ImagePack(String name, String author, String path) {
		this.name = name;
		this.author = author;
		this.path = path;
	}

	public ImagePack(String name, String author, String path, boolean isFull) {
		this(name, author, path);
		this.isFull = isFull;
	}

	/** String info about package, must be shown to user */
	public String toString() {
		String packInfo = name;
		if (author != "") {
			packInfo += SPLITTER + "Author: " + author;
		}
		if (!isFull) {
			packInfo += SPLITTER + "not finished";
		}
		return packInfo;
	}

	// getters

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}

	public String getPath() {
		return path;
	}
}
