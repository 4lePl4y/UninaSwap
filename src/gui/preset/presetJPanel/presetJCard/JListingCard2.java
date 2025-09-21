package gui.preset.presetJPanel.presetJCard;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import entities.annuncio.*;
import entities.annuncio.AnnuncioVendita;
import entities.studente.Studente;
import gui.preset.JDisplayTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;

import javax.swing.Box;
import javax.swing.BoxLayout;



public class JListingCard2 extends JCard {
	private static final long serialVersionUID = 1L;
	private Annuncio annuncio;
	private Studente autore; 
	
	public JListingCard2(Annuncio annuncio, Controller controller) {
		super(controller);
		this.setSize(280, 450);
		this.annuncio = annuncio;
		this.autore = annuncio.getAutore();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		this.add(Box.createVerticalStrut(20));
		//TOP PANEL: contiene immagine e titolo
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(255, 255, 255));
		topPanel.setPreferredSize(new Dimension(280, 70));
		topPanel.setMaximumSize(new Dimension(280, 70));
		topPanel.setLayout(new BorderLayout());
		this.add(topPanel);
		
		ImageIcon icon = new ImageIcon("src/img/elettronica.png"); 
		icon = new ImageIcon(icon.getImage().getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH));
		JLabel imageLabel = new JLabel(icon);
		imageLabel.setBorder(new EmptyBorder(0, 10, 0, 0));
		topPanel.add(imageLabel, BorderLayout.WEST);
		
		JLabel titleLabel = new JLabel("<html>" + getTipoAnnuncio() + "<br>" + annuncio.getTitolo() + "</html>");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));  //TODO: cambiare font
		topPanel.add(titleLabel, BorderLayout.CENTER);
		
		
		
		this.add(Box.createVerticalStrut(20));
		//MIDDLE PANEL: contiene descrizione e info oggetto
		JPanel middlePanel = new JPanel(); 
		middlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		middlePanel.setBackground(new Color(255, 255, 255));
		middlePanel.setPreferredSize(new Dimension(280, 250));
		middlePanel.setMaximumSize(new Dimension(280, 250));
		this.add(middlePanel);
		middlePanel.setLayout(new BorderLayout(0, 0));
		
		JDisplayTextArea descriptionTextArea = new JDisplayTextArea(annuncio.getDescrizione() + "\n\n" + annuncio.getOggetto().getBasicInfo());
		descriptionTextArea.setFont(new Font("Tahoma", Font.PLAIN, 14));  //TODO: cambiare font
		middlePanel.add(descriptionTextArea);
	}
	
	

	//METODI
	public void onFaiOffertaClicked() {
		controller.onFaiOffertaClicked(annuncio, autore);
	}
	
	private String getTipoAnnuncio() {
		String tipoAnnuncio = ""; 
		if (annuncio instanceof AnnuncioVendita) 
			tipoAnnuncio = "VENDITA";
		else if (annuncio instanceof AnnuncioScambio) 
			tipoAnnuncio = "SCAMBIO";
		else 
			tipoAnnuncio = "REGALO";
		
		return tipoAnnuncio;
	}
}
