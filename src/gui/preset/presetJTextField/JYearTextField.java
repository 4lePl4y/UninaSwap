package gui.preset.presetJTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.Year;

public class JYearTextField extends JCustomTextField {
	private static final long serialVersionUID = 1L;

	// COSTRUTTORI
	public JYearTextField(String placeholder) {
		super(placeholder);
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0' && c <= '9') || c == KeyEvent.VK_BACK_SPACE) || getSuperText().length() >= 4) {
					e.consume(); // Ignora se non è una cifra oppure se contiene più di 4 caratteri
				}
			}
		});
	}

	public JYearTextField(String placeholder, String nameField) {
		this(placeholder);
		this.setName(nameField);
		
	}
	
	// METODI 
	public Year getAnno() {
		return Year.parse(super.getText());
	}
	
	private String getSuperText() {
		return super.getText();
	}

}
