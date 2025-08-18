package gui.preset.presetJPanel.presetJCard;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import entities.annuncio.Annuncio;
import entities.annuncio.AnnuncioVendita;
import gui.preset.JButtonWithBorder;

import java.awt.Rectangle;
import javax.swing.JButton;



public class JListingCard extends JCard {
	private static final long serialVersionUID = 1L;

	public JListingCard(Annuncio annuncio) {
		super();
		this.setBounds(new Rectangle(0, 0, 240, 450));
		
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
		
		
		if (annuncio instanceof AnnuncioVendita) {
			JLabel priceLabel = new JLabel("Prezzo: " + String.valueOf(((AnnuncioVendita)annuncio).getPrezzo()) + "€");
			priceLabel.setBounds(10, 297, 220, 13);
			add(priceLabel);
			priceLabel.setVisible(true);
		}
		
	}
}
