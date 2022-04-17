
package defaultPackage;

import header.Header;

/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2022. All rights reserved.
 */
public class Border extends CompoundElement {

	private final BorderFragment[][] borderHorizontal;  // holds only horizontal fragments
	private final BorderFragment[][] borderVertical;  // holds all fragments of left and right column

	private final Minefield minefield;
	private final int minefieldWidthCells; // length of the borderHorizontal array
	private final int minefieldHeightCells;
	private final Header header;
	private final int headerHeightCells;

	public Border() {
		super(Cell.SIZE * (Game.getMinefield().getWidthCells() + 2),
				Cell.SIZE * (Header.HEIGHT_CELLS + Game.getMinefield().getHeightCells() + 3));
		minefield = Game.getMinefield();  // todo i think we need to create minefield ourselves here;
		minefieldWidthCells = minefield.getWidthCells();
		minefieldHeightCells = minefield.getHeightCells();
		header = Game.getHeader();  // todo create header ourselves
		headerHeightCells = Header.HEIGHT_CELLS;
		borderHorizontal = new BorderFragment[minefieldWidthCells][3];
		borderVertical = new BorderFragment[2][minefieldHeightCells + headerHeightCells + 3];
		create();
	}

	private void create() {
		setLayout(null);  // todo get rid of

		for (int i = 0; i < minefieldWidthCells; i++) {

			for (int j = 0; j <= 2; j++) {
				borderHorizontal[i][j] = new BorderFragment(BorderFragment.Type.HORIZONTAL);
				addChild(borderHorizontal[i][j]);
			}

			int horizontalX = BorderFragment.getSmallSize() + i * Cell.getCellSize();
			borderHorizontal[i][0].setLocation(horizontalX, 0);
			borderHorizontal[i][1].setLocation(horizontalX,
					BorderFragment.getSmallSize() + headerHeightCells * Cell.getCellSize());
			borderHorizontal[i][2].setLocation(horizontalX,
					2 * BorderFragment.getSmallSize()
							+ Cell.getCellSize() * (minefieldHeightCells + headerHeightCells));
		}

		for (int i = 0; i <= 1; i++) {

			for (int j = 0; j < minefieldHeightCells + headerHeightCells + 3; j++) {

				if (j == 0) {
					if (i == 0) {
						borderVertical[i][j] = new BorderFragment(BorderFragment.Type.NORTH_WEST);
					} else {
						borderVertical[i][j] = new BorderFragment(BorderFragment.Type.NORTH_EAST);
					}
				} else if (j == headerHeightCells + 1) {
					if (i == 0) {
						borderVertical[i][j] = new BorderFragment(BorderFragment.Type.CENTER_WEST);
					} else {
						borderVertical[i][j] = new BorderFragment(BorderFragment.Type.CENTER_EAST);
					}
				} else if (j == (minefieldHeightCells + headerHeightCells + 2)) {
					if (i == 0) {
						borderVertical[i][j] = new BorderFragment(BorderFragment.Type.SOUTH_WEST);
					} else {
						borderVertical[i][j] = new BorderFragment(BorderFragment.Type.SOUTH_EAST);
					}
				} else {
					borderVertical[i][j] = new BorderFragment(BorderFragment.Type.VERTICAL);
				}

//				borderVertical[i][j].setSize(borderVertical[i][j].getSizeX(), borderVertical[i][j].getSizeY());  todo remove
				borderVertical[i][j].setLocation(
						i == 0 ? 0 : BorderFragment.getSmallSize() + Cell.getCellSize() * minefieldWidthCells,
						j == 0 ? 0
								: j <= headerHeightCells + 1
								? BorderFragment.getSmallSize() + (j - 1) * BorderFragment.getBigSize()
								: 2 * BorderFragment.getSmallSize() + (j - 2) * BorderFragment.getBigSize());
				addChild(borderVertical[i][j]);
			}
		}
		header.setLocation(BorderFragment.getSmallSize(), BorderFragment.getSmallSize());
		addChild(header);
		minefield.setLocation(BorderFragment.getSmallSize(),
				2 * BorderFragment.getSmallSize() + headerHeightCells * Cell.getCellSize());
		addChild(minefield);

	}

//	public void resetImages() {  todo remove
//		for (BorderFragment[] fragments : borderHorizontal) {
//			for (BorderFragment fragment : fragments) {
//				fragment.loadImage();
//			}
//		}
//		for (BorderFragment[] fragments : borderVertical) {
//			for (BorderFragment fragment : fragments) {
//				fragment.loadImage();
//			}
//		}
//	}
}
