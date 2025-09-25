package gui.preset.presetJButton;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JDeclineButton extends JButtonWithBorder {
	// ATTRIBUTI
	static final long serialVersionUID = 1L;

	// COSTRUTTORE
	public JDeclineButton() {
		super("Rifiuta");
		this.setBackground(new Color(200, 0, 0));
		this.addMouseListener(new MouseAdapter() {
			@Override
    		public void mouseEntered(MouseEvent e) {
    		setBackground(new Color(250, 0, 0));
    		setCursor(new Cursor(Cursor.HAND_CURSOR));
    		}
    		
    		@Override
    		public void mouseExited(MouseEvent e) {
    		setBackground(new Color(200, 0, 0));
    		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    		}
	    		
	    });
	}

}
