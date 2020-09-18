/**
 * @author Kovalenko Lev
*/
/**
 * Copyright Kovalenko Lev (Sweeper) 2016. All rights reserved.
 */
package borderFragments;

import java.awt.Graphics;
import java.awt.Image;
//import java.nio.file.*;
import java.util.InvalidPropertiesFormatException;

import javax.swing.JPanel;

import defaultPackage.Game;

public class BorderFragment extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1044860589052129107L;
	protected static final String MY_FOLDER = "borderFragments/";
	protected int sizeX;
	protected int sizeY;
	protected static int sizeBig = (int) (16 * Game.getScale());
	protected static int sizeSmall = (int) (10 * Game.getScale());
	public Image myImage;

	public BorderFragment() {
		resetImage();
	}

	public void paintComponent(Graphics g) {
		g.drawImage(myImage, 0, 0, sizeX, sizeY, this);
	}

	public static void resetScale() {
		sizeBig = (int) (16 * Game.getScale());
		sizeSmall = (int) (10 * Game.getScale());
	}

	public void resetImage() {
		try {
			myImage = Game.getImage(Game.getImagesPath() + MY_FOLDER + this.getClass().getSimpleName() + ".png");
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		}
	}

	// setters and getters

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public static int getSizeBig() {
		return sizeBig;
	}

	public static int getSizeSmall() {
		return sizeSmall;
	}

}
