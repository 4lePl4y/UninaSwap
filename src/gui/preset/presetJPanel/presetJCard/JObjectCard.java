package gui.preset.presetJPanel.presetJCard;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import entities.oggetto.*;
import gui.preset.JDisplayTextArea;
import gui.preset.presetJButton.JButtonWithBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class JObjectCard extends JAbstractCard {
	private static final long serialVersionUID = 1L;
	public static final int cardWidth = 280;
	public static final int cardHeight= 280;
	private Oggetto oggetto;

	public JObjectCard(Oggetto oggetto, Controller controller) {
		super(controller);
//		this.setSize(280, 200);
		this.setPreferredSize(new Dimension(cardWidth, cardHeight));
        this.setMaximumSize(new Dimension(cardWidth, cardHeight));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//TOP PANEL: contiene immagine e nome dell'oggetto
		this.add(Box.createVerticalStrut(20));
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(255, 255, 255));
		topPanel.setPreferredSize(new Dimension(cardWidth, 70));
		topPanel.setMaximumSize(new Dimension(cardWidth, 70));
		topPanel.setLayout(new BorderLayout());
		this.add(topPanel);
		
		ImageIcon icon = new ImageIcon(oggetto.getSourceImage()); 
		icon = new ImageIcon(icon.getImage().getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH));
		JLabel imageLabel = new JLabel(icon);
		imageLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		topPanel.add(imageLabel, BorderLayout.WEST);
		
		JLabel nameLabel = new JLabel("<html>" + oggetto.getNome() + "</html>");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 16));  //TODO: cambiare font
		topPanel.add(nameLabel, BorderLayout.CENTER);
		
		//MIDDLE PANEL: contiene le info dell'oggetto
		this.add(Box.createVerticalStrut(20));
		JPanel middlePanel = new JPanel();
		middlePanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		middlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		middlePanel.setBackground(new Color(255, 255, 255));
		middlePanel.setLayout(new BorderLayout(0, 0));
		this.add(middlePanel);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(new Color(255, 255, 255));
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		middlePanel.add(infoPanel, BorderLayout.CENTER);
		
		JDisplayTextArea infoTextArea = new JDisplayTextArea(oggetto.getBasicInfo());
		infoTextArea.setFont(new Font("Tahoma", Font.PLAIN, 14));  //TODO: cambiare font
		infoTextArea.setPreferredSize(new Dimension(260, 150));
		infoTextArea.setMaximumSize(new Dimension(260, 150));
		infoTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
		infoPanel.add(infoTextArea);
		
		//BOTTOM PANEL: contiene i bottoni per modificare o eliminare l'oggetto
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(255, 255, 255));
		this.add(bottomPanel);
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton modifyButton = new JButtonWithBorder("Modifica");
		modifyButton.setBounds(30, 410, 100, 30);
		modifyButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				onModificaOggettoFrameClicked();
			}
		});
		bottomPanel.add(modifyButton);

		bottomPanel.add(Box.createHorizontalStrut(15));
		
		JButton deleteButton = new JButtonWithBorder("Elimina");
		deleteButton.setBounds(155, 410, 100, 30);
		deleteButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				onCancellaOggettoClicked();
			}
		});
		bottomPanel.add(deleteButton);
		this.add(Box.createVerticalStrut(10));
	}
		
	//METODI
	public void onCancellaOggettoClicked() {
		controller.onCancellaOggettoClicked(oggetto);
	}
	
	public void onModificaOggettoFrameClicked() {
		controller.onModificaOggettoFrameClicked(oggetto);
	}
	

}
