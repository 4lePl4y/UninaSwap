package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controller.Controller;

public class SignUp extends JFrame {
    private Controller controller;  
    private static final long serialVersionUID = 1L;
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
        ImageIcon backgroundIcon = new ImageIcon("SignUpBackground.png");
        Image backgroundImage = backgroundIcon.getImage();
        contentPane = new JPanel() {
            private static final long serialVersionUID = 1L;

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

        // Create the panel for input fields
        JPanel panel = new JPanel(){
       	   	private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25); 
            g2.dispose();
            super.paintComponent(g);
			}
		};
		
		panel.setOpaque(false);
        panel.setBackground(panel.getBackground());
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(400, 70, 374, 468);
        contentPane.add(panel);
        panel.setPreferredSize(new Dimension(500, 750));
        
        // Presentation Labels 
        lblNewLabel = new JLabel("Registrati a UninaSwap");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblNewLabel.setForeground(new Color(0, 51, 102));
        lblNewLabel.setBounds(88, 10, 202, 21);
        panel.add(lblNewLabel);
        
        lblNewLabel_1 = new JLabel("Riempi i campi sottostanti");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(108, 41, 161, 23);
        panel.add(lblNewLabel_1);
        
        // Name text field
        nameTxtField = new JTextField();
        nameTxtField.setFocusTraversalPolicyProvider(true);
        nameTxtField.setText("Nome");
        nameTxtField.setForeground(Color.GRAY);
        nameTxtField.setBounds(10, 91, 354, 33);
        nameTxtField.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {
        		if (nameTxtField.getText().equals("Nome")) {
        			nameTxtField.setText("");
        			nameTxtField.setForeground(Color.BLACK);
					}
        	}
        
        	@Override
        	public void focusLost(FocusEvent e) {
        		if (nameTxtField.getText().isEmpty()) {
        			nameTxtField.setText("Nome");
        			nameTxtField.setForeground(Color.GRAY);
        		}
        	}
		});
                panel.setLayout(null);
                nameTxtField.setColumns(10);
                panel.add(nameTxtField);

                
		// Surname text field
        surnameTxtField = new JTextField();
        surnameTxtField.setForeground(Color.GRAY);
        surnameTxtField.setText("Cognome");
        surnameTxtField.setBounds(10, 146, 354, 33);
        surnameTxtField.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {
				if (surnameTxtField.getText().equals("Cognome")) {
					surnameTxtField.setText("");
					surnameTxtField.setForeground(Color.BLACK);
				}
        	}
        	
        	@Override
        	public void focusLost(FocusEvent e) {
        		if (surnameTxtField.getText().isEmpty()) {
					surnameTxtField.setText("Cognome");
					surnameTxtField.setForeground(Color.GRAY);
				}
        	}
        });
        surnameTxtField.setColumns(10);
        panel.add(surnameTxtField);

        
        // Username text field
        userTxtField = new JTextField();
        userTxtField.setForeground(Color.GRAY);
        userTxtField.setText("Username");
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
        userTxtField.setBounds(10, 200, 354, 33);
        userTxtField.setColumns(10);
        panel.add(userTxtField);

        
        // Password text field
        pswTxtField = new JPasswordField();
        pswTxtField.setForeground(Color.GRAY);
        pswTxtField.setText("Password");
        pswTxtField.setEchoChar((char)0);
        pswTxtField.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {
        		if (String.valueOf(pswTxtField.getPassword()).equals("Password")) {
					pswTxtField.setText("");
					  pswTxtField.setForeground(Color.BLACK);
			          pswTxtField.setEchoChar('•');
        		}
        	}
        	
        	@Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(pswTxtField.getPassword()).isEmpty()) {
                    pswTxtField.setForeground(Color.GRAY);
                    pswTxtField.setText("Password");
                    pswTxtField.setEchoChar((char)0);
                }
            }
        });
        
        pswTxtField.setBounds(10, 257, 354, 33);
        panel.add(pswTxtField);

        
        // Check Password text field
        checkPswTxtField = new JPasswordField();
        checkPswTxtField.setForeground(Color.GRAY);
        checkPswTxtField.setText("Conferma Password");
        checkPswTxtField.setEchoChar((char)0);
        checkPswTxtField.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {
        		if (String.valueOf(checkPswTxtField.getPassword()).equals("Conferma Password")) {
					checkPswTxtField.setText("");
					  checkPswTxtField.setForeground(Color.BLACK);
			          checkPswTxtField.setEchoChar('•');
        		}
        	}
        	
        	@Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(checkPswTxtField.getPassword()).isEmpty()) {
                    checkPswTxtField.setForeground(Color.GRAY);
                    checkPswTxtField.setText("Conferma Password");
                    checkPswTxtField.setEchoChar((char)0);
                }
            }
        });
        checkPswTxtField.setBounds(10, 311, 354, 33);
        panel.add(checkPswTxtField);

        // Registrati Button
        JButton registerButton = new JButton("Registrati") {
       	   	private static final long serialVersionUID = 1L;
			protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25); 
            g2.dispose();
            super.paintComponent(g);
			}
        };
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setFocusPainted(false);
        
        registerButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Codice per la registrazione
        	}
        });
        registerButton.setBounds(74, 374, 216, 28);
        registerButton.setBackground(new Color(30, 144, 255));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        registerButton.setBorderPainted(false);
        registerButton.setFocusPainted(false);

        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                registerButton.setBackground(new Color(0, 102, 204));
                registerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                registerButton.setBackground(new Color(30, 144, 255));
                registerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            }
        });
        panel.add(registerButton);
        
        lblNewLabel_2 = new JLabel("Hai già un account?");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(90, 425, 114, 22);
        panel.add(lblNewLabel_2);
        
        loginLabel = new JLabel("Accedi");
        loginLabel.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		onLoginClicked();
        	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		loginLabel.setForeground(Color.BLUE);
				loginLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		loginLabel.setForeground(Color.BLACK);
				loginLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        	}
        });
        loginLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        loginLabel.setBounds(214, 430, 45, 13);
        panel.add(loginLabel);
        
        panel.setFocusable(true);
        SwingUtilities.invokeLater(() -> panel.requestFocusInWindow());
    }
    
    
  //METODI
  	public void onLoginClicked(){
  		controller.onLoginClicked();
  	}
  	
  	
}
