/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
package defaultPackage;

import header.display.MineCounter;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

final class MineCounterTest {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel(null);
		panel.setBackground(Color.CYAN);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.setSize(400, 200);

		MineCounter counter = new MineCounter();
		counter.setMinesCount(1100);
		panel.add(counter);
		frame.setVisible(true);
		for (int i = 1; i <= 1500; i++) {
			counter.incCellsMarked();
			frame.repaint();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private MineCounterTest() {
	}
}
