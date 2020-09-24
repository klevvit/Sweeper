/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
package defaultPackage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Notification {

	private String toSay;

	public Notification(String s) {
		toSay = s;
		go();
	}

	private void go() {

		JFrame frame = new JFrame();
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel text = new JLabel();
		text.setText(toSay);
		text.setHorizontalAlignment(SwingConstants.CENTER);
		Font textFont = new Font(null, Font.BOLD, 30);
		text.setFont(textFont);
		// JPanel panel = new JPanel();
		frame.getContentPane().add(BorderLayout.CENTER, text);

		frame.pack();
		frame.setTitle(toSay);
		frame.setResizable(false);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (size.width - frame.getWidth()) / 2;
		int y = (size.height - frame.getHeight()) / 2;
		frame.setLocation(x, y);
		frame.setVisible(true);
	}
}
