package gui.preset.presetJLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gui.preset.presetJTextField.JCustomPasswordField;

public class JShowPasswordLabel extends JInteractiveLabel {
	private static final long serialVersionUID = 1L;

	public JShowPasswordLabel(JCustomPasswordField passwordField) {
		super("Mostra");
		this.setSize(70, 15);
		this.setHorizontalAlignment(RIGHT);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String password = String.valueOf(passwordField.getPassword());
				if(password.equals("Password")) {
					return; // Non fare nulla se il campo contiene il placeholder
				}
				
				if (passwordField.getEchoChar() == '•') {
					passwordField.setEchoChar((char)0);
					setText("Nascondi");
				} else {
					passwordField.setEchoChar('•');
					setText("Mostra");
				}
			}
		});	
	}

}
