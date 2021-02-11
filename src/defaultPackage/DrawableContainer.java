package defaultPackage;

import java.util.HashSet;

public interface DrawableContainer extends WindowElementContainer {

	/** Returns HashSet of all Drawables it contains */
	HashSet<Drawable> getDrawables();

}
