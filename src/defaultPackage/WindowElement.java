package defaultPackage;

import javax.swing.*;
import java.awt.*;

/**
 * Class for all minesweeper elements that are shown in the window.
 * Controls width and height for the element and adopts them to the scale set in the Game class.
 */
public abstract class WindowElement extends JPanel {

    /** element width for scale = 1 */
    private final int SIZE_X;
    /** element height for scale = 1 */
    private final int SIZE_Y;

    /**
     * Memorises element dimensions and sets its real size.
     * @param SIZE_X width for scale = 1 in pixels
     * @param SIZE_Y height for scale = 1 in pixels
     */
    protected WindowElement(int SIZE_X, int SIZE_Y) {
        this.SIZE_X = SIZE_X;
        this.SIZE_Y = SIZE_Y;

        setSize(getScaledSize());
    }

    /** Returns scaled size in pixels */
    protected Dimension getScaledSize(int defaultSizeX, int defaultSizeY) {
        int realSizeX = (int) (defaultSizeX * Game.getScale());
        int realSizeY = (int) (defaultSizeY * Game.getScale());

        return new Dimension(realSizeX, realSizeY);
    }

    /** Returns scaled size in pixels */
    protected Dimension getScaledSize() {
        return getScaledSize(SIZE_X, SIZE_Y);
    }

    /** Sets location for elements which this element contain. Empty by default. */
//    protected void locateElements() {} todo remove

    /** Updates element size if scale was changed. todo remove method  */
//    @Override
//    public void paintComponent(Graphics g) {
//        Dimension newSize = getScaledSize();
//
//        if (!getSize().equals(newSize)) {
//            locateElements();
//            setSize(newSize);
//        }
//
//        super.paintComponent(g);
//    }
}
