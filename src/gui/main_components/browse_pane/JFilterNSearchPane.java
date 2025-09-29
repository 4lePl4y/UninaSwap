package gui.main_components.browse_pane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import controller.Controller;
import gui.preset.presetJButton.JButtonWithBorder;
import gui.preset.presetJLabel.JInteractiveLabel;

public class JFilterNSearchPane extends JPanel {
    private static final long serialVersionUID = 1L;
    private Controller controller;
    private boolean[] filtersObjectType = new boolean[5]; // Filtri per oggetto
    private boolean[] filtersAnnuncio = new boolean[3]; // Filtri per annuncio
    
    private JInteractiveLabel refreshButton;
    private JSearchTextField searchTxtField;
    private JButtonWithBorder searchButton;
    private JCheckBox[] objectTypeCheckBoxes;
    private JCheckBox[] annuncioCheckBoxes;

    public JFilterNSearchPane(Controller controller) {
        this.controller = controller;

        this.setPreferredSize(new Dimension(1106, 120));
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(255, 255, 255));

        // TOP PANEL 
        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setOpaque(false);

        GridBagConstraints gbc_top = new GridBagConstraints();
        gbc_top.insets = new Insets(5, 10, 5, 10);
        gbc_top.gridy = 0;
        gbc_top.fill = GridBagConstraints.HORIZONTAL;

