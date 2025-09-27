package gui.main_components.profile_pane;

import controller.Controller;
import entities.annuncio.*;
import entities.enumerazioni.Stato;
import entities.offerta.*;
import gui.preset.presetJPanel.JPanelWithBackground;
import gui.preset.presetJPanel.JPanelWithBorder;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
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
        
        JPanel upperPanel = new JProfilePaneUpperPanel(controller);
        add(upperPanel, BorderLayout.NORTH);
		
		JPanel lowerPanel = new JPanelWithBackground("src/img/scrollPanebackground.png");
		lowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		lowerPanel.setPreferredSize(new Dimension(1100, 500));
		lowerPanel.setMaximumSize(new Dimension(1100, 500));
		add(lowerPanel, BorderLayout.CENTER);

        // Creazione grafici con JFreeChart
        JFreeChart sentOffersBarChart = ChartFactory.createBarChart(
        		"Offerte inviate",
                "Tipologia",
                "Numero",
                sentOffers(),
                PlotOrientation.VERTICAL,
                true, false, false);
                
		CategoryPlot sentOffersBarChartPlot = (CategoryPlot) sentOffersBarChart.getPlot();
		NumberAxis rangeAxis_sentOffersBarChart = (NumberAxis) sentOffersBarChartPlot.getRangeAxis();
		rangeAxis_sentOffersBarChart.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		
        JFreeChart acceptedOffersBarChart = ChartFactory.createBarChart(
                "Offerta accettate",
                "Tipologia",
                "Numero",
                acceptedOffers(),
                PlotOrientation.VERTICAL,
                true, false, false);
        CategoryPlot acceptedOffersBarChartPlot = (CategoryPlot) acceptedOffersBarChart.getPlot();
        NumberAxis rangeAxis_acceptedOffersBarChart = (NumberAxis) acceptedOffersBarChartPlot.getRangeAxis();
        rangeAxis_acceptedOffersBarChart.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
               
        JFreeChart offersNumberPieChart = ChartFactory.createPieChart("Totale delle offerte", offersNumber(), true, true, false);
        PiePlot<?> offersNumberPieChartPlot = (PiePlot<?>) offersNumberPieChart.getPlot();
     
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
        NumberAxis domain = (NumberAxis) plot.getDomainAxis();
        domain.setTickLabelsVisible(false);

	    XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
	    plot.setSeriesRenderingOrder(org.jfree.chart.plot.SeriesRenderingOrder.FORWARD);

	    // Serie 0 = "allOffers" → SOLO punti
	    renderer.setSeriesPaint(0, Color.BLACK);
	    renderer.setSeriesLinesVisible(0, false);   // niente linea
	    renderer.setSeriesShapesVisible(0, true);   // solo punti
	
	    // Serie 1 = "media" → linea + punti
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
	    JPanel chartPanel1 = new JPanelWithBorder();
	    chartPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
	    chartPanel1.setPreferredSize(new Dimension(485, 295));
	    chartPanel1.setMaximumSize(new Dimension(485, 295));
	    chartPanel1.setBackground(new Color(255, 255, 255));
	    
	    ChartPanel sentOffersChartPanel = new ChartPanel(sentOffersBarChart);
	    sentOffersChartPanel.setPreferredSize(new Dimension(470, 290));
	    sentOffersChartPanel.setMaximumSize(new Dimension(470, 290));
        sentOffersChartPanel.setOpaque(false);
        
        chartPanel1.add(sentOffersChartPanel);
        lowerPanel.add(chartPanel1);
        

        // Chart 3
        JPanel chartPanel3 = new JPanelWithBorder();
        chartPanel3.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        chartPanel3.setPreferredSize(new Dimension(485, 295));
        chartPanel3.setMaximumSize(new Dimension(485, 295));
        chartPanel3.setBackground(new Color(255, 255, 255));
        
        ChartPanel offersNumberChartPanel = new ChartPanel(offersNumberPieChart);        
        offersNumberChartPanel.setPreferredSize(new Dimension(470, 290));
        offersNumberChartPanel.setMaximumSize(new Dimension(470, 290));
        offersNumberChartPanel.setOpaque(false);
        
        chartPanel3.add(offersNumberChartPanel);
        lowerPanel.add(chartPanel3);

        // Chart 2
        JPanel chartPanel2 = new JPanelWithBorder();
        chartPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        chartPanel2.setPreferredSize(new Dimension(485, 295));
        chartPanel2.setMaximumSize(new Dimension(485, 295));
        chartPanel2.setBackground(new Color(255, 255, 255));

        ChartPanel acceptedOffersChartPanel = new ChartPanel(acceptedOffersBarChart);
        acceptedOffersChartPanel.setPreferredSize(new Dimension(470, 290));
        acceptedOffersChartPanel.setMaximumSize(new Dimension(470, 290));
        acceptedOffersChartPanel.setOpaque(false);
        
        chartPanel2.add(acceptedOffersChartPanel);
        lowerPanel.add(chartPanel2);
        
        // Chart 4
        JPanel chartPanel4 = new JPanelWithBorder();
        chartPanel4.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        chartPanel4.setPreferredSize(new Dimension(1495, 295));
        chartPanel4.setMaximumSize(new Dimension(1495, 295));
        chartPanel4.setBackground(new Color(255, 255, 255));
        
        ChartPanel earningsTimeChartPanel = new ChartPanel(earningsTimeChart);
        earningsTimeChartPanel.setPreferredSize(new Dimension(1480, 290));
        earningsTimeChartPanel.setMaximumSize(new Dimension(1480, 290));
        earningsTimeChartPanel.setOpaque(false);
        
        chartPanel4.add(earningsTimeChartPanel);
        lowerPanel.add(chartPanel4);
                
    }

	
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
        boolean hasData = false;
    	
        // Min e max
        for (int i = 0; i < offerteRicevute.size(); i++) {
        	offerta = offerteRicevute.get(i);
            if(offerta instanceof OffertaDenaro o && o.getStato().equals(Stato.Accettata)) {
            	hasData = true;
            	if(o.getOfferta()<min) {
            		min=o.getOfferta();
            		minX=pos;
            		minY=o.getOfferta();
            	}
            	else if(o.getOfferta()>max) {
            		max=o.getOfferta();
            		maxX=pos;
            		maxY=o.getOfferta();
            	}
            	pos++;
            	
            	allOffers.add(i, o.getOfferta());	
            	totale += o.getOfferta();
            	media.add(i, totale / (i+1));
            }
        }    
        
        if(!hasData) {
        	XYSeries dummy_allOffers = new XYSeries( "Tutte le offerte" );   
        	XYSeries dummy_media = new XYSeries( "Media delle offerte" );                        
        	XYSeries dummy_minimo = new XYSeries( "l'offerta più bassa" );                        
        	XYSeries dummy_massimo = new XYSeries( "l'offerta più alta" );
        	dataset.addSeries(dummy_allOffers);
        	dataset.addSeries(dummy_media);
        	dataset.addSeries(dummy_minimo);
        	dataset.addSeries(dummy_massimo);
            return dataset;
        }
        
        minimo.add(minX, minY);
        massimo.add(maxX, maxY);
        
		dataset.addSeries(allOffers);
		dataset.addSeries(media);
		dataset.addSeries(minimo);
		dataset.addSeries(massimo);

        return dataset;
    }
    
}




