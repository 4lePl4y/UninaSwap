package gui.main_components.offers_pane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.Controller;
import entities.studente.Studente;
import gui.preset.presetJLabel.JInteractiveLabel;

public class JOffersUpperPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Controller controller;
    private Studente studenteLoggato;
    
    public JOffersUpperPanel(Controller controller) {
        this.controller = controller;
        this.studenteLoggato = controller.getStudenteLoggato();
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

        upperPanel.add(Box.createHorizontalStrut(20));

        JLabel logoLabel = new JLabel("LOGO");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 30));
        upperPanel.add(logoLabel);

        upperPanel.add(Box.createHorizontalGlue());

        JLabel titleLabel = new JLabel("Ciao " + studenteLoggato.getUsername() + ", Ecco le tue offerte");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        upperPanel.add(titleLabel);

        upperPanel.add(Box.createHorizontalGlue());

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
        offerteRicevuteLabel.setFont(new Font("Arial", Font.BOLD, 14));
        lowerPanel.add(offerteRicevuteLabel);

        // spazio flessibile in mezzo
        lowerPanel.add(Box.createHorizontalGlue());

        JLabel offerteFatteLabel = new JLabel("OFFERTE FATTE:");
        offerteFatteLabel.setFont(new Font("Arial", Font.BOLD, 14));
        lowerPanel.add(offerteFatteLabel);

        lowerPanel.add(Box.createHorizontalStrut(20));

        mainPanel.add(upperPanel);
        mainPanel.add(lowerPanel);

        this.add(mainPanel, BorderLayout.CENTER);
    }
    
    public void refresh() {
        controller.refreshAllOffers();
    }
}
