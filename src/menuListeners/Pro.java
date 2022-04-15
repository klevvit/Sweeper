/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2021. All rights reserved.
 */
package menuListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import defaultPackage.Game;

public class Pro implements ActionListener {

	public void actionPerformed(ActionEvent ev) {
		Game.launchNewGame(99, 99, 1500);
	}
}
