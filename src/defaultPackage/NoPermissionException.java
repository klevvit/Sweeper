package defaultPackage;

public class NoPermissionException extends Exception {
	
	private static final long serialVersionUID = 4234733217577574689L;

	public NoPermissionException(String message) {
		super(message);
	}
	
	public NoPermissionException() {
		super();
	}
}
