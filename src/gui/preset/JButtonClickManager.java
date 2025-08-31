package gui.preset;

public class JButtonClickManager extends JButtonWithBorder {
	private static final long serialVersionUID = 1L;
	private boolean clicked;

	public JButtonClickManager(String text) {
		super(text);
		this.clicked = false;
		
		this.addActionListener(e -> {this.clicked = true;});
	}
	
	public boolean isFirstClick() {
		return !this.clicked;
	}
	
}