        // Refresh button
        refreshButton = new JInteractiveLabel("⟳");
        refreshButton.setFont(refreshButton.getFont().deriveFont(30.0f));
        gbc_top.gridx = 0;
        gbc_top.weightx = 0;
        refreshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refresh();
            }
        });
        topPanel.add(refreshButton, gbc_top);

        // Search text field
        searchTxtField = new JSearchTextField(controller, "Cerca il tuo prossimo articolo preferito...");
        gbc_top.gridx = 1;
        gbc_top.weightx = 1;
        searchTxtField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    setFilters();
                    search();
                }
            }
        });
        topPanel.add(searchTxtField, gbc_top);

        // Search button
        searchButton = new JButtonWithBorder("Cerca", Controller.APP_BLUE);
        gbc_top.gridx = 2;
        gbc_top.weightx = 0;
        searchButton.setPreferredSize(new Dimension(100, 30));
        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setFilters();
                search();
            }
        });
        topPanel.add(searchButton, gbc_top);

        this.add(topPanel, BorderLayout.NORTH);

        // FILTERS PANEL 
        JPanel filtersPanel = new JPanel();
        filtersPanel.setLayout(new BoxLayout(filtersPanel, BoxLayout.X_AXIS));
        filtersPanel.setOpaque(false);
  
        objectTypeCheckBoxes = createCheckBoxPanel(filtersPanel, "Filtra per oggetto:",
                new String[]{"Abbigliamento", "Elettronica", "Libro", "Strumenti Musicali", "Varie"});
        
        
        filtersPanel.add(Box.createHorizontalStrut(100)); 
        		
        annuncioCheckBoxes = createCheckBoxPanel(filtersPanel, "Filtra per annuncio:",
                new String[]{"Vendita", "Scambio", "Regalo"});
        
        filtersPanel.add(Box.createVerticalStrut(30));

        this.add(filtersPanel, BorderLayout.CENTER);


    }

    private JCheckBox[] createCheckBoxPanel(JPanel parent, String title, String[] items) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5)); // compatto, orizzontale
        panel.setOpaque(false);
        panel.setBorder(new javax.swing.border.TitledBorder(title) {
        	private static final long serialVersionUID = 1L;
            private final int arc = 20;           // raggio angoli
            private final int titleGapLeft = 10;  // distanza da sinistra per il titolo
            private final int titlePadding = 6;   // padding orizzontale intorno al titolo

            @Override
            public void paintBorder(java.awt.Component c, java.awt.Graphics g, int x, int y, int width, int height) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                try {
                    g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

                    // font/metriche
                    java.awt.Font font = c.getFont();
                    g2.setFont(font);
                    java.awt.FontMetrics fm = g2.getFontMetrics();
                    String txt = title == null ? "" : title;
                    int txtW = fm.stringWidth(txt);
                    int txtH = fm.getHeight();
                    int txtAscent = fm.getAscent();

                    // posizione testo (leading/top)
                    int tx = x + titleGapLeft;
                    int tyBaseline = y + txtAscent;

                    // area del titolo (usata per escluderla dal disegno del bordo)
                    double titleX = tx - titlePadding;
                    double titleY = y;
                    double titleW = txtW + titlePadding * 2;
                    double titleH = txtH;

                    // rettangolo stondato del bordo (posizionato sotto il titolo)
                    int borderTop = y + txtH / 2;
                    int borderHeight = height - txtH / 2 - 1;
                    java.awt.geom.RoundRectangle2D.Double rr =
                        new java.awt.geom.RoundRectangle2D.Double(x, borderTop, width - 1, borderHeight, arc, arc);

                    // CLIP: escludo l'area del titolo, così il bordo non verrà disegnato sotto la scritta
                    java.awt.Shape oldClip = g2.getClip();
                    java.awt.geom.Area clipArea;
                    if (oldClip == null) {
                        clipArea = new java.awt.geom.Area(new java.awt.geom.Rectangle2D.Double(x, y, width, height));
                    } else {
                        clipArea = new java.awt.geom.Area(oldClip);
                    }
                    clipArea.subtract(new java.awt.geom.Area(new java.awt.geom.Rectangle2D.Double(titleX, titleY, titleW, titleH)));
                    g2.setClip(clipArea);

                    // disegno il bordo (solo le parti non escluse dal clip)
                    g2.setColor(Controller.APP_BLUE);
                    g2.draw(rr);

                    // ripristino clip
                    g2.setClip(oldClip);

                    // disegno il titolo sopra (così non può essere coperto)
                    g2.setColor(c.getForeground());
                    g2.drawString(txt, tx, tyBaseline);

                } finally {
                    g2.dispose();
                }
            }

            @Override
            public java.awt.Insets getBorderInsets(java.awt.Component c, java.awt.Insets insets) {
                if (insets == null) insets = new java.awt.Insets(0, 0, 0, 0);
                java.awt.FontMetrics fm = c.getFontMetrics(c.getFont());
                int txtH = fm.getHeight();
                insets.top = txtH;        // riservo spazio in alto per il titolo
                insets.left = 8;
                insets.right = 8;
                insets.bottom = 8;
                return insets;
            }

            @Override
            public java.awt.Insets getBorderInsets(java.awt.Component c) {
                return getBorderInsets(c, new java.awt.Insets(0, 0, 0, 0));
            }

            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        });

        JCheckBox[] checkBoxes = new JCheckBox[items.length];
        for (int i = 0; i < items.length; i++) {
            JCheckBox cb = new JCustomCheckBox(items[i]);
            checkBoxes[i] = cb;
            panel.add(cb);
        }
       
        parent.add(panel);
        return checkBoxes;
    }

    // METODI 
    public void refresh() {
        controller.refreshBrowse();
    }

    public void search() {
        controller.refreshBrowseForResearch();
    }

    public String getSearchBarText() {
        String research = searchTxtField.getText().trim();
        return research.equals("Cerca il tuo prossimo articolo preferito...") ? "" : research;
    }

    public boolean[] getFiltersForObjectType() {
        return filtersObjectType;
    }

    public boolean[] getFiltersForListings() {
        return filtersAnnuncio;
    }

    public void setFilters() {
        // Aggiorna filtri oggetto
        for (int i = 0; i < objectTypeCheckBoxes.length; i++) {
            filtersObjectType[i] = objectTypeCheckBoxes[i].isSelected();
        }

        // Aggiorna filtri annuncio
        for (int i = 0; i < annuncioCheckBoxes.length; i++) {
            filtersAnnuncio[i] = annuncioCheckBoxes[i].isSelected();
        }
    }
}
