package gui.preset.presetJPanel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JPanel;

import gui.preset.presetJPanel.presetJCard.JCard;
import gui.preset.presetJPanel.presetJCard.JListingCard;

public class JCardsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int cardCount = 7; // Number of cards to display
	private int cardWidth = 240; // Width of each card
	private int hGap = 30; // Horizontal gap between cards
	private int vGap = 15; // Vertical gap between cards
	
	
	public JCardsPanel(int cardCount, int hGap, int vGap) {
		super();
		this.cardCount = cardCount;
		setLayout(new java.awt.GridBagLayout()); // Use GridBagLayout for flexible layout
		setOpaque(false); // Make the panel transparent
		setBackground(getBackground()); // Set the background color
		updateCardsLayout(getCardsPerRow(1080)); // Initial layout based on default viewport width
		
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


    public void updateCardsLayout(int cardsPerRow) {
        removeAll();
        for (int i = 0; i < cardCount; i++) {
            JPanel card = new JListingCard();

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = i % cardsPerRow;  //Imposta la colonna della card
            gbc.gridy = i / cardsPerRow;  //Imposta la riga della card
            gbc.insets = new Insets(vGap / 2, hGap / 2, vGap / 2, hGap / 2);  // Margini tra le card

            add(card, gbc);
        }
        revalidate();
        repaint(); 
    }	
}
