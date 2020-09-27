package defaultPackage;


import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Class for simple work with different image packs.
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
public class ImagePack {

    /** path to root folder with images */
//    private final Path rootPath = Paths.get("src/images");

    private final String packFolderName;

    private static final String DEFAULT_EXTENSION = "png";

	/** image pack name; will be shown to user */
	private final String name;

	/** image pack author name; will be shown to user */
	private final String author;

	/** if false, user will see a remark that package is not finished */
	private final boolean isFull;

	/** symbols which visually split info parts about package (name, author, etc.) when displaying them to user */
	private static final String SPLITTER = "; ";

	public ImagePack(String name, String author, String packFolderName, boolean isFull) {
		this.name = name;
		this.author = author;
		this.packFolderName = packFolderName;
        this.isFull = isFull;
	}

	public ImagePack(String name, String author, String packFolderName) {
		this(name, author, packFolderName, true);
	}

	/**Gives Image class to be drawn by java.awt.Graphics.drawImage
     * @param imageCategory name of the catalog with required image; e.g. <i>"borderFragments"</i>
     * @param fileName name of file of the required image <b>without extension</b>; e.g. <i>"NorthWest"</i>
     * @param extension extension of image file <b>without dot</b>; e.g. <i>"jpg"</i>;<br>
     *                  - <i>"png"</i> if not stated
     * @return java.awt.Image class of required image
     */
	public Image getImage(String imageCategory, String fileName, String extension) {
        String imagePath = /* ROOT_PATH.toString() */ getRoot() + "/" + packFolderName + "/"
                + getBestScaleFolderName(Game.getScale()) + "/"
                + imageCategory + "/" + fileName + "." + extension;
//        System.out.println(Paths.get(imagePath).toAbsolutePath());
        System.out.println("v6" + imagePath);
        try {
            return new ImageIcon(new URL(imagePath)).getImage();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get URL String path to <i>"images"</i> folder inside project.
     * @return absolute path to "images" folder with "file:" or "jar:file" prefix (whether code is packed inside jar or
     * not).
     */
    private String getRoot() {
        // getResource() inside .jar file has double prefix "jar:file:", and getPath() method removes only the first
        // one. That's why we need replaceFirst() method.
        return this.getClass().getResource("/images").toString();
    }

//    public static Image getImage(String path) {
//        if (Resource.class.getResource(path) == null) {
//            System.err.println("Image loading error\n" +
//                    "    Null resource: " + path);
//        }
//        return new ImageIcon(Resource.class.getResource(path)).getImage();
//    }

    public Image getImage(String folderName, String fileName) {
	    return getImage(folderName, fileName, DEFAULT_EXTENSION);
    }

    /**Returns name of folder with the best texture size for given scale.
     * @param scale scale multiplier that was set for the image size
     * @return String with folder name: "1.0x" if scale is 1 or 2, "1.5x" otherwise.
     */
    private String getBestScaleFolderName(float scale) {
	    if (Float.compare(scale, 1f) == 0)
	        return "1.0x";
	    if (Float.compare(scale, 2f) == 0)
	        return "1.0x";
	    return "1.5x";
    }

    // todo
//    public Image getIcon(...) {}

    /** return default ImagePack set that is initially among project files */
    public static ImagePack[] getDefaultPackSet() {
        ImagePack[] packArray = new ImagePack[2];
        packArray[0] = new ImagePack("Default", "", "default");
        packArray[1] = new ImagePack("Battleship", "Andrés Dugin", "battleship", false);
        return packArray;
    }

    /** String info about package, must be shown to user */
    public String toString() {
        String packInfo = name;
        if (!author.isEmpty()) {
            packInfo += SPLITTER + "Author: " + author;
        }
        if (!isFull) {
            packInfo += SPLITTER + "not full";
        }
        return packInfo;
    }

}
