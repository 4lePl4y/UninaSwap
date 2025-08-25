package gui.preset;

import java.awt.GridBagConstraints;

public class JRefreshButton extends JButtonWithBorder {
	
	private static final long serialVersionUID = 1L;
	private GridBagConstraints gbc = new GridBagConstraints();
	
	public JRefreshButton(String text) {
		super(text);
		this.setSize(30, 30);
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.gridx = 0;
	}
	
	public GridBagConstraints getGbc() {
		return gbc;
	}

}
