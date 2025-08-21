package gui.main_components.browse_pane;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Controller;
import entities.annuncio.Annuncio;
import gui.preset.JCustomScrollPane;

public class JBrowsePane extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFilterNSearchPane filterNSearchPane;
	private JScrollPane browseScrollPane;
	
	public JBrowsePane(ArrayList<Annuncio> annunciPubblicati, Controller controller){
		this.setLayout(new BorderLayout(0, 0)); 
		filterNSearchPane = new JFilterNSearchPane();
		browseScrollPane = new JCustomScrollPane<Annuncio>(annunciPubblicati, controller);
		this.add(filterNSearchPane, BorderLayout.NORTH);
		this.add(browseScrollPane, BorderLayout.CENTER);
	}
}
