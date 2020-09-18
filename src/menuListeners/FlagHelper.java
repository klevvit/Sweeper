/**
 * @author Kovalenko Lev
*/
/**
 * Copyright Kovalenko Lev (Sweeper) 2016. All rights reserved.
 */
package menuListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.NoPermissionException;

import defaultPackage.Cell;
import defaultPackage.Game;
import defaultPackage.Minefield;

public class FlagHelper implements ActionListener {

	private Minefield field;
	private Cell[][] cell;
	private boolean isAnythingChanged;
	private boolean isField = false;

	private void getField() {
		field = Game.getMinefield();
		cell = field.getCellArray();
		isField = true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		reputFlags();
		Game.repaint();
	}

	public void reputFlags() {
		if (Game.isGameOn()) {
			if (!isField)
				getField();
			for (int i = 0; i < field.getWidth(); i++) {
				for (int j = 0; j < field.getHeight(); j++) {
					field.setFlag(i, j, false);
				}
			}
			go();
		}
	}

	public boolean go() {
		isAnythingChanged = false;
		if (Game.isGameOn()) {
			if (!isField)
				getField();
			isAnythingChanged = false;

			try {

				for (int i = 0; i < field.getWidth(); i++) {
					for (int j = 0; j < field.getHeight(); j++) {
						if (cell[i][j].getOpened() && cell[i][j].getDigit() != 0
								&& cell[i][j].getDigit() == countClosedCellsAround(cell[i][j])) {
							setFlagsAround(i, j);
						}
					}
				}
			} catch (NoPermissionException nex) {
				nex.printStackTrace();
			}
		}
		return isAnythingChanged;
	}

	private void setFlagsAround(int posX, int posY) {
		for (int m = posX - 1; m <= posX + 1; m++) {
			for (int n = posY - 1; n <= posY + 1; n++) {
				if (field.isCorrect(m, n) && !cell[m][n].getOpened() && !cell[m][n].getFlag()) {
					field.setFlag(m, n, true);
					isAnythingChanged = true;
				}
			}
		}
	}

	private int countClosedCellsAround(Cell checkCell) {
		int countClosed = 0;
		for (int m = checkCell.x() - 1; m <= checkCell.x() + 1; m++) {
			for (int n = checkCell.y() - 1; n <= checkCell.y() + 1; n++) {
				if (field.isCorrect(m, n) && !cell[m][n].getOpened()
						&& !(checkCell.x() == m && checkCell.y() == n)) {
					countClosed++;
				}
			}
		}
		return countClosed;
	}

}
