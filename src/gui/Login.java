package gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import controller.Controller;

public class Login extends JFrame {
	private Controller controller;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userTxtField;
	private JPasswordField pswTxtField;

	/**
	 * Create the frame.
	 */
	public Login(Controller controller) {
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
		userTxtField.setBounds(470, 132, 157, 33);
		contentPane.add(userTxtField);
		userTxtField.setColumns(10);
		
		JLabel userLabel = new JLabel("Username");
		userLabel.setLabelFor(userTxtField);
		userLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		userLabel.setBounds(375, 137, 88, 20);
		contentPane.add(userLabel);
		
		JLabel pswLabel = new JLabel("Password");
		pswLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		pswLabel.setBounds(385, 278, 78, 20);
		contentPane.add(pswLabel);
		
		pswTxtField = new JPasswordField();
		pswLabel.setLabelFor(pswTxtField);
		pswTxtField.setBounds(470, 273, 157, 33);
		contentPane.add(pswTxtField);
		
		JButton lgnButton = new JButton("LOGIN");
		lgnButton.setBackground(new Color(255, 255, 255));
		lgnButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lgnButton.setBounds(504, 347, 104, 33);
		contentPane.add(lgnButton);
		
		JLabel lblNewLabel = new JLabel("Non hai un account? Registrati qui.");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onRegistratiClicked();
			}
		});
		lblNewLabel.setBounds(438, 429, 205, 16);
		contentPane.add(lblNewLabel);
		
		}
	
	//METODI
	public void onRegistratiClicked(){
		controller.onRegistratiClicked(); 
	}
}
