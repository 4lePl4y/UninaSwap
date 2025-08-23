package gui.main_components.offers_pane;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import controller.Controller;
import entities.offerta.Offerta;
import gui.preset.JCustomScrollPane;

public class JOffersPane extends JPanel{
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private JOffersUpperPanel offersUpperPanel;
	private JSplitPane scrollPanesContainer;
	private JCustomScrollPane<Offerta> receivedOffersScrollPane;
	private JCustomScrollPane<Offerta> madeOffersScrollPane;
	
	public JOffersPane(ArrayList<Offerta> offerteRicevute, ArrayList<Offerta> offerteFatte, Controller controller) {
		this.controller = controller;
		this.setLayout(new BorderLayout(0, 0)); 
		offersUpperPanel = new JOffersUpperPanel(controller);
		receivedOffersScrollPane = new JCustomScrollPane<Offerta>(offerteRicevute, controller);
		madeOffersScrollPane = new JCustomScrollPane<Offerta>(offerteFatte, controller);
		scrollPanesContainer = new JSplitPane (JSplitPane.HORIZONTAL_SPLIT, receivedOffersScrollPane, madeOffersScrollPane);
		scrollPanesContainer.setBounds(0, 0, this.getWidth(), this.getHeight());
		scrollPanesContainer.setEnabled(false);
		scrollPanesContainer.setDividerSize(0);
		scrollPanesContainer.setDividerLocation(540);
		this.setVisible(true);
		scrollPanesContainer.setResizeWeight(0.5);
		
		this.add(offersUpperPanel, BorderLayout.NORTH);
		this.add(scrollPanesContainer, BorderLayout.CENTER);
	}
	
	//**Metodo per aggiornare la lista di offerte ricevute*/
	public void refreshOfferteRicevute(ArrayList<Offerta> offerteRicevute) {
		scrollPanesContainer.remove(receivedOffersScrollPane);
		receivedOffersScrollPane = new JCustomScrollPane<Offerta>(offerteRicevute, controller);
		scrollPanesContainer.add(receivedOffersScrollPane);
		scrollPanesContainer.revalidate();
		scrollPanesContainer.repaint();
	}
	
	//**Metodo per aggiornare la lista di offerte fatte*/
	public void refreshOfferteFatte(ArrayList<Offerta> offerteFatte) {
		scrollPanesContainer.remove(madeOffersScrollPane);
		madeOffersScrollPane = new JCustomScrollPane<Offerta>(offerteFatte, controller);
		scrollPanesContainer.add(madeOffersScrollPane);
		scrollPanesContainer.revalidate();
		scrollPanesContainer.repaint();
	}
	
	//**Metodo per aggiornare sia la lista di offerte ricevute che fatte*/
	public void refresh(ArrayList<Offerta> offerteRicevute, ArrayList<Offerta> offerteFatte) {
		refreshOfferteRicevute(offerteRicevute);
		refreshOfferteFatte(offerteFatte);
	}
}
