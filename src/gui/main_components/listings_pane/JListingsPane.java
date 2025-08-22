package gui.main_components.listings_pane;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Controller;
import entities.annuncio.Annuncio;
import gui.preset.JCustomScrollPane;

public class JListingsPane extends JPanel {

	private static final long serialVersionUID = 1L;
	private JListingsUpperPanel listingsUpperPanel;
	private JScrollPane listingsScrollPane;
	
	public JListingsPane(ArrayList<Annuncio> mieiAnnunci, Controller controller){
		this.setLayout(new BorderLayout(0, 0)); 
		listingsUpperPanel = new JListingsUpperPanel(controller);
		listingsScrollPane = new JCustomScrollPane<Annuncio>(mieiAnnunci, controller);
		this.add(listingsUpperPanel, BorderLayout.NORTH);
		this.add(listingsScrollPane, BorderLayout.CENTER);
	}

}
