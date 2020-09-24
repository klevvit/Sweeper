/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
package defaultPackage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CellListener implements MouseListener {

	private final Minefield minefield;
	private final Smile smile;

	private static boolean leftPressed = false;
	private static boolean rightPressed = false;
	private static boolean entered = false;
	private static int posX;
	private static int posY;

	public CellListener() {
		minefield = Game.getMinefield();
		smile = Game.getHeader().getSmile();
	}

	public void mousePressed(MouseEvent ev) {
		if (Game.isGameOn()) {
			int b = ev.getButton();
			if (b == MouseEvent.BUTTON1) {
				leftPressed = true;
				smile.scare();
			} else if (b == MouseEvent.BUTTON3) {
				rightPressed = true;
			}
			if (entered) {

				if (rightPressed && leftPressed) {
					minefield.pressAround(posX, posY);
					repaint();
				} else if (b == MouseEvent.BUTTON1) {
					leftPressed = true;
					minefield.press(posX, posY);
					repaint();
				} else if (b == MouseEvent.BUTTON3) {
					rightPressed = true;
					minefield.changeFlag(posX, posY);
					repaint();
				}
			}

		}
	}

	public void mouseEntered(MouseEvent ev) {
		if (Game.isGameOn()) {

			entered = true;
			getCellPos(ev);

			if (leftPressed && rightPressed) {
				minefield.pressAround(posX, posY);
				smile.scare();
				minefield.repaint();

			} else if (leftPressed) {

				minefield.press(posX, posY);
				;
				smile.scare();
				minefield.repaint();
			}

		}
	}

	public void mouseReleased(MouseEvent ev) {
		if (Game.isGameOn()) {

			int b = ev.getButton();

			if (entered) {

				if (leftPressed && rightPressed) {
					leftPressed = false;
					rightPressed = false;
					minefield.openAround(posX, posY);
					smile.calm();
					repaint();

				} else if ((b == MouseEvent.BUTTON1) && leftPressed) {

					leftPressed = false;
					minefield.open(posX, posY);
					smile.calm();
					repaint();

				}
			}
			if (b == MouseEvent.BUTTON1) {
				leftPressed = false;

			} else if (b == MouseEvent.BUTTON3) {
				rightPressed = false;

			}

		}
	}

	public void mouseExited(MouseEvent ev) {
		if (Game.isGameOn()) {

			entered = false;

			if (leftPressed && rightPressed) {
				minefield.releaseAround(posX, posY);
				smile.calm();
				repaint();

			} else if (leftPressed) {
				minefield.release(posX, posY);
				smile.calm();
				repaint();
			}

		}
	}

	public void mouseClicked(MouseEvent ev) {
	}

	public void getCellPos(MouseEvent ev) {
		Cell cellCursor = (Cell) (ev.getSource());
		posX = cellCursor.x();
		posY = cellCursor.y();
	}

	public void repaint() {
		minefield.repaint();
	}
}
