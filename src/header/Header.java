package header;

import defaultPackage.*;
import header.display.MineCounter;
import header.display.TimeCounter;

import java.awt.Color;

/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
public class Header extends WindowElement {

//	private final JPanel myPanel = new JPanel(null); todo remove

	/**
	 * header height measured in minefield cells
	 */
	public static final int HEIGHT_IN_CELLS = 2;
	public static final int DEFAULT_SHIFT_FROM_SIDE_IN_PIXELS = 6;

	private final Smile smile;
	private final MineCounter mineCounter;
	private final TimeCounter timer;

	public Header(int widthInCells) {
		super(widthInCells * Cell.DEFAULT_SIZE_IN_PIXELS, HEIGHT_IN_CELLS * Cell.DEFAULT_SIZE_IN_PIXELS);

		setLayout(null);  // todo change

		setBackground(Color.LIGHT_GRAY);

		smile = new Smile(Game.getScale());  // todo change when better Smile implementation
		mineCounter = new MineCounter();
		timer = new TimeCounter();

		locateElements();

		smile.addMouseListener(new SmileListener());

		add(smile);
		add(mineCounter);
		add(timer);
	}

	public void resetScale() {  // todo fix and possibly get rid of
		float scale = Game.getScale();
		smile.setScale(scale);
	}

//	@Override
	protected void locateElements() {  // todo remove
		// position smile in the center
		smile.setLocation((getWidth() - smile.getSmileSize()) / 2, (getHeight() - smile.getSmileSize()) / 2);

		int sideShift = (int) (DEFAULT_SHIFT_FROM_SIDE_IN_PIXELS * Game.getScale());

		mineCounter.setLocation(sideShift, (getHeight() - mineCounter.getHeight()) / 2);
		timer.setLocation(getWidth() - sideShift - timer.getWidth(), (getHeight() - timer.getHeight()) / 2);
	}

	public void resetImages() {
		smile.resetImages();
		mineCounter.loadImages();

	}

	// setters and getters

	public Smile getSmile() {
		return smile;
	}

	public MineCounter getMineCounter() {
		return mineCounter;
	}

	public TimeCounter getTimer() {
		return timer;
	}
}
