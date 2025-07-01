package gui;
//ELIMINAMIII
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Dimension;

import controller.Controller;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.DropMode;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JSlider;

public class Login extends JFrame {
	public Controller controller;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userTxtField;
	private JPasswordField pswTxtField;

	/**
	 * Create the frame.
	 */
	public Login(Controller controller) {
		this.controller = controller; 
		
		setTitle("Unina Swap");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 650);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		contentPane.add(panel, gbc);
		panel.setPreferredSize(new Dimension(500, 350));
		
				
				JLabel userLabel = new JLabel("Username");
				userLabel.setBounds(167, 70, 62, 20);
				userLabel.setLabelFor(userTxtField);
				userLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				panel.add(userLabel);
				
				JLabel pswLabel = new JLabel("Password");
				pswLabel.setBounds(167, 140, 62, 20);
				pswLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				panel.add(pswLabel);
				
				pswTxtField = new JPasswordField();
				pswTxtField.setBounds(167, 160, 160, 30);
				pswLabel.setLabelFor(pswTxtField);
				panel.add(pswTxtField);
				
				JButton lgnButton = new JButton("Login");
				lgnButton.setBounds(167, 218, 160, 28);
				lgnButton.setBackground(SystemColor.textHighlight);
				lgnButton.setForeground(new Color(255, 255, 255));
				lgnButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				lgnButton.setBorderPainted(false);
				lgnButton.setFocusPainted(false);
				
//		lgnButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
				
				lgnButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						lgnButton.setBackground(new Color(0, 102, 204));
						lgnButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
					}
				});
				
				lgnButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseExited(MouseEvent e) {
						lgnButton.setBackground(SystemColor.textHighlight);
						lgnButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
				});
				
				panel.add(lgnButton);
				
				JLabel SignUpLabel = new JLabel("Registrati");
				SignUpLabel.setForeground(Color.DARK_GRAY);
				SignUpLabel.setBounds(272, 299, 78, 17);
				SignUpLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
				SignUpLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						onRegistratiClicked();
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						SignUpLabel.setForeground(Color.BLUE);
						SignUpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						SignUpLabel.setForeground(Color.DARK_GRAY);
				SignUpLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
				});
				
				userTxtField = new JTextField();
				userTxtField.setBounds(167, 90, 160, 30);
				userTxtField.setColumns(10);
				panel.add(userTxtField);
				panel.add(SignUpLabel);
				
				JLabel lblNewLabel_1 = new JLabel("Non hai un account?");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNewLabel_1.setForeground(Color.DARK_GRAY);
				lblNewLabel_1.setBounds(139, 301, 130, 13);
				panel.add(lblNewLabel_1);
		
	
		}
	
	//METODI
	public void onRegistratiClicked(){
		controller.onRegistratiClicked();
	}
}