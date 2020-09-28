package defaultPackage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Class for simple work with different image packs. An object of this class represents one definite image pack.
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
public class ImagePack {

	/** image pack name; will be shown to user */
	private final String name;

	/** image pack author name; will be shown to user */
	private final String author;

	/** name of root folder for this package; will be used in path; e.g. "default" */
    private final String packFolderName;

	/** if false, user will see a remark that package is not finished */
	private final boolean isFull;

	/** extension that images inside image pack have by default */
    private static final String DEFAULT_EXTENSION = "png";

	/** symbols which visually split info parts about package (name, author, etc.) when displaying them to user */
	private static final String SPLITTER = "; ";

	/** name of folder with image packs inside project */
	private static final String PROJECT_IMAGES_FOLDER = "images";

	/** name of folder with app icons inside image pack */
	private static final String ICONS_FOLDER = "icons";

    /**
     * Creates ImagePack object that will load pictures from <code>packFolderName</code> folder.
     * @param name image pack name; will be shown to user
     * @param author image pack author name; will be shown to user
     * @param packFolderName name of root folder for this package; will be used in path; e.g. "default"
     * @param isFull if false, user will see a remark that package is not finished
     */
	public ImagePack(String name, String author, String packFolderName, boolean isFull) {
		this.name = name;
		this.author = author;
		this.packFolderName = packFolderName;
        this.isFull = isFull;
	}

    /**
     * Creates ImagePack object that will load pictures from <code>packFolderName</code> folder.
     * Considered to contain all necessary images.
     * @param name image pack name; will be shown to user
     * @param author image pack author name; will be shown to user
     * @param packFolderName name of root folder for this package; will be used in path; e.g. "default"
     */
	public ImagePack(String name, String author, String packFolderName) {
		this(name, author, packFolderName, true);
	}

	/**
     * For asked image creates Image class that can be drawn by <code>java.awt.Graphics.drawImage</code>.
     * @param imageCategory name of the catalog with required image; e.g. <code>"borderFragments"</code>
     * @param fileName file name of the required image <b>without extension</b>; e.g. <code>"NorthWest"</code>
     * @param extension extension of image file <b>without dot</b>; e.g. <code>"jpg"</code>;
     *                  <code>{@value #DEFAULT_EXTENSION}</code> if not stated
     * @return <code>java.awt.Image</code> class of required image
     */
	public Image getImage(String imageCategory, String fileName, String extension) {
        String scaleFolderName = getScaleFolderName(Game.getScale());
        String imagePath = getRoot() + "/" + packFolderName + "/" + scaleFolderName + "/" + imageCategory + "/" +
                fileName + "." + extension;
        try {
            URL imageURL = new URL(imagePath);
            // check whether access to image is successful
            if (ImageIO.read(imageURL) == null) {
                throw new NullPointerException("Null image source");
            }

            return new ImageIcon(imageURL).getImage();

        } catch (IOException | NullPointerException e) {
            System.err.println("Image load error!\n" +
                    "   packFolderName: " + packFolderName + "\n" +
                    "   scale: " + scaleFolderName + "\n" +
                    "   imageCategory: " + imageCategory + "\n" +
                    "   fileName: " + fileName + "\n" +
                    "   extension: " + extension + "\n" +
                    "   root: " + getRoot() + "\n" +
                    "   full path: " + imagePath);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * For asked image creates Image class that can be drawn by <code>java.awt.Graphics.drawImage</code>. Image file is
     * considered to have <code>{@value #DEFAULT_EXTENSION}</code> extension.
     * @param imageCategory name of the catalog with required image; e.g. <code>"borderFragments"</code>
     * @param fileName file name of the required image <b>without extension</b>; e.g. <code>"NorthWest"</code>
     * @return <code>java.awt.Image</code> of required image
     */
    public Image getImage(String imageCategory, String fileName) {
	    return getImage(imageCategory, fileName, DEFAULT_EXTENSION);
    }

    /**
     * Returns String path to folder with image packs that is located inside the project.
     * @return String absolute path to "images" folder with "file:" or "jar:file:" prefix (whether code is packed inside
     * jar or not).
     */
    private static String getRoot() {
        // slash before PROJECT_IMAGES_FOLDER means making absolute path from the root of project
        return ImagePack.class.getResource("/" + PROJECT_IMAGES_FOLDER).toString();
    }

    /**Returns name of folder with the best texture size for given scale.
     * @param scale scale multiplier that was set for the image size
     * @return String with folder name: "1.0x" if scale is 1 or 2, "1.5x" otherwise.
     */
    private static String getScaleFolderName(float scale) {
	    if (Float.compare(scale, 1f) == 0)
	        return "1.0x";
	    if (Float.compare(scale, 2f) == 0)
	        return "1.0x";
	    return "1.5x";
    }

    /**
     * Creates Image objects for images from <code>{@value ICONS_FOLDER}</code> folder and pushes them them into
     * <code>ArrayList</code>. In case of loading errors exits correctly and prints all the information to standard
     * error stream.
     * @param imageNames String array of icon filenames <b>with</b> extension; e.g. <code>"icon16.png"</code>
     * @return {@code ArrayList<Image>} with <code>Image</code> objects for given images; partially filled or empty if
     * some errors have happened
     */
    public ArrayList<Image> getIconList(String[] imageNames) {
        ArrayList<Image> iconList = new ArrayList<>();
        for (String imageName : imageNames) {

            String imagePath = getRoot() + "/" + packFolderName + "/" + ICONS_FOLDER + "/" + imageName;

            try {
                URL imageURL = new URL(imagePath);
                // check whether access to image is successful
                if (ImageIO.read(imageURL) == null) {
                    throw new NullPointerException("Null image source");
                }

                iconList.add(new ImageIcon(imageURL).getImage());

            } catch (IOException | NullPointerException e) {
                System.err.println("Icon image load error!\n" +
                        "   packFolderName: " + packFolderName + "\n" +
                        "   ICONS_FOLDER: " + ICONS_FOLDER + "\n" +
                        "   imageName: " + imageName + "\n" +
                        "   root: " + getRoot() + "\n" +
                        "   full path: " + imagePath);
                e.printStackTrace();
            }
        }
        return iconList;
    }

    /** return default ImagePack set that is initially among project files */
    public static ImagePack[] getDefaultPackSet() {
        ImagePack[] packArray = new ImagePack[2];
        packArray[0] = new ImagePack("Default", "", "default");
        packArray[1] = new ImagePack("Battleship", "Andrés Dugin", "battleship", false);
        return packArray;
    }

    /** String info about package, considered to be shown to user */
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
