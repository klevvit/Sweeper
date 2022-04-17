package header;

import defaultPackage.Drawable;
import defaultPackage.Game;

import java.awt.Image;

/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2022. All rights reserved.
 */
public class Smile extends Drawable {  // todo bug: no reaction on pressing / winning

	private static final String IMAGE_FOLDER_NAME = "smile";
	private static Image imageSmile;
	private static Image imagePressed;
	private static Image imageScared;
	private static Image imageDead;
	private static Image imageVictory;
	private Image imageNow = imageSmile;

	public static final int SIZE = 26;
	private boolean isPressed;
	private boolean isScared;
	private boolean isDead;
	private boolean isVictory;

	static {
		loadImages();
	}

	public Smile() {
		super(SIZE, SIZE, imageSmile);
	}

	/** Updates all Image variables. */
	public static void loadImages() {
		imageSmile = Game.getPackNow().getImage(IMAGE_FOLDER_NAME, "Smile");
		imagePressed = Game.getPackNow().getImage(IMAGE_FOLDER_NAME, "Pressed");
		imageScared = Game.getPackNow().getImage(IMAGE_FOLDER_NAME, "Scared");
		imageDead = Game.getPackNow().getImage(IMAGE_FOLDER_NAME, "Dead");
		imageVictory = Game.getPackNow().getImage(IMAGE_FOLDER_NAME, "Victory");

//		todo probably get rid of
//		if (!isPressed) {
//			if (isVictory) {
//				imageNow = imageVictory;
//			} else if (isDead) {
//				imageNow = imageDead;
//			} else if (isScared) {
//				imageNow = imageScared;
//			} else {
//				imageNow = imageSmile;
//			}
//		} else {
//			imageNow = imagePressed;
//		}
	}

	public void press() {
		if (!isPressed) {
			isPressed = true;
			if (!isScared) {
				imageNow = imagePressed;
			}
		}
	}

	public void release() {

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

	public void chill() {
		if (!isDead && !isVictory) {
			isVictory = true;
			isScared = false;
			imageNow = imageVictory;
		}
	}
}
