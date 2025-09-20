package exception;

import java.sql.SQLException;

public class CustomSQLException extends SQLException {
	private static final long serialVersionUID = 1L;
	
	public CustomSQLException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return super.getMessage().substring(7, super.getMessage().indexOf("Dove"));
	}
}
