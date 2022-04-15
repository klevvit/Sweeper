package defaultPackage;

import java.awt.*;

/**
 * Class for minesweeper elements that have an image and will be drawn in the window.
 *
 * Besides dimension control from WindowElement, draws element's image that fills all its size.
 */
public abstract class Drawable extends WindowElement {

    /** current image to be displayed */
    protected Image currentImage;

    protected Drawable(int SIZE_X, int SIZE_Y, Image currentImage) {
        super(SIZE_X, SIZE_Y);
        this.currentImage = currentImage;
    }

    @Override
    public void updateSize() {
        setSize(getScaledSize());
    }

//    public void updateImages() {
//
//        repaint();
//    }
//
//    /** Gets all needed images from given image pack. */
//    abstract protected void loadImages(ImagePack pack);

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Dimension size = getScaledSize();
        g.drawImage(currentImage, 0, 0, size.width, size.height, this);
    }
}
