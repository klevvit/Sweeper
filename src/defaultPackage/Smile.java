/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
package defaultPackage;

import java.awt.Graphics;
import java.awt.Image;
import java.util.InvalidPropertiesFormatException;

import javax.swing.JPanel;

public class Smile extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 816844242319281397L;
	public static String imageFolder;
	private static Image imageSmile;
	private static Image imagePressed;
	private static Image imageScared;
	private static Image imageDead;
	private static Image imageVictory;
	private Image imageNow = imageSmile;

	private int size;
	private float scale;
	public static final int DEFAULT_SIZE = 26;
	private boolean isPressed;
	private boolean isScared;
	private boolean isDead;
	private boolean isVictory;

	public Smile(float sc) {
		scale = sc;
		size = (int) (scale * DEFAULT_SIZE);
		setSize(size, size);
		resetImages();
	}

	public void paintComponent(Graphics g) {
		g.drawImage(imageNow, 0, 0, size, size, this);
	}

	public void setScale(float sc) {
		scale = sc;
		size = (int) (scale * DEFAULT_SIZE);
		setSize(size, size);
	}

	public void resetImages() {
		try {
			imageFolder = Game.getImagesPath() + "smile/";
		} catch (InvalidPropertiesFormatException ex) {
			ex.printStackTrace();
		}
		imageSmile = Game.getImage(imageFolder + "Smile.png");
		imagePressed = Game.getImage(imageFolder + "Pressed.png");
		imageScared = Game.getImage(imageFolder + "Scared.png");
		imageDead = Game.getImage(imageFolder + "Dead.png");
		imageVictory = Game.getImage(imageFolder + "Victory.png");

		if (!isPressed) {
			if (isVictory) {
				imageNow = imageVictory;
			} else if (isDead) {
				imageNow = imageDead;
			} else if (isScared) {
				imageNow = imageScared;
			} else {
				imageNow = imageSmile;
			}
		} else {
			imageNow = imagePressed;
		}
	}

	public void press() {
		if (!isPressed) {
			isPressed = true;
			if (!isScared) {
				imageNow = imagePressed;
			}
		}
	}

	public void unpress() {

		if (isPressed) {
			isPressed = false;
			if (isDead) {
				imageNow = imageDead;
			} else if (isVictory) {
				imageNow = imageVictory;
			} else {
				imageNow = imageSmile;
			}
		}
	}

	public void scare() {
		if (!isScared) {
			isScared = true;
			imageNow = imageScared;
		}
	}

	public void calm() {
		if (isScared) {
			isScared = false;
			imageNow = imageSmile;
		}
	}

	public void kill() {
		if (!isDead) {
			isDead = true;
			isScared = false;
			imageNow = imageDead;
		}
	}

	public void swag() {
		if (!isDead && !isVictory) {
			isVictory = true;
			isScared = false;
			imageNow = imageVictory;
		}
	}

	public int getSmileSize() {
		return size;
	}
}
