package defaultPackage;

import header.Header;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
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
		frame.setSize(400, 200);

		Header header = new Header(10);
		panel.add(header);

		frame.setVisible(true);

		System.out.println("Header width: " + header.getWidth() + "\n" +
				"Header height: " + header.getHeight());
	}

	private HeaderTest() {}
}
