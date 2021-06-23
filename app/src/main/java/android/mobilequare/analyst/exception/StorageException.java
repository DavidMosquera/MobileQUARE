package android.mobilequare.analyst.exception;
public class StorageException extends Exception {
	private static final long serialVersionUID = 1L;
	public StorageException() {
		super("An unexpected error has occurred during a storage operation");
	}
}
