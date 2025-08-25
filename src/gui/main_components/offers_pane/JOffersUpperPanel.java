package gui.main_components.offers_pane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.Controller;
import entities.studente.Studente;
import gui.preset.JRefreshButton;

public class JOffersUpperPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private Studente studenteLoggato;
	
	public JOffersUpperPanel(Controller controller) {
		this.controller = controller;
		this.studenteLoggato = controller.getStudenteLoggato();
		this.setBackground(new Color(255, 255, 255));
		this.setPreferredSize(new Dimension(800, 85));
		GridBagLayout gbl_offersUpperPanel = new GridBagLayout();
		gbl_offersUpperPanel.columnWidths = new int[]{0, 0, 0};
		gbl_offersUpperPanel.rowHeights = new int[]{36, 0, 0};
		gbl_offersUpperPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_offersUpperPanel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		this.setLayout(gbl_offersUpperPanel);
		
		JRefreshButton refreshButton = new JRefreshButton("↻");
		refreshButton.setLocation(0, 0);
		refreshButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				refresh();
			}
		});
		this.add(refreshButton);
		
		JLabel welcomingLabel = new JLabel("Ciao "+studenteLoggato.getUsername()+"! Vedi le tue offerte fatte e le offerte ricevute da altri studenti della Federico II");
		welcomingLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_welcomingLabel = new GridBagConstraints();
		gbc_welcomingLabel.gridwidth = 2;
		gbc_welcomingLabel.insets = new Insets(0, 0, 5, 0);
		gbc_welcomingLabel.gridx = 0;
		gbc_welcomingLabel.gridy = 0;
		this.add(welcomingLabel, gbc_welcomingLabel);
		
		
		JLabel receivedOffersLabel = new JLabel("Ecco qui le offerte che hai ricevuto:");
		receivedOffersLabel.setHorizontalAlignment(SwingConstants.CENTER);
		receivedOffersLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		GridBagConstraints gbc_receivedOffersLabel = new GridBagConstraints();
		gbc_receivedOffersLabel.insets = new Insets(0, 0, 0, 5);
		gbc_receivedOffersLabel.gridx = 0;
		gbc_receivedOffersLabel.gridy = 1;
		this.add(receivedOffersLabel, gbc_receivedOffersLabel);
		
		JLabel madeOffersLabel = new JLabel("Ecco qui le tue offerte fatte:");
		madeOffersLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		madeOffersLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_madeOffersLabel = new GridBagConstraints();
		gbc_madeOffersLabel.gridx = 1;
		gbc_madeOffersLabel.gridy = 1;
		add(madeOffersLabel, gbc_madeOffersLabel);
		
	}
	
	public void refresh() {
		controller.refreshReceivedOffers();
	}
	
}
