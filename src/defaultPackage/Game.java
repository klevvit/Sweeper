/**
 * @author Kovalenko Lev
 * 
*/
/**
 * Copyright © Kovalenko Lev (Sweeper) 2016. All rights reserved.
 */
package defaultPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import borderFragments.BorderFragment;
import images.Resource;
import menuListeners.FlagHelper;
import menuListeners.OpenHelper;
import menuListeners.SolveHelper;

public class Game {

	static JFrame frame;
	private static Menu menu;
	private static Header header;
	private static Minefield minefield;
	private static Border border;
	private static JPanel mainPanel = null;

	private static int cellCountX = 10;
	private static int cellCountY = 10;
	private static int minesCount = 10;

	private static boolean isGameOn = false;


	private static float scale = 2f;
	public static final String IMAGE_FOLDER = "";
	private static final ImagePack[] PACK = { new ImagePack("Default", "", IMAGE_FOLDER + "default/"),
			new ImagePack("Battleship", "Andrés Dugin", IMAGE_FOLDER + "battleship/", false) };
	private static ImagePack packNow = PACK[0];
	public static final int FRAME_CORRECTION_X = +6;
	public static final int FRAME_CORRECTION_Y = +52;

	static boolean justLaunched = true;

	public void go() {
		// creating frame
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("Sweeper");
		ArrayList<Image> iconList = new ArrayList<Image>();
		iconList.add(getImage("Icon16.png"));
		iconList.add(getImage("Icon24.png"));
		iconList.add(getImage("Icon32.png"));
		frame.setIconImages(iconList);

		menu = new Menu();
		frame.getContentPane().add(BorderLayout.NORTH, menu.getMenuBar());

		launchNewGame();
		frame.setVisible(true);

		frame.addKeyListener(new KeyReader());  // key bindings for opening cells, setting flags, etc.
	}

	public static void launchNewGame() {
		resetGame();
		header = new Header(cellCountX);
		header.create();
		minefield = new Minefield(cellCountX, cellCountY, minesCount);
		minefield.create();
		border = new Border();
		border.create();
		mainPanel = border.getPanel();
		mainPanel.setBackground(Color.WHITE);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.revalidate();

		Dimension frameSize = new Dimension(
				cellCountX * Cell.getCellSize() + 2 * BorderFragment.getSizeSmall() + FRAME_CORRECTION_X,
				(cellCountY + header.getHeight()) * Cell.getCellSize() + 3 * BorderFragment.getSizeSmall()
						+ FRAME_CORRECTION_Y);
		frame.setSize(frameSize);

		setGameOn(true);

		if (justLaunched) {
			justLaunched = false;
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int windowX = (screenSize.width - frame.getWidth()) / 2;
			int windowY = (screenSize.height - frame.getHeight()) / 2;
			frame.setLocation(windowX, windowY);
		}
	}

	public static void launchNewGame(int width, int height, int countOfMines) {
		cellCountX = width;
		cellCountY = height;
		minesCount = countOfMines;
		launchNewGame();
	}

	public static void resetGame() {
		if (mainPanel != null) {
			frame.remove(mainPanel);
		}
	}

	public static void lose() {
		if (!isGameOn)
			return;
		setGameOn(false);
		Cell.setSafeMode(false);
		header.getTimer().stop();
		header.getSmile().kill();
		minefield.showMines();
	}

	public static void win() {
		if (!isGameOn)
			return;
		Cell.setSafeMode(false);
		setGameOn(false);
		header.getTimer().stop();
		header.getSmile().swag();
		minefield.setFlags();

	}

	public static void repaint() {
		frame.repaint();
	}

	public static void setScale(float sc) {
		scale = sc;
		resetImages();
		frame.remove(mainPanel);
		header.resetScale();
		minefield.resetScale();
		border = new Border();
		border.create();
		mainPanel = border.getPanel();
		mainPanel.setBackground(Color.WHITE);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.revalidate();

		Dimension frameSize = new Dimension(
				cellCountX * Cell.getCellSize() + 2 * BorderFragment.getSizeSmall() + FRAME_CORRECTION_X,
				(cellCountY + header.getHeight()) * Cell.getCellSize() + 3 * BorderFragment.getSizeSmall()
						+ FRAME_CORRECTION_Y);
		frame.setSize(frameSize);
	}

	public static void resetImages() {
		header.resetImages();
		minefield.resetImages();
		border.resetImages();
		repaint();
	}

	public static Image getImage(String path) {
		if (Resource.class.getResource(path) == null) {
			System.out.println("Null resource: " + path);
		}
		return new ImageIcon(Resource.class.getResource(path)).getImage();
	}

	// setters and getters

	public static float getScale() {
		return scale;
	}

	public static Minefield getMinefield() {
		return minefield;
	}

	public static Header getHeader() {
		return header;
	}

	public static boolean isGameOn() {
		return isGameOn;
	}

	public static void setGameOn(boolean isGameOn) {
		Game.isGameOn = isGameOn;
	}

	public static int getMinesCount() {
		return minesCount;
	}

	public static String getPackPath() {
		return packNow.getPath();
	}

	public static String getImagesPath() throws InvalidPropertiesFormatException {
		String scaleFolder;
		if (scale % 1.5 == 0) {
			scaleFolder = "1.5x/";
		} else if (scale % 1 == 0) {
			scaleFolder = "1.0x/";
		} else
			throw new InvalidPropertiesFormatException("Incorrect scale.");

		return packNow.getPath() + scaleFolder;
	}

	public static ImagePack[] getPacks() {
		return PACK;
	}

	public static ImagePack getPackNow() {
		return packNow;
	}

	public static void setPackNow(int packNum) {
		packNow = PACK[packNum];
		resetImages();
	}

}
