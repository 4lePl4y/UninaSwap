package gui.modify_windows;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import gui.preset.JButtonWithBorder;
import gui.preset.presetJTextField.JCustomTextField;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModifyUsername extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel usernameLabel;
    private JCustomTextField cambiaUsernameField;
    private JButtonWithBorder confermaButton;
	
	public ModifyUsername(JFrame mainFrame, Controller controller){
		super(mainFrame, "Modifica Username", true);
        this.setSize(500, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);
        
        JPanel cambiaUsernamePanel = new JPanel();
        cambiaUsernamePanel.setLayout(null);
        
        usernameLabel = new JLabel("Inserici il nuovo username:");
        usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        usernameLabel.setBounds(30, 81, 170, 13);
        cambiaUsernamePanel.add(usernameLabel);
        
        cambiaUsernameField = new JCustomTextField("");
        cambiaUsernameField.setColumns(20);
        cambiaUsernameField.setBounds(30, 104, 400, 30);
        cambiaUsernamePanel.add(cambiaUsernameField);
        
        confermaButton = new JButtonWithBorder("Conferma");
        confermaButton.setBounds(150, 169, 200, 30);
        confermaButton.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        			String newUsername = cambiaUsernameField.getText().trim();;
        			controller.onModificaUsernameClicked(newUsername);
        	}
        });
        
        cambiaUsernamePanel.add(confermaButton);
        contentPane.add(cambiaUsernamePanel);
	}
}
