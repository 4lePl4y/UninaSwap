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
import java.awt.Graphics;
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
import java.awt.Image;

import javax.swing.DropMode;
import javax.swing.ImageIcon;

import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JSlider;
import java.awt.Component;

public class Login extends JFrame {
	private Controller controller;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userTxtField;
	private JPasswordField pswTxtField;


	public Login(Controller controller) {
		this.controller = controller; 
		
		setTitle("Unina Swap");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 650);
		

		ImageIcon backgroundIcon = new ImageIcon("LoginBackground.png");
	    Image backgroundImage = backgroundIcon.getImage();
		
		contentPane = new JPanel() {
			  @Override
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
			  }
		};

		
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(396, 163, 374, 350);
		contentPane.add(panel);
		panel.setPreferredSize(new Dimension(500, 350));
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{374, 0};
		gbl_panel.rowHeights = new int[]{38, 38, 38, 38, 38, 38, 38, 38, 0, 38, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton lgnButton = new JButton("Login");
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
			
					
		JLabel userLabel = new JLabel("Username");
		userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		userLabel.setLabelFor(panel);
		userLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		GridBagConstraints gbc_userLabel = new GridBagConstraints();
		gbc_userLabel.fill = GridBagConstraints.BOTH;
		gbc_userLabel.insets = new Insets(0, 0, 5, 0);
		gbc_userLabel.gridx = 0;
		gbc_userLabel.gridy = 0;
		panel.add(userLabel, gbc_userLabel);
			
		userTxtField = new JTextField();
		userTxtField.setColumns(10);
		GridBagConstraints gbc_userTxtField = new GridBagConstraints();
		gbc_userTxtField.fill = GridBagConstraints.BOTH;
		gbc_userTxtField.insets = new Insets(0, 0, 5, 0);
		gbc_userTxtField.gridx = 0;
		gbc_userTxtField.gridy = 1;
		panel.add(userTxtField, gbc_userTxtField);
			
		JLabel pswLabel = new JLabel("Password");
		pswLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		GridBagConstraints gbc_pswLabel = new GridBagConstraints();
		gbc_pswLabel.fill = GridBagConstraints.BOTH;
		gbc_pswLabel.insets = new Insets(0, 0, 5, 0);
		gbc_pswLabel.gridx = 0;
		gbc_pswLabel.gridy = 2;
		panel.add(pswLabel, gbc_pswLabel);
		pswLabel.setLabelFor(pswTxtField);
			
		pswTxtField = new JPasswordField();
		GridBagConstraints gbc_pswTxtField = new GridBagConstraints();
		gbc_pswTxtField.fill = GridBagConstraints.BOTH;
		gbc_pswTxtField.insets = new Insets(0, 0, 5, 0);
		gbc_pswTxtField.gridx = 0;
		gbc_pswTxtField.gridy = 3;
		panel.add(pswTxtField, gbc_pswTxtField);
			
		JLabel label_3 = new JLabel("");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.fill = GridBagConstraints.BOTH;
		gbc_label_3.insets = new Insets(0, 0, 5, 0);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 4;
		panel.add(label_3, gbc_label_3);
		
		GridBagConstraints gbc_lgnButton = new GridBagConstraints();
		gbc_lgnButton.fill = GridBagConstraints.BOTH;
		gbc_lgnButton.insets = new Insets(0, 0, 5, 0);
		gbc_lgnButton.gridx = 0;
		gbc_lgnButton.gridy = 5;
		panel.add(lgnButton, gbc_lgnButton);
			
		JLabel label_4 = new JLabel("");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.fill = GridBagConstraints.BOTH;
		gbc_label_4.insets = new Insets(0, 0, 5, 0);
		gbc_label_4.gridx = 0;
		gbc_label_4.gridy = 6;
		panel.add(label_4, gbc_label_4);
			
		JLabel lblNewLabel_1 = new JLabel("Non hai un account?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 7;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			
		JLabel SignUpLabel = new JLabel("Registrati");
		SignUpLabel.setForeground(Color.DARK_GRAY);
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
		
		GridBagConstraints gbc_SignUpLabel = new GridBagConstraints();
		gbc_SignUpLabel.insets = new Insets(0, 0, 5, 0);
		gbc_SignUpLabel.fill = GridBagConstraints.VERTICAL;
		gbc_SignUpLabel.gridx = 0;
		gbc_SignUpLabel.gridy = 8;
		panel.add(SignUpLabel, gbc_SignUpLabel);
		}
	
	//METODI
	public void onRegistratiClicked(){
		controller.onRegistratiClicked();
	}
}