package gui.preset.presetJPanel.presetJCard;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import controller.Controller;
import entities.enumerazioni.Stato;
import entities.offerta.Offerta;
import gui.preset.presetJButton.JButtonWithBorder;

public class JMadeOfferCard extends JAbstractOfferCard {
    private static final long serialVersionUID = 1L;

    public JMadeOfferCard(Offerta offerta, Controller controller) {
        super(offerta, controller);

        // FOOTER personalizzato
        if (offerta.getStato() == Stato.InAttesa) {
            JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
            footer.setOpaque(false);

            JButton modifyButton = new JButtonWithBorder("Modifica", Controller.APP_BLUE);
            JButton deleteButton = new JButtonWithBorder("Ritira", Controller.APP_RED);


            modifyButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onModificaOffertaFrameClicked();
                }
            });

            deleteButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onCancellaOffertaClicked();
                }
            });

            footer.add(modifyButton);
            footer.add(deleteButton);
            footer.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0)); 
            add(footer, BorderLayout.SOUTH);
        }
    }

    @Override
    protected String rightTitleLabel() {
    	return "<html>Per l'annuncio: <b>" + offerta.getAnnuncio().getTitolo() + "</b> <br>" 
    			+ "Pubblicato da:<b> " + offerta.getAnnuncio().getAutore().getUsername() + "</b> </html>";
    }

    public void onModificaOffertaFrameClicked() {
        controller.onModificaOffertaFrameClicked(offerta);
    }

    public void onCancellaOffertaClicked() {
        controller.onCancellaOffertaClicked(offerta);
    }
}
