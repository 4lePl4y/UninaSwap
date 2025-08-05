package gui.presetJFrame;

public class JMailTextField extends JCustomTextField {
	private static final long serialVersionUID = 1L;

	JMailTextField(String placeholder) {
		super(placeholder);
	}

	public boolean isValidEmail() {
		String email = getText();
		String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		return email.matches(emailRegex);
	}
	
}
