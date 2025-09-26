package gui.preset;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.DefaultListCellRenderer;



public class JCustomComboBox<E> extends JComboBox<E> {
	private static final long serialVersionUID = 1L;
	private int arc = 20;
	
	public JCustomComboBox(E[] items) {
		super(items);
		setOpaque(false);        
        setBackground(new Color(250, 250, 250));
		setBorder(BorderFactory.createEmptyBorder());
		setUI(new BasicComboBoxUI() {
			
			@Override
            protected JButton createArrowButton() {
                // pulsante invisibile, serve solo per la logica ma non si vede
                JButton b = new JButton();
                b.setPreferredSize(new Dimension(0, 0));
                b.setOpaque(false);
                b.setContentAreaFilled(false);
                b.setBorderPainted(false);
                return b;
            }
			
            @Override
            public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                // Do nothing: prevents default background painting
            }
        });
		
		setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
		    public Component getListCellRendererComponent(
		            JList<?> list, Object value, int index,
		            boolean isSelected, boolean cellHasFocus) {

		        JLabel lbl = (JLabel) super.getListCellRendererComponent(
		                list, value, index, isSelected, cellHasFocus);

		        if (index == -1) {
		            // valore mostrato nel combo chiuso
		            lbl.setOpaque(false); // sfondo trasparente
		        } else {
		            // celle nel popup: lascia l'opacità per l'highlight
		            lbl.setOpaque(true);
		        }
		        
		     // aggiungi padding interno (top, left, bottom, right)
		        lbl.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
		        
		        return lbl;
		    }
		});
    }
		
	

	@Override
	public void setBorder(javax.swing.border.Border border) {
	    super.setBorder(BorderFactory.createEmptyBorder());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    Graphics2D g2 = (Graphics2D) g.create();
	    g2.setColor(getBackground());
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
	    g2.dispose();
	    
	 // lascia a Swing il compito di disegnare il testo del renderer
        super.paintComponent(g);

        // freccia custom
        Graphics2D g4 = (Graphics2D) g.create();
        g4.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int cx = getWidth() - 12;
        int cy = getHeight() / 2;
        int size = 6;
        int half = size / 2;
        Polygon arrow = new Polygon(
                new int[]{cx - half, cx + half, cx},
                new int[]{cy - 1, cy - 1, cy + half},
                3
        );
        g4.setColor(isEnabled() ? new Color(0x333333) : Color.GRAY);
        g4.fill(arrow);
        g4.dispose();
	    
	}

	
	@Override
	protected void paintBorder(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(Color.LIGHT_GRAY);
		g2.setStroke(new BasicStroke(1.5f));
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, arc, arc);
		g2.dispose();
	}
	
	
}
