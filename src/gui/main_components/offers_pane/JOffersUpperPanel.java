package gui.main_components.offers_pane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import entities.enumerazioni.Stato;
import entities.studente.Studente;
import gui.preset.JCustomCheckBox;
import gui.preset.presetJLabel.JInteractiveLabel;
import gui.preset.presetJPanel.presetJCard.JAbstractCard;
import gui.preset.presetJPanel.presetJCard.JAbstractOfferCard;

public class JOffersUpperPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Controller controller;
    private Studente studenteLoggato;
	private JOffersPane parent;
    
    public JOffersUpperPanel(JOffersPane parent, Controller controller) {
        this.controller = controller;
        this.studenteLoggato = controller.getStudenteLoggato();
        this.parent = parent; 
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(800, 100));
        this.setLayout(new BorderLayout(0, 0));

        // === PANEL PRINCIPALE A COLONNA ===
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // UPPER PANEL 
        JPanel upperPanel = new JPanel();
        upperPanel.setBackground(Color.WHITE);
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.X_AXIS));

        ImageIcon logoIcon = new ImageIcon("src/img/logo.jpg");
        logoIcon = new ImageIcon(logoIcon.getImage().getScaledInstance(250, 65, java.awt.Image.SCALE_SMOOTH));
        JLabel logo = new JLabel(logoIcon);
        upperPanel.add(logo, BorderLayout.WEST);

       upperPanel.add(Box.createHorizontalStrut(400));

        JLabel titleLabel = new JLabel("Ciao " + studenteLoggato.getUsername() + ", Ecco le tue offerte");
        titleLabel.setFont(new Font("Montserrat", Font.BOLD, 20)); 
        upperPanel.add(titleLabel, BorderLayout.CENTER);

        upperPanel.add(Box.createHorizontalStrut(550));
        
        JInteractiveLabel refreshButton = new JInteractiveLabel("⟳");
        refreshButton.setFont(refreshButton.getFont().deriveFont(30.0f));
        refreshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refresh();
            }
        });
        upperPanel.add(refreshButton);
        upperPanel.add(Box.createHorizontalStrut(20));

        
        // LOWER PANEL 
        JPanel lowerPanel = new JPanel();
        lowerPanel.setBackground(Color.WHITE);
        lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.X_AXIS));

        lowerPanel.add(Box.createHorizontalStrut(20));

        JLabel offerteRicevuteLabel = new JLabel("OFFERTE RICEVUTE:");
        offerteRicevuteLabel.setFont(new Font("Montserrat", Font.BOLD, 14)); 	
        lowerPanel.add(offerteRicevuteLabel);

        // spazio flessibile in mezzo
        lowerPanel.add(Box.createHorizontalGlue());

        JCustomCheckBox showAllOffersCheckBox = new JCustomCheckBox("Mostra tutte le offerte");
        showAllOffersCheckBox.setSelected(true);
        showAllOffersCheckBox.addActionListener(e -> {
				onShowAllOffersClicked(showAllOffersCheckBox.isSelected());
		});
        lowerPanel.add(showAllOffersCheckBox);
        lowerPanel.add(Box.createHorizontalStrut(30));
        
        JLabel offerteInviateLabel = new JLabel("OFFERTE INVIATE:");
        offerteInviateLabel.setFont(new Font("Montserrat", Font.BOLD, 14));
        lowerPanel.add(offerteInviateLabel);

        lowerPanel.add(Box.createHorizontalStrut(20));

        mainPanel.add(upperPanel);
        mainPanel.add(lowerPanel);

        this.add(mainPanel, BorderLayout.CENTER);
    }
    
    public void refresh() {
        controller.refreshAllOffers();
    }
    
    public void onShowAllOffersClicked(boolean selected) {
    	if(selected){
    		for(int i=0; i<parent.madeOffersScrollPane.getAllCards().size(); i++) {
    			JAbstractCard card = parent.madeOffersScrollPane.getAllCards().get(i);
    			card.setVisible(true);    			
    		}
    	}else {
    		for(int i=0; i<parent.madeOffersScrollPane.getAllCards().size(); i++) {
    		JAbstractOfferCard card = (JAbstractOfferCard)parent.madeOffersScrollPane.getAllCards().get(i);
    			if (!(card.getOfferta().getStato().equals(Stato.InAttesa)))
					card.setVisible(false);
    	}
    		
    	parent.madeOffersScrollPane.revalidate();
    	parent.madeOffersScrollPane.repaint();
    	}
    }
}
