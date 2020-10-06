/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
package menuListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import javax.naming.NoPermissionException;

import defaultPackage.Cell;
import defaultPackage.Game;
import defaultPackage.Minefield;

public class SolveHelper implements ActionListener {

	private Minefield field;
	private Cell[][] cell;
	
	private ArrayList<Integer>[][] groups;		
	private HashMap<Integer,CellGroup> map;
	private int lastKey;

	private void getField() {
		field = Game.getMinefield();
		cell = field.getCellArray();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		go();
	}

	public void go() {
		if (Game.isGameOn()) {
			
			getField();

			field.resetFlags(false);
			lastKey = 0;
			createGroups();
			
//			Set<CellGroup> groupsToCheck = new Set<CellGroup>(map.values()); //WTF???
//			do {
				
				//TODO some deep crap here
//				primitiveSolve(groupsToCheck);
				
//				groupsToCheck = new Set(combineGroups());	
			
//			} while (somethingChanged);
					
		}
		field.repaint();
		System.out.println("exit successfully");
	}
	
	
	/**
	 * 
	 * @return true if new openable with primitiveSolve() groups appeared.
	 */	
	private ArrayList<CellGroup> combineGroups() {
		//TODO ES IST NOTWENDIG ZU OPTIMIEREN!
//		boolean newGoodGroups = false;
		ArrayList<CellGroup> newGroups = new ArrayList<CellGroup>();
		ArrayList<Integer> keyList = new ArrayList<Integer>(map.keySet());
		System.out.println("�������� ���������");
		a: while (!keyList.isEmpty()) {
			CellGroup group1 = map.get(keyList.remove(0));
			b: for (int i = 0; i < keyList.size(); i++) {
				CellGroup group2 = map.get(keyList.get(i));
				
				if (group1.cellList.size() != group2.cellList.size()) {
					
					CellGroup groupBig;
					CellGroup groupSmall;
					if (group1.cellList.size() > group2.cellList.size()) {
						groupBig = group1;
						groupSmall = group2;
					} else {
						groupBig = group2;
						groupSmall = group1;
					}
					
				
/*					if (groupBig.cellList.containsAll(groupSmall.cellList)) {
					
						ArrayList<Cell> newGroupList = new ArrayList<Cell>(groupBig.cellList);
						newGroupList.removeAll(groupSmall.cellList);
					
						if (!exists(newGroupList)) {
							CellGroup newCellGroup = new CellGroup(groupBig.mines - groupSmall.mines, newGroupList);	
							addGroup(newCellGroup);
							newGroups.add(newCellGroup);
							keyList.add(newCellGroup.key);
							if (newCellGroup.mines == 0 || newCellGroup.mines == newCellGroup.cellList.size()) {
								newGoodGroups = true;
								break a;
							}
							continue b;
						}
					}
				}
*/				
				
				ArrayList<Cell> common = new ArrayList<Cell>();
				ArrayList<Cell> cells1 = new ArrayList<Cell>(group1.cellList);
				ArrayList<Cell> cells2 = new ArrayList<Cell>(group2.cellList);
				
				for (int j = 0; j < cells1.size(); j++) {
					int index = cells2.indexOf(cells1.get(j));
					if (index != -1) {
						common.add(cells1.get(j));
						cells1.remove(j);
						j--;
						cells2.remove(index);
					}
				}
				
				int commonMinMines = Math.max(0, Math.max(group1.mines - cells1.size(), group2.mines - cells2.size()));
				int commonMaxMines = Math.min(common.size(), Math.max(group1.mines, group2.mines));
				int group1MinMines = Math.max(0, group1.mines - commonMaxMines);
				int group1MaxMines = Math.min(cells1.size(), group1.mines - commonMinMines);
				int group2MinMines = Math.max(0, group2.mines - commonMaxMines);
				int group2MaxMines = Math.min(cells2.size(), group2.mines - commonMinMines);
				
				if (group1MinMines == group1MaxMines && !exists(cells1)) {
					CellGroup newCellGroup = new CellGroup(group1MinMines, cells1);	
					addGroup(newCellGroup);
					newGroups.add(newCellGroup);
					keyList.add(newCellGroup.key);
//					if (newCellGroup.mines == 0 || newCellGroup.mines == newCellGroup.cellList.size()) {
//						newGoodGroups = true;
//						break a;
//					}
				}
				
				if (group2MinMines == group2MaxMines && !exists(cells2)) {
					CellGroup newCellGroup = new CellGroup(group2MinMines, cells2);
					addGroup(newCellGroup);
					newGroups.add(newCellGroup);
					keyList.add(newCellGroup.key);
//					if (newCellGroup.mines == 0 || newCellGroup.mines == newCellGroup.cellList.size()) {
//						newGoodGroups = true;
//						break a;
//					}
				}
			}
		}
	}
	System.out.println("������� ���������");
	return newGroups;
	}
	
