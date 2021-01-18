package defaultPackage;

import java.awt.*;

/**
 * Class for minesweeper elements that have image and will be drawn in the window.
 *
 * Besides dimension control from WindowElement, draws element's image that fills all its size.
 */
public abstract class Drawable extends WindowElement {

    /** current image to be displayed */
    protected Image imageNow;

    protected Drawable(int SIZE_X, int SIZE_Y, Image imageNow) {
        super(SIZE_X, SIZE_Y);
        this.imageNow = imageNow;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Dimension size = getScaledSize();  // todo may have troubles here
        g.drawImage(imageNow, 0, 0, size.width, size.height, this);
    }
}
