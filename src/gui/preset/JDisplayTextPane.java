package gui.preset;

import java.awt.Font;
import javax.swing.JTextPane;

public class JDisplayTextPane extends JTextPane {
	private static final long serialVersionUID = 1L;

	public JDisplayTextPane(String text) {
		this.setText(text);
		this.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		this.setFocusable(false);
		this.setEditable(false);	
	}

}
