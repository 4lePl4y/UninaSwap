package gui.preset.presetJPanel.presetJCard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import entities.annuncio.Annuncio;
import entities.annuncio.AnnuncioVendita;
import gui.preset.JDisplayTextArea;

public abstract class AbstractListingCard extends JCard {
	private static final long serialVersionUID = 1L;
	protected JPanel middlePanel;
	
	//COSTRUTTORE
	public AbstractListingCard(Annuncio annuncio, Controller controller) {
		super(controller);
		this.setSize(280, 450);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//TOP PANEL: contiene immagine e titolo
		this.add(Box.createVerticalStrut(20));
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(255, 255, 255));
		topPanel.setPreferredSize(new Dimension(280, 70));
		topPanel.setMaximumSize(new Dimension(280, 70));
		topPanel.setLayout(new BorderLayout());
		this.add(topPanel);
		
		ImageIcon icon = new ImageIcon(annuncio.getOggetto().getSourceImage()); 
		icon = new ImageIcon(icon.getImage().getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH));
		JLabel imageLabel = new JLabel(icon);
		imageLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		topPanel.add(imageLabel, BorderLayout.WEST);
		
		JLabel titleLabel = new JLabel("<html>" + annuncio.getTipoAnnuncio() + "<br>" + annuncio.getTitolo() + "</html>");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));  //TODO: cambiare font
		topPanel.add(titleLabel, BorderLayout.CENTER);
		
		
		//MIDDLE PANEL: contiene descrizione e info oggetto
		this.add(Box.createVerticalStrut(20));
		middlePanel = new JPanel();
		middlePanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		middlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		middlePanel.setBackground(new Color(255, 255, 255));
		middlePanel.setLayout(new BorderLayout(0, 0));
		this.add(middlePanel);
		
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(new Color(255, 255, 255));
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		middlePanel.add(infoPanel, BorderLayout.CENTER);
		
		JDisplayTextArea descriptionTextArea = new JDisplayTextArea(annuncio.getDescrizione() + "\n\n" + annuncio.getOggetto().getBasicInfo());
		descriptionTextArea.setFont(new Font("Tahoma", Font.PLAIN, 14));  //TODO: cambiare font
		descriptionTextArea.setPreferredSize(new Dimension(260, 150));
		descriptionTextArea.setMaximumSize(new Dimension(260, 150));
		descriptionTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
		infoPanel.add(descriptionTextArea);
		
		JLabel sedeLabel = new JLabel("Sede dell'incontro: "+ annuncio.getLuogo());
		sedeLabel.setAlignmentX(Component.LEFT_ALIGNMENT); 
		infoPanel.add(sedeLabel);
		
		JLabel timeLabel = new JLabel("Ora dell'incontro: "+ annuncio.getOraIncontro() + " ");
		timeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		infoPanel.add(timeLabel);
		
		if(annuncio instanceof AnnuncioVendita annuncioVendita){
			JLabel priceLabel = new JLabel("Prezzo: " + String.valueOf(annuncioVendita.getPrezzo() + "€"));
			priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));  //TODO: cambiare font
			priceLabel.setForeground(new Color(0, 119, 130));
			priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
			infoPanel.add(priceLabel);
		}
	}

}
