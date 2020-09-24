/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
package defaultPackage;

import java.util.ArrayList;

import javax.naming.NoPermissionException;
import javax.swing.JPanel;

public class Minefield {

	private boolean isMined = false;
	private final int width;
	private final int height;
	private final int minesCount;
	private final MineCounter mineCounter = Game.getHeader().getMineCounter();
	private JPanel myPanel = new JPanel(null);
	private CellListener cellListener;

	private int cellsMarked = 0;
	private int cellsOpened = 0;

	private float scale = Game.getScale();
	public static final int WIDTH_MAX = 75;
	public static final int HEIGHT_MAX = 50;
	public static final int LEVEL_BEGINNER = 1;
	public static final int LEVEL_AMATEUR = 2;
	public static final int LEVEL_EXPERT = 3;
	private Cell[][] cell;

	public Minefield(int fieldWidth, int fieldHeight, int countOfMines) {
		width = Math.min(fieldWidth, WIDTH_MAX);
		height = Math.min(fieldHeight, HEIGHT_MAX);
		minesCount = Math.min(countOfMines, width * height - 1);
		Game.setGameOn(true);
	}

	public void create() {

		cell = new Cell[width][height];
		Cell.setScale(scale);
		myPanel.setSize(Cell.getCellSize() * width, Cell.getCellSize() * height);
		cellListener = new CellListener();

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				cell[i][j] = new Cell(i, j);
				cell[i][j].setLocation(i * Cell.getCellSize(), j * Cell.getCellSize());
				myPanel.add(cell[i][j]);
				cell[i][j].addMouseListener(cellListener);
			}
		}
		mineCounter.setMinesCount(minesCount);
	}

	public void resetScale() {
		scale = Game.getScale();
		Cell.setScale(scale);
		myPanel = new JPanel(null);
		myPanel.setSize((int) (16 * width * scale), (int) (16 * height * scale));

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				cell[i][j].resetSize();
				cell[i][j].setLocation(i * Cell.getCellSize(), j * Cell.getCellSize());
				myPanel.add(cell[i][j]);
			}
		}
	}

	public void mine(int openedPosX, int openedPosY) {
		if (!isMined) {
			try {
				Cell.setSafeMode(false);
				int minePosX;
				int minePosY;
				for (int i = 1; i <= minesCount; i++) {
					do {
						minePosX = (int) (Math.random() * width);
						minePosY = (int) (Math.random() * height);
					} while (cell[minePosX][minePosY].getMine()
							|| ((minePosX == openedPosX) && (minePosY == openedPosY)));

					cell[minePosX][minePosY].setMine(true);
				}

				for (int i = 0; i < width; i++) {
					for (int j = 0; j < height; j++) {
						for (int m = i - 1; m <= i + 1; m++) {
							for (int n = j - 1; n <= j + 1; n++) {
								if ((m >= 0) && (m < width) && (n >= 0) && (n < height) && (cell[m][n].getMine())) {

									cell[i][j].incDigit();
								}
							}
						}
					}
				}
				Cell.setSafeMode(true);
				Thread timerThread = new Thread(Game.getHeader().getTimer(), "TimerThread");
				timerThread.start();
				isMined = true;
			} catch (NoPermissionException nex) {
				nex.printStackTrace();
			}
		}
	}

	public void press(int posX, int posY) {
		if ((posX >= 0) && (posX < width) && (posY >= 0) && (posY < height)) {
			cell[posX][posY].press();
		}
	}

	public void release(int posX, int posY) {
		if ((posX >= 0) && (posX < width) && (posY >= 0) && (posY < height)) {
			cell[posX][posY].release();
		}
	}

	public void pressAround(int posX, int posY) {
		for (int i = posX - 1; i <= posX + 1; i++) {
			for (int j = posY - 1; j <= posY + 1; j++) {

				if ((i >= 0) && (i < width) && (j >= 0) && (j < height)) {
					cell[i][j].press();
				}
			}
		}
	}

	public void releaseAround(int posX, int posY) {
		for (int i = posX - 1; i <= posX + 1; i++) {
			for (int j = posY - 1; j <= posY + 1; j++) {

				if ((i >= 0) && (i < width) && (j >= 0) && (j < height)) {
					cell[i][j].release();
				}
			}
		}
	}

	public void openAround(int posX, int posY) {
		try {
			if (cell[posX][posY].getOpened() && !cell[posX][posY].getMine()) {

				int countFlagsAround = 0;

				for (int i = posX - 1; i <= posX + 1; i++) {
					for (int j = posY - 1; j <= posY + 1; j++) {

						if (isCorrect(i, j) && (cell[i][j].getFlag() || (cell[i][j].getOpened() && cell[i][j].getMine()))) {

							countFlagsAround++;
						}
					}
				}

				if (cell[posX][posY].getDigit() == countFlagsAround) {

					for (int i = posX - 1; i <= posX + 1; i++) {
						for (int j = posY - 1; j <= posY + 1; j++) {
							open(i, j, false);
						}
					}
					repaint();
				} else {
					releaseAround(posX, posY);
				}
			} else {
				releaseAround(posX, posY);
			}

		} catch (NoPermissionException nex) {
			nex.printStackTrace();
		}

	}

	public void open(int posX, int posY) {
		open(posX, posY, true);
	}
	
	public void open(int posX, int posY, boolean needRepainting) {

		if (isCorrect(posX, posY)) {
			if (!isMined)
				mine(posX, posY);

			int result = cell[posX][posY].open();
			switch (result) {
			case Cell.MINED:
				Game.lose();
				break;
			case 0:
				for (int i = posX - 1; i <= posX + 1; i++)
					for (int j = posY - 1; j <= posY + 1; j++)
						open(i, j, false);
				break;
			default:
				break;
			}
			
			
			if (result != Cell.NOT_OPENED) {
				cellsOpened++;
				if (needRepainting)
					repaint();
			}

			if (cellsOpened == width * height - minesCount)
				Game.win();
		}
	}

	public void open(Cell toOpen) {
//		System.out.println("opening:" + toOpen.getPosX() + ";" + toOpen.getPosY());
		open(toOpen.x(), toOpen.y());
	}
	
	public ArrayList<Cell> openWithList(int posX, int posY, boolean needRepainting) {
		
		ArrayList<Cell> opened = new ArrayList<Cell>();
		
		if (isCorrect(posX, posY)) {
			
			if (!isMined)
				mine(posX, posY);

			int result = cell[posX][posY].open();
			switch (result) {
			case Cell.MINED:
				Game.lose();
				break;
			case 0:
				for (int i = posX - 1; i <= posX + 1; i++)
					for (int j = posY - 1; j <= posY + 1; j++)
						opened.addAll(openWithList(i, j, false));
				break;
			default:
				break;
			}
			
			if (result != Cell.NOT_OPENED) {
				cellsOpened++;
				opened.add(cell[posX][posY]);
				if (needRepainting)
					repaint();
			}

			if (cellsOpened == width * height - minesCount)
				Game.win();
		}
		
		return opened;
	}

	public ArrayList<Cell> openWithList(int posX, int posY) {
		return openWithList(posX, posY, true);
	}
	
	public ArrayList<Cell> openWithList(Cell toOpen) {
		return openWithList(toOpen.x(), toOpen.y(), true);
	}
	
	public ArrayList<Cell> openWithList(Cell toOpen, boolean needRepainting) {
		return openWithList(toOpen.x(), toOpen.y(), needRepainting);
	}
	
	public void changeFlag(int posX, int posY) {
		if ((posX >= 0) && (posX < width) && (posY >= 0) && (posY < height) && (!cell[posX][posY].getOpened())) {
			cell[posX][posY].changeFlag();
			if (cell[posX][posY].getFlag()) {
				cellsMarked++;
				mineCounter.incCellsMarked();
			} else {
				cellsMarked--;
				mineCounter.decCellsMarked();
			}
		}
	}

	public void changeFlag(Cell toChange) {
		changeFlag(toChange.x(), toChange.y());
	}

	public void setFlag(int posX, int posY, boolean state, boolean needRepainting) {
		if (isCorrect(posX, posY) && cell[posX][posY].setFlag(state)) {
			if (state) {
				cellsMarked++;
				mineCounter.incCellsMarked();
			} else {
				cellsMarked--;
				mineCounter.decCellsMarked();
			}
			if (needRepainting)
				repaint();
		}
	}

	public void setFlag(int posX, int posY, boolean state) {
		setFlag(posX, posY, state, true);
	}
	
	public void setFlag(Cell toSet, boolean state) {
		setFlag(toSet.x(), toSet.y(), state);
	}
	
	public void setFlag(Cell toSet, boolean state, boolean needRepainting) {
		setFlag(toSet.x(), toSet.y(), state, needRepainting);
	}

	public void showMines() {
		try {
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					cell[i][j].showMine();
				}
			}
		} catch (NoPermissionException nex) {
			nex.printStackTrace();
		}
		repaint();
	}

	public void setFlags() {
		try {
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					if (cell[i][j].setFlagIfMine()) {
						cellsMarked++;
					}
				}
			}
		} catch (NoPermissionException nex) {
			nex.printStackTrace();
		}
		mineCounter.setCellsMarked(cellsMarked);
		repaint();
	}

	/**
	 * Removes all flags from cells.
	 * @param needRepainting repaints frame after removing flags if true, does nothing otherwise.
	 */
	public void resetFlags(boolean needRepainting) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				setFlag(i, j, false, false);
			}
		}
		if (needRepainting)
			repaint();
	}
	
	/**
	 * Removes all flags from cells.
	 */
	public void resetFlags() {
		resetFlags(true);
	}
	
	public void resetImages() {
		Cell.resetImages();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				cell[i][j].reset();
			}
		}
	}

	public boolean isCorrect(int x, int y) {
		if (x >= 0 && x < getWidth() && y >= 0 && y < getHeight())
			return true;
		else
			return false;
	}

	public void repaint() {
		Game.repaint();
	}

	// setters and getters

	public JPanel getPanel() {
		return myPanel;
	}

	public Cell getCell(int x, int y) {
		return cell[x][y];
	}

	public Cell[][] getCellArray() {
		return cell;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getMinesCount() {
		return minesCount;
	}
}
