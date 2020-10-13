/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
package defaultPackage;

import javax.swing.JPanel;

import borderFragments.BorderFragment;
import borderFragments.CenterEast;
import borderFragments.CenterWest;
import borderFragments.Horizontal;
import borderFragments.NorthEast;
import borderFragments.NorthWest;
import borderFragments.SouthEast;
import borderFragments.SouthWest;
import borderFragments.Vertical;
import header.Header;

public class Border {

	private final BorderFragment[][] borderHor; // horizontal
	private final BorderFragment[][] borderVert; // vertical
	private final JPanel myPanel = new JPanel(null);

	private final JPanel minefieldPanel;
	private final int minefieldWidth; // of the borderHor array
	private final int minefieldHeight;
	private final JPanel headerPanel;
	private final int headerHeight;

	public Border() {
		Minefield field = Game.getMinefield();
		minefieldPanel = field.getPanel();
		minefieldWidth = field.getWidth();
		minefieldHeight = field.getHeight();
		Header header = Game.getHeader();
//		headerPanel = header.getPanel();  todo just commented and wrote the next line
		headerPanel = header;
		headerHeight = Header.HEIGHT_IN_CELLS;
		borderHor = new BorderFragment[minefieldWidth][3];
		borderVert = new BorderFragment[2][minefieldHeight + headerHeight + 3];
		create();
	}

	private void create() {
		BorderFragment.resetScale();
		for (int i = 0; i < minefieldWidth; i++) {
			for (int j = 0; j <= 2; j++) {
				borderHor[i][j] = new Horizontal();
				borderHor[i][j].setSize(borderHor[i][j].getSizeX(), borderHor[i][j].getSizeY());
				borderHor[i][j].setLocation(BorderFragment.getSizeSmall() + i * Cell.getCellSize(),
						j == 0 ? 0
								: j == 1 ? BorderFragment.getSizeSmall() + headerHeight * Cell.getCellSize()
										: 2 * BorderFragment.getSizeSmall()
												+ Cell.getCellSize() * (minefieldHeight + headerHeight));
				myPanel.add(borderHor[i][j]);
			}
		}

		for (int i = 0; i <= 1; i++) {

			for (int j = 0; j < minefieldHeight + headerHeight + 3; j++) {

				if (j == 0) {
					if (i == 0) {
						borderVert[i][j] = new NorthWest();
					} else {
						borderVert[i][j] = new NorthEast();
					}
				} else if (j == headerHeight + 1) {
					if (i == 0) {
						borderVert[i][j] = new CenterWest();
					} else {
						borderVert[i][j] = new CenterEast();
					}
				} else if (j == (minefieldHeight + headerHeight + 2)) {
					if (i == 0) {
						borderVert[i][j] = new SouthWest();
					} else {
						borderVert[i][j] = new SouthEast();
					}
				} else {
					borderVert[i][j] = new Vertical();
				}

				borderVert[i][j].setSize(borderVert[i][j].getSizeX(), borderVert[i][j].getSizeY());
				borderVert[i][j].setLocation(
						i == 0 ? 0 : borderVert[i][j].getSizeX() + Cell.getCellSize() * minefieldWidth,
						j == 0 ? 0
								: j <= headerHeight + 1
										? BorderFragment.getSizeSmall() + (j - 1) * BorderFragment.getSizeBig()
										: 2 * BorderFragment.getSizeSmall() + (j - 2) * BorderFragment.getSizeBig());
				myPanel.add(borderVert[i][j]);
			}
		}
		headerPanel.setLocation(BorderFragment.getSizeSmall(), BorderFragment.getSizeSmall());
		myPanel.add(headerPanel);
		minefieldPanel.setLocation(BorderFragment.getSizeSmall(),
				2 * BorderFragment.getSizeSmall() + headerHeight * Cell.getCellSize());
		myPanel.add(minefieldPanel);

	}

	public void resetImages() {
		for (int i = 0; i < borderHor.length; i++) {
			for (int j = 0; j < borderHor[i].length; j++) {
				borderHor[i][j].resetImage();
			}
		}
		for (int i = 0; i < borderVert.length; i++) {
			for (int j = 0; j < borderVert[i].length; j++) {
				borderVert[i][j].resetImage();
			}
		}
	}

	public JPanel getPanel() {
		return myPanel;
	}
}
