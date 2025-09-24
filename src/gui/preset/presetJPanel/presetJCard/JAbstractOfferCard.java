package gui.preset.presetJPanel.presetJCard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import controller.Controller;
import entities.offerta.Offerta;
import entities.offerta.OffertaDenaro;
import entities.offerta.OffertaScambio;
import entities.oggetto.Oggetto;
import gui.preset.JCustomList;
import gui.preset.JDisplayTextArea;
import java.awt.Rectangle;

public abstract class JAbstractOfferCard extends JAbstractCard {
	private static final long serialVersionUID = 1L;
	public static final int cardWidth = 700;
	public static final int cardHeight = 150;
	protected Offerta offerta;
	private JPanel middleSXPanel;
	private JPanel middleDXPanel;
	private JPanel middlePanel;

	public JAbstractOfferCard(Offerta offerta, Controller controller) {
		super(controller);
		this.offerta = offerta;
		this.setPreferredSize(new Dimension(cardWidth, cardHeight));
		this.setMaximumSize(new Dimension(cardWidth, cardHeight));
		this.setLayout(new BorderLayout(0, 0));
		
		//LEFT PANEL: contiene immagine
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(255, 255, 255));
		leftPanel.setPreferredSize(new Dimension(100, 130));
		leftPanel.setMaximumSize(new Dimension(100, 130));
		leftPanel.setOpaque(false);
		leftPanel.setLayout(new BorderLayout());
		this.add(leftPanel, BorderLayout.WEST);
		
		ImageIcon icon = new ImageIcon(offerta.getSourceImage()); 
		icon = new ImageIcon(icon.getImage().getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH));
		JLabel imageLabel = new JLabel(icon);
		leftPanel.add(imageLabel, BorderLayout.CENTER);
		
		
		//MIDDLE PANEL: contiene titolo e messaggio 
		middlePanel = new JPanel();
		middlePanel.setAlignmentY(Component.CENTER_ALIGNMENT);
		middlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		middlePanel.setBackground(new Color(255, 255, 255));
		middlePanel.setLayout(new BorderLayout(0, 0));
		middlePanel.setPreferredSize(new Dimension(500, 130));
		middlePanel.setMaximumSize(new Dimension(500, 130));
		this.add(middlePanel, BorderLayout.CENTER);
		
		middleSXPanel = new JPanel();
		middleSXPanel.setBackground(new Color(255, 255, 255));
		middleSXPanel.setLayout(new BorderLayout(0, 0));
		middleSXPanel.setPreferredSize(new Dimension(250, 130));
		middleSXPanel.setMaximumSize(new Dimension(250, 130));
		middlePanel.add(middleSXPanel, BorderLayout.WEST);
		
		JLabel titleLabel = new JLabel("<html>"+ rightTitleLabel() + "</html>");
		titleLabel.setPreferredSize(new Dimension(250, 65));
		titleLabel.setMaximumSize(new Dimension(250, 65));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));  //TODO: cambiare font
		middleSXPanel.add(titleLabel, BorderLayout.NORTH);
		
		middleDXPanel = new JPanel();
		middleDXPanel.setPreferredSize(new Dimension(220, 130));
		middleDXPanel.setMaximumSize(new Dimension(220, 130));
		middleDXPanel.setLayout(new BorderLayout(0, 0));
		middlePanel.add(middleDXPanel, BorderLayout.EAST);
		
		JLabel messageLabel = new JLabel("MESSAGGIO:");
		messageLabel.setPreferredSize(new Dimension(220, 20));
		messageLabel.setMaximumSize(new Dimension(220, 20));
		middleDXPanel.add(messageLabel, BorderLayout.NORTH);
		
		JDisplayTextArea messageTextArea = new JDisplayTextArea(offerta.getMessaggio());
		messageTextArea.setOpaque(true);
		messageTextArea.setPreferredSize(new Dimension(220, 100));
		messageTextArea.setMaximumSize(new Dimension(220, 100));
		messageTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
		middleDXPanel.add(messageTextArea, BorderLayout.SOUTH);
		
		addOffering();
		
	}
	
	abstract String rightTitleLabel();
	
	private void addOffering() {
		if(offerta instanceof OffertaDenaro od) {
			middlePanel.add(Box.createHorizontalStrut(15));
			JLabel moneyLabel = new JLabel("<html>Denaro offerto: " +  "<b>" + od.getOfferta() + "€</b> </html>"); 
			moneyLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15)); 
			moneyLabel.setHorizontalAlignment(SwingConstants.CENTER);;
			middleSXPanel.add(moneyLabel, BorderLayout.CENTER); 
		} else {
			JPanel offeredObjectsPanel = new JPanel();
			offeredObjectsPanel.setLayout(new BorderLayout(0, 0));
			middleSXPanel.add(offeredObjectsPanel, BorderLayout.CENTER);
			
			JLabel offeredObjectsLabel = new JLabel("Oggetti offerti in scambio: "); 
			offeredObjectsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15)); //TODO: cambiare font
			offeredObjectsPanel.add(offeredObjectsLabel, BorderLayout.NORTH); 
			
			JPanel objectsListPanel = new JCustomList<Oggetto>(((OffertaScambio)offerta).getOggettiOfferti(), JCustomList.Mode.DISPLAY_ONLY, 205, 200);
			offeredObjectsPanel.add(objectsListPanel, BorderLayout.CENTER);
		}
	}
	
}
