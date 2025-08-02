package gui.presetJFrame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class JInteractiveLabel extends JLabel {
	private static final long serialVersionUID = 1L;

	public JInteractiveLabel(String text) {
		super(text);
		this.setForeground(Color.DARK_GRAY);
		this.setFont(new Font("Times New Roman", Font.BOLD, 15));
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setForeground(Color.BLUE);
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setForeground(Color.DARK_GRAY);
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
	};
}


