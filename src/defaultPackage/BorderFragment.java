package defaultPackage;

import java.awt.*;
import java.util.HashMap;

/**
 * @author Kovalenko Lev
 * Copyright © Kovalenko Lev (Sweeper) 2016-2022. All rights reserved.
 */
public class BorderFragment extends Drawable {

	public enum Type {
		NORTH_WEST,
		CENTER_WEST,
		SOUTH_WEST,
		NORTH_EAST,
		CENTER_EAST,
		SOUTH_EAST,
		HORIZONTAL,
		VERTICAL
	}

	private static final String IMAGE_FOLDER_NAME = "borderFragments";

	private static final int BIG_SIZE = 16;
	private static final int SMALL_SIZE = 10;
	public BorderFragment(Type type) {
		super(getDimension(type), getImage(type));
	}

	private static Image getImage(Type type) {
		HashMap<Type, String> map = new HashMap<>();
		map.put(Type.NORTH_WEST, "NorthWest");
		map.put(Type.CENTER_WEST, "CenterWest");
		map.put(Type.SOUTH_WEST, "SouthWest");
		map.put(Type.NORTH_EAST, "NorthEast");
		map.put(Type.CENTER_EAST, "CenterEast");
		map.put(Type.SOUTH_EAST, "SouthEast");
		map.put(Type.HORIZONTAL, "Horizontal");
		map.put(Type.VERTICAL, "Vertical");

		return Game.getPackNow().getImage(IMAGE_FOLDER_NAME, map.get(type));
	}

	private static Dimension getDimension(Type type) {
		HashMap<Type, Dimension> map = new HashMap<>();
		map.put(Type.NORTH_WEST, new Dimension(SMALL_SIZE, SMALL_SIZE));
		map.put(Type.CENTER_WEST, new Dimension(SMALL_SIZE, SMALL_SIZE));
		map.put(Type.SOUTH_WEST, new Dimension(SMALL_SIZE, SMALL_SIZE));
		map.put(Type.NORTH_EAST, new Dimension(SMALL_SIZE, SMALL_SIZE));
		map.put(Type.CENTER_EAST, new Dimension(SMALL_SIZE, SMALL_SIZE));
		map.put(Type.SOUTH_EAST, new Dimension(SMALL_SIZE, SMALL_SIZE));
		map.put(Type.HORIZONTAL, new Dimension(BIG_SIZE, SMALL_SIZE));
		map.put(Type.VERTICAL, new Dimension(SMALL_SIZE, BIG_SIZE));

		return map.get(type);
	}

	public static int getSmallSize() {
		return (int) (SMALL_SIZE * WindowElement.getScale());
	}

	public static int getBigSize() {
		return (int) (BIG_SIZE * WindowElement.getScale());
	}

}
