package gui.profilo;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import gui.preset.JButtonWithBorder;
import gui.preset.presetJTextField.JCustomPasswordField;

public class ModifyPassword extends JDialog {
	//ATTRIBUTI
	private static final long serialVersionUID = 1L;	
	private Controller controller;
	private JPanel contentPane;
	private JLabel oldPasswordLabel;
	private JCustomPasswordField oldPasswordField;
	private JLabel newPasswordLabel;
    private JCustomPasswordField newPasswordField;
    private JButtonWithBorder confermaButton;
	
	public ModifyPassword(JFrame mainFrame, Controller controller){
		super(mainFrame, "Modifica Password", true);
		this.controller = controller;
        this.setSize(500, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);
        
        JPanel cambiaPasswordPanel = new JPanel();
        cambiaPasswordPanel.setLayout(null);
        
        oldPasswordLabel = new JLabel("Inserici la vecchia password:");
        oldPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        oldPasswordLabel.setBounds(28, 11, 180, 17);
        cambiaPasswordPanel.add(oldPasswordLabel);
        
        oldPasswordField = new JCustomPasswordField("");
        oldPasswordField.setColumns(20);
        oldPasswordField.setBounds(28, 34, 400, 30);
        cambiaPasswordPanel.add(oldPasswordField);
        
        newPasswordLabel = new JLabel("Inserici la nuova password:");
        newPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        newPasswordLabel.setBounds(28, 106, 180, 17);
        cambiaPasswordPanel.add(newPasswordLabel);
        
        newPasswordField = new JCustomPasswordField("");
        newPasswordField.setColumns(20);
        newPasswordField.setBounds(28, 129, 400, 30);
        cambiaPasswordPanel.add(newPasswordField);
        
        confermaButton = new JButtonWithBorder("Conferma");
        confermaButton.setBounds(131, 193, 200, 30);
        confermaButton.addActionListener(e -> {
        	onModificaPasswordClicked();
        });
        
        cambiaPasswordPanel.add(confermaButton);
        contentPane.add(cambiaPasswordPanel);
	}

	// METODI
	private void onModificaPasswordClicked() {
		controller.onModificaPasswordClicked(controller.getStudenteLoggato());
	}

	public String getOldPassword() {
		return new String(oldPasswordField.getPassword());
	}

	public String getNewPassword() {
		return new String(newPasswordField.getPassword());
	}

}
