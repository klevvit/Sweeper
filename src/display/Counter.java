/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
package defaultPackage;

import javax.swing.JPanel;

public class Counter {
	protected int displayValue;
	protected JPanel myPanel = new JPanel(null);
	protected static float scale = Game.getScale();
	protected final Display[] display = new Display[3];

	protected void create() {
		for (int i = 0; i < display.length; i++) {
			display[i] = new Display(scale);
		}
		setLocationAndSize();
	}

	protected void setLocationAndSize() {
		myPanel.setSize(0, 0);
		for (int i = 0; i < display.length; i++) {
			display[i].setScale(scale);
			display[i].setLocation(i * display[i].getWidth(), 0);
			myPanel.setSize(myPanel.getWidth() + display[i].getWidth(), display[i].getHeight());
			myPanel.add(display[i]);
		}
	}

	protected void resetCounter() {
		if (displayValue < 0) {
			display[0].setMinus();
		} else {
			display[0].setDigit(displayValue / 100 % 10);
		}
		display[1].setDigit(Math.abs(displayValue) / 10 % 10);
		display[2].setDigit(Math.abs(displayValue) % 10);
	}

	public void setValue(int value) {
		displayValue = value;
		resetCounter();
	}

	public void setScale(float newScale) {
		scale = newScale;
		setLocationAndSize();
		resetCounter();
	}

	public void inc() {
		displayValue++;
		resetCounter();
	}

	public void resetImages() {
		Display.resetImages();
		resetCounter();
	}

	public JPanel getPanel() {
		return myPanel;
	}
}
