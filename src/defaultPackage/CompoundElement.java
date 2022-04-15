package defaultPackage;

import java.util.ArrayList;

public abstract class CompoundElement extends WindowElement {

	/** Elements this element contains */
	protected ArrayList<WindowElement> children;

	/**
	 * Memorises element dimensions and sets its real size. Then calls createElements(), addElements() and
	 * locateElements().
	 *
	 * @param SIZE_X width for scale = 1 in pixels
	 * @param SIZE_Y height for scale = 1 in pixels
	 */
	protected CompoundElement(int SIZE_X, int SIZE_Y) {
		super(SIZE_X, SIZE_Y);
		children = new ArrayList<>();
//		createElements();  todo necessary?
//		addElements();
//		locateElements();
	}

	/** Adds element to its JPanel and saves  */
	protected void addChild(WindowElement elem) {
		add(elem);
		children.add(elem);
	}

	protected void removeChild(WindowElement elem) {
		remove(elem);
		children.remove(elem);
	}

	/** Sets its size and size of all its elements according to current Game scale. */
	@Override
	public void updateSize() {
		setSize(getScaledSize());
		for (WindowElement elem : children) {
			elem.updateSize();
		}
	}

	/** Sets location for elements which this element contain. */
//	abstract protected void locateElements();  // todo necessary?

	/** Updates currentImage variable for all Drawables that are under its control. */
//	abstract public void updateCurrentImages();  // todo necessary?

}
