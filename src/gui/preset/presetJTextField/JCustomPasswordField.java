package gui.preset.presetJTextField;

import gui.preset.presetJLabel.JInteractiveLabel;
import gui.preset.presetJLabel.JShowPasswordLabel;

import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class JCustomPasswordField extends JPasswordField {
	private static final long serialVersionUID = 1L;
	private JShowPasswordLabel showPsw = new JShowPasswordLabel(this);
	private int arc = 20;
	
	//COSTRUTTORE
	public JCustomPasswordField(String placeholder) {
		super(placeholder);
		this.setLayout(null);
		this.setForeground(Color.GRAY);
		this.setEchoChar((char)0); 
		this.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		this.setOpaque(false); 
		this.setBorder(new EmptyBorder(5, 10, 5, 10)); // Padding
		this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(getPassword()).equals(placeholder)) {
                	setText("");
                	setForeground(Color.BLACK);
                }
                
                if(showPsw.getText().equals("Mostra")) {
					setEchoChar('•');
				} else {
					setEchoChar((char)0);
				}
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(getPassword()).equals("")) {
                	setText(placeholder);
                	setEchoChar((char)0);
                	setForeground(Color.GRAY);
                }   
            }
        });	
		
	}
	
	
	//METODI
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
	
	public void resetHint(String hint) {
		setText(hint);
		setForeground(Color.GRAY);
	}
	
	public JInteractiveLabel getShowPswLabel() {
		return showPsw;
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
	    super.setBounds(x, y, width, height);
	    showPsw.setLocation(width - 80, 8);
	    this.add(showPsw);
	    showPsw.setVisible(true);
	    this.repaint();
	}


}
