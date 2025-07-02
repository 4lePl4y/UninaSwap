package gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
import java.awt.RenderingHints;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JSlider;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;

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
			private static final long serialVersionUID = 1L;

			@Override
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
			  }
		};

		
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(255, 255, 255));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel(){
			private static final long serialVersionUID = 1L;
			@Override
		    protected void paintComponent(Graphics g) {
		        Graphics2D g2 = (Graphics2D) g.create();
		        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        g2.setColor(getBackground());
		        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // 20 is the arc radius
		        super.paintComponent(g);
		        g2.dispose();
		    }
		};
		panel.setBackground(new Color(255, 255, 255));
		panel.setPreferredSize(new Dimension(500, 350));
		panel.setOpaque(false);
		panel.setBackground(panel.getBackground());
		
		JButton lgnButton = new JButton("Login") {
			private static final long serialVersionUID = 1L;
			@Override
		    protected void paintComponent(Graphics g) {
		        Graphics2D g2 = (Graphics2D) g.create();
		        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		        g2.setColor(getBackground());
		        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25); 
		        g2.dispose();
		        super.paintComponent(g);
		    }
		};
		lgnButton.setBounds(51, 251, 183, 33);
		lgnButton.setBackground(new Color(0, 51, 102));
		lgnButton.setForeground(new Color(255, 255, 255));
		lgnButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lgnButton.setBorderPainted(false);
		lgnButton.setContentAreaFilled(false);
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
			
			@Override
			public void mouseExited(MouseEvent e) {
			lgnButton.setBackground(SystemColor.textHighlight);
			lgnButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				onLoginClicked();
			}
		});
			panel.setLayout(null);
			
		userTxtField = new JTextField();
		userTxtField.setForeground(Color.GRAY);
		userTxtField.setText("Username");
		userTxtField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		userTxtField.setBounds(10, 113, 262, 33);
		userTxtField.setColumns(10);
		panel.add(userTxtField);
		
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

			
		pswTxtField = new JPasswordField();
		pswTxtField.setForeground(Color.GRAY);
		pswTxtField.setText("Password");
		pswTxtField.setEchoChar((char)0);
		pswTxtField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		pswTxtField.setBounds(10, 178, 262, 33);
		panel.add(pswTxtField);
		panel.add(lgnButton);
			
		pswTxtField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(pswTxtField.getPassword()).equals("Password")) {
                	pswTxtField.setText("");
                	pswTxtField.setEchoChar('•');
                	pswTxtField.setForeground(Color.BLACK);
                }
                
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(pswTxtField.getPassword()).equals("")) {
                	pswTxtField.setText("Password");
                	pswTxtField.setEchoChar((char)0);
                	pswTxtField.setForeground(Color.GRAY);
                }
                
            }
        });
			
			JLabel questionLabel = new JLabel("Non hai un account?");
			questionLabel.setBounds(24, 307, 130, 33);
			questionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			questionLabel.setForeground(Color.DARK_GRAY);
			panel.add(questionLabel);
			
				JLabel signUpLabel = new JLabel("Registrati");
				signUpLabel.setBounds(175, 315, 70, 17);
				signUpLabel.setForeground(Color.DARK_GRAY);
				signUpLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
				signUpLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					onRegisterClicked();
				}
				@Override
				public void mouseEntered(MouseEvent e) {
				signUpLabel.setForeground(Color.BLUE);
				signUpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				@Override
				public void mouseExited(MouseEvent e) {
				signUpLabel.setForeground(Color.DARK_GRAY);
				signUpLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
		});
				panel.add(signUpLabel);
				
				JLabel titleLabel = new JLabel("Accedi a UninaSwap");
				titleLabel.setForeground(new Color(0, 51, 102));
				titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
				titleLabel.setBounds(24, 10, 234, 26);
				panel.add(titleLabel);
				GroupLayout gl_contentPane = new GroupLayout(contentPane);
				gl_contentPane.setHorizontalGroup(
					gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(158)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(624, Short.MAX_VALUE))
				);
				gl_contentPane.setVerticalGroup(
					gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(133, Short.MAX_VALUE)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(130))
				);
				
				JTextPane descTxtPane = new JTextPane();
				descTxtPane.setVerifyInputWhenFocusTarget(false);
				descTxtPane.setRequestFocusEnabled(false);
				descTxtPane.setFocusCycleRoot(false);
				descTxtPane.setFocusTraversalKeysEnabled(false);
				descTxtPane.setFocusable(false);
				descTxtPane.setEditable(false);
				descTxtPane.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				descTxtPane.setText("Scambia, vendi o regala oggetti con altri studenti della Federico II");
				descTxtPane.setBounds(24, 48, 234, 41);
				panel.add(descTxtPane);
				contentPane.setLayout(gl_contentPane);
				
				panel.setFocusable(true);
		        SwingUtilities.invokeLater(() -> panel.requestFocusInWindow());
		}
	
	//METODI
	public void onRegisterClicked(){
		controller.onRegisterClicked();
	}
	
	public void onLoginClicked() {
		controller.onLoginClicked();
	}
}

