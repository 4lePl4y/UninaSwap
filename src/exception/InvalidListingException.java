package exception;


public class InvalidListingException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidListingException(String message) {
		super(message);
	}
}
