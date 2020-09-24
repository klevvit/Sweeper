/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
package menuListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import defaultPackage.Game;

public class Amateur implements ActionListener {

	public void actionPerformed(ActionEvent ev) {
		Game.launchNewGame(16, 16, 40);
	}
}
