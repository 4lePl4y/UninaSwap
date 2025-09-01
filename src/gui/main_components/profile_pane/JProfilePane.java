package gui.main_components.profile_pane;

import controller.Controller;

import gui.preset.JButtonWithBorder;
import entities.annuncio.*;
import entities.offerta.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class JProfilePane extends JPanel {

    private static final long serialVersionUID = 1L;
	private ArrayList<Offerta> offerteInviate;


    // COSTRUTTORE
    public JProfilePane(ArrayList<Offerta> offerteInviate, Controller controller) {
        this.offerteInviate = offerteInviate;
		setLayout(new BorderLayout(0, 0));

        // Titolo
        JLabel title = new JLabel("📊 Report Offerte", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        // ScrollPane principale
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        add(scrollPane, BorderLayout.CENTER);

        // Contenitore principale dentro la scrollPane
        JPanel contentPanel = new JPanel(new BorderLayout());
        scrollPane.setViewportView(contentPanel);

        // Stats Panel con FlowLayout che va a capo automaticamente
        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        contentPanel.add(statsPanel, BorderLayout.CENTER);

        // Creazione grafici con JFreeChart
        JFreeChart barChart = ChartFactory.createBarChart(
                "Offerte inviate",
                "Tipologia",
                "Numero",
                createDataSet(),
                PlotOrientation.VERTICAL,
                true, false, false);

        JFreeChart barChartOfferteAccettate = ChartFactory.createBarChart(
                "Distribuzione offerte ricevute e accettate",
                "Tipologia",
                "Numero",
                createDataSet(),
                PlotOrientation.VERTICAL,
                true, false, false);

        // Chart 1
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(700, 500));
        statsPanel.add(chartPanel);

        // Chart 2
        ChartPanel chartPanelOfferteAccettate = new ChartPanel(barChartOfferteAccettate);
        chartPanelOfferteAccettate.setPreferredSize(new Dimension(700, 500));
        statsPanel.add(chartPanelOfferteAccettate);

        // Settings Panel (sempre sotto ai grafici)
        JPanel settingsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        settingsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.add(settingsPanel, BorderLayout.SOUTH);

        // --- Sezione Gestione Credenziali ---
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
    private CategoryDataset createDataSet() {
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
}

