package gui.profilo;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Font;

import controller.Controller;
import gui.preset.presetJButton.JButtonWithBorder;
import gui.preset.presetJTextField.JMailTextField;

public class ModifyEmail extends JDialog {
	// ATTRIBUTI
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel emailLabel;
    private JMailTextField cambiaEmailField;
    private JButtonWithBorder confermaButton;
	private Controller controller;
	
    // COSTRUTTORE
	public ModifyEmail(JFrame mainFrame, Controller controller){
		super(mainFrame, "Modifica Email", true);
		this.controller = controller;
        this.setSize(500, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBackground(new Color(255, 255, 255));
        setContentPane(contentPane);
        
        JPanel cambiaEmailPanel = new JPanel();
        cambiaEmailPanel.setLayout(null);
        cambiaEmailPanel.setOpaque(false);
        
        emailLabel = new JLabel("Inserici la nuova email:");
        emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        emailLabel.setBounds(30, 81, 147, 13);
        cambiaEmailPanel.add(emailLabel);
        
        cambiaEmailField = new JMailTextField("");
        cambiaEmailField.setColumns(20);
        cambiaEmailField.setBounds(30, 104, 400, 30);
        cambiaEmailPanel.add(cambiaEmailField);
        
        confermaButton = new JButtonWithBorder("Conferma", Controller.APP_BLUE);
        confermaButton.setBounds(150, 169, 160, 30);
        confermaButton.addActionListener(e -> {
        	if(cambiaEmailField.isValidInput()) {
    			onModificaEmailClicked();
    		} else {
    			JOptionPane.showMessageDialog(contentPane, "Inserisci un'email valida!");
    		}
        });
        
        cambiaEmailPanel.add(confermaButton);
        contentPane.add(cambiaEmailPanel);
	}
	
	// METODI
	public void onModificaEmailClicked() {
		controller.onModificaEmailClicked(controller.getStudenteLoggato());
	}
	
	// Getter
	public String getEmail() {
		return cambiaEmailField.getText();
	}
}
