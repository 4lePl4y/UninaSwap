package gui.preset.presetJPanel.presetJCard;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import controller.Controller;
import entities.enumerazioni.Stato;
import entities.offerta.Offerta;
import gui.preset.presetJButton.JAcceptButton;
import gui.preset.presetJButton.JDeclineButton;

public class JReceivedOfferCard extends JAbstractOfferCard {
    private static final long serialVersionUID = 1L;

    public JReceivedOfferCard(Offerta offerta, Controller controller) {
        super(offerta, controller);

        // FOOTER personalizzato
        if (offerta.getStato() == Stato.InAttesa) {
            JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
            footer.setOpaque(false);

            JButton acceptButton = new JAcceptButton();
            JButton declineButton = new JDeclineButton();

            acceptButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onAccettaOffertaClicked();
                }
            });

            declineButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onRifiutaOffertaClicked();
                }
            });

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
