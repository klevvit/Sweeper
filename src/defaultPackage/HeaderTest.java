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

final class HeaderTest {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(null);
		panel.setBackground(Color.CYAN);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setSize(300, 200);

		Header h = new Header(10);
		h.create();
		panel.add(h.getPanel());

		frame.setVisible(true);
	}

	private HeaderTest() {
	}
}