	/**
	 * Checks if group of these cells already exists.
	 * @param checkList list of cells to check
	 * @return true if given list is empty or group of these cells already exists, false otherwise.
	 */
	private boolean exists(ArrayList<Cell> checkList) {
		if (checkList.size() == 0)
			return true;
		Cell cellToCheck = checkList.get(0);
		ArrayList<Integer> listToCheck = groups[cellToCheck.x()][cellToCheck.y()];
		boolean ans = false;
		for (int j = 0; j < listToCheck.size(); j++) {
			CellGroup groupToCompare = map.get(listToCheck.get(j));
			if (groupToCompare.cellList.size() == checkList.size() && checkList.containsAll(groupToCompare.cellList)) {
				ans = true;
				break;
			}
		}
		return ans;
	}
	
	private boolean primitiveSolve(Set<CellGroup> groupsSet) {
		
		//TODO work with CellGroups not keys
		
		boolean smthChanged = false;
		LinkedList<CellGroup> keyQueue = new LinkedList<CellGroup>(groupsSet);
		
				
		while (!keyQueue.isEmpty()) {
			CellGroup group = keyQueue.poll();
			int keyNow = group.key;

			if (group == null) {
//				System.out.println("DEAD GROUP key=" + keyNow + " keyQueue_size=" + keyQueue.size());
				continue;
			}
					
				//sysouts
				/*
					System.out.println("Group=" + group.key + " mines=" + group.mines + " cells=" + group.cellList.size());
					for (Cell c : group.cellList)
						System.out.println("x=" + c.x() + " y=" + c.y());
					System.out.println();
				*/
				//end of sysouts
					
			if (group.mines == 0 && group.cellList.size() != 0) {
				smthChanged = true;
						
//				System.out.println("zero mines");
						
				TreeSet<Cell> toUpdateInfo = new TreeSet<Cell>();
				while (!group.cellList.isEmpty()) {
					
					Cell c = group.cellList.remove(0);
					groups[c.x()][c.y()].remove((Integer) keyNow);
					toUpdateInfo.addAll(field.openWithList(c, false));
							
				}
				keyQueue.addAll(refreshGroupList(new ArrayList<Cell>(toUpdateInfo)));
						
				map.remove(keyNow);

//						System.out.println("zero done\n");
					
			} else if (group.mines == group.cellList.size()) {
				smthChanged = true;
						
//				System.out.println("mines=cells");
						
				while (!group.cellList.isEmpty()) {
					Cell c = group.cellList.remove(0);
					field.setFlag(c, true, false);
					
					ArrayList<Integer> keysToUpdate = groups[c.x()][c.y()]; 
					keysToUpdate.remove((Integer) keyNow);
					for (Integer key : keysToUpdate) {
						keyQueue.add(map.get(key));
					}
						
					while (!keysToUpdate.isEmpty()) {
						CellGroup toUpdate = map.get(keysToUpdate.remove(0));
						toUpdate.cellList.remove(c);
						toUpdate.mines--;
					}
				}
				map.remove(keyNow);
						
//				System.out.println("mining done\n");
			}
		}
				
	return smthChanged;			
	}

