package gui.preset.presetJPanel.presetJCard;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import controller.Controller;
import entities.annuncio.*;
import exception.CustomSQLException;
import gui.preset.presetJButton.JButtonWithBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Component;


public class JListingCard extends JAbstractListingCard {
	// ATTRIBUTI
	private static final long serialVersionUID = 1L;
	private Annuncio annuncio;
	
	// COSTRUTTORE
	public JListingCard(Annuncio annuncio, Controller controller) {
		super(annuncio, controller);
		this.setSize(280, 450);
		this.annuncio = annuncio;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		JLabel sellerLabel = new JLabel("<html>Pubblicato da: <b>" + annuncio.getAutore().getUsername() + "</b></html>");
		sellerLabel.setFont(new Font("Montserrat", Font.PLAIN, 14));  
		sellerLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		middlePanel.add(sellerLabel, BorderLayout.NORTH);
		
		
		//BOTTOM PANEL: contiene il bottone per fare un'offerta
		this.add(Box.createVerticalStrut(20));
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(255, 255, 255));
		this.add(bottomPanel);
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		if(annuncio instanceof AnnuncioVendita) {
			JButton acceptButton = new JButtonWithBorder("Acquista", Controller.APP_GREEN);
			acceptButton.setPreferredSize(new Dimension(130, 30));
			bottomPanel.add(acceptButton);
			acceptButton.addActionListener(e -> onAcquistaClicked());	
		}
		
		JButton placeOfferButton = new JButtonWithBorder("Fai un'offerta", Controller.APP_BLUE);
		placeOfferButton.setPreferredSize(new Dimension(130, 30));
		bottomPanel.add(placeOfferButton);
		placeOfferButton.addActionListener(e -> onFaiOffertaClicked());
		
		this.add(Box.createVerticalStrut(20));
		
	}	

	

	// METODI
	
	public void onAcquistaClicked() {
		try {
			controller.onAcquistaClicked(annuncio);
		} catch (CustomSQLException e) {
    		JOptionPane.showMessageDialog(null, e.getMessage());
    		return;
    	} catch (SQLException e) {
    		JOptionPane.showMessageDialog(null, "Errore durante l'invio dell'offerta. Riprova!");
    		return;
    	}
		
		JOptionPane.showMessageDialog(null, "Offerta inviata!");
	}
	
	
	public void onFaiOffertaClicked() {
		controller.onFaiOffertaClicked(annuncio);
	}
	
}
