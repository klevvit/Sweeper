package defaultPackage;

import java.util.ArrayList;

public interface DrawableContainer extends WindowElementContainer {

	/** Returns HashSet of all Drawables it contains */
	ArrayList<Drawable> getDrawables();

}
