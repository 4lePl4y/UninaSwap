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
import java.awt.geom.Ellipse2D;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

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
                
		CategoryPlot sentOffersBarChartPlot = (CategoryPlot) sentOffersBarChart.getPlot();
		NumberAxis rangeAxis = (NumberAxis) sentOffersBarChartPlot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		
        JFreeChart acceptedOffersBarChart = ChartFactory.createBarChart(
                "Offerta accettate",
                "Tipologia",
                "Numero",
                acceptedOffers(),
                PlotOrientation.VERTICAL,
                true, false, false);
               
        JFreeChart offersNumberPieChart = ChartFactory.createPieChart("Totale delle offerte", offersNumber(), true, true, false);
        PiePlot offersNumberPieChartPlot = (PiePlot) offersNumberPieChart.getPlot();
     
        // {0} = nome categoria, {1} = valore, {2} = percentuale
        offersNumberPieChartPlot.setLabelGenerator(new StandardPieSectionLabelGenerator(
        		"{0}: {1}\n {2}"
        		));
     
        JFreeChart earningsTimeChart = ChartFactory.createXYLineChart(
        		"Guadagni",
        		"",
        		"offerta in denaro",
        		earningsDataset(),
        		PlotOrientation.VERTICAL,
        		true,
        		true,
        		true);
        
        XYPlot plot = earningsTimeChart.getXYPlot();
	    XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
	    plot.setSeriesRenderingOrder(org.jfree.chart.plot.SeriesRenderingOrder.FORWARD);

	    // Serie 0 = "allOffers" → SOLO punti
	    renderer.setSeriesPaint(0, Color.BLACK);
	    renderer.setSeriesLinesVisible(0, false);   // niente linea
	    renderer.setSeriesShapesVisible(0, true);   // solo punti
	
	    // Serie 1 = "media" → linea + punti (puoi personalizzare come vuoi)
	    renderer.setSeriesPaint(1, Color.GREEN);
	    renderer.setSeriesLinesVisible(1, true);
	    renderer.setSeriesShapesVisible(1, true);
	  
	    renderer.setSeriesShape(2, new Ellipse2D.Double(-5, -5, 10, 10));
	    renderer.setSeriesShapesVisible(2, true);
	    renderer.setSeriesLinesVisible(2, false);
	    renderer.setSeriesPaint(2, Color.BLUE);

	    // Massimo (serie 3)
	    renderer.setSeriesShape(3, new Ellipse2D.Double(-5, -5, 10, 10));
	    renderer.setSeriesShapesVisible(3, true);
	    renderer.setSeriesLinesVisible(3, false);
	    renderer.setSeriesPaint(3, Color.RED);

	    
	    plot.setRenderer(renderer);
	        
        
        // Chart 1
        ChartPanel sentOffersChartPanel = new ChartPanel(sentOffersBarChart);
        sentOffersChartPanel.setPreferredSize(new Dimension(400, 200));
        statsPanel.add(sentOffersChartPanel);
        
        
        // Chart 2
        ChartPanel acceptedOffersChartPanel = new ChartPanel(acceptedOffersBarChart);
        acceptedOffersChartPanel.setPreferredSize(new Dimension(400, 200));
        statsPanel.add(acceptedOffersChartPanel);
        
        // Chart 3
        ChartPanel offersNumberChartPanel = new ChartPanel(offersNumberPieChart);
        offersNumberChartPanel.setPreferredSize(new Dimension(400, 200));
        statsPanel.add(offersNumberChartPanel);
        
        // Chart 4
        ChartPanel earningsTimeChartPanel = new ChartPanel(earningsTimeChart);
        earningsTimeChartPanel.setPreferredSize(new Dimension(400, 200));
        statsPanel.add(earningsTimeChartPanel);
        
        statsPanel.add(sentOffersChartPanel);
        statsPanel.add(acceptedOffersChartPanel);
        statsPanel.add(offersNumberChartPanel);
        statsPanel.add(earningsTimeChartPanel);

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
		
		String inviate = "Offerte inviate";
		String ricevute = "Offerte ricevute";
	    
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
    
    private PieDataset<String> offersNumber() {
    	DefaultPieDataset<String> dataset = new DefaultPieDataset<String> ();
    	int numOfferteInviate = offerteInviate.size();
    	int numOfferteRicevute = offerteRicevute.size();
    	
    	dataset.setValue("Inviate", numOfferteInviate);
    	dataset.setValue("Ricevute", numOfferteRicevute);

    	return dataset;
    }
    
    private XYDataset earningsDataset() {
    	XYSeriesCollection dataset = new XYSeriesCollection();
    	XYSeries allOffers = new XYSeries( "Tutte le offerte" );   
    	XYSeries media = new XYSeries( "Media delle offerte" );                        
    	XYSeries minimo = new XYSeries( "l'offerta più bassa" );                        
    	XYSeries massimo = new XYSeries( "l'offerta più alta" );                        
        Offerta offerta = null;
        double min=9999.99, max=0;
        double minX=0, minY=0, maxX=0, maxY=0, pos=0;
        int totale = 0; 
    	
        // Min e max
        for (int i = 0; i < offerteRicevute.size(); i++) {
        	offerta = offerteRicevute.get(i);
            if(offerta instanceof OffertaDenaro o && o.getStato().equals(Stato.Accettata)) {
            	if(o.getOfferta()<min) {
            		min=o.getOfferta();
            		minX=pos;
            		minY=o.getOfferta();
            	}
            	if(o.getOfferta()>max) {
            		max=o.getOfferta();
            		maxX=pos;
            		maxY=o.getOfferta();
            	}
            	allOffers.add(i, o.getOfferta());
            	pos++;
            }
        }    	
        
        minimo.add(minX, minY);
        massimo.add(maxX, maxY);
        
        
        // Media 
		for(int i = 0; i < offerteRicevute.size(); i++){
            offerta = offerteRicevute.get(i);
            if(offerta instanceof OffertaDenaro o){
            	totale += o.getOfferta();
            	media.add(i, totale / (i+1));
            }
        }		
		
		dataset.addSeries(allOffers);
		dataset.addSeries(media);
		dataset.addSeries(minimo);
		dataset.addSeries(massimo);

        return dataset;
    }
    
}




