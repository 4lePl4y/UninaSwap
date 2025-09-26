package gui.preset.presetJButton;

import java.awt.Color;

public class JButtonClickManager extends JButtonWithBorder {
	private static final long serialVersionUID = 1L;
	private boolean clicked;

	public JButtonClickManager(String text, Color color) {
		super(text, color);
		this.clicked = false;
		
		this.addActionListener(e -> {this.clicked = true;});
	}
	
	public boolean isFirstClick() {
		return !this.clicked;
	}
	
}

