package gui.preset.presetJPanel.presetJCard;

import java.awt.Color;
import java.awt.Dimension;

import controller.Controller;
import gui.preset.presetJPanel.JPanelWithBorder;

public abstract class JAbstractCard extends JPanelWithBorder {
	private static final long serialVersionUID = 1L;
	public static int cardWidth = 280;
	public static int cardHeight = 450;
	protected Controller controller;

	public JAbstractCard(Controller controller) {
		super();
		this.controller = controller;
		this.setPreferredSize(new Dimension(cardWidth, cardHeight));
        this.setMaximumSize(new Dimension(cardWidth, cardHeight));
		this.setBackground(new Color(255, 255, 255));
		this.setLayout(null);
	}
	
	// Additional methods can be added here if needed

}
