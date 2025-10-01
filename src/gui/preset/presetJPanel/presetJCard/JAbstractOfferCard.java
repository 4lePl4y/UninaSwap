package gui.preset.presetJPanel.presetJCard;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import entities.offerta.Offerta;
import entities.offerta.OffertaScambio;
import entities.offerta.OffertaDenaro;
import gui.preset.JCustomList;
import gui.preset.JDisplayTextArea;

public abstract class JAbstractOfferCard extends JAbstractCard {
    private static final long serialVersionUID = 1L;
    public static final int cardWidth = 580;
    public static final int cardHeight = 330;

    protected Offerta offerta;
    protected Controller controller;

    // COSTRUTTORE
    public JAbstractOfferCard(Offerta offerta, Controller controller) {
        super(controller);
        this.offerta = offerta;
        this.controller = controller;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(cardWidth, cardHeight));
        setMaximumSize(new Dimension(cardWidth, cardHeight));

        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(15, 20, 15, 20));

        // ===== HEADER =====
        JPanel header = new JPanel(new BorderLayout(0, 0));
        header.setOpaque(false);

        ImageIcon originalIcon = new ImageIcon(offerta.getSourceImage());
        Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JLabel icon = new JLabel(new ImageIcon(scaledImage));
        JLabel title = new JLabel(rightTitleLabel());
        title.setFont(new Font("SansSerif", Font.PLAIN, 18));
        JLabel status = new JLabel("<html><h3> Stato: " + offerta.getStato().toString() + " </h3></html>");
        

        header.add(icon, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        header.add(status, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // ===== CENTER =====
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        // Messaggio
        JLabel msgLabel = new JLabel("MESSAGGIO:");
        msgLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        msgLabel.setBorder(new EmptyBorder(10, 0, 2, 0));
        msgLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(msgLabel);

        String messaggio = (offerta.getMessaggio().isBlank()) ? "<Nessun messaggio>" : offerta.getMessaggio();
        JDisplayTextArea msgArea = new JDisplayTextArea(messaggio);
        msgArea.setOpaque(false);
        msgArea.setFont(new Font("SansSerif", Font.PLAIN, 14));

        // Scroll con propagazione
        JScrollPane msgScroll = new JScrollPane(msgArea);
        msgScroll.setPreferredSize(new Dimension(600, 130));
        msgScroll.setMaximumSize(new Dimension(600, 130));
        msgScroll.setWheelScrollingEnabled(true);
        msgScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        msgScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        msgScroll.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Propaga lo scroll al parent se siamo a inizio/fine o la barra non c'è
        msgArea.addMouseWheelListener(e -> {
            JScrollBar vBar = msgScroll.getVerticalScrollBar();

            boolean atTop = vBar.getValue() == 0;
            boolean atBottom = vBar.getValue() >= vBar.getMaximum() - vBar.getVisibleAmount();

            if (!vBar.isVisible() || (e.getWheelRotation() < 0 && atTop) || (e.getWheelRotation() > 0 && atBottom)) {
                msgScroll.getParent().dispatchEvent(
                        SwingUtilities.convertMouseEvent(msgArea, e, msgScroll.getParent())
                );
            } else
            	msgScroll.dispatchEvent(SwingUtilities.convertMouseEvent(msgArea, e, msgScroll));
        });

        centerPanel.add(msgScroll);

        // Denaro/Oggetti offerti
        if (offerta instanceof OffertaDenaro od) {
            JLabel money = new JLabel("Denaro offerto: " + od.getOfferta() + "€");
            money.setFont(new Font("SansSerif", Font.PLAIN, 14));
            money.setBorder(new EmptyBorder(10, 0, 0, 0));
            money.setAlignmentX(Component.LEFT_ALIGNMENT);
            centerPanel.add(money);
        } else if (offerta instanceof OffertaScambio os) {
            JLabel objLabel = new JLabel("Oggetti offerti in scambio:");
            objLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
            objLabel.setBorder(new EmptyBorder(10, 0, 2, 0));
            objLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            centerPanel.add(objLabel);

            JPanel objListPanel = new JCustomList<>(
                    os.getOggettiOfferti(),
                    JCustomList.Mode.DISPLAY_ONLY,
                    600,
                    200
            );
            objListPanel.setOpaque(false);
            objListPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            centerPanel.add(objListPanel);
        }

        add(centerPanel, BorderLayout.CENTER);
    }

    // METODI
    protected abstract String rightTitleLabel();
    
    public Offerta getOfferta() {return offerta;};
    
}
