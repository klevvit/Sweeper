package header;

import defaultPackage.*;
import header.display.MineCounter;
import header.display.Stopwatch;

import java.awt.Color;

/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
public class Header extends CompoundElement {

	/** header height measured in minefield cells */
	public static final int HEIGHT_CELLS = 2;
	public static final int DEFAULT_SHIFT_FROM_SIDE_IN_PIXELS = 6;

	private final Smile smile;
	private final MineCounter mineCounter;
	private final Stopwatch stopwatch;

	public Header(int widthInCells) {
		super(widthInCells * Cell.SIZE, HEIGHT_CELLS * Cell.SIZE);

		setLayout(null);  // todo change

		setBackground(Color.LIGHT_GRAY);

		smile = new Smile();
		mineCounter = new MineCounter();
		stopwatch = new Stopwatch();

		smile.setLocation((getWidth() - smile.getWidth()) / 2, (getHeight() - smile.getHeight()) / 2);
		int sideShift = (int) (DEFAULT_SHIFT_FROM_SIDE_IN_PIXELS * getScale());
		mineCounter.setLocation(sideShift, (getHeight() - mineCounter.getHeight()) / 2);
		stopwatch.setLocation(getWidth() - sideShift - stopwatch.getWidth(), (getHeight() - stopwatch.getHeight()) / 2);

		smile.addMouseListener(new SmileListener());

		addChild(smile);
		addChild(mineCounter);
		addChild(stopwatch);
	}

	// setters and getters

	public Smile getSmile() {
		return smile;
	}

	public MineCounter getMineCounter() {
		return mineCounter;
	}

	public Stopwatch getStopwatch() {
		return stopwatch;
	}
}
