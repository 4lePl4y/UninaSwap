package gui.main_components.browse_pane;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class JCustomCheckBox extends JCheckBox {

	private static final long serialVersionUID = 1L;
	private GridBagConstraints gbc = new GridBagConstraints();
	private Color checkColor = new Color(0, 51, 102); // blu moderno
    private Color borderColor = Color.GRAY;
    private int diameter = 15; // dimensione del quadratino

	public JCustomCheckBox(String item) {
		super(item);
		setOpaque(false); // rende il background trasparente
        setFont(new Font("Arial", Font.PLAIN, 14));

        
    }

    @Override
    protected void paintComponent(Graphics g) {
    	Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = 1;
        int y = (getHeight() - diameter) / 2;

        // cerchio vuoto (bordo)
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(x, y, diameter, diameter);

        // se selezionato, cerchio pieno
        if (isSelected()) {
            g2.setColor(checkColor);
            g2.fillOval(x + 3, y + 3, diameter - 6, diameter - 6);
        }

        // disegno del testo a destra del cerchio
        g2.setColor(getForeground());
        g2.setFont(getFont());
        g2.drawString(getText(), diameter + 5, y + diameter - 4);

        g2.dispose();
    }
	
	public GridBagConstraints getGbc() {
		return gbc;
	}

	

}
