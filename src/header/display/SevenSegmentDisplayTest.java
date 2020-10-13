package header.display;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Show small window with 11 SevenSegmentDisplay instances with all kinds of symbols (-0123456789).
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
final class SevenSegmentDisplayTest {
	public static void main(String[] args) {

	    // create frame and add our panel
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel(null);
		panel.setBackground(Color.GRAY);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setSize(400, 200);

		// create displays with all possible images and add to panel
		SevenSegmentDisplay[] d = new SevenSegmentDisplay[11];

		for (int i = -1; i <= 9; i++) {
			int ind = i + 1;  // index of the element

			d[ind] = new SevenSegmentDisplay();
			d[ind].setDigit(i);
			d[ind].setLocation((ind) * d[ind].getWidth(), d[ind].getHeight());

			panel.add(d[ind]);
		}

		frame.setVisible(true);
	}
}
