package defaultPackage;

import javax.swing.JPanel;
import java.awt.Dimension;

/**
 * Class for all minesweeper elements that are shown in the window.
 * Controls width and height for the element and adopts them to the scale set in the Game class.
 */
public abstract class WindowElement extends JPanel {

    private static float scale = 1.0f;
    /** element width for scale = 1 */
    protected final int SIZE_X;
    /** element height for scale = 1 */
    protected final int SIZE_Y;

    /** returns current scale for all window elements **/
    public static float getScale() {
        return scale;
    }

    public static void setScale(float sc) {
        scale = sc;
    }

    /**
     * Memorises element dimensions and sets its real size.
     *
     * @param SIZE_X width for scale = 1 in pixels
     * @param SIZE_Y height for scale = 1 in pixels
     */
    protected WindowElement(int SIZE_X, int SIZE_Y) {
        this.SIZE_X = SIZE_X;
        this.SIZE_Y = SIZE_Y;

        setSize(getScaledSize());
    }

    /** Returns scaled size in pixels
     * todo necessary?
     */
    protected Dimension getScaledSize(int defaultSizeX, int defaultSizeY) {
        int realSizeX = (int) (defaultSizeX * scale);
        int realSizeY = (int) (defaultSizeY * scale);

        return new Dimension(realSizeX, realSizeY);
    }

    /** Returns scaled size in pixels */
    protected Dimension getScaledSize() {
        return getScaledSize(SIZE_X, SIZE_Y);
    }

    /** Sets its size according to current Game scale. */
    abstract public void updateSize();

}
