/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
package defaultPackage;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import menuListeners.*;

public class Menu {

	private JMenuBar menuBar;

	public Menu() {
		create();
	}

	public void create() {
		menuBar = new JMenuBar();

		createMenuNewGame();
		createMenuOptions();
		createMenuCheats();
	}

	private void createMenuNewGame() {
		JMenu newGame = new JMenu("New game");
		menuBar.add(newGame);

		JMenuItem beginner = new JMenuItem("Beginner");
		beginner.addActionListener(new Beginner());
		newGame.add(beginner);

		JMenuItem amateur = new JMenuItem("Amateur");
		amateur.addActionListener(new Amateur());
		newGame.add(amateur);

		JMenuItem expert = new JMenuItem("Expert");
		expert.addActionListener(new Expert());
		newGame.add(expert);

		JMenuItem pro = new JMenuItem("Pro");
		expert.addActionListener(new Pro());
		newGame.add(pro);
	}

	private void createMenuOptions() {
		JMenu options = new JMenu("Options");
		menuBar.add(options);

		JMenu scale = new JMenu("Scale");
		options.add(scale);

		JMenuItem scale100 = new JMenuItem("100%");
		scale100.addActionListener(new Scale100());
		scale.add(scale100);

		JMenuItem scale150 = new JMenuItem("150%");
		scale150.addActionListener(new Scale150());
		scale.add(scale150);

		JMenuItem scale200 = new JMenuItem("200%");
		scale200.addActionListener(new Scale200());
		scale.add(scale200);

		JMenu imagePack = new JMenu("Image pack");
		options.add(imagePack);

		ImagePack[] pack = Game.getPacks();

		JMenuItem packItem = null;
		ImagePackChanger il = new ImagePackChanger();

		for (int i = 0; i < pack.length; i++) {
			packItem = new JMenuItem(i + 1 + ". " + pack[i].toString());
			packItem.addActionListener(il);
			imagePack.add(packItem);
		}

	}

	private void createMenuCheats() {
		JMenu cheats = new JMenu("Cheats");
		menuBar.add(cheats);

		JMenuItem flagHelper = new JMenuItem("Reset flags");
		flagHelper.addActionListener(new FlagHelper());
		cheats.add(flagHelper);

		JMenuItem openHelper = new JMenuItem("Open cells");
		openHelper.addActionListener(new OpenHelper());
		cheats.add(openHelper);

		JMenuItem solveHelper = new JMenuItem("Solve it!");
		solveHelper.addActionListener(new SolveHelper());
		cheats.add(solveHelper);
	}

	public JMenuBar getMenuBar() {
		return menuBar;
	}
}
