package gui.preset.presetJButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JRefreshButton extends JButtonWithBorder {
    private static final long serialVersionUID = 1L;
    
    public JRefreshButton() {
        super("⟳", Color.WHITE);
        this.setFont(new Font("Segoe UI Symbol", Font.BOLD, 25));
        this.setPreferredSize(new Dimension(30, 30));
        this.setBackground(Color.WHITE);
        this.setForeground(Color.GRAY);
//        ImageIcon icon = new ImageIcon("src/img/Refresh.png");
//        Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
//        icon = new ImageIcon(img);
//        this.setIcon(icon);
        
        this.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseEntered(MouseEvent e) {
    			setBackground(Color.WHITE);
    			setForeground(new Color(200, 200, 200));
    		}
    		
    		@Override
    		public void mouseExited(MouseEvent e) {
    			setBackground(Color.WHITE);
    			setForeground(Color.GRAY);
    		}
    		
    	});
        
    }
}
