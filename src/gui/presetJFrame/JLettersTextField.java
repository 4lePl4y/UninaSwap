package gui.presetJFrame;

public class JLettersTextField extends JCustomTextField {
	private static final long serialVersionUID = 1L;

	public JLettersTextField(String placeholder) {
		super(placeholder);
	}
	
	public boolean isValidInput() {
		String text = getText();
		return text.matches("[a-zA-Z]+");
	}

}
