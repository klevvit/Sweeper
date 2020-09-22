package defaultPackage;

/**
 * @author Kovalenko Lev
 * Copyright Kovalenko Lev (Sweeper) 2016. All rights reserved.
 */
public class MineCounter extends Counter {
	private int minesCount;
	private int cellsMarked = 0;

	public MineCounter() {
		create();
		reset();
	}

	private void reset() {
		if (minesCount - cellsMarked > 999) {
			displayValue = 999;
		} else if (minesCount - cellsMarked < -99) {
			displayValue = -99;
		} else {
			displayValue = minesCount - cellsMarked;
		}
		resetCounter();
	}

	// setters and getters

	public void setMinesCount(int mc) {
		minesCount = Math.max(0, mc);
		reset();
	}

	public void incMinesCount() {
		minesCount++;
		reset();
	}

	public void decMinesCount() {
		minesCount--;
		reset();
	}

	public void setCellsMarked(int mm) {
		cellsMarked = mm;
		reset();
	}

	public void incCellsMarked() {
		cellsMarked++;
		reset();
	}

	public void decCellsMarked() {
		cellsMarked--;
		reset();
	}

}
