package gui.preset.presetJPanel;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class JPanelWithGradient extends JPanel {
		private static final long serialVersionUID = 1L;		
		private Color colorEnd = new Color(161,195,192);
		private Color colorStart = new Color(0, 51, 102);

	public JPanelWithGradient() {
		super();
	}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // converti Graphics in Graphics2D
        Graphics2D g2d = (Graphics2D) g.create();

        // abilitare anti-aliasing opzionale
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // crea il gradient paint: dall'alto in basso
        GradientPaint gp = new GradientPaint(0, height , colorStart, 0, width, colorEnd);

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);

        g2d.dispose();
    }

}
