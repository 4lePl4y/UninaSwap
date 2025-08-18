package gui.preset.presetJPanel.presetJCard;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import gui.preset.JButtonWithBorder;

import java.awt.Rectangle;
import javax.swing.JButton;



public class JListingCard extends JCard {
	private static final long serialVersionUID = 1L;

	public JListingCard() {
		super();
		this.setBounds(new Rectangle(0, 0, 240, 450));
		
		JLabel titleLabel = new JLabel("Titolo");
		titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(10, 10, 220, 13);
		add(titleLabel);
		
		JLabel descriptionLabel = new JLabel("Descripition");
		descriptionLabel.setVerticalAlignment(SwingConstants.TOP);
		descriptionLabel.setHorizontalTextPosition(SwingConstants.LEADING);
		descriptionLabel.setBounds(10, 146, 220, 134);
		add(descriptionLabel);
		
		JLabel sedeLabel = new JLabel("Sede dell'incontro:");
		sedeLabel.setBounds(10, 320, 86, 13);
		add(sedeLabel);

		JLabel timeLabel = new JLabel("Ora dell'incontro:");
		timeLabel.setBounds(10, 341, 79, 13);
		add(timeLabel);
		
		JLabel priceLabel = new JLabel("Prezzo:");
		priceLabel.setBounds(10, 297, 45, 13);
		add(priceLabel);
		
		JButton placeOfferButton = new JButtonWithBorder("Fai un'offerta");
		placeOfferButton.setBounds(55, 405, 140, 21);
		add(placeOfferButton);
		
		
	}
}
