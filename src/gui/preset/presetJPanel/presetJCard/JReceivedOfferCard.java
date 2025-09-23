package gui.preset.presetJPanel.presetJCard; 

import controller.Controller;
import entities.enumerazioni.Stato;
import entities.offerta.*; 
import gui.preset.presetJButton.JButtonWithBorder;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JReceivedOfferCard extends JAbstractOfferCard { 
	private static final long serialVersionUID = 1L; 
	
	public JReceivedOfferCard(Offerta offerta, Controller controller) { 
		super(offerta, controller); 
		
		//BOTTOM PANEL
		this.add(Box.createVerticalStrut(20));
		JPanel bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(450, 100));
		bottomPanel.setMaximumSize(new Dimension(450, 100));
		bottomPanel.setBorder(new LineBorder(Color.BLACK, 1));
		bottomPanel.setBackground(new Color(255, 255, 255));
		this.add(bottomPanel);
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
		
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
