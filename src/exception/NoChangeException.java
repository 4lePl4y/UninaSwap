package exception;

public class NoChangeException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public NoChangeException(String message) {
		super(message);
	}
}
