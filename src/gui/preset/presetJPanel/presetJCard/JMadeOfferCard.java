package gui.preset.presetJPanel.presetJCard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Controller;
import entities.enumerazioni.Stato;
import entities.offerta.Offerta;
import gui.preset.presetJButton.JButtonWithBorder;

public class JMadeOfferCard extends JAbstractOfferCard {
	private static final long serialVersionUID = 1L;


	public JMadeOfferCard(Offerta offerta, Controller controller) {
		super(offerta, controller);
		
		//BOTTOM PANEL
		JPanel rightPanel = new JPanel();
		rightPanel.setOpaque(false);
		rightPanel.setLayout(new BorderLayout(0, 0));
		rightPanel.setPreferredSize(new java.awt.Dimension(100, 130));
		rightPanel.setMaximumSize(new java.awt.Dimension(100, 130));
		this.add(rightPanel, BorderLayout.EAST);
		
		if(offerta.getStato() == Stato.InAttesa) {
			JButton modifyButton = new JButtonWithBorder("Modifica"); 
			modifyButton.setBounds(30, 410, 100, 30); 
			modifyButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					onModificaOffertaFrameClicked();
				}
			});
			rightPanel.add(modifyButton, BorderLayout.NORTH);
						
			JButton deleteButton = new JButtonWithBorder("Ritira"); 
			deleteButton.setBounds(155, 410, 100, 30); 
			deleteButton.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					onCancellaOffertaClicked();
				}
			});
			rightPanel.add(deleteButton, BorderLayout.SOUTH);
		}
	}
	
	
	@Override
	String rightTitleLabel() {
		return "Per: "+offerta.getAnnuncio().getTitolo();
	}
	
	public void onModificaOffertaFrameClicked() {
		controller.onModificaOffertaFrameClicked(offerta);
	}
	
	public void onCancellaOffertaClicked() {
		controller.onCancellaOffertaClicked(offerta);
	}
	

}
