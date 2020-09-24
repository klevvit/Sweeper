
package defaultPackage;

import java.awt.Color;
import javax.swing.JPanel;

/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
public class Header {

	private final JPanel myPanel = new JPanel(null);

	/** header width measured in minefield cells */
	private final int width;
	/** header height measured in minefield cells */
	public static final int HEIGHT = 2;
	private float scale;
	private final Smile smile;
	private final MineCounter mineCounter;
	private final TimeCounter timer;

    public Header(int minefieldWidth) {
		width = minefieldWidth;
		scale = Game.getScale();
		smile = new Smile(scale);
		mineCounter = new MineCounter();
		timer = new TimeCounter();
		create();
	}

	private void create() {
		myPanel.setSize((int) (width * Cell.DEFAULT_SIZE_IN_PIXELS * scale),
                (int) (HEIGHT * Cell.DEFAULT_SIZE_IN_PIXELS * scale));
		myPanel.setBackground(Color.LIGHT_GRAY);

		// position smile in the center
		smile.setLocation((myPanel.getWidth() - smile.getSmileSize()) / 2,
				(myPanel.getHeight() - smile.getSmileSize()) / 2);

		smile.addMouseListener(new SmileListener());
		myPanel.add(smile);

        JPanel mineCounterPanel = mineCounter.getPanel();
		mineCounterPanel.setLocation((int) (6 * scale), (myPanel.getHeight() - mineCounterPanel.getHeight()) / 2);  // TODO: what's the constant?
		myPanel.add(mineCounterPanel);

        JPanel timerPanel = timer.getPanel();
		timerPanel.setLocation(myPanel.getWidth() - (int) (6 * scale) - timerPanel.getWidth(),  // TODO: what's the constant?
				(myPanel.getHeight() - mineCounterPanel.getHeight()) / 2);
		myPanel.add(timerPanel);
	}

	public void resetScale() {
		scale = Game.getScale();
		smile.setScale(scale);
		mineCounter.setScale(scale);
		timer.setScale(scale);
		create();
	}

	public void resetImages() {
		smile.resetImages();
		mineCounter.resetImages();

	}

	// setters and getters

	public JPanel getPanel() {
		return myPanel;
	}

	public int getHeight() {
		return HEIGHT;
	}

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
