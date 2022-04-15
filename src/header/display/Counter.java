package header.display;

import defaultPackage.CompoundElement;
import java.awt.*;

/**
 * Class of 3 7-segment displays in a row.
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2022. All rights reserved.
 */
public class Counter extends CompoundElement {

	/** a number to display */
	private int value;

	private final SevenSegmentDisplay[] display = new SevenSegmentDisplay[3];

	protected Counter(int value) {
		super(3 * SevenSegmentDisplay.SIZE_X, SevenSegmentDisplay.SIZE_Y);

		setLayout(new GridLayout(1, 3));

		for (int i = 0; i < display.length; i++) {
			display[i] = new SevenSegmentDisplay();
			addChild(display[i]);
		}

		setValue(value);
	}

	protected Counter() {
		this(0);
	}

	/** Sets digits on displays to show the number from <code>value</code> variable. */
	private void setValue() {

		int toShow = getValueToShow(value);

		if (toShow < 0) {
			display[0].setMinus();
		} else {
			display[0].setDigit(toShow / 100 % 10);
		}

		display[1].setDigit(Math.abs(toShow) / 10 % 10);
		display[2].setDigit(Math.abs(toShow) % 10);
	}

	/**
	 * Returns the maximal/minimal number display can show if val is out of bounds
	 * @param val value required to be adopted
	 * @return <code>val</code> if it can be shown on display; -99 if it's less than -99; 999 if it's more than 999.
	 */
	private int getValueToShow(int val) {
		return Math.max(-99, Math.min(val, 999));
	}

	/** Updates object value and sets digits on displays to show it */
	protected void setValue(int val) {
		value = val;
		setValue();
	}

	protected int getValue() {
		return value;
	}

//	public void loadImages() {  // todo
//		SevenSegmentDisplay.loadImages();
//		setValue();
//	}

	/**
	 * Override default scaled size calculation to adopt to size of 7-segment display.
	 * @param defaultSizeX element width for scale = 1
	 * @param defaultSizeY element height for scale = 1
	 * @return Dimension object with sizes for current scale.
	 */
	@Override
	protected Dimension getScaledSize(int defaultSizeX, int defaultSizeY) {
		if (display == null) {
			return super.getScaledSize(defaultSizeX, defaultSizeY);
		}

		int realSizeX = 3 * display[0].getWidth();
		int realSizeY = display[0].getHeight();

		return new Dimension(realSizeX, realSizeY);
	}

	@Override
	public void paintComponent(Graphics g) {
		for (int i = 0; i < display.length; i++) {
			display[i].setLocation(i * display[i].getWidth(), 0);
			add(display[i]);
		}
		super.paintComponent(g);
	}
}
