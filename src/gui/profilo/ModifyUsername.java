package gui.profilo;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import gui.preset.presetJButton.JButtonWithBorder;
import gui.preset.presetJTextField.JCustomTextField;

import java.awt.Color;
import java.awt.Font;


public class ModifyUsername extends JDialog {
	//ATTRIBUTI
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel usernameLabel;
    private JCustomTextField cambiaUsernameField;
    private JButtonWithBorder confermaButton;
	
    // COSTRUTTORE
	public ModifyUsername(JFrame mainFrame, Controller controller){
		super(mainFrame, "Modifica Username", true);
        this.setSize(500, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setBackground(new Color(255,255,255));
        
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBackground(new Color (255,255,255));
        setContentPane(contentPane);
        
        JPanel cambiaUsernamePanel = new JPanel();
        cambiaUsernamePanel.setLayout(null);
        cambiaUsernamePanel.setOpaque(false);
        
        usernameLabel = new JLabel("Inserici il nuovo username:");
        usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        usernameLabel.setBounds(30, 81, 170, 13);
        cambiaUsernamePanel.add(usernameLabel);
        
        cambiaUsernameField = new JCustomTextField("");
        cambiaUsernameField.setColumns(20);
        cambiaUsernameField.setBounds(30, 104, 400, 30);
        cambiaUsernamePanel.add(cambiaUsernameField);
        
        confermaButton = new JButtonWithBorder("Conferma", Controller.APP_BLUE);
        confermaButton.setBounds(150, 169, 200, 30);
        confermaButton.addActionListener(e -> {
        	controller.onModificaUsernameClicked(controller.getStudenteLoggato());
        });
        
        cambiaUsernamePanel.add(confermaButton);
        contentPane.add(cambiaUsernamePanel);
	}
	
	// METODI
	
	// Getter
	public String getUsername() {
		return cambiaUsernameField.getText();
	}
}
