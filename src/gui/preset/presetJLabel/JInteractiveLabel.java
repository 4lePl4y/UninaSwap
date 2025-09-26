package gui.preset.presetJLabel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import controller.Controller;

public class JInteractiveLabel extends JLabel {
	private static final long serialVersionUID = 1L;

	public JInteractiveLabel(String text) {
		super(text);
		this.setForeground(Color.DARK_GRAY);
		this.setFont(new Font("Noto Color Emoji", Font.BOLD, 15));	//TODO: cambiare font
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setForeground(Controller.APP_BLUE);
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


