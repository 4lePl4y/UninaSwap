
package gui.preset.presetJTextField;


import java.awt.Color;
import java.awt.*;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class JCustomTextField extends JTextField {
	private static final long serialVersionUID = 1L;
	private String placeholder;
	private int arc = 20; 
	
	// CONSTRUCTOR
	public JCustomTextField(String placeholder) {
		super(placeholder);
		this.placeholder = placeholder;
		this.setForeground(Color.GRAY);
		this.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		setOpaque(false); 
		setBorder(new EmptyBorder(5, 10, 5, 10)); // Padding
		
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
	
	public JCustomTextField(String placeholder, String nameField) {
		this(placeholder);
		this.setName(nameField);
	}
	
	// METHODS
	
	@Override
	public String getText() {
		String text = super.getText().trim();
		if (text.equals(placeholder))
			text = "";
		return text;
	}
	
	private String getSuperText() {
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