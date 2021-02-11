package defaultPackage;

import java.util.HashSet;

public interface WindowElementContainer {

	/** Returns HashSet of all WindowElements it contains, including itself */
	HashSet<WindowElement> getWindowElements();

}
