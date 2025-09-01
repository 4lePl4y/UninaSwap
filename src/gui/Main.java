package gui;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Color;

import controller.Controller;
import entities.annuncio.*;
import entities.oggetto.*;
import entities.studente.*;
import entities.offerta.*;
import gui.main_components.browse_pane.JBrowsePane;
import gui.main_components.listings_pane.JListingsPane;
import gui.main_components.my_objects_pane.JMyObjectsPane;
import gui.main_components.offers_pane.JOffersPane;
import gui.main_components.profile_pane.JProfilePane;
import gui.preset.JButtonWithBorder;
import gui.preset.JButtonClickManager;

public class Main extends JFrame {
	//ATTRIBUTI
	private Controller controller;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel buttonPane;
	private Studente studenteLoggato;
	private ArrayList<Annuncio> altriAnnunci;
	private ArrayList<Annuncio> mieiAnnunci;
	private ArrayList<Oggetto> mieiOggetti;
	private ArrayList<Offerta> offerteRicevute;
	private ArrayList<Offerta> offerteInviate;
	private JBrowsePane browsePane;
	private JListingsPane listingsPane;
	private JMyObjectsPane myObjectsPane;
	private JOffersPane offersPane;
	private JProfilePane profilePane;
		

	//COSTRUTTORE
	public Main(Controller controller) {
		this.controller = controller;
		this.studenteLoggato = controller.getStudenteLoggato();
		this.altriAnnunci = controller.getAltriAnnunci(40, studenteLoggato.getUsername());
		this.mieiOggetti = controller.getMieiOggetti(this.studenteLoggato.getUsername()); 
		this.mieiAnnunci = new ArrayList<Annuncio>();
		this.offerteRicevute = new ArrayList<Offerta>();
		this.offerteInviate = new ArrayList<Offerta>();
		
		setTitle("UninaSwap");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 650);	
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		// Content panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.setFocusable(true);
		getContentPane().add(contentPane, BorderLayout.CENTER);
		
		this.browsePane = new JBrowsePane(this.altriAnnunci, controller);
		contentPane.add(browsePane, "BROWSE");
		
        this.listingsPane = new JListingsPane(this.mieiAnnunci, controller);
		contentPane.add(listingsPane, "LISTINGS");
		
		this.offersPane = new JOffersPane(this.offerteRicevute, this.offerteInviate, controller);
		contentPane.add(offersPane, "OFFERS");
		
		this.myObjectsPane = new JMyObjectsPane(this.mieiOggetti, controller);
		contentPane.add(myObjectsPane, "MYOBJECTS");
		
		refreshAllOffers();
		this.profilePane = new JProfilePane(this.offerteInviate, controller);
		contentPane.add(profilePane, "PROFILE");
		
		// Button panel per scegliere le finestre
        buttonPane = new JPanel();
        buttonPane.setBackground(new Color(255, 255, 255));
        FlowLayout fl_buttonPane = (FlowLayout) buttonPane.getLayout();
        fl_buttonPane.setVgap(10);
        fl_buttonPane.setHgap(50);
        
        JButton browseButton = new JButtonWithBorder("Browse");
        browseButton.addActionListener( e -> {
        	CardLayout cl = (CardLayout)(contentPane.getLayout());
			cl.show(contentPane, "BROWSE");
		});

        JButtonClickManager listingsButton = new JButtonClickManager("My Listings");
        listingsButton.addActionListener(e -> {
        	if(listingsButton.isFirstClick())
    			refreshListings();
        	
    		CardLayout cl = (CardLayout)(contentPane.getLayout());
    		cl.show(contentPane, "LISTINGS");
        });
        
        
        JButtonWithBorder myObjectsButton = new JButtonWithBorder("My Objects");
        myObjectsButton.addActionListener(e -> {
    		CardLayout cl = (CardLayout)(contentPane.getLayout());
    		cl.show(contentPane, "MYOBJECTS");
        });
        
        JButtonClickManager offersButton = new JButtonClickManager("Offers");
        offersButton.addActionListener(e -> {
        	if(offersButton.isFirstClick()) 
        		refreshAllOffers();
        	
        	CardLayout cl = (CardLayout)(contentPane.getLayout());
			cl.show(contentPane, "OFFERS");
		});
        
        JButton profileButton = new JButtonWithBorder("Profilo");
        profileButton.addActionListener(e -> {
        	//TODO: refreshStats(); 
			CardLayout cl = (CardLayout)(contentPane.getLayout());
			cl.show(contentPane, "PROFILE");
		});
        
        buttonPane.add(browseButton);
        buttonPane.add(listingsButton);
        buttonPane.add(offersButton);
        buttonPane.add(myObjectsButton);
        buttonPane.add(profileButton);
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
    }

	//METODI
	public ArrayList<Oggetto>getMieiOggetti() {
		if (mieiOggetti == null) {
			mieiOggetti = controller.getMieiOggetti(studenteLoggato.getUsername());
		}
		return mieiOggetti;
	}
	
	public void refreshBrowse() {
		this.altriAnnunci = controller.getAltriAnnunci(40, studenteLoggato.getUsername());
		browsePane.refresh(altriAnnunci);
	}
	
	public void refreshBrowseForResearch() {
		String research = browsePane.getSearchBarText();
		boolean[] object_filters = browsePane.getFiltersForObjectType();
		boolean[] listings_filters = browsePane.getFiltersForListings();
		this.altriAnnunci = controller.getAltriAnnunciByRicerca(studenteLoggato.getUsername(), research, object_filters, listings_filters);
		browsePane.refresh(altriAnnunci);
	}
	
	public void refreshListings() {
		this.mieiAnnunci = controller.getMieiAnnunci(studenteLoggato.getUsername());		
		listingsPane.refresh(mieiAnnunci);
	}
	
	public void refreshMyObjects() {
		this.mieiOggetti = controller.getMieiOggetti(studenteLoggato.getUsername());
		myObjectsPane.refresh(mieiOggetti);
	}
	
	public void refreshAllOffers() {
		this.offerteRicevute = controller.getOfferteRicevute(studenteLoggato.getUsername());
		this.offerteInviate = controller.getOfferteInviate(studenteLoggato.getUsername());
		offersPane.refresh(offerteRicevute, offerteInviate);
	}
	
	public void refreshReceivedOffers() {
		this.offerteRicevute = controller.getOfferteRicevute(studenteLoggato.getUsername());
		offersPane.refreshOfferteRicevute(offerteRicevute);
	}
	
	public void refreshMadeOffers() {
		this.offerteInviate = controller.getOfferteInviate(studenteLoggato.getUsername());
		offersPane.refreshOfferteInviate(offerteInviate);
	}
	
	
}