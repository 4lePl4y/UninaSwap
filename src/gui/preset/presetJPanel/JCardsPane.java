package gui.preset.presetJPanel;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JViewport;

import controller.Controller;
import entities.annuncio.Annuncio;
import entities.offerta.Offerta;
import entities.oggetto.Oggetto;
import gui.preset.presetJPanel.presetJCard.*;
public class JCardsPane<T> extends JPanel {
	private static final long serialVersionUID = 1L;
	private int cardWidth = JAbstractCard.cardWidth; // Width of each card
	private int cardHeight = JAbstractCard.cardHeight; 
	private int hGap = 30; // Horizontal gap between cards
	private int vGap = 15; // Vertical gap between cards
	private Controller controller; // Controller reference
	
	//COSTRUTTORE
	public JCardsPane(Controller controller) {
		super();
		this.controller = controller; // Initialize the controller
		setLayout(new GridBagLayout()); // Use GridBagLayout for flexible layout
		setOpaque(false); // Make the panel transparent
	}
	
	
	//METODI 
	public void setCorrectSizes(T type) {
		switch(type) {
		case Oggetto o->{
			cardWidth = JObjectCard.cardWidth;
			cardHeight = JObjectCard.cardHeight;
			}
		case Offerta of->{
			cardWidth = JAbstractOfferCard.cardWidth;
			cardHeight = JAbstractOfferCard.cardHeight;
			}
		default -> {}
		}
	}

	public int getCardsPerRow(int viewportWidth) {
		int maxCards = Math.max(1, (viewportWidth + hGap) / (cardWidth + hGap));
        return maxCards;
    }


    public void updateCardsLayout(int cardsPerRow, ArrayList<T> contents) {
        removeAll();
        for (int i = 0; i < contents.size(); i++) {
        	setCorrectSizes(contents.get(i));
            JPanel card = createCard(contents.get(i));
            
            if(card != null) {
	            GridBagConstraints gbc = new GridBagConstraints();
	            gbc.gridx = i % cardsPerRow;  //Imposta la colonna della card
	            gbc.gridy = i / cardsPerRow;  //Imposta la riga della card
	            gbc.insets = new Insets(vGap / 2, hGap / 2, vGap / 2, hGap / 2);  // Margini tra le card
	            add(card, gbc);
            }
        }
        revalidate();
        repaint(); 
    }
    
    @Override
    public Dimension getPreferredSize() {
        int numCards = getComponentCount();
        if (numCards == 0) return new Dimension(0, 0);

        Container parent = getParent();
        int viewportWidth = (parent instanceof JViewport vp) ? vp.getWidth() : 800; // fallback

        int cardsPerRow = getCardsPerRow(viewportWidth);
        int rows = (int) Math.ceil((double) numCards / cardsPerRow);

        int totalWidth = cardsPerRow * (cardWidth + hGap);
        int totalHeight = rows * (cardHeight + vGap);

        return new Dimension(totalWidth, totalHeight);
    }
    
    private JPanel createCard(T content) {
    	String usernameStudenteLoggato = controller.getStudenteLoggato().getUsername();
		switch (content) {
			case Annuncio a -> {
				//Se l'annuncio appartiene allo studente loggato, crea una JMyListingCard
				if(usernameStudenteLoggato.equals(a.getAutore().getUsername())) {
					return new JMyListingCard((Annuncio) a, controller);
				} else {
					//Se l'annuncio non appartiene allo studente loggato, crea una JListingCard
					return new JListingCard((Annuncio) a, controller);
				}
			}
				
			case Oggetto o -> {return new JObjectCard((Oggetto) o, controller);}
			
			case Offerta of ->{
				if (of.getOfferente().getUsername().equals(usernameStudenteLoggato))
					return new JMadeOfferCard (of, controller);
				else {
					if(of.getStato().toString().equals("InAttesa"))
						return new JReceivedOfferCard (of, controller);
					else
						return null; // Non mostrare offerte già accettate o rifiutate
				}
			}
			
			default -> {throw new IllegalArgumentException("Unsupported content type: " + content.getClass().getName());}
		}
	}
    
}
