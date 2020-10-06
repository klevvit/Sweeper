package display;

/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
public class MineCounter extends Counter {
	private int minesCount = 0;
	private int cellsMarked = 0;

	public MineCounter() {
		setValue(minesCount - cellsMarked);
	}

	public void setMinesCount(int count) {
		minesCount = Math.max(0, count);
		setValue(minesCount - cellsMarked);
	}

	public void setCellsMarked(int count) {
		cellsMarked = count;
		setValue(minesCount - cellsMarked);
	}

	public void incCellsMarked() {
		cellsMarked++;
		setValue(minesCount - cellsMarked);
	}

	public void decCellsMarked() {
		cellsMarked--;
		setValue(minesCount - cellsMarked);
	}

}
