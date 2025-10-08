package gui;

import controller.Controller;
import gui.preset.*;
import gui.preset.presetJButton.JButtonWithBorder;
import gui.preset.presetJLabel.*;
import gui.preset.presetJPanel.JPanelWithBackground;
import gui.preset.presetJPanel.JPanelWithBorder;
import gui.preset.presetJTextField.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private JPanel contentPane;
	private JTextField userTxtField;
	private JCustomPasswordField pswTxtField;
	private JWarningLabel warningLabel;


	public Login(Controller controller) {
		this.controller = controller; 
	
		setTitle("Unina Swap");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 1080, 650);

		contentPane = new JPanelWithBackground("src/img/LoginBackground.png");
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		int panelWidth=290;
		int panelHeight=360;
		
		JPanel panel = new JPanelWithBorder();
		panel.setBorder(BorderFactory.createCompoundBorder(
			    new LineBorder(Color.WHITE, 0, true),
			    new javax.swing.border.EmptyBorder(10, 10, 10, 10) // top, left, bottom, right
			));

		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(230, 280, panelWidth + 30, panelHeight);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
        
		JLabel titleLabel = new JLabel("Accedi a UninaSwap");
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		titleLabel.setPreferredSize(new Dimension(panelWidth, 30));
		titleLabel.setMaximumSize(new Dimension(panelWidth, 30));
		titleLabel.setForeground(new Color(0, 51, 102));
		titleLabel.setFont(new Font("Montserrat", Font.BOLD, 25));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

		
		
		JDisplayTextArea descTxtPane = new JDisplayTextArea("Scambia, vendi o regala oggetti con altri studenti della Federico II");
		descTxtPane.setPreferredSize(new Dimension(panelWidth, 40));
		descTxtPane.setMaximumSize(new Dimension(panelWidth, 40));
		
										
		userTxtField = new JCustomTextField("4le ");
		userTxtField.setPreferredSize(new Dimension(panelWidth, 35));
		userTxtField.setMaximumSize(new Dimension(panelWidth, 35));
		userTxtField.setColumns(10);
		
		
		pswTxtField = new JCustomPasswordField("1234");
		pswTxtField.setPreferredSize(new Dimension(panelWidth, 35));
		pswTxtField.setMaximumSize(new Dimension(panelWidth, 35));
		
										
		JButton lgnButton = new JButtonWithBorder("Login", Controller.APP_BLUE);
		lgnButton.setPreferredSize(new Dimension(panelWidth, 35));
		lgnButton.setMaximumSize(new Dimension(panelWidth, 35));
		lgnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		lgnButton.setHorizontalAlignment(SwingConstants.CENTER);
		
										
		lgnButton.addMouseListener(new MouseAdapter() {			
			@Override
			public void mouseClicked(MouseEvent e) {
				onLoginClicked();
			}
		});
		
		JPanel altPanel = new JPanel();
		altPanel.setLayout(new BoxLayout(altPanel, BoxLayout.X_AXIS));
		altPanel.setOpaque(false);
		
		JLabel questionLabel = new JLabel("Non hai un account?");
		questionLabel.setPreferredSize(new Dimension(150, 35));
		questionLabel.setMaximumSize(new Dimension(150, 35));
		questionLabel.setFont(new Font("Montserrat", Font.PLAIN, 15));
		questionLabel.setForeground(Color.DARK_GRAY);
		questionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		questionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel signUpLabel = new JInteractiveLabel("Registrati");
		signUpLabel.setPreferredSize(new Dimension(70, 20));
		signUpLabel.setMaximumSize(new Dimension(70, 20));
		signUpLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		signUpLabel.setHorizontalAlignment(SwingConstants.LEFT);

		altPanel.add(Box.createHorizontalStrut(30));
		altPanel.add(questionLabel);
		altPanel.add(signUpLabel);
		altPanel.add(Box.createHorizontalStrut(40));
		
		signUpLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onGoToSignUpClicked();
			}
		});
		
		warningLabel = new JWarningLabel("");
		warningLabel.setPreferredSize(new Dimension(250, 20));
		warningLabel.setMaximumSize(new Dimension(250, 20));
		warningLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		warningLabel.setHorizontalAlignment(SwingConstants.CENTER);

		
		
		contentPane.add(panel);
		// After each panel.add(component), add a vertical strut for spacing
		panel.add(Box.createVerticalStrut(10));
		panel.add(titleLabel);
		panel.add(Box.createVerticalStrut(10));
		panel.add(descTxtPane);
		panel.add(Box.createVerticalStrut(20));
		panel.add(userTxtField);
		panel.add(Box.createVerticalStrut(10));
		panel.add(pswTxtField);
		panel.add(Box.createVerticalStrut(30));
		panel.add(lgnButton);
		panel.add(Box.createVerticalStrut(20));
		panel.add(altPanel);
		panel.add(Box.createVerticalStrut(20));
		panel.add(warningLabel);
		panel.add(Box.createVerticalStrut(20));

	}
	
	//METODI
	public void onGoToSignUpClicked(){
		controller.onGoToSignUpClicked();
	}
	
	public void onLoginClicked() {
		controller.onLoginClicked();
	}
	
	public void resetWarningLabel() {
		warningLabel.reset();
	}
	
	public void setWarningLabel(String message) {
		warningLabel.setMessage(message);
	}
	
	public String getUsername() {
		return userTxtField.getText();
	}
	
	public String getPassword() {
		return new String(pswTxtField.getPassword());
	}
}

