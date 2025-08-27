package gui.main_components.browse_pane;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class JFilterRadioButton extends JRadioButton {

	private static final long serialVersionUID = 1L;
	private GridBagConstraints gbc = new GridBagConstraints();

	public JFilterRadioButton(String text) {
		super(text);
		setHorizontalAlignment(SwingConstants.CENTER);
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.insets = new Insets(10, 0, 0, 0);
	}
	
	public GridBagConstraints getGbc() {
		return gbc;
	}

	

}
