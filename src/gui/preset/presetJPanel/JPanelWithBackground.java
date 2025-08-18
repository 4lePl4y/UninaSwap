package gui.preset.presetJPanel;  

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPanelWithBackground extends JPanel {

    private static final long serialVersionUID = 1L;
    private Image backgroundImage;

    public JPanelWithBackground(String img_src) {
        super();
        ImageIcon backgroundIcon = new ImageIcon(img_src);
        backgroundImage = backgroundIcon.getImage();
        this.setFocusable(true);
        
        this.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				requestFocusInWindow();
			}
		});
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
}
