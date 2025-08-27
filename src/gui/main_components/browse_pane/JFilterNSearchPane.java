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

import javax.swing.JPanel;

import controller.Controller;
import gui.preset.JButtonWithBorder;
import gui.preset.JRefreshButton;

public class JFilterNSearchPane extends JPanel{
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private boolean[] filters = new boolean[5];
	private JRefreshButton refreshButton;
	private JSearchTextField searchTxtField;
	private JButtonWithBorder searchButton;
	private JFilterRadioButton filterAbbigliamento;
	private JFilterRadioButton filterElettronica;
	private JFilterRadioButton filterLibri;
	private JFilterRadioButton filterStrumentiMusicali;
	private JFilterRadioButton filterMisc;
	
	
	public JFilterNSearchPane(Controller controller) {
	    this.controller = controller;
	
	    this.setBackground(Color.WHITE);
	    this.setPreferredSize(new Dimension(1106, 121));
	    this.setLayout(new BorderLayout());
	
	    // -------------------- TOP PANEL --------------------
	    JPanel topPanel = new JPanel(new GridBagLayout());
	    topPanel.setBackground(Color.WHITE);
	
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(5, 10, 5, 10);
	    gbc.gridy = 0;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	
	    // Refresh button (colonna 0, piccolo)
	    refreshButton = new JRefreshButton();
	    gbc.gridx = 0;
	    gbc.weightx = 0; // non si allarga
	    refreshButton.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            refresh();
	        }
	    });
	    topPanel.add(refreshButton, gbc);
	
	    // Search text field (colonna 1, largo)
	    searchTxtField = new JSearchTextField(controller, "Cerca il tuo prossimo articolo preferito...");
	    gbc.gridx = 1;
	    gbc.weightx = 1; // si espande
	    searchTxtField.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyReleased(KeyEvent e) {
	            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	                setFiltersForObjectType();
	                search();
	            }
	        }
	    });
	    topPanel.add(searchTxtField, gbc);
	
	    // Search button (colonna 2, medio)
	    searchButton = new JButtonWithBorder("Cerca");
	    gbc.gridx = 2;
	    gbc.weightx = 0; // rimane dimensionato
	    searchButton.setPreferredSize(new Dimension(100, 30));
	    searchButton.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            setFiltersForObjectType();
	            search();
	        }
	    });
	    topPanel.add(searchButton, gbc);
	
	    this.add(topPanel, BorderLayout.NORTH);
	
	    // -------------------- FILTERS PANEL --------------------
	    JPanel filtersPanel = new JPanel(new GridBagLayout());
	    filtersPanel.setBackground(Color.WHITE);
	
	    gbc.gridy = 0;
	    gbc.weightx = 1; 
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	
	    filterAbbigliamento = new JFilterRadioButton("Abbigliamento");
	    filterElettronica = new JFilterRadioButton("Elettronica");
	    filterLibri = new JFilterRadioButton("Libri");
	    filterStrumentiMusicali = new JFilterRadioButton("Strumenti Musicali");
	    filterMisc = new JFilterRadioButton("Varie");
	
	    JFilterRadioButton[] filters = {
	        filterAbbigliamento, filterElettronica, filterLibri,
	        filterStrumentiMusicali, filterMisc
	    };
	
	    for (int i = 0; i < filters.length; i++) {
	        gbc.gridx = i;
	        filters[i].setPreferredSize(new Dimension(180, 30)); // larghezza uniforme
	        filtersPanel.add(filters[i], gbc);
	    }
	
	    this.add(filtersPanel, BorderLayout.CENTER);
}




	//METODI
	public void refresh() {
		controller.refreshBrowse();	
	}
	
	
	public void search() {
		controller.refreshBrowseForResearch();
	}
	
	public String getSearchBarText() {
		String research = searchTxtField.getText().trim();
		research = research.equals("Cerca il tuo prossimo articolo preferito...") ? "" : research;
		return research;
	}
	
	public boolean[] getFiltersForObjectType() {
		return filters;
	}
	
	public void setFiltersForObjectType() {
		filters[0] = filterAbbigliamento.isSelected();
		filters[1] = filterElettronica.isSelected();
		filters[2] = filterLibri.isSelected();
		filters[3] = filterStrumentiMusicali.isSelected();
		filters[4] = filterMisc.isSelected();
	}

}
