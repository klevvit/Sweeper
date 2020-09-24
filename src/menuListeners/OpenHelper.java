/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
package menuListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.NoPermissionException;

import defaultPackage.Cell;
import defaultPackage.Game;
import defaultPackage.Minefield;

public class OpenHelper implements ActionListener {

	private Minefield field;
	private Cell[][] cell;
	private boolean isAnythingChanged;

	@Override
	public void actionPerformed(ActionEvent e) {
		go();
		Game.repaint();
	}

	public boolean go() {
		isAnythingChanged = false;
		if (Game.isGameOn()) {
			field = Game.getMinefield();
			cell = field.getCellArray();
			isAnythingChanged = false;

			try {
				for (int i = 0; i < field.getWidth(); i++) {
					for (int j = 0; j < field.getHeight(); j++) {
						if (cell[i][j].getOpened() && cell[i][j].getDigit() != 0
								&& cell[i][j].getDigit() == countFlagsAround(cell[i][j])) {
							openAround(i, j);
						}
					}
				}
			} catch (NoPermissionException nex) {
				nex.printStackTrace();
			}
		}
		return isAnythingChanged;
	}

	private void openAround(int posX, int posY) {
		boolean openIt = false;
		a: for (int m = posX - 1; m <= posX + 1; m++) {
			for (int n = posY - 1; n <= posY + 1; n++) {
				if (field.isCorrect(m, n) && !cell[m][n].getOpened() && !cell[m][n].getFlag()) {
					isAnythingChanged = true;
					openIt = true;
					break a;
				}
			}
		}
		if (openIt)
			field.openAround(posX, posY);
	}

	private int countFlagsAround(Cell checkCell) {
		int flags = 0;
		for (int m = checkCell.x() - 1; m <= checkCell.x() + 1; m++) {
			for (int n = checkCell.y() - 1; n <= checkCell.y() + 1; n++) {
				if (field.isCorrect(m, n) && cell[m][n].getFlag()
						&& !(checkCell.x() == m && checkCell.y() == n)) {
					flags++;
				}
			}
		}
		return flags;
	}

}
