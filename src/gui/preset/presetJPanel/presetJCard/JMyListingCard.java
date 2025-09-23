package gui.preset.presetJPanel.presetJCard;

import javax.swing.JPanel;
import controller.Controller;
import entities.annuncio.*;
import gui.preset.presetJButton.JButtonWithBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.Box;
import java.awt.FlowLayout;


public class JMyListingCard extends JAbstractListingCard {
	private static final long serialVersionUID = 1L;
	private Annuncio annuncio; 
	
	public JMyListingCard(Annuncio annuncio, Controller controller) {
		super(annuncio, controller);
		this.annuncio = annuncio;
		
		//BOTTOM PANEL: contiene i bottoni per modificare o eliminare l'annuncio
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(255, 255, 255));
		this.add(bottomPanel);
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton modifyButton = new JButtonWithBorder("Modifica");
		modifyButton.setPreferredSize(new Dimension(100, 30));
		modifyButton.setMaximumSize(new Dimension(100, 30));
		
		modifyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onModificaAnnuncioClicked();
			}
		});
		bottomPanel.add(modifyButton);

		bottomPanel.add(Box.createHorizontalStrut(15));
		
		JButton deleteButton = new JButtonWithBorder("Elimina");
		deleteButton.setPreferredSize(new Dimension(100, 30));
		deleteButton.setMaximumSize(new Dimension(100, 30));
		deleteButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				onCancellaAnnuncioClicked();
			}
		});
		bottomPanel.add(deleteButton);
		
		this.add(Box.createVerticalStrut(30));
	}	

	

	//METODI
	private void onModificaAnnuncioClicked() {
		controller.onModificaAnnuncioFrameClicked(annuncio);	
	}
	
	public void onCancellaAnnuncioClicked() {
		controller.onCancellaAnnuncioClicked(annuncio);
	}
	
	
}
