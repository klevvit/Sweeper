/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
package defaultPackage;

import java.awt.Image;

import javax.naming.NoPermissionException;

public class Cell extends Drawable implements Comparable<Cell> {

	public static final int MINED = -1;  // todo use enums
	public static final int NOT_OPENED = -2;  // todo use enums
	private static final String IMAGE_FOLDER_NAME = "cells";
	private static Image imageClosed;
	private static Image imageFlag;
	private static Image imageMine;
	private static Image imageMineWrong;
	private static Image imageMineExploded;
	private static final Image[] imageDigit = new Image[9];
	public static final int SIZE = 16;

	private static boolean safeMode = true;  // todo write meaning

	private boolean isOpened = false;  // todo use enums instead of many booleans
	private boolean isPressed = false;
	private boolean isMine = false;
	private boolean isFlag = false;
	private boolean isShown = false;
	private int digit = 0;

	private final int posX;
	private final int posY;

	static {
		loadImages();
	}

	public static void loadImages() {
		imageClosed = Game.getPackNow().getImage(IMAGE_FOLDER_NAME, "Closed");
		imageFlag = Game.getPackNow().getImage(IMAGE_FOLDER_NAME, "Flag");
		imageMine = Game.getPackNow().getImage(IMAGE_FOLDER_NAME, "Mine");
		imageMineWrong = Game.getPackNow().getImage(IMAGE_FOLDER_NAME, "MineWrong");
		imageMineExploded = Game.getPackNow().getImage(IMAGE_FOLDER_NAME, "MineExploded");
		for (int i = 0; i <= 8; i++) {
			imageDigit[i] = Game.getPackNow().getImage(IMAGE_FOLDER_NAME, Integer.toString(i));
		}

	}

	public Cell(int posX, int posY) {
		super(SIZE, SIZE, imageClosed);
		this.posX = posX;
		this.posY = posY;
	}

	public void press() {
		if (!isPressed && !isOpened && !isFlag) {
			isPressed = true;
			currentImage = imageDigit[0];
		}
	}

	public void release() {
		if (isPressed && !isOpened) {
			isPressed = false;
			currentImage = imageClosed;
		}
	}

	/**
	 * Opens cell if it is not opened, not marked and there is no restrictions on opening it.
	 * @return Cell.MINED if there is a mine in the cell;
	 * Cell.NOT_OPENED if cell was not opened;
	 * digit in the cell otherwise.
	 */
	public int open() {
		if (isOpened || isFlag)
			return NOT_OPENED;
		
		isOpened= true;
		isShown = true;
		isPressed = false;
		if (isMine) {
			currentImage = imageMineExploded;
			return MINED;
		} else {
			currentImage = imageDigit[digit];
			return digit;
		}
	}

	/**
	 * @return true if flag state has been successfully changed, false otherwise.
	 */
	public boolean setFlag(boolean state) {
		if (isOpened || isFlag == state)
			return false;
		isFlag = state;
		if (isFlag) {
			currentImage = imageFlag;
		} else {
			currentImage = imageClosed;
		}
		return true;
	}
	
	/**
	 * 
	 * @return true if flag has been successfully changed, false otherwise.
	 */
	public boolean changeFlag() {
		if (isOpened)
			return false;
		
		isFlag = !isFlag;
		if (isFlag) {
			currentImage = imageFlag;
		} else {
			currentImage = imageClosed;
		}
		return true;
	}

	public void showMine() throws NoPermissionException {
		if (!safeMode) {
			if (!isOpened && isMine && !isFlag) {
				currentImage = imageMine;
				isShown = true;
			} else if (!isOpened && isFlag && !isMine) {
				currentImage = imageMineWrong;
				isShown = true;
			}
		} else
			throw new NoPermissionException("Safe mode is on.");
	}

	public boolean setFlagIfMine() throws NoPermissionException {
		if (!safeMode) {
			if (!isOpened && isMine && !isFlag) {
				changeFlag();
				return (true);
			} else {
				return (false);
			}
		} else
			throw new NoPermissionException("Safe mode is on.");
	}

	public void reset() {
		if (!isOpened) {
			if (isPressed) {
				currentImage = imageDigit[0];
			} else if (isFlag) {
				if (isShown && !isMine) {
					currentImage = imageMineWrong;
				} else {
					currentImage = imageFlag;
				}
			} else if (isShown && isMine) {
				currentImage = imageMine;
			} else {
				currentImage = imageClosed;
			}
		} else if (isMine) {
			currentImage = imageMineExploded;
		} else {
			currentImage = imageDigit[digit];
		}

	}

	// setters and getters

	public int x() {
		return posX;
	}

	public int y() {
		return posY;
	}
	
	public boolean setMine(boolean mine) {
		if (!safeMode && (isMine != mine)) {
			isMine = mine;
			return true;
		} else
			return false;
	}

	public boolean getMine() throws NoPermissionException {
		if (isShown || !safeMode)
			return isMine;
		else
			throw new NoPermissionException("Cell is not shown and safe mode is on.");
	}

	public void incDigit() throws NoPermissionException {
		if (!safeMode)
			digit++;
		else
			throw new NoPermissionException("Safe mode is on.");

	}

	public void setDigit(int numberOfMinesAround) throws NoPermissionException {
		if (!safeMode)
			digit = numberOfMinesAround;
		else
			throw new NoPermissionException("Safe mode is on.");

	}

	public int getDigit() throws NoPermissionException {
		if (isShown || !safeMode)
			return digit;
		else
			throw new NoPermissionException("Cell is not shown and safe mode is on.");
	}

	public boolean getOpened() {
		return isOpened;
	}

	public boolean getPressed() {
		return isPressed;
	}

	public boolean getFlag() {
		return isFlag;
	}

	public static int getCellSize() {
		return (int) (SIZE * getScale());
	}

	public static void setSafeMode(boolean mode) {
		safeMode = mode;
	}

	public static boolean getSafeMode() {
		return safeMode;
	}

	@Override
	public int compareTo(Cell cell) {
		if (posY == cell.y())
			return posX-cell.x();
		return posY - cell.y();
	}
}
