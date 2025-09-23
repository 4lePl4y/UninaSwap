package gui.preset.presetJPanel.presetJCard; 

import controller.Controller;
import entities.enumerazioni.Stato;
import entities.offerta.*; 
import entities.oggetto.Oggetto; 
import entities.studente.Studente;
import gui.preset.JDisplayTextArea;
import gui.preset.presetJButton.JButtonWithBorder;
import gui.preset.presetJLabel.JInteractiveLabel; 
import javax.swing.JLabel; 
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JReceivedOfferCard extends JAbstractOfferCard { 
	private static final long serialVersionUID = 1L; 
	
	public JReceivedOfferCard(Offerta offerta, Controller controller) { 
		super(offerta, controller); 
		this.setSize(280, 450);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//BOTTOM PANEL
		this.add(Box.createVerticalStrut(20));
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(255, 255, 255));
		this.add(bottomPanel);
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		if(offerta.getStato() == Stato.Accettata) {
			JButton acceptButton = new JButtonWithBorder("Accetta"); 
			acceptButton.setBounds(30, 410, 100, 30); 
			acceptButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					onAccettaOffertaClicked();
				}
			});
			bottomPanel.add(acceptButton); 
			
			bottomPanel.add(Box.createHorizontalStrut(15));
			
			JButton declineButton = new JButtonWithBorder("Rifiuta"); 
			declineButton.setBounds(155, 410, 100, 30); 
			declineButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					onRifiutaOffertaClicked();
				}
			});
			bottomPanel.add(declineButton);
			
		}
			
	}
	
	@Override
	String rightTitleLabel() {
		return "Per: " + offerta.getAnnuncio().getTitolo() + "<br> di: " + offerta.getOfferente().getUsername();
	}

	public void onAccettaOffertaClicked() {
		controller.onAccettaOffertaClicked(offerta);
	}
	
	public void onRifiutaOffertaClicked() {
		controller.onRifiutaOffertaClicked(offerta);
	}

	
}
