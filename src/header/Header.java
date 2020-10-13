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

	/** header height measured in minefield cells */
	public static final int HEIGHT_IN_CELLS = 2;

	private final Smile smile;
	private final MineCounter mineCounter;
	private final TimeCounter timer;

    public Header(int widthInCells) {
	    super(widthInCells * Cell.DEFAULT_SIZE_IN_PIXELS, HEIGHT_IN_CELLS * Cell.DEFAULT_SIZE_IN_PIXELS);

	    smile = new Smile(Game.getScale());  // todo change when better Smile implementation
		mineCounter = new MineCounter();
		timer = new TimeCounter();

	    setBackground(Color.LIGHT_GRAY);

	    // position smile in the center
	    smile.setLocation((getWidth() - smile.getSmileSize()) / 2, (getHeight() - smile.getSmileSize()) / 2);
	    smile.addMouseListener(new SmileListener());
	    add(smile);

	    mineCounter.setLocation((int) (6 * Game.getScale()), (getHeight() - mineCounter.getHeight()) / 2);  // TODO: what's the constant?
	    add(mineCounter);

	    System.out.println("mineCounter width: " + mineCounter.getWidth() + "\n" +
			    "mineCounter height: " + mineCounter.getHeight());
	    System.out.println("mineCounter x: " + mineCounter.getX() + "\n" +
			    "mineCounter y: " + mineCounter.getY());

	    timer.setLocation(getWidth() - (int) (6 * Game.getScale()) - timer.getWidth(),  // TODO: what's the constant?
			    (getHeight() - mineCounter.getHeight()) / 2);
	    add(timer);
	}

	public void resetScale() {  // todo fix and possibly get rid of
		float scale = Game.getScale();
		smile.setScale(scale);
//		create();
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
