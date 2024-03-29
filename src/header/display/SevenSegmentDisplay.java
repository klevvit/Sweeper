
package header.display;

import defaultPackage.Drawable;
import defaultPackage.Game;

import java.awt.Image;

/**
 *  JPanel with controllable image of 7-segment display.
 *  @author Kovalenko Lev
 *  Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
class SevenSegmentDisplay extends Drawable {

	private static final String IMAGE_FOLDER_NAME = "display";
	private static final Image[] image = new Image[10];
	protected static Image imageMinus;
	protected static final int SIZE_X = 13;
	protected static final int SIZE_Y = 23;

	static {
		loadImages();
	}

	/** Gets images of digits and "minus" from current image pack of the game. */
	public static void loadImages() {

		imageMinus = Game.getPackNow().getImage(IMAGE_FOLDER_NAME, "-");

		for (int i = 0; i <= 9; i++) {
			image[i] = Game.getPackNow().getImage(IMAGE_FOLDER_NAME, Integer.toString(i));
		}
	}

	public SevenSegmentDisplay() {
		super(SIZE_X, SIZE_Y, imageMinus);
	}

	/**
	 * Updates its image to required digit if d = 0..9 or to "minus" if d = -1. Does nothing for any other value.
	 * @param d digit to be shown; -1 if "minus" needed.
	 */
	public void setDigit(int d) {

		if (d >= -1 && d <= 9) {

			Image newImage;

			if (d == -1) {
				newImage = imageMinus;
			} else {
				newImage = image[d];
			}

			if (!newImage.equals(currentImage)) {
				currentImage = newImage;
				repaint();
			}
		}

	}

	/** Equivalent to a function call <code>setDigit(-1)</code>. */
	public void setMinus() {
		setDigit(-1);
	}

}
