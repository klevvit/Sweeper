package menuListeners;

import java.util.ArrayList;

import defaultPackage.Cell;

public class CellGroup {

	public int mines;
	public ArrayList<Cell> cellList;
	public int key;

	public CellGroup(int minesCount, ArrayList<Cell> cellList, int key) {
		this.mines = minesCount;
		this.cellList = cellList;
		this.key = key;
	}
	
	public CellGroup(int minesCount, ArrayList<Cell> cellList) {
		this.mines = minesCount;
		this.cellList = cellList;
		this.key = -1;
	}
	
	public CellGroup(int key) {
		this.mines = 0;
		this.cellList = new ArrayList<Cell>();
		this.key = key;
	}

}
