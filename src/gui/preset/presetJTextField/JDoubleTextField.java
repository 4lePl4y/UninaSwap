package gui.preset.presetJTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JDoubleTextField extends JCustomTextField {

	private static final long serialVersionUID = 1L;

	public JDoubleTextField(String placeholder) {
		super(placeholder);
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0' && c <= '9') || c == '.' || c == KeyEvent.VK_BACK_SPACE)) {
					e.consume(); // Ignore the event if the character is not a digit or a decimal point
				}
			}
		});
	}

}
