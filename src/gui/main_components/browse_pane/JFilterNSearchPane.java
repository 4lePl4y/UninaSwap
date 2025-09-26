package gui.main_components.browse_pane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        JPanel filtersPanel = new JPanel(new GridBagLayout());
        filtersPanel.setOpaque(false);

        GridBagConstraints gbc_filters = new GridBagConstraints();
        gbc_filters.insets = new Insets(0, 0, 0, 0); // margine esterno sinistro/destra
        gbc_filters.gridy = 0;
        gbc_filters.fill = GridBagConstraints.HORIZONTAL; 

        // Filtra per oggetto
        gbc_filters.gridx = 0;
        gbc_filters.weightx = 0;
        objectTypeCheckBoxes = createCheckBoxPanel(filtersPanel, "Filtra per oggetto:",
                new String[]{"Abbigliamento", "Elettronica", "Libro", "Strumenti Musicali", "Varie"});
        

        // Filtra per annuncio
        gbc_filters.gridx = 1;
        gbc_filters.weightx = 0;
        annuncioCheckBoxes = createCheckBoxPanel(filtersPanel, "Filtra per annuncio:",
                new String[]{"Vendita", "Scambio", "Regalo"});

        this.add(filtersPanel, BorderLayout.CENTER);


    }

    private JCheckBox[] createCheckBoxPanel(JPanel parent, String title, String[] items) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5)); // compatto, orizzontale
        panel.setOpaque(false);
        panel.setBorder(javax.swing.BorderFactory.createTitledBorder(title));

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
