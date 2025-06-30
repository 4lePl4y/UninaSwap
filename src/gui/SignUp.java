package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import controller.Controller;

public class SignUp extends JFrame {
	private Controller controller;  
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userTxtField;
	private JPasswordField pswTxtField;
	private JTextField nameTxtField;
	private JTextField surTxtField;
	private JPasswordField checkPswTxtField;

	/**
	 * Create the frame.
	 */
	public SignUp(Controller controller) {
		this.controller=controller; 
		setTitle("Unina Swap");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 650);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		userTxtField = new JTextField();
		userTxtField.setBounds(473, 222, 157, 33);
		contentPane.add(userTxtField);
		userTxtField.setColumns(10);
		
		JLabel userLabel = new JLabel("Username");
		userLabel.setLabelFor(userTxtField);
		userLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		userLabel.setBounds(375, 226, 88, 20);
		contentPane.add(userLabel);
		
		JLabel pswLabel = new JLabel("Password");
		pswLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		pswLabel.setBounds(375, 278, 78, 20);
		contentPane.add(pswLabel);
		
		pswTxtField = new JPasswordField();
		pswLabel.setLabelFor(pswTxtField);
		pswTxtField.setBounds(473, 274, 157, 33);
		contentPane.add(pswTxtField);
		
		JButton lgnButton = new JButton("REGISTRATI");
		lgnButton.setBackground(new Color(255, 255, 255));
		lgnButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lgnButton.setBounds(473, 368, 157, 33);
		contentPane.add(lgnButton);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNome.setBounds(398, 118, 55, 20);
		contentPane.add(lblNome);
		
		nameTxtField = new JTextField();
		lblNome.setLabelFor(nameTxtField);
		nameTxtField.setColumns(10);
		nameTxtField.setBounds(473, 114, 157, 33);
		contentPane.add(nameTxtField);
		
		surTxtField = new JTextField();
		surTxtField.setColumns(10);
		surTxtField.setBounds(473, 168, 157, 33);
		contentPane.add(surTxtField);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setLabelFor(surTxtField);
		lblCognome.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblCognome.setBounds(375, 172, 88, 20);
		contentPane.add(lblCognome);
		
		JLabel checkPswLabel = new JLabel("Conferma Password");
		checkPswLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		checkPswLabel.setBounds(295, 330, 168, 20);
		contentPane.add(checkPswLabel);
		
		checkPswTxtField = new JPasswordField();
		checkPswTxtField.setBounds(473, 317, 157, 33);
		contentPane.add(checkPswTxtField);
	}
}
