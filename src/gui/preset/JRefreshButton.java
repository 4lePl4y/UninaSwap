package gui.preset;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;

public class JRefreshButton extends JButtonWithBorder {
    private static final long serialVersionUID = 1L;
    
    public JRefreshButton() {
        super("");
        setPreferredSize(new Dimension(35, 30));
        ImageIcon icon = new ImageIcon("src/img/Refresh.png");
        Image img = icon.getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        setIcon(icon);
        
    }
}
