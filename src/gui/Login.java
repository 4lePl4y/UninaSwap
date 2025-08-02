package gui;

import controller.Controller;
import gui.presetJFrame.*;

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.SwingUtilities;
import javax.swing.JTextPane;


public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private JPanel contentPane;
	private JTextField userTxtField;
	private JPasswordField pswTxtField;


	public Login(Controller controller) {
		this.controller = controller; 
	
		setTitle("Unina Swap");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 650);

		contentPane = new JPanelWithBackground("src/img/LoginBackground.png");
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(255, 255, 255));
		setContentPane(contentPane);
		
		
		JPanel panel = new JPanelWithBorder();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(158, 133, 284, 350);
		panel.setPreferredSize(new Dimension(500, 350));
		panel.setFocusable(true);
        SwingUtilities.invokeLater(() -> panel.requestFocusInWindow());
        contentPane.add(panel);
		
		JLabel titleLabel = new JLabel("Accedi a UninaSwap");
		titleLabel.setBounds(24, 10, 234, 26);
		titleLabel.setForeground(new Color(0, 51, 102));
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		
		JTextPane descTxtPane = new JTextPane();
		descTxtPane.setBounds(24, 48, 234, 41);
		descTxtPane.setVerifyInputWhenFocusTarget(false);
		descTxtPane.setRequestFocusEnabled(false);
		descTxtPane.setFocusCycleRoot(false);
		descTxtPane.setFocusTraversalKeysEnabled(false);
		descTxtPane.setFocusable(false);
		descTxtPane.setEditable(false);
		descTxtPane.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		descTxtPane.setText("Scambia, vendi o regala oggetti con altri studenti della Federico II");
		
			
		userTxtField = new JTextField("Username");
		userTxtField.setBounds(10, 113, 262, 33);
		userTxtField.setForeground(Color.GRAY);
	
		userTxtField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		userTxtField.setColumns(10);
		
		userTxtField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (userTxtField.getText().equals("Username")) {
                	userTxtField.setText("");
                	userTxtField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (userTxtField.getText().isEmpty()) {
                	userTxtField.setText("Username");
                	userTxtField.setForeground(Color.GRAY);
                }
            }
        });

			
		pswTxtField = new JCustomPasswordField("Password");
		pswTxtField.setBounds(10, 178, 262, 33);
		
		
		
		JButton lgnButton = new JButtonWithBorder("Login");
		lgnButton.setBounds(51, 251, 183, 33);
		lgnButton.setBorderPainted(false);
		lgnButton.setContentAreaFilled(false);
		lgnButton.setFocusPainted(false);
		
		lgnButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Codice per il login
        	}
        });
		
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
				onRegisterClicked();
			}
		});
			
		contentPane.setLayout(null);	
		contentPane.add(panel);
		panel.setLayout(null);
		panel.add(titleLabel);
		panel.add(descTxtPane);
		panel.add(userTxtField);
		panel.add(pswTxtField);
		panel.add(lgnButton);
		panel.add(questionLabel);
		panel.add(signUpLabel);
				
				
	}
	
	//METODI
	public void onRegisterClicked(){
		controller.onRegisterClicked();
	}
	
	public void onLoginClicked() {
		controller.onLoginClicked();
	}
}

