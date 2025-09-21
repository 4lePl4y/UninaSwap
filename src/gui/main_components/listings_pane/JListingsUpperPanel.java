package gui.main_components.listings_pane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.SwingConstants;

import controller.Controller;
import entities.studente.Studente;
import gui.preset.JButtonWithBorder;

public class JListingsUpperPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Studente studenteLoggato;
	
	public JListingsUpperPanel(Controller controller) {
		this.studenteLoggato = controller.getStudenteLoggato();
		this.setBackground(new Color(255, 255, 255));
		this.setPreferredSize(new Dimension(800, 85));
		this.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagLayout gbl_listingsUpperPanel = new GridBagLayout();
		gbl_listingsUpperPanel.columnWidths = new int[]{0, 0, 0};
		gbl_listingsUpperPanel.rowHeights = new int[]{36, 0, 0};
		gbl_listingsUpperPanel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_listingsUpperPanel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		this.setLayout(gbl_listingsUpperPanel);
		
		JLabel welcomingLabel = new JLabel("Ciao "+studenteLoggato.getUsername()+"! Liberati di quello che non usi più e connettiti con altri studenti della Federico II");
		welcomingLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_welcomingLabel = new GridBagConstraints();
		gbc_welcomingLabel.insets = new Insets(0, 0, 5, 5);
		gbc_welcomingLabel.gridx = 0;
		gbc_welcomingLabel.gridy = 0;
		this.add(welcomingLabel, gbc_welcomingLabel);
		
		JButton newListingButton = new JButtonWithBorder("Crea un nuovo annuncio +");
		GridBagConstraints gbc_newListingButton = new GridBagConstraints();
		gbc_newListingButton.insets = new Insets(0, 0, 5, 0);
		gbc_newListingButton.gridx = 1;
		gbc_newListingButton.gridy = 0;
		this.add(newListingButton, gbc_newListingButton);
		newListingButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.onNuovoAnnuncioClicked();
			}
		});
		
		JLabel yourListingsLabel = new JLabel("Ecco qui i tuoi annunci:");
		yourListingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		yourListingsLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		GridBagConstraints gbc_yourListingsLabel = new GridBagConstraints();
		gbc_yourListingsLabel.anchor = GridBagConstraints.WEST;
		gbc_yourListingsLabel.insets = new Insets(0, 10, 0, 0);
		gbc_yourListingsLabel.gridx = 0;
		gbc_yourListingsLabel.gridy = 1;
		this.add(yourListingsLabel, gbc_yourListingsLabel);
		
	}
	


}
