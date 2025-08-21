package gui.main_components.browse_pane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class JFilterNSearchPane extends JPanel{
	private static final long serialVersionUID = 1L;
	private JSearchTextField searchTxtField;
	private JFilterRadioButton filterAbbigliamento;
	private JFilterRadioButton filterElettronica;
	private JFilterRadioButton filterLibri;
	private JFilterRadioButton filterStrumentiMusicali;
	private JFilterRadioButton filterMisc;
	
	public JFilterNSearchPane(){
		this.setBackground(new Color(255, 255, 255));
		this.setPreferredSize(new Dimension(800, 85));
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] {264, 264, 264, 264, 264};
		this.setLayout(gbl);
	
		searchTxtField = new JSearchTextField("Cerca il tuo prossimo articolo preferito...");
		this.add(searchTxtField, searchTxtField.getGbc());
	
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


}
