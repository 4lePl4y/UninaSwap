package gui;

import controller.Controller;
import gui.preset.*;
import gui.preset.presetJButton.JButtonWithBorder;
import gui.preset.presetJLabel.*;
import gui.preset.presetJPanel.JPanelWithBackground;
import gui.preset.presetJPanel.JPanelWithBorder;
import gui.preset.presetJTextField.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SignUp extends JFrame {
	private static final long serialVersionUID = 1L;
    private Controller controller;  
    private JPanel contentPane;
    private JLettersTextField nameTxtField;
    private JLettersTextField surnameTxtField;
    private JCustomTextField userTxtField;
    private JMailTextField emailTxtField;
    private JCustomPasswordField pswTxtField;
    private JCustomPasswordField checkPswTxtField;
    private JLabel loginLabel;
    private JWarningLabel warningLabel;
    

    //Create the frame
    public SignUp(Controller controller) {
        this.controller = controller; 
        
        setTitle("Unina Swap");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setBounds(0, 0, 1080, 650);
    
        
        contentPane = new JPanelWithBackground("src/img/SignUpBackground.png");
        contentPane.setForeground(new Color(0, 0, 0));
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Create the panel for input fields
        JPanel panel = new JPanelWithBorder();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(560, 150, 400, 500);
        panel.setLayout(null);
          
        
        // Presentation Labels 
        JLabel lblNewLabel = new JLabel("Registrati a UninaSwap");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblNewLabel.setForeground(new Color(0, 51, 102));
        lblNewLabel.setBounds((panel.getWidth()-202)/2, 7, 202, 21);
        
        JLabel lblNewLabel_1 = new JLabel("Riempi i campi sottostanti");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNewLabel_1.setBounds((panel.getWidth()-161)/2, 38, 161, 23);
        
        
        // Name text field
        nameTxtField = new JLettersTextField("Nome");
        nameTxtField.setBounds(24, 81, 354, 33);
           
        
		// Surname text field
        surnameTxtField = new JLettersTextField("Cognome"); 
        surnameTxtField.setBounds(24, 131, 354, 33);
       

		// Username text field
        userTxtField = new JCustomTextField("Username");
        userTxtField.setBounds(24, 181, 354, 33);
        
        // Email text field
        emailTxtField = new JMailTextField("Email");
        emailTxtField.setBounds(24, 231, 354, 33);
        
        // Password text field
        pswTxtField = new JCustomPasswordField("Password");
        pswTxtField.setBounds(24, 281, 354, 33);
        

        // Check Password text field
        checkPswTxtField = new JCustomPasswordField("Conferma Password");
        checkPswTxtField.setBounds(24, 331, 354, 33);
        

        // Registrati Button
        JButton registerButton = new JButtonWithBorder("Registrati");
        registerButton.setBounds((panel.getWidth()-216)/2, 397, 216, 33);
        registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onRegisterClicked();
			}
		});
        
        
        JLabel lblNewLabel_2 = new JLabel("Hai già un account?");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(112, 461, 114, 22);
        
        
        loginLabel = new JInteractiveLabel("Accedi");
        loginLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        loginLabel.setBounds(236, 466, 45, 13);
        loginLabel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		onReturnToLoginClicked();
        	}
        });
        
        
        warningLabel = new JWarningLabel("");
        warningLabel.setHorizontalAlignment(SwingConstants.CENTER);
        warningLabel.setBounds(63, 440, 270, 16);
        
        
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
        panel.add(lblNewLabel_1);
        panel.add(warningLabel);
    }
    
    
  //METODI
  	public void onReturnToLoginClicked(){
  		controller.onReturnToLoginClicked();
  	}
  	
  	public void onRegisterClicked() {
  		controller.onRegisterClicked();
  	}
  	
  	//method to mark easy and common errors in the input fields
  	public boolean areInputsValid() {
  		String psw = new String(pswTxtField.getPassword());
  		String checkPsw = new String(checkPswTxtField.getPassword());
  		if(!nameTxtField.isValidInput() || nameTxtField.getText().equals("Nome")) {
  			warningLabel.setMessage("Attenzione! Nome non valido");
  			nameTxtField.resetHint("Nome");
  			return false;
  		}else if(!surnameTxtField.isValidInput() || surnameTxtField.getText().equals("Cognome")) {
  			warningLabel.setMessage("Attenzione! Cognome non valido");
  			surnameTxtField.resetHint("Cognome");
  			return false;
  		}else if(userTxtField.getText().equals("Username")) {	
  			warningLabel.setMessage("Attenzione! Username non valido");
  			userTxtField.resetHint("Username");
  			return false;
  		}else if(!emailTxtField.isValidInput()) {
  			warningLabel.setMessage("Attenzione! Email non valida");
  			emailTxtField.resetHint("Email");
  			return false;
  		}else if(psw.equals("Password") || checkPsw.equals("Conferma Password")) {
  			warningLabel.setMessage("Attenzione! Campi password vuoti");
  			pswTxtField.resetHint("Password");
  			checkPswTxtField.resetHint("Conferma Password");
  			return false;
  		}else if(!psw.equals(checkPsw)) {
  			warningLabel.setMessage("Attenzione! Le password non coincidono");
  			pswTxtField.resetHint("Password");
  			checkPswTxtField.resetHint("Conferma Password");
  			return false;
  		}
  		return true;
  	}
  	
  	public void resetWarningLabel() {
  		warningLabel.reset();
  	}
  	
  	public void setWarningLabel(String message) {
  		warningLabel.setMessage(message);
  	}

  	public String getName() {
  		return nameTxtField.getText();
  	}
  	
  	public String getSurname() {
  		return surnameTxtField.getText();
  	}
  	
  	public String getUsername() {
  		return userTxtField.getText();
  	}
  	
  	public void setUsername(String username) {
  		userTxtField.resetHint(username);
  	}
  	
  	public String getEmail() {
  		return emailTxtField.getText();
  	}
  	
  	
  	public void setEmail(String email) {
  		emailTxtField.resetHint(email);
  	}
  	public String getPassword() {
  		return new String(pswTxtField.getPassword());
  	}
  	
  	public String getCheckPassword() {
  		return new String(checkPswTxtField.getPassword());
  	}
  	
  	
  	
}
