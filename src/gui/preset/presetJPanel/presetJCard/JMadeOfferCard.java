package gui.preset.presetJPanel.presetJCard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.Controller;
import entities.enumerazioni.Stato;
import entities.offerta.Offerta;
import gui.preset.presetJButton.JButtonWithBorder;

public class JMadeOfferCard extends JAbstractOfferCard {
    private static final long serialVersionUID = 1L;

    public JMadeOfferCard(Offerta offerta, Controller controller) {
        super(offerta, controller);

        // RIGHT PANEL
        JPanel rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(100, 130));
        rightPanel.setMaximumSize(new Dimension(100, 130));
        this.add(rightPanel, BorderLayout.EAST);

        if (offerta.getStato() == Stato.InAttesa) {
            // Colla in alto per centrare
            rightPanel.add(Box.createVerticalGlue());

            JButton modifyButton = new JButtonWithBorder("Modifica");
            modifyButton.setAlignmentX(CENTER_ALIGNMENT);
            modifyButton.setPreferredSize(new Dimension(100, 30));
            modifyButton.setMaximumSize(new Dimension(100, 30));
            modifyButton.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    onModificaOffertaFrameClicked();
                }
            });
            rightPanel.add(modifyButton);

            // spazio tra i due bottoni
            rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));

            JButton deleteButton = new JButtonWithBorder("Ritira");
            deleteButton.setAlignmentX(CENTER_ALIGNMENT);
            deleteButton.setPreferredSize(new Dimension(100, 30));
            deleteButton.setMaximumSize(new Dimension(100, 30));
            deleteButton.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    onCancellaOffertaClicked();
                }
            });
            rightPanel.add(deleteButton);

            // Colla in basso per centrare
            rightPanel.add(Box.createVerticalGlue());
        }
    }

    @Override
    String rightTitleLabel() {
        return "Per: " + offerta.getAnnuncio().getTitolo();
    }

    public void onModificaOffertaFrameClicked() {
        controller.onModificaOffertaFrameClicked(offerta);
    }

    public void onCancellaOffertaClicked() {
        controller.onCancellaOffertaClicked(offerta);
    }
}
