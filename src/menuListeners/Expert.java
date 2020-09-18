/**
 * @author Kovalenko Lev
*/
/**
 * Copyright Kovalenko Lev (Sweeper) 2016. All rights reserved.
 */
package menuListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import defaultPackage.Game;

public class Expert implements ActionListener {

	public void actionPerformed(ActionEvent ev) {
		Game.launchNewGame(30, 16, 99);
	}
}
