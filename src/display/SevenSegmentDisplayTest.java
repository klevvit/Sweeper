/**
 * @author Kovalenko Lev
*/
/**
 * Copyright Kovalenko Lev (Sweeper) 2016. All rights reserved.
 */
package defaultPackage;

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
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(null);
		panel.setBackground(Color.CYAN);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setSize(400, 200);

		Display[] d = new Display[11];
		for (int i = -1; i <= 9; i++) {
			d[i + 1] = new Display(2);
			d[i + 1].setDigit(i);
			d[i + 1].setLocation((i + 1) * d[i + 1].getWidth(), d[i + 1].getHeight());
			panel.add(d[i + 1]);
		}
		frame.setVisible(true);
	}

	private DisplayTest() {
	}
}
