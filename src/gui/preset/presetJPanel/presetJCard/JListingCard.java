package gui.preset.presetJPanel.presetJCard;

import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;

import controller.Controller;
import entities.annuncio.*;
import entities.annuncio.AnnuncioVendita;
import entities.studente.Studente;
import gui.preset.JDisplayTextArea;
import gui.preset.presetJButton.JButtonWithBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
		this.setBounds(new Rectangle(0, 0, 280, 450));
		this.annuncio = annuncio;
		this.autore = annuncio.getAutore();
		
		JLabel titleLabel = new JLabel(tipoAnnuncio() + " - " + annuncio.getTitolo());
		titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(10, 10, 220, 13);
		add(titleLabel);
		
		
		JDisplayTextArea descriptionTextArea = new JDisplayTextArea(annuncio.getDescrizione() + "\n" + annuncio.getOggetto().getBasicInfo());
		descriptionTextArea.setBorder((Border) new javax.swing.border.LineBorder(new Color(0, 0, 0), 1, true));
		add(descriptionTextArea);
		
		JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
		descriptionScrollPane.setBounds(10, 146, 260, 110);
		JScrollBar vBar = descriptionScrollPane.getVerticalScrollBar();
        vBar.setPreferredSize(new Dimension(8, Integer.MAX_VALUE)); // larghezza 8px
        vBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(120, 120, 120); // grigio scuro
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton btn = new JButton();
                btn.setPreferredSize(new Dimension(0, 0));
                btn.setVisible(false);
                return btn;
            }
        });

		add(descriptionScrollPane);
		
		if (annuncio instanceof AnnuncioVendita annuncioVendita) {
			JLabel priceLabel = new JLabel("Prezzo: " + String.valueOf(annuncioVendita.getPrezzo() + "€"));
			priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			priceLabel.setForeground(new Color(0, 119, 130));
			priceLabel.setBounds(10, 297, 220, 13);
			add(priceLabel);
			priceLabel.setVisible(true);
		}
		
		JLabel sedeLabel = new JLabel("Sede dell'incontro: "+ annuncio.getLuogo());
		sedeLabel.setBounds(10, 320, 220, 13);
		add(sedeLabel);

		JLabel timeLabel = new JLabel("Ora dell'incontro: "+ annuncio.getOraIncontro());
		timeLabel.setBounds(10, 341, 220, 13);
		add(timeLabel);
		
		
		JButton placeOfferButton = new JButtonWithBorder("Fai un'offerta");
		placeOfferButton.setBounds(55, 405, 140, 30);
		add(placeOfferButton);
		
	
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
	
	private String tipoAnnuncio() {
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
