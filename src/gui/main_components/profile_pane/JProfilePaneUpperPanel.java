package gui.main_components.profile_pane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import entities.studente.Studente;
import gui.preset.presetJButton.JButtonWithBorder;

public class JProfilePaneUpperPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private Studente studenteLoggato;
	
	
	public JProfilePaneUpperPanel(Controller controller) {
		this.controller = controller;
		this.studenteLoggato = controller.getStudenteLoggato();
		this.setBackground(new Color(255, 255, 255));
		this.setPreferredSize(new Dimension(800, 90));
		this.setMaximumSize(new Dimension(800, 90));
		this.setVisible(true);
		
		this.setLayout(new BorderLayout(0,0));
		
		JPanel topPanel = new JPanel();
		topPanel.setOpaque(false);
		JLabel welcomingLabel = new JLabel("Ciao " + studenteLoggato.getUsername() + "! Questo è il tuo profilo, modifica i tuoi dati, "
				+ "controlla le tue interazioni con gli altri studenti della Federico II e vedi i tuoi guadagni!");
		welcomingLabel.setHorizontalAlignment(JLabel.CENTER);
		welcomingLabel.setForeground(Color.black);
		topPanel.add(welcomingLabel, BorderLayout.WEST);
		this.add(topPanel, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setOpaque(false);
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		
		// --- SEZIONE GESTIONE  CREDENZIALE ---
       
        JPanel credentialsPanel = new JPanel();
        credentialsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Gestione credenziali"));
        credentialsPanel.setLayout(new BoxLayout(credentialsPanel, BoxLayout.X_AXIS));
        credentialsPanel.setOpaque(false);
        
        
        JButtonWithBorder changeEmailButton = new JButtonWithBorder("Cambia email", Controller.APP_BLUE);
        changeEmailButton.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		openModificaEmailFrame();
        	}
        });
        
        
        JButtonWithBorder changeUsernameButton = new JButtonWithBorder("Cambia username", Controller.APP_BLUE);
        changeUsernameButton.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		openModificaUsernameFrame();
        	}
        });

        
        JButtonWithBorder changePasswordButton = new JButtonWithBorder("Cambia password", Controller.APP_BLUE);
        changePasswordButton.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		openModificaPasswordFrame();
        	}
        });

        credentialsPanel.add(Box.createHorizontalStrut(10));
        credentialsPanel.add(changeEmailButton);
        credentialsPanel.add(Box.createHorizontalStrut(10));
        credentialsPanel.add(changeUsernameButton);
        credentialsPanel.add(Box.createHorizontalStrut(10));
        credentialsPanel.add(changePasswordButton);
        credentialsPanel.add(Box.createHorizontalStrut(10));
        
        // --- Sezione Azioni Importanti ---
        JPanel actionsPanel = new JPanel();
        actionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Azioni importanti"));
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.X_AXIS));
        actionsPanel.setOpaque(false);
        
        JButtonWithBorder logoutButton = new JButtonWithBorder("Logout", Controller.APP_BLUE);
        logoutButton.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		onLogoutClicked();
        	}
        });
        
        JButtonWithBorder deleteButton = new JButtonWithBorder("Elimina account", Controller.APP_RED);
        deleteButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				onEliminaAccountClicked();
			}
		});

        actionsPanel.add(Box.createHorizontalStrut(30));
        actionsPanel.add(logoutButton);
        actionsPanel.add(Box.createHorizontalStrut(30));
        actionsPanel.add(deleteButton);
        actionsPanel.add(Box.createHorizontalStrut(20));

        // Aggiunta pannelli bottoni al settingsPanel
        bottomPanel.add(Box.createHorizontalStrut(20));
        bottomPanel.add(credentialsPanel);
        bottomPanel.add(Box.createHorizontalStrut(600));
        bottomPanel.add(actionsPanel);
		this.add(bottomPanel, BorderLayout.SOUTH);
		
	}
	
    // METODI
	public void openModificaEmailFrame() {
		controller.openModificaEmailFrame();
	}
	
	public void openModificaUsernameFrame() {
		controller.openModificaUsernameFrame();
	}
	
	public void openModificaPasswordFrame() {
		controller.openModificaPasswordFrame();
	}
	
	public void onLogoutClicked() {
		controller.onLogoutClicked();
	}
	
	public void onEliminaAccountClicked() {
		controller.onEliminaAccountClicked();
	}
	
	
}
