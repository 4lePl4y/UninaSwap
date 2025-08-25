package gui.main_components.browse_pane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
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
		
		this.setBackground(new Color(255, 255, 255));
		this.setPreferredSize(new Dimension(800, 85));
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] {264, 264, 264, 264, 264};
		this.setLayout(gbl);
	
		refreshButton = new JRefreshButton("↻");
		refreshButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				refresh();
			}
		});
		this.add(refreshButton);
		
		searchTxtField = new JSearchTextField(controller, "Cerca il tuo prossimo articolo preferito...");
		searchTxtField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) { 
					setFiltersForObjectType();
					search();
				}
			}
		});
		this.add(searchTxtField, searchTxtField.getGbc());
		
		searchButton = new JButtonWithBorder("Cerca");
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setFiltersForObjectType();
				search();
			}
		});
		this.add(searchButton);
	
		filterAbbigliamento = new JFilterRadioButton("Abbigliamento");
		filterAbbigliamento.getGbc().gridx = 0;
		filterAbbigliamento.getGbc().gridy = 1;
	
		filterElettronica = new JFilterRadioButton("Elettronica");
		filterElettronica.getGbc().gridx = 1;
		filterElettronica.getGbc().gridy = 1;
	
		filterLibri = new JFilterRadioButton("Libri");
		filterLibri.getGbc().gridx = 2;
		filterLibri.getGbc().gridy = 1;
	
		filterStrumentiMusicali = new JFilterRadioButton("Strumenti Musicali");
		filterStrumentiMusicali.getGbc().gridx = 3;
		filterStrumentiMusicali.getGbc().gridy = 1;
	
		filterMisc = new JFilterRadioButton("Varie");
		filterMisc.getGbc().gridx = 4;
		filterMisc.getGbc().gridy = 1;

		this.add(filterAbbigliamento, filterAbbigliamento.getGbc());
		this.add(filterElettronica, filterElettronica.getGbc());
		this.add(filterLibri, filterLibri.getGbc());
		this.add(filterStrumentiMusicali, filterStrumentiMusicali.getGbc());
		this.add(filterMisc, filterMisc.getGbc());
		}

	//METODI
	
	public void search() {
		controller.refreshBrowseForResearch();
	}
	
	public void refresh() {
		controller.refreshBrowse();
		
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
