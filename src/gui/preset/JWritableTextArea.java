package gui.preset;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class JWritableTextArea extends JTextArea{
	private static final long serialVersionUID = 1L;
	private String placeholder;
	private int arc = 20;
	

	// COSTRUTTORE
	public JWritableTextArea(String placeholder) {
		super(placeholder);
		this.placeholder = placeholder;
		this.setForeground(Color.GRAY);
		this.setFont(new Font("Montserrat", Font.PLAIN, 15));	
		this.setLineWrap(true);
		this.setWrapStyleWord(true);
		this.setOpaque(false); 
		this.setBorder(new EmptyBorder(5, 10, 5, 10)); // Padding
		
		this.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (getSuperText().equals(placeholder)) {
					setText("");
					setForeground(Color.BLACK);
				}
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if (getSuperText().isEmpty()) {
					setText(placeholder);
					setForeground(Color.GRAY);
				}
			}
		});
	}
	
	
	// METODI
	@Override
	public String getText() {
		String text = super.getText().trim();
		if(text.equals(placeholder))
			text = "";
		
		return text;
	}
	
	public String getSuperText(){
		return super.getText();
	}
	
	public void resetHint(String hint) {
		setText(hint);
		setForeground(Color.GRAY);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(getBackground());
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
		super.paintComponent(g);
		g2.dispose();
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
