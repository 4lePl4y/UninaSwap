package gui.preset.presetJButton;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class JButtonWithBorder extends JButton {
	private static final long serialVersionUID = 1L;

	public JButtonWithBorder(String text) {
		super(text);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.setBackground(new Color(0, 51, 102));
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        
        this.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseEntered(MouseEvent e) {
    		setBackground(new Color(0, 102, 204));
    		setCursor(new Cursor(Cursor.HAND_CURSOR));
    		}
    		
    		@Override
    		public void mouseExited(MouseEvent e) {
    		setBackground(new Color(0, 51, 102));
    		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    		}
    		
    	});
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25); 
        g2.dispose();
        super.paintComponent(g);
	}
	
		

}
