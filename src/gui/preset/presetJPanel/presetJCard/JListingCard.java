package gui.preset.presetJPanel.presetJCard;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controller.Controller;
import entities.annuncio.*;
import entities.annuncio.AnnuncioVendita;
import entities.studente.Studente;
import gui.preset.JButtonWithBorder;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;



public class JListingCard extends JCard {
	private static final long serialVersionUID = 1L;
	private Annuncio annuncio;
	private Studente autore; 
	
	public JListingCard(Annuncio annuncio, Controller controller) {
		super(controller);
		this.setBounds(new Rectangle(0, 0, 240, 450));
		this.annuncio = annuncio;
		this.autore = annuncio.getAutore();
		
		JLabel titleLabel = new JLabel(annuncio.getTitolo());
		titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(10, 10, 220, 13);
		add(titleLabel);
		
		JLabel descriptionLabel = new JLabel(annuncio.getDescrizione());
		descriptionLabel.setVerticalAlignment(SwingConstants.TOP);
		descriptionLabel.setHorizontalTextPosition(SwingConstants.LEADING);
		descriptionLabel.setBounds(10, 146, 220, 134);
		add(descriptionLabel);
		
		JLabel sedeLabel = new JLabel("Sede dell'incontro: "+ annuncio.getLuogo());
		sedeLabel.setBounds(10, 320, 220, 13);
		add(sedeLabel);

		JLabel timeLabel = new JLabel("Ora dell'incontro: "+ annuncio.getOraIncontro());
		timeLabel.setBounds(10, 341, 220, 13);
		add(timeLabel);
		
		
		JButton placeOfferButton = new JButtonWithBorder("Fai un'offerta");
		placeOfferButton.setBounds(55, 405, 140, 30);
		add(placeOfferButton);
		
		JLabel typeLabel = new JLabel("New label");
		typeLabel.setBounds(10, 364, 70, 13);
		add(typeLabel);
		
		
		if (annuncio instanceof AnnuncioVendita) {
			JLabel priceLabel = new JLabel("Prezzo: " + String.valueOf(((AnnuncioVendita)annuncio).getPrezzo()) + "€");
			priceLabel.setBounds(10, 297, 220, 13);
			add(priceLabel);
			priceLabel.setVisible(true);
			typeLabel.setText("Vendita");
		}else if (annuncio instanceof AnnuncioScambio) {
			typeLabel.setText("Scambio");
		}else {
			typeLabel.setText("Regalo");
		}
			
			
		placeOfferButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				onFaiOffertaClicked();
			}
		});
		
	}
	
	//METODI
	public void onFaiOffertaClicked() {
		controller.onFaiOffertaClicked(annuncio, autore);
	}
}
