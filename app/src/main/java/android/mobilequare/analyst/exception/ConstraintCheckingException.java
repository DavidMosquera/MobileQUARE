package android.mobilequare.analyst.exception;
public class ConstraintCheckingException extends Exception {
	private static final long serialVersionUID = 1L;
	public ConstraintCheckingException() {
		super("An unexpected error has occurred during constraint checking");
	}
}
