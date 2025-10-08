package gui.main_components.profile_pane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.Controller;
import entities.studente.Studente;

public class JProfilePaneUpperPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Studente studenteLoggato;

	public JProfilePaneUpperPanel(Controller controller) {
		this.studenteLoggato = controller.getStudenteLoggato();
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 85));
        setLayout(new BorderLayout(0, 0));

        // WEST: logo
        JPanel westPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 10));
        westPanel.setBackground(Color.WHITE);
        ImageIcon logoIcon = new ImageIcon("src/img/logo.jpg");
        logoIcon = new ImageIcon(logoIcon.getImage().getScaledInstance(250, 65, java.awt.Image.SCALE_SMOOTH));
        JLabel logo = new JLabel(logoIcon);
        westPanel.add(logo);
        add(westPanel, BorderLayout.WEST);

        // CENTER: label benvenuto
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 25)); 
        centerPanel.setBackground(Color.WHITE);
        JLabel welcomingLabel = new JLabel("Ciao " + studenteLoggato.getUsername() + ",  Modifica il tuo profilo e visualizza le tue statistiche", SwingConstants.CENTER);
        welcomingLabel.setFont(new Font("Montserrat", Font.BOLD, 20));
        centerPanel.add(welcomingLabel);
        add(centerPanel, BorderLayout.CENTER);
	}
	
}
