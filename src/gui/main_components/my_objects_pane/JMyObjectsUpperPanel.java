package gui.main_components.my_objects_pane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.Controller;
import entities.studente.Studente;
import gui.preset.presetJButton.JButtonWithBorder;

public class JMyObjectsUpperPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private Studente studenteLoggato;
	
	public JMyObjectsUpperPanel(Controller controller) {
		this.studenteLoggato = controller.getStudenteLoggato();
		setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 85));
        setLayout(new BorderLayout(5, 0)); // margine orizzontale ridotto tra le sezioni

        // WEST: logo
        JPanel westPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 25)); 
        westPanel.setBackground(Color.WHITE);
        JLabel logo = new JLabel("UNINA SWAP");	//TODO: sostituire con logo vero (?)
        logo.setFont(new Font("SansSerif", Font.BOLD, 20));
        logo.setForeground(new Color(30, 30, 30));
        westPanel.add(logo);
        add(westPanel, BorderLayout.WEST);

        // CENTER: label benvenuto
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 25)); 
        centerPanel.setBackground(Color.WHITE);
        String username = (studenteLoggato != null) ? studenteLoggato.getUsername() : "";
        JLabel welcomingLabel = new JLabel("Ciao " + username + " 👋, Ecco i tuoi oggetti", SwingConstants.CENTER);
        welcomingLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        welcomingLabel.setForeground(new Color(80, 80, 80));
        centerPanel.add(welcomingLabel);
        add(centerPanel, BorderLayout.CENTER);

        // EAST: pulsante nuovo oggetto
        JPanel eastPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 25));
        eastPanel.setBackground(Color.WHITE);
        JButton newListingButton = new JButtonWithBorder("+ Nuovo Oggetto", Controller.APP_BLUE);
        newListingButton.setFocusPainted(false);
        newListingButton.setPreferredSize(new Dimension(170, 35));
        newListingButton.addActionListener(e -> controller.onNuovoOggettoClicked());
        eastPanel.add(newListingButton);
        add(eastPanel, BorderLayout.EAST);
	}
}
