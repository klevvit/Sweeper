package defaultPackage;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @deprecated
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
final class HeaderTest {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(null);
		panel.setBackground(Color.CYAN);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setSize(300, 200);

		Header h = new Header(10);
		panel.add(h.getPanel());

		frame.setVisible(true);
	}

	private HeaderTest() {
	}
}
