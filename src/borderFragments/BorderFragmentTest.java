/**
 * @author Kovalenko Lev
*/
/**
 * Copyright Kovalenko Lev (Sweeper) 2016. All rights reserved.
 */
package borderFragments;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

final class BorderFragmentTest {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(500, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		JPanel panel = new JPanel(null);
		panel.setBackground(Color.PINK);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		BorderFragment[] bf = new BorderFragment[8];
		bf[0] = new CenterEast();
		bf[1] = new CenterWest();
		bf[2] = new Horizontal();
		bf[3] = new NorthEast();
		bf[4] = new NorthWest();
		bf[5] = new SouthEast();
		bf[6] = new SouthWest();
		bf[7] = new Vertical();

		for (int i = 0; i < bf.length; i++) {
			bf[i].setLocation(10 + i * BorderFragment.sizeBig, 5);
			bf[i].setSize(bf[i].getSizeX(), bf[i].getSizeY());
			bf[i].setBackground(Color.BLACK);
			System.out.println(bf[i].getSizeX() + " " + bf[i].getSizeY());
			panel.add(bf[i]);
		}
		frame.repaint();
	}

	private BorderFragmentTest() {
	}
}
