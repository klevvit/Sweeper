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

    protected WindowElement(int SIZE_X, int SIZE_Y) {
        this.SIZE_X = SIZE_X;
        this.SIZE_Y = SIZE_Y;

        setSize(getScaledSize());
    }

    protected Dimension getScaledSize(int defaultSizeX, int defaultSizeY) {
        int realSizeX = (int) (defaultSizeX * Game.getScale());
        int realSizeY = (int) (defaultSizeY * Game.getScale());

        return new Dimension(realSizeX, realSizeY);
    }

    protected Dimension getScaledSize() {
        return getScaledSize(SIZE_X, SIZE_Y);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setSize(getScaledSize());
    }
}
