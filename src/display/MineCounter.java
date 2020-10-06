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

	public void setMinesCount(int mc) {
		minesCount = Math.max(0, mc);
		setValue(minesCount - cellsMarked);
	}

	public void setCellsMarked(int mm) {
		cellsMarked = mm;
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
