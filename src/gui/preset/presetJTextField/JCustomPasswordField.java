package gui.preset.presetJTextField;

import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class JCustomPasswordField extends JPasswordField {
	private static final long serialVersionUID = 1L;

	public JCustomPasswordField(String placeholder) {
		super(placeholder);
		this.setForeground(Color.GRAY);
		this.setEchoChar((char)0); 
		this.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(getPassword()).equals(placeholder)) {
                	setText("");
                	setEchoChar('•');
                	setForeground(Color.BLACK);
                }
                
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(getPassword()).equals("")) {
                	setText(placeholder);
                	setEchoChar((char)0);
                	setForeground(Color.GRAY);
                }
                
            }
        });
	}

}
