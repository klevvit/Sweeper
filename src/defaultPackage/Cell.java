/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
package defaultPackage;

import java.awt.Graphics;
import java.awt.Image;
import java.util.InvalidPropertiesFormatException;

import javax.naming.NoPermissionException;
import javax.swing.JPanel;

public class Cell extends JPanel implements Comparable<Cell> {

	private static final long serialVersionUID = 8464539739576885428L;
	public static final int MINED = -1;
	public static final int NOT_OPENED = -2;
	public static String cellFolder;
	private static Image imageClosed;
	private static Image imageFlag;
	private static Image imageMine;
	private static Image imageMineWrong;
	private static Image imageMineExploded;
	private static Image[] imageDigit = new Image[9];
	private Image imageNow = imageClosed;
	private static float scale;
	private static int size;

	public static final int DEFAULT_SIZE_IN_PIXELS = 16;


	private static boolean safeMode = true;

	private boolean isOpened = false;
	private boolean isPressed = false;
	private boolean isMine = false;
	private boolean isFlag = false;
	private boolean isShown = false;
	private int digit = 0;

	private final int posX;
	private final int posY;

	static {
		resetImages();
	}

	public static void resetImages() {
		try {
			cellFolder = Game.getImagesPath() + "cells/";
		} catch (InvalidPropertiesFormatException ex) {
			ex.printStackTrace();
		}

		imageClosed = Game.getImage(cellFolder + "Closed.png");
		imageFlag = Game.getImage(cellFolder + "Flag.png");
		imageMine = Game.getImage(cellFolder + "Mine.png");
		imageMineWrong = Game.getImage(cellFolder + "MineWrong.png");
		imageMineExploded = Game.getImage(cellFolder + "MineExploded.png");
		for (int i = 0; i <= 8; i++) {
			imageDigit[i] = Game.getImage(cellFolder + i + ".png");
		}

	}

	public Cell(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		setSize(size, size);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(imageNow, 0, 0, size, size, this);
	}

	public void press() {
		if (!isPressed && !isOpened && !isFlag) {
			isPressed = true;
			imageNow = imageDigit[0];
		}
	}

	public void release() {
		if (isPressed && !isOpened) {
			isPressed = false;
			imageNow = imageClosed;
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
			imageNow = imageMineExploded;
			return MINED;
		} else {
			imageNow = imageDigit[digit];
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
			imageNow = imageFlag;
		} else {
			imageNow = imageClosed;
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
			imageNow = imageFlag;
		} else {
			imageNow = imageClosed;
		}
		return true;
	}

	public void showMine() throws NoPermissionException {
		if (!safeMode) {
			if (!isOpened && isMine && !isFlag) {
				imageNow = imageMine;
				isShown = true;
			} else if (!isOpened && isFlag && !isMine) {
				imageNow = imageMineWrong;
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

	public void resetSize() {
		setSize(size, size);
	}

	public void reset() {
		if (!isOpened) {
			if (isPressed) {
				imageNow = imageDigit[0];
			} else if (isFlag) {
				if (isShown && !isMine) {
					imageNow = imageMineWrong;
				} else {
					imageNow = imageFlag;
				}
			} else if (isShown && isMine) {
				imageNow = imageMine;
			} else {
				imageNow = imageClosed;
			}
		} else if (isMine) {
			imageNow = imageMineExploded;
		} else {
			imageNow = imageDigit[digit];
		}

	}

	// setters and getters

	public static void setScale(float sc) {
		scale = sc;
		size = (int) (16 * scale);
	}

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
		return size;
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
