package gui.presetJFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;

public class JCustomTextField extends JTextField {
	private static final long serialVersionUID = 1L;

	public JCustomTextField(String placeholder) {
		super(placeholder);
		this.setForeground(Color.GRAY);
		this.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		this.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (getText().equals(placeholder)) {
					setText("");
					setForeground(Color.BLACK);
				}
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if (getText().isEmpty()) {
					setText(placeholder);
					setForeground(Color.GRAY);
				}
			}
		});
	}

}
