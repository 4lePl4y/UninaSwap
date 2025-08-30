package gui.preset.presetJTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JPriceTextField extends JCustomTextField {
	private static final long serialVersionUID = 1L;

	// COSTRUTTORE
	public JPriceTextField(String placeholder) {
		super(placeholder);
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (! ((c >= '0' && c <= '9') || c == '.' || c == KeyEvent.VK_BACK_SPACE) || getSuperText().length() >= 7) {
					e.consume(); // Ignora se non è una cifra oppure il punto oppure se contiene più di 7 caratteri (MAX PREZZO: 9999.99)
				}
			}
		});
	}
	
	// METODI
	public double getPrezzo() {
		return Double.parseDouble(super.getText());
	}
	
	private String getSuperText() {
		return super.getText();
	}

}
