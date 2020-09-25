/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
package display;

import defaultPackage.Game;

import java.awt.Graphics;
import java.awt.Image;
import java.util.InvalidPropertiesFormatException;

import javax.swing.JPanel;

/**
 *  JPanel with controllable image of 7-segment display.
 */
class SevenSegmentDisplay extends JPanel {

	private static final long serialVersionUID = 9019126328963812906L;
	private static String displayFolder;
	private static Image imageMinus;
	private static Image[] image = new Image[10];
	private Image imageNow = imageMinus;
	private static float scale;
	public static final int DEFAULT_WIDTH = 13;
	public static final int DEFAULT_HEIGHT = 23;
	private static int width;
	private static int height;
	public static final int MINUS = -1;
	private int digit = -1;

	static {
		resetImages();
	}

	public static void resetImages() {
		try {
			displayFolder = Game.getImagesPath() + "display/";
		} catch (InvalidPropertiesFormatException ex) {
			ex.printStackTrace();
		}
		imageMinus = Game.getImage(displayFolder + "-.png");
		for (int i = 0; i <= 9; i++) {
			image[i] = Game.getImage(displayFolder + i + ".png");
		}
	}

	public SevenSegmentDisplay(float setScale) {
		scale = setScale;
		width = (int) (scale * DEFAULT_WIDTH);
		height = (int) (scale * DEFAULT_HEIGHT);
		setSize(width, height);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(imageNow, 0, 0, width, height, this);
	}

	public void setDigit(int d) {
		if (d >= -1 && d <= 9) {
			digit = d;
			if (d == -1) {
				imageNow = imageMinus;
			} else {
				imageNow = image[d];
			}
		}
	}

	public void setMinus() {
		digit = -1;
		imageNow = imageMinus;
	}

	public int getDigit() {
		return digit;
	}

	public void setScale(float newScale) {
		scale = newScale;
		width = (int) (scale * DEFAULT_WIDTH);
		height = (int) (scale * DEFAULT_HEIGHT);
		setSize(width, height);
	}
}
