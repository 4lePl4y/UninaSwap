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

public class SignUp extends JFrame {
	private static final long serialVersionUID = 1L;
    private Controller controller;  
    private JPanel contentPane;
    private JLettersTextField nameTxtField;
    private JLettersTextField surnameTxtField;
    private JTextField userTxtField;
    private JMailTextField emailTxtField;
    private JPasswordField pswTxtField;
    private JPasswordField checkPswTxtField;
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
        panel.setBounds(330, 70, 398, 504);
        panel.setPreferredSize(new Dimension(500, 750));
        panel.setLayout(null);
          
        
        // Presentation Labels 
        JLabel lblNewLabel = new JLabel("Registrati a UninaSwap");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblNewLabel.setForeground(new Color(0, 51, 102));
        lblNewLabel.setBounds(88, 7, 202, 21);
        
        
        // Name text field
        nameTxtField = new JLettersTextField("Nome");
        nameTxtField.setBounds(10, 81, 354, 33);
        nameTxtField.setColumns(10);
           
                
		// Surname text field
        surnameTxtField = new JLettersTextField("Cognome");
        surnameTxtField.setBounds(10, 131, 354, 33);
        surnameTxtField.setColumns(10);
       

		// Username text field
        userTxtField = new JCustomTextField("Username");
        userTxtField.setBounds(10, 181, 354, 33);
        userTxtField.setColumns(10);
        
        // Email text field
        emailTxtField = new JMailTextField("Email");
        emailTxtField.setBounds(10, 231, 354, 33);
        emailTxtField.setColumns(10);
        
        // Password text field
        pswTxtField = new JCustomPasswordField("Password");
        pswTxtField.setBounds(10, 281, 354, 33);
        

        // Check Password text field
        checkPswTxtField = new JCustomPasswordField("Conferma Password");
        checkPswTxtField.setBounds(10, 331, 354, 33);
        

        // Registrati Button
        JButton registerButton = new JButtonWithBorder("Registrati");
        registerButton.setBounds(88, 397, 216, 33);
        registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onRegisterClicked();
			}
		});
        
        
        JLabel lblNewLabel_2 = new JLabel("Hai già un account?");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(88, 461, 114, 22);
        
        
        loginLabel = new JInteractiveLabel("Accedi");
        loginLabel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		onReturnToLoginClicked();
        	}
        });
        loginLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        loginLabel.setBounds(212, 466, 45, 13);
        
        
        contentPane.add(panel);
        panel.add(lblNewLabel);
        panel.add(nameTxtField);
        panel.add(surnameTxtField);
        panel.add(userTxtField);
        panel.add(emailTxtField);
        panel.add(pswTxtField);
        panel.add(checkPswTxtField);
        panel.add(registerButton);
        panel.add(lblNewLabel_2);
        panel.add(loginLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Riempi i campi sottostanti");
        lblNewLabel_1.setBounds(113, 38, 161, 23);
        panel.add(lblNewLabel_1);
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        
        JLabel wrongNameLabel = new JLabel("Attenzione! Nome non valido.");
        wrongNameLabel.setForeground(Color.RED);
        wrongNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        wrongNameLabel.setBounds(88, 443, 189, 13);
        panel.add(wrongNameLabel);
        
        JLabel wrongSurnameLabel = new JLabel("Attenzione! Cognome non valido.");
        wrongSurnameLabel.setForeground(Color.RED);
        wrongSurnameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        wrongSurnameLabel.setBounds(88, 443, 202, 13);
        panel.add(wrongSurnameLabel);
        
        JLabel wrongEmailLabel = new JLabel("Attenzione! E-mail non valido.");
        wrongEmailLabel.setForeground(Color.RED);
        wrongEmailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        wrongEmailLabel.setBounds(88, 443, 216, 13);
        panel.add(wrongEmailLabel);
    }
    
    
  //METODI
  	public void onReturnToLoginClicked(){
  		controller.onReturnToLoginClicked();
  	}
  	
  	public void onRegisterClicked() {
  		controller.onRegisterClicked(nameTxtField.getText(), surnameTxtField.getText(), userTxtField.getText(), emailTxtField.getText(),
  				new String(pswTxtField.getPassword()), new String(checkPswTxtField.getPassword())
  				);
  	}
  	
  	public boolean areInputsValid() {
  		if(!nameTxtField.isValidInput()) {
  			
  			return false;
  		}else if(!surnameTxtField.isValidInput()) {
  			
  			return false;
  		}else if(!emailTxtField.isValidInput()) {
  			
  			return false;
  		}
  			
  		return true;
  	}
}
