package gui.preset;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextArea;

public class JCustomTextArea extends JTextArea{
	private static final long serialVersionUID = 1L;

	public JCustomTextArea(String placeholder) {
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
	
	public void resetHint(String hint) {
		setText(hint);
		setForeground(Color.GRAY);
	}
	
}
