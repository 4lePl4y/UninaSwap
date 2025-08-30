package gui.preset;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextArea;

public class JWritableTextArea extends JTextArea{
	private static final long serialVersionUID = 1L;
	private String placeholder;

	// COSTRUTTORE
	public JWritableTextArea(String placeholder) {
		super(placeholder);
		this.placeholder = placeholder;
		this.setForeground(Color.GRAY);
		this.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		this.setLineWrap(true);
		this.setWrapStyleWord(true);
		
		this.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (getSuperText().equals(placeholder)) {
					setText("");
					setForeground(Color.BLACK);
				}
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if (getSuperText().isEmpty()) {
					setText(placeholder);
					setForeground(Color.GRAY);
				}
			}
		});
	}
	
	
	// METODI
	@Override
	public String getText() {
		String text = super.getText().trim();
		if(text.equals(placeholder))
			text = "";
		
		return text;
	}
	
	public String getSuperText(){
		return super.getText();
	}
	
	public void resetHint(String hint) {
		setText(hint);
		setForeground(Color.GRAY);
	}
	
}
