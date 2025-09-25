package gui.preset.presetJButton;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JAcceptButton extends JButtonWithBorder {
	private static final long serialVersionUID = 1L;
	
	// COSTRUTTOREs
	public JAcceptButton() {
		super("Accetta");
		this.setBackground(new Color(0, 150, 0));	
		this.addMouseListener(new MouseAdapter() {
			@Override
    		public void mouseEntered(MouseEvent e) {
    		setBackground(new Color(0, 200, 0));
    		setCursor(new Cursor(Cursor.HAND_CURSOR));
    		}
    		
    		@Override
    		public void mouseExited(MouseEvent e) {
    		setBackground(new Color(0, 150, 0));
    		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    		}
	    		
	    });
	}

}
