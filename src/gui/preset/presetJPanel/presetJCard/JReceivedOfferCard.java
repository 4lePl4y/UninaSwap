package gui.preset.presetJPanel.presetJCard;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import controller.Controller;
import entities.enumerazioni.Stato;
import entities.offerta.Offerta;
import gui.preset.presetJButton.JButtonWithBorder;

public class JReceivedOfferCard extends JAbstractOfferCard {
    private static final long serialVersionUID = 1L;

    public JReceivedOfferCard(Offerta offerta, Controller controller) {
        super(offerta, controller);

        // FOOTER personalizzato
        if (offerta.getStato() == Stato.InAttesa) {
            JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
            footer.setOpaque(false);

            JButton acceptButton = new JButtonWithBorder("Accetta", new Color(0, 150, 0));
            JButton declineButton = new JButtonWithBorder("Rifiuta", new Color(200, 0, 0));

            acceptButton.addActionListener(e -> onAccettaOffertaClicked());

            declineButton.addActionListener(e -> onRifiutaOffertaClicked());

            footer.add(acceptButton);
            footer.add(declineButton);
            footer.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0)); 
            add(footer, BorderLayout.SOUTH);
        }
    }

    @Override
    protected String rightTitleLabel() {
        return "<html>Per l'annuncio: <b>" + offerta.getAnnuncio().getTitolo() + "</b> <br>" 
             + "Inviato da: <b>" + offerta.getOfferente().getUsername() + "</b> </html>";
    }

    public void onAccettaOffertaClicked() {
        controller.onAccettaOffertaClicked(offerta);
    }

    public void onRifiutaOffertaClicked() {
        controller.onRifiutaOffertaClicked(offerta);
    }
}
