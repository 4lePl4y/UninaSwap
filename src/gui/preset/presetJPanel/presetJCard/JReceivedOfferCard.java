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

import java.awt.BorderLayout;
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
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout(0, 0));
		rightPanel.setPreferredSize(new Dimension(100, 130));
		rightPanel.setMaximumSize(new Dimension(100, 130));
		rightPanel.setBorder(new LineBorder(Color.BLACK, 1));
		rightPanel.setOpaque(false);
		this.add(rightPanel, BorderLayout.EAST);
		
		if(offerta.getStato() == Stato.Accettata) {
			JButton acceptButton = new JButtonWithBorder("Accetta"); 
			acceptButton.setBounds(30, 410, 100, 30); 
			acceptButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					onAccettaOffertaClicked();
				}
			});
			rightPanel.add(acceptButton, BorderLayout.NORTH); 
			
			JButton declineButton = new JButtonWithBorder("Rifiuta"); 
			declineButton.setBounds(155, 410, 100, 30); 
			declineButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					onRifiutaOffertaClicked();
				}
			});
			rightPanel.add(declineButton, BorderLayout.SOUTH);
			
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
