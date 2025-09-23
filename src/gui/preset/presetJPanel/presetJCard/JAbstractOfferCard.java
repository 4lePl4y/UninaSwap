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

import controller.Controller;
import entities.offerta.Offerta;
import entities.offerta.OffertaDenaro;
import entities.offerta.OffertaScambio;
import entities.oggetto.Oggetto;
import gui.preset.JDisplayTextArea;

public abstract class JAbstractOfferCard extends JAbstractCard {
	private static final long serialVersionUID = 1L;
	public static final int cardWidth = 450;
	public static final int cardHeight = 280;
	protected Offerta offerta;
	private JPanel infoPanel;
	private JPanel middlePanel;

	public JAbstractOfferCard(Offerta offerta, Controller controller) {
		super(controller);
		this.offerta = offerta;
		this.setPreferredSize(new Dimension(cardWidth, cardHeight));
		this.setMaximumSize(new Dimension(cardWidth, cardHeight));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//TOP PANEL: contiene immagine e titolo
		this.add(Box.createVerticalStrut(20));
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(255, 255, 255));
		topPanel.setPreferredSize(new Dimension(450, 70));
		topPanel.setMaximumSize(new Dimension(450, 70));
		topPanel.setLayout(new BorderLayout());
		this.add(topPanel);
		
		ImageIcon icon = new ImageIcon(offerta.getSourceImage()); 
		icon = new ImageIcon(icon.getImage().getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH));
		JLabel imageLabel = new JLabel(icon);
		imageLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		topPanel.add(imageLabel, BorderLayout.WEST);
		
		JLabel titleLabel = new JLabel("<html>"+ rightTitleLabel() + "</html>");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));  //TODO: cambiare font
		topPanel.add(titleLabel, BorderLayout.CENTER);
		
		
		//MIDDLE PANEL: contiene descrizione e info oggetto
		middlePanel = new JPanel();
		middlePanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		middlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		middlePanel.setBackground(new Color(255, 255, 255));
		middlePanel.setLayout(new BorderLayout(0, 0));
		this.add(middlePanel);
		
		
		infoPanel = new JPanel();
		infoPanel.setBackground(new Color(255, 255, 255));
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		middlePanel.add(infoPanel, BorderLayout.CENTER);
		
		JDisplayTextArea messageTextArea = new JDisplayTextArea(offerta.getMessaggio());
		messageTextArea.setFont(new Font("Tahoma", Font.PLAIN, 14));  //TODO: cambiare font
		messageTextArea.setPreferredSize(new Dimension(430, 150));
		messageTextArea.setMaximumSize(new Dimension(430, 150));
		messageTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
		infoPanel.add(messageTextArea);
		
		addOffering();
		
	}
	
	abstract String rightTitleLabel();
	
	private void addOffering() {
		if(offerta instanceof OffertaDenaro od) {
			middlePanel.add(Box.createHorizontalStrut(15));
			JLabel moneyLabel = new JLabel("Denaro offerto: " + od.getOfferta()); 
			moneyLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15)); 
			moneyLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
			infoPanel.add(moneyLabel); 
		} else {
			JLabel offeredObjectsLabel = new JLabel("Oggetti offerti in scambio: "); 
			offeredObjectsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15)); 
			offeredObjectsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
			infoPanel.add(offeredObjectsLabel); 
		
			JList<Oggetto> list = new JList<>(((OffertaScambio)offerta).getOggettiOfferti().toArray(new Oggetto[0]));
			list.setSelectionModel(new DefaultListSelectionModel() {
				@Override
				public void setSelectionInterval(int index0, int index1) {
					// Non fa niente -> impedisce la selezione
				}
			});
            JScrollPane sp = new JScrollPane(list);
            sp.setPreferredSize(new Dimension(430, 150));
    		sp.setMaximumSize(new Dimension(430, 150));
            sp.setBorder(null);
            infoPanel.add(sp);
		}
	}
	
}
