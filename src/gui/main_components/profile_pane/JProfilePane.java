package gui.main_components.profile_pane;

import controller.Controller;

import gui.preset.JButtonWithBorder;
import entities.annuncio.*;
import entities.enumerazioni.Stato;
import entities.offerta.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class JProfilePane extends JPanel {

    private static final long serialVersionUID = 1L;
	private ArrayList<Offerta> offerteInviate;
	private ArrayList<Offerta> offerteRicevute;


    // COSTRUTTORE
    public JProfilePane(ArrayList<Offerta> offerteInviate, ArrayList<Offerta> offerteRicevute,  Controller controller) {
        this.offerteInviate = offerteInviate;
        this.offerteRicevute = offerteRicevute;
		setLayout(new BorderLayout(0, 0));

        // Titolo
        JLabel title = new JLabel("📊 Report Offerte", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        // Contenitore principale dentro la scrollPane
        JPanel contentPanel = new JPanel(new BorderLayout());
        add(contentPanel);

        // Stats Panel con FlowLayout che va a capo automaticamente
        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        contentPanel.add(statsPanel, BorderLayout.CENTER);

        // Creazione grafici con JFreeChart
        JFreeChart sentOffersBarChart = ChartFactory.createBarChart(
                "Offerte inviate",
                "Tipologia",
                "Numero",
                sentOffers(),
                PlotOrientation.VERTICAL,
                true, false, false);
                
		CategoryPlot plot = (CategoryPlot) sentOffersBarChart.getPlot();
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		
        JFreeChart acceptedOffersBarChart = ChartFactory.createBarChart(
                "Offerta accettate",
                "Tipologia",
                "Numero",
                acceptedOffers(),
                PlotOrientation.VERTICAL,
                true, false, false);
               
        JFreeChart offersNumberPieChart = ChartFactory.createPieChart("Totale delle offerte", offersNumber(), true, true, false);
        
       


        // Chart 1
        ChartPanel sentOffersChartPanel = new ChartPanel(sentOffersBarChart);
        sentOffersChartPanel.setPreferredSize(new Dimension(400, 400));
        statsPanel.add(sentOffersChartPanel);
        
        
        // Chart 2
        ChartPanel acceptedOffersChartPanel = new ChartPanel(acceptedOffersBarChart);
        acceptedOffersChartPanel.setPreferredSize(new Dimension(400, 400));
        statsPanel.add(acceptedOffersChartPanel);
        
        // Chart 3
        
        
        statsPanel.add(sentOffersChartPanel);
        statsPanel.add(acceptedOffersChartPanel);

		 // --- SEZIONE GESTIONE  CREDENZIALE ---
        JPanel settingsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        settingsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.add(settingsPanel, BorderLayout.SOUTH);

       
        JPanel credentialsPanel = new JPanel();
        credentialsPanel.setBorder(BorderFactory.createTitledBorder("Gestione credenziali"));

        JButtonWithBorder changeEmailButton = new JButtonWithBorder("Cambia email");
        changeEmailButton.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		controller.openModificaEmailFrame();
        	}
        });
        
        JButtonWithBorder changeUsernameButton = new JButtonWithBorder("Cambia username");
        changeUsernameButton.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		controller.openModificaUsernameFrame();
        	}
        });
        
        JButtonWithBorder changePasswordButton = new JButtonWithBorder("Cambia password");
        changePasswordButton.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		controller.openModificaPasswordFrame(controller.getStudenteLoggato());
        	}
        });

        credentialsPanel.add(changeEmailButton);
        credentialsPanel.add(changeUsernameButton);
        credentialsPanel.add(changePasswordButton);
        

        // --- Sezione Azioni Importanti ---
        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));
        actionsPanel.setBorder(BorderFactory.createTitledBorder("Azioni importanti"));

        JButtonWithBorder logoutButton = new JButtonWithBorder("Logout");
        JButtonWithBorder deleteButton = new JButtonWithBorder("Elimina account");

        actionsPanel.add(logoutButton);
        actionsPanel.add(Box.createVerticalStrut(10));
        actionsPanel.add(deleteButton);

        // Aggiunta pannelli bottoni al settingsPanel
        settingsPanel.add(credentialsPanel);
        settingsPanel.add(actionsPanel);
    }

    // METODI
    private CategoryDataset sentOffers() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int numVendite = 0; 
        int numScambio = 0; 
        int numRegalo = 0; 
        
        for(Offerta o : offerteInviate) {
            Annuncio annuncio = o.getAnnuncio();
            switch(annuncio){
                case AnnuncioVendita av -> {numVendite++;}
                case AnnuncioScambio as -> {numScambio++;}
                case AnnuncioRegalo ar -> {numRegalo++;}
                default -> {}
            }
        }
        dataset.addValue(numVendite, "Offerte inviate", "Vendita");
        dataset.addValue(numScambio, "Offerte inviate", "Scambio");
        dataset.addValue(numRegalo, "Offerte inviate", "Regalo");

        return dataset;
    }
    
    private CategoryDataset acceptedOffers() {
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	int numVenditeRic = 0; 
    	int numScambioRic = 0; 
    	int numRegaloRic = 0; 
    	int numVenditeInv = 0; 
    	int numScambioInv = 0; 
    	int numRegaloInv = 0; 
		
		String inviate = "Offerte inviate accettate";
		String ricevute = "Offerte ricevute accettate";
	    
	    for(Offerta o : offerteRicevute) {
	    	if(o.getStato().equals(Stato.Accettata)) {
	    		Annuncio annuncio = o.getAnnuncio();
	    		switch(annuncio){
	    		case AnnuncioVendita av -> {numVenditeRic++;}
	    		case AnnuncioScambio as -> {numScambioRic++;}
	    		case AnnuncioRegalo ar -> {numRegaloRic++;}
	    		default -> {}
	    		}
	    	}
	    }
	    
	    for(Offerta o : offerteInviate) {
	    	if(o.getStato().equals(Stato.Accettata)) {
	    		Annuncio annuncio = o.getAnnuncio();
	    		switch(annuncio){
	    		case AnnuncioVendita av -> {numVenditeInv++;}
	    		case AnnuncioScambio as -> {numScambioInv++;}
	    		case AnnuncioRegalo ar -> {numRegaloInv++;}
	    		default -> {}
	    		}
	    	}
	     }
	    dataset.addValue(numVenditeInv, inviate, "Vendita");
	    dataset.addValue(numVenditeRic, ricevute, "Vendita");
	    dataset.addValue(numScambioInv, inviate, "Scambio");
	    dataset.addValue(numScambioRic, ricevute, "Scambio");
	    dataset.addValue(numRegaloInv, inviate, "Regalo");
	    dataset.addValue(numRegaloRic, ricevute, "Regalo");
	   
    	return dataset;
    }
    
    private PieDataset<Integer> offersNumber() {
    	DefaultPieDataset<Integer>  dataset = new DefaultPieDataset<Integer> ();

    	return dataset;
    }
    
    
}




