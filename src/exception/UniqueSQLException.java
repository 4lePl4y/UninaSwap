package exception;

import java.sql.SQLException;

public class UniqueSQLException extends SQLException {
	private static final long serialVersionUID = 1L;

	public UniqueSQLException(String message) {
		super(message);
	}
}
