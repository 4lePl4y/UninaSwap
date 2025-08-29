package gui.modify_windows;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import gui.preset.JButtonWithBorder;
import gui.preset.presetJTextField.JMailTextField;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModifyEmail extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel emailLabel;
    private JMailTextField cambiaEmailField;
    private JButtonWithBorder confermaButton;
	
	public ModifyEmail(JFrame mainFrame, Controller controller){
		super(mainFrame, "Modifica Email", true);
        this.setSize(500, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);
        
        JPanel cambiaEmailPanel = new JPanel();
        cambiaEmailPanel.setLayout(null);
        
        emailLabel = new JLabel("Inserici la nuova email:");
        emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        emailLabel.setBounds(30, 81, 147, 13);
        cambiaEmailPanel.add(emailLabel);
        
        cambiaEmailField = new JMailTextField("");
        cambiaEmailField.setColumns(20);
        cambiaEmailField.setBounds(30, 104, 400, 30);
        cambiaEmailPanel.add(cambiaEmailField);
        
        confermaButton = new JButtonWithBorder("Conferma");
        confermaButton.setBounds(150, 169, 160, 30);
        confermaButton.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		if(cambiaEmailField.isValidInput()) {
        			String newEmail = cambiaEmailField.getText().trim();
        			controller.onModificaEmailClicked(newEmail);
        		} else {
        			JOptionPane.showMessageDialog(contentPane, "Inserisci un'email valida!");
        		}
        	}
        });
        
        cambiaEmailPanel.add(confermaButton);
        contentPane.add(cambiaEmailPanel);
	}
}
