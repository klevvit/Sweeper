package defaultPackage;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2022. All rights reserved.
 */
final class BorderFragmentTest {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(500, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(null);
		panel.setBackground(Color.PINK);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		BorderFragment[] bf = new BorderFragment[8];
		bf[0] = new BorderFragment(BorderFragment.Type.CENTER_EAST);
		bf[1] = new BorderFragment(BorderFragment.Type.CENTER_WEST);
		bf[2] = new BorderFragment(BorderFragment.Type.HORIZONTAL);
		bf[3] = new BorderFragment(BorderFragment.Type.NORTH_EAST);
		bf[4] = new BorderFragment(BorderFragment.Type.NORTH_WEST);
		bf[5] = new BorderFragment(BorderFragment.Type.SOUTH_EAST);
		bf[6] = new BorderFragment(BorderFragment.Type.SOUTH_WEST);
		bf[7] = new BorderFragment(BorderFragment.Type.VERTICAL);

		for (int i = 0; i < bf.length; i++) {
			bf[i].setLocation(10 + i * BorderFragment.getBigSize(), 5);
			bf[i].setBackground(Color.BLACK);
			panel.add(bf[i]);
		}
		frame.setVisible(true);
	}

}
