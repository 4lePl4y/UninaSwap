package gui.preset.presetJLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class JWarningLabel extends JLabel{
	private static final long serialVersionUID = 1L;

	public JWarningLabel(String text) {
		super(text);
		setForeground(Color.RED);
        setFont(new Font("Times New Roman", Font.PLAIN, 15));
        setVisible(false);
	}

}
