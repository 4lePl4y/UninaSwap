package gui.preset;

import java.awt.Font;
import javax.swing.JTextArea;

public class JDisplayTextArea extends JTextArea {
	private static final long serialVersionUID = 1L;

	 public JDisplayTextArea(String text) {
		this.setText(text);
		this.setFont(new Font("Times New Roman", Font.PLAIN, 15));	//TODO: cambiare font
		this.setLineWrap(true);
		this.setWrapStyleWord(true);
		this.setFocusable(false);
		this.setEditable(false);
	}
	 
}
