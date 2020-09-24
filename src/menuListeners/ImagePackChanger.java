/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
package menuListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import defaultPackage.Game;

public class ImagePackChanger implements ActionListener {

	public void actionPerformed(ActionEvent ev) {
		int num = Integer.parseInt(Character.toString(ev.getActionCommand().charAt(0))) - 1;
		Game.setPackNow(num);
	}

}
