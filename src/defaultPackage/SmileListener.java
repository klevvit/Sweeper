/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
package defaultPackage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SmileListener implements MouseListener {
	private boolean entered = false;
	private boolean pressed = false;

	public void mouseEntered(MouseEvent ev) {
		entered = true;
		if (pressed) {
			Smile s = (Smile) (ev.getSource());
			s.press();
			s.repaint();
		}
	}

	public void mouseExited(MouseEvent ev) {
		entered = false;
		if (pressed) {
			Smile s = (Smile) (ev.getSource());
			s.unpress();
			s.repaint();
		}
	}

	public void mousePressed(MouseEvent ev) {
		if (entered) {
			if (ev.getButton() == MouseEvent.BUTTON1) {
				pressed = true;
				Smile s = (Smile) (ev.getSource());
				s.press();
				s.repaint();
			}
		}
	}

	public void mouseReleased(MouseEvent ev) {

		if (pressed) {
			pressed = false;
			if (entered) {
				Smile s = (Smile) (ev.getSource());
				s.unpress();
				Game.launchNewGame();
			}
		}
	}

	public void mouseClicked(MouseEvent ev) {
	}

}
