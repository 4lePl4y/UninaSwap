package gui.main_components.browse_pane;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import controller.Controller;
import gui.preset.presetJTextField.JCustomTextField;

public class JSearchTextField extends JCustomTextField {
	//Attributi
	private static final long serialVersionUID = 1L;
	private GridBagConstraints gbc = new GridBagConstraints();
	
	//Costruttore
	public JSearchTextField(Controller controller, String placeholder) {
		super(placeholder);
		this.setPreferredSize(new Dimension(1056, 30));
		this.setColumns(10);
		
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = 1;
		gbc.gridy = 0;
	}
	
	//Metodi
	public GridBagConstraints getGbc() {
		return gbc;
	}
	
}