	@SuppressWarnings("unchecked")
	private void createGroups() {
		map = new HashMap<Integer, CellGroup>();
		groups = new ArrayList[field.getWidth()][field.getHeight()];
		for (int i = 0; i < field.getWidth(); i++)
			for (int j = 0; j < field.getHeight(); j++)
				groups[i][j] = new ArrayList<Integer>();
		
		for (int i = 0; i < field.getWidth(); i++)
			for (int j = 0; j < field.getHeight(); j++)
				addGroup(i, j);
		
		ArrayList<Cell> fullFieldList = new ArrayList<Cell>();
		for (int i = 0; i < field.getWidth(); i++)
			for (int j = 0; j < field.getHeight(); j++) {
				fullFieldList.add(field.getCell(i, j));
			}
		CellGroup fullFieldGroup = new CellGroup(field.getMinesCount(), fullFieldList);
		addGroup(fullFieldGroup);
		
//		System.out.println("end of start creating");
	}

	/**
	 * Adds given cell group to map and puts key into groups[][] array lists.
	 * @param group cell group to add
	 */
	private void addGroup(CellGroup group) {
		if (group.key == -1) { 
			lastKey++;
			group.key = lastKey;
		}
		
		for (int i = 0; i < group.cellList.size(); i++)
			groups[group.cellList.get(i).x()][group.cellList.get(i).y()].add(lastKey);
		
		map.put(group.key, group);
	}
	
	/**
	 * Creates new cell group using number in the given cell, adds it to map and puts its key into groups[][] array lists.
	 * @param x X-coordinate of the given cell
	 * @param y Y-coordinate of the given cell
	 */
	private boolean addGroup(int x, int y) {
		try {
			if (cell[x][y].getOpened() && cell[x][y].getDigit() != 0) {
				
//				System.out.println("creating key=" + (lastKey + 1) + " x=" + x + " y=" + y);
				
				ArrayList<Cell> list = new ArrayList<Cell>();
				int minesCount = 0;
				minesCount = cell[x][y].getDigit();

				for (int i = x - 1; i <= x + 1; i++) {
					for (int j = y - 1; j <= y + 1; j++) {
						if (!((i == x) && (j == y)) && field.isCorrect(i, j)) {
							if (cell[i][j].getFlag()) {
								minesCount--;
							} else if (!cell[i][j].getOpened()) {
								list.add(cell[i][j]);
							}
						}
					}
				}
				
				addGroup(new CellGroup(minesCount, list));
				
//				System.out.println("creating done mines=" + group.mines + " cells=" + group.cellList.size());
				return true;
			}

		} catch (NoPermissionException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Creates new cell group using number in the given cell, adds it to map and puts key into groups[][] array lists.
	 * @param c given cell
	 */
	private boolean addGroup(Cell c) {
		return addGroup(c.x(), c.y());
	}

	private TreeSet<CellGroup> refreshGroupList(ArrayList<Cell> cellList) {
		TreeSet<CellGroup> groupSet = new TreeSet<CellGroup>();
		for (Cell cell : cellList)
			while (!groups[cell.x()][cell.y()].isEmpty()) {
				
				int key = groups[cell.x()][cell.y()].remove(0);
				CellGroup group = map.get(key);
				group.cellList.remove(cell);
				groupSet.add(group);
			}
		
		for (Cell cell : cellList) {
			if (addGroup(cell))
				groupSet.add(map.get(lastKey));
		}
		return groupSet;
	}

/*	private void removeGroup(int key) {
		removeGroup(map.get(key));
	}
	
	private void removeGroup(CellGroup group) {
		for (Cell cell : group.cellList) {
			groups[cell.x()][cell.y()].remove((Integer) group.key);
		}
		map.remove(group.key);
	}
*/
}
