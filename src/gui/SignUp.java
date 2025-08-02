package gui;

import controller.Controller;
import gui.presetJFrame.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class SignUp extends JFrame {
	private static final long serialVersionUID = 1L;
    private Controller controller;  
    private JPanel contentPane;
    private JTextField nameTxtField;
    private JTextField surnameTxtField;
    private JTextField userTxtField;
    private JPasswordField pswTxtField;
    private JPasswordField checkPswTxtField;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel loginLabel;

    //Create the frame
    public SignUp(Controller controller) {
        this.controller = controller; 
        
        setTitle("Unina Swap");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1080, 650);
    
        
        contentPane = new JPanelWithBackground("src/img/SignUpBackground.png");
        contentPane.setForeground(new Color(0, 0, 0));
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Create the panel for input fields
        JPanel panel = new JPanelWithBorder();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(400, 70, 374, 468);
        panel.setPreferredSize(new Dimension(500, 750));
        panel.setLayout(null);
        panel.setFocusable(true);
        SwingUtilities.invokeLater(() -> panel.requestFocusInWindow());
        contentPane.add(panel);
        
        // Presentation Labels 
        lblNewLabel = new JLabel("Registrati a UninaSwap");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblNewLabel.setForeground(new Color(0, 51, 102));
        lblNewLabel.setBounds(88, 10, 202, 21);
        
        lblNewLabel_1 = new JLabel("Riempi i campi sottostanti");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(108, 41, 161, 23);
        
        
        // Name text field
        nameTxtField = new JCustomTextField("Nome");
        nameTxtField.setBounds(10, 91, 354, 33);
        nameTxtField.setColumns(10);
           
                
		// Surname text field
        surnameTxtField = new JCustomTextField("Cognome");
        surnameTxtField.setBounds(10, 146, 354, 33);
        surnameTxtField.setColumns(10);
       

        // Username text field
        userTxtField = new JCustomTextField("Username");
        userTxtField.setBounds(10, 200, 354, 33);
        userTxtField.setColumns(10);
        
        
        // Password text field
        pswTxtField = new JCustomPasswordField("Password");
        pswTxtField.setBounds(10, 257, 354, 33);
        

        // Check Password text field
        checkPswTxtField = new JCustomPasswordField("Conferma Password");
        checkPswTxtField.setBounds(10, 311, 354, 33);
        

        // Registrati Button
        JButton registerButton = new JButtonWithBorder("Registrati");
        registerButton.setBounds(74, 374, 216, 33);
        
        registerButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Codice per la registrazione
        	}
        });
        
        lblNewLabel_2 = new JLabel("Hai già un account?");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(90, 425, 114, 22);
        
        
        loginLabel = new JInteractiveLabel("Accedi");
        loginLabel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		onReturnToLoginClicked();
        	}
        });
        loginLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        loginLabel.setBounds(214, 430, 45, 13);
        
        
        panel.add(lblNewLabel);
        panel.add(lblNewLabel_1);
        panel.add(nameTxtField);
        panel.add(surnameTxtField);
        panel.add(userTxtField);
        panel.add(pswTxtField);
        panel.add(checkPswTxtField);
        panel.add(registerButton);
        panel.add(lblNewLabel_2);
        panel.add(loginLabel);
    }
    
    
  //METODI
  	public void onReturnToLoginClicked(){
  		controller.onReturnToLoginClicked();
  	}
  	
  	
}
