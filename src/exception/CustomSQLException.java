package exception;

import java.sql.SQLException;

public class CustomSQLException extends SQLException {
	private static final long serialVersionUID = 1L;
	
	public CustomSQLException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		if(super.getMessage().contains("unique")) {
			if(super.getMessage().contains("email"))
				return "Esiste già un account associato a questa email.";
			else if(super.getMessage().contains("noAnnunciDuplicati"))
				return "Esiste già un annuncio con questo oggetto.";
			else if(super.getMessage().contains("username"))
				return "Esiste già un account con questo username.";
		}
		return super.getMessage().substring(7, super.getMessage().indexOf("Dove"));
	}
}
