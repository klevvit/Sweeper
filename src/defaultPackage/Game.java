package defaultPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import borderFragments.BorderFragment;
import images.Resource;

/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2020. All rights reserved.
 */
public class Game {

	static JFrame frame;
	private static Menu menu;
	private static Header header;
	private static Minefield minefield;
	private static Border border;
	private static JPanel mainPanel = null;  // TODO what is this?

	private static int cellCountX = 10;
	private static int cellCountY = 10;
	private static int minesCount = 10;

	private static boolean isGameOn = false;


	private static float scale = 2f;
	private static final ImagePack[] PACK = ImagePack.getDefaultPackSet();
	private static ImagePack packNow = PACK[0];
	public static final int FRAME_CORRECTION_X = +6;
	public static final int FRAME_CORRECTION_Y = +52;

	static boolean justLaunched = true;

	public void go() {
		// create frame
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("Sweeper");
		frame.setIconImages(makeIconList());

		menu = new Menu();
		frame.getContentPane().add(BorderLayout.NORTH, menu.getMenuBar());

		launchNewGame();
        frame.addKeyListener(new KeyReader());  // key bindings for opening cells, setting flags, etc.
		frame.setVisible(true);
	}
	
	private ArrayList<Image> makeIconList() {
        ArrayList<Image> iconList = new ArrayList<>();
        iconList.add(getImage("Icon16.png"));
        iconList.add(getImage("Icon24.png"));
        iconList.add(getImage("Icon32.png"));
        return iconList;
    }
    
	public static void launchNewGame() {
		removeMainPanel();
		header = new Header(cellCountX);
		minefield = new Minefield(cellCountX, cellCountY, minesCount);
		minefield.create();
		border = new Border();
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
			locateFrameAtScreenCenter();
		}
	}

    public static void launchNewGame(int cellCountX, int cellCountY, int minesCount) {
        Game.cellCountX = cellCountX;
        Game.cellCountY = cellCountY;
        Game.minesCount = minesCount;
        launchNewGame();
    }

    private void createLocateFrameElements() {
	    // TODO
    }

	private static void locateFrameAtScreenCenter() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int windowX = (screenSize.width - frame.getWidth()) / 2;
        int windowY = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(windowX, windowY);
    }

	private static void removeMainPanel() {
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
