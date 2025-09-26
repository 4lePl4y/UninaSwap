package gui.preset.presetJPanel.presetJCard;

import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.Controller;
import entities.annuncio.*;
import entities.studente.Studente;
import gui.preset.presetJButton.JButtonWithBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Component;


public class JListingCard extends JAbstractListingCard {
	private static final long serialVersionUID = 1L;
	private Annuncio annuncio;
	private Studente autore; 
	
	public JListingCard(Annuncio annuncio, Controller controller) {
		super(annuncio, controller);
		this.setSize(280, 450);
		this.annuncio = annuncio;
		this.autore = annuncio.getAutore();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		JLabel sellerLabel = new JLabel("<html>Pubblicato da: <b>" + autore.getUsername() + "</b></html>");
		sellerLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));  //TODO: cambiare font
		sellerLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		middlePanel.add(sellerLabel, BorderLayout.NORTH);
		
		
		//BOTTOM PANEL: contiene il bottone per fare un'offerta
		this.add(Box.createVerticalStrut(20));
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(255, 255, 255));
		this.add(bottomPanel);
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton placeOfferButton = new JButtonWithBorder("Fai un'offerta", Controller.APP_BLUE);
		placeOfferButton.setPreferredSize(new Dimension(140, 30));
		placeOfferButton.setMaximumSize(new Dimension(140, 30));
		bottomPanel.add(placeOfferButton);
		placeOfferButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onFaiOffertaClicked();
			}
		});
		this.add(Box.createVerticalStrut(20));
		
	}	

	

	//METODI
	public void onFaiOffertaClicked() {
		controller.onFaiOffertaClicked(annuncio, autore);
	}
	
}
