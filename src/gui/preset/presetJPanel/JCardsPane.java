package gui.preset.presetJPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JPanel;

import controller.Controller;
import entities.annuncio.Annuncio;
import entities.oggetto.Oggetto;
import gui.preset.presetJPanel.presetJCard.JListingCard;
import gui.preset.presetJPanel.presetJCard.JObjectCard;

public class JCardsPane<T> extends JPanel {
	private static final long serialVersionUID = 1L;
	private int cardWidth = 240; // Width of each card
	private int hGap = 30; // Horizontal gap between cards
	private int vGap = 15; // Vertical gap between cards
	private Controller controller; // Controller reference
	
	public JCardsPane(Controller controller) {
		super();
		this.controller = controller; // Initialize the controller
		setLayout(new GridBagLayout()); // Use GridBagLayout for flexible layout
		setOpaque(false); // Make the panel transparent
		setBackground(getBackground()); // Set the background color
		
	}
	
	public int getCardsPerRow(int viewportWidth) {
        if (viewportWidth >= (cardWidth + hGap) * 5 - hGap) {
            return 5;
        } else if (viewportWidth >= (cardWidth + hGap) * 4 - hGap) {
            return 4;
        } else if (viewportWidth >= (cardWidth + hGap) * 3 - hGap) {
            return 3;
        } else {
            return 2;
        }
    }


    public void updateCardsLayout(int cardsPerRow, ArrayList<T> contents) {
        removeAll();
        for (int i = 0; i < contents.size(); i++) {
            JPanel card = createCard(contents.get(i)); // Create a new card for each announcement

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = i % cardsPerRow;  //Imposta la colonna della card
            gbc.gridy = i / cardsPerRow;  //Imposta la riga della card
            gbc.insets = new Insets(vGap / 2, hGap / 2, vGap / 2, hGap / 2);  // Margini tra le card

            add(card, gbc);
        }
        revalidate();
        repaint(); 
    }
    
    
    private JPanel createCard(T content) {
		switch (content) {
			case Annuncio a -> {return new JListingCard((Annuncio) a, controller);}
			case Oggetto o -> {return new JObjectCard((Oggetto) o, controller);}
			
			default -> {throw new IllegalArgumentException("Unsupported content type: " + content.getClass().getName());}
		}
	}
    
}
