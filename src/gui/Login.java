package gui;

import controller.Controller;
import gui.preset.*;
import gui.preset.presetJLabel.*;
import gui.preset.presetJTextField.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import javax.swing.JTextPane;


public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private JPanel contentPane;
	private JTextField userTxtField;
	private JPasswordField pswTxtField;
	private JLabel wrongUsrnmLabel;
	private JLabel wrongPwdLabel;


	public Login(Controller controller) {
		this.controller = controller; 
	
		setTitle("Unina Swap");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 650);

		contentPane = new JPanelWithBackground("src/img/LoginBackground.png");
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setLayout(null);	
		setContentPane(contentPane);
		
		
		JPanel panel = new JPanelWithBorder();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(158, 133, 284, 350);
		panel.setPreferredSize(new Dimension(500, 350));
		
        
		JLabel titleLabel = new JLabel("Accedi a UninaSwap");
		titleLabel.setBounds(24, 10, 234, 26);
		titleLabel.setForeground(new Color(0, 51, 102));
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		
		JTextPane descTxtPane = new JDisplayTextPane("Scambia, vendi o regala oggetti con altri studenti della Federico II");
		descTxtPane.setBounds(24, 48, 234, 41);
		
		
		userTxtField = new JCustomTextField("Username");
		userTxtField.setBounds(10, 113, 262, 33);
		userTxtField.setColumns(10);
		
		
		pswTxtField = new JCustomPasswordField("Password");
		pswTxtField.setBounds(10, 178, 262, 33);
		
		
		JButton lgnButton = new JButtonWithBorder("Login");
		lgnButton.setBounds(51, 251, 183, 33);
		
		
		lgnButton.addMouseListener(new MouseAdapter() {			
			@Override
			public void mouseClicked(MouseEvent e) {
				onLoginClicked();
			}
		});
			
		JLabel questionLabel = new JLabel("Non hai un account?");
		questionLabel.setBounds(24, 307, 130, 33);
		questionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		questionLabel.setForeground(Color.DARK_GRAY);
			
		JLabel signUpLabel = new JInteractiveLabel("Registrati");
		signUpLabel.setBounds(151, 315, 70, 17);
		
		signUpLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onGoToSignUpClicked();
			}
		});
			
		wrongUsrnmLabel = new JWarningLabel("Attenzione! Username errato");
		wrongUsrnmLabel.setBounds(51, 292, 183, 13);
		
		wrongPwdLabel = new JWarningLabel("Attenzione! Password errata");
		wrongPwdLabel.setBounds(51, 294, 183, 13);


		contentPane.add(panel);
		panel.setLayout(null);
		panel.add(titleLabel);
		panel.add(descTxtPane);
		panel.add(userTxtField);
		panel.add(pswTxtField);
		panel.add(lgnButton);
		panel.add(questionLabel);
		panel.add(signUpLabel);		
		panel.add(wrongUsrnmLabel);
		panel.add(wrongPwdLabel);
				
				
	}
	
	//METODI
	public void onGoToSignUpClicked(){
		controller.onGoToSignUpClicked();
	}
	
	public void onLoginClicked() {
		controller.onLoginClicked();
	}
	
	public String getUsername() {
		return userTxtField.getText();
	}
	
	public String getPassword() {
		return new String(pswTxtField.getPassword());
	}
}

