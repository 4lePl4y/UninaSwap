package gui.main_components.profile_pane;

import controller.Controller;
import entities.annuncio.*;
import entities.enumerazioni.Stato;
import entities.offerta.*;
import gui.preset.presetJButton.JButtonWithBorder;
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
    private JProfilePaneUpperPanel upperPanel;

    public JProfilePane(ArrayList<Offerta> offerteInviate, ArrayList<Offerta> offerteRicevute, Controller controller) {
        this.offerteInviate = offerteInviate;
        this.offerteRicevute = offerteRicevute;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // --- PANNELLO SUPERIORE ---
        upperPanel = new JProfilePaneUpperPanel(controller);
        this.add(upperPanel, BorderLayout.NORTH);
        
        
        // --- CONTENITORE PRINCIPALE ---
        JPanel mainPanel = new JPanelWithBackground("src/img/scrollPanebackground.png");
        mainPanel.setLayout(new BorderLayout(20, 0));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.add(mainPanel, BorderLayout.CENTER);
        
        // --- SEZIONE SINISTRA: GESTIONE PROFILO ---
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);
        JPanel contentPanel = new JPanelWithBorder();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setPreferredSize(new Dimension(300, 400));
        contentPanel.setMaximumSize(new Dimension(300, 400));
        leftPanel.add(contentPanel);
        
        JLabel profileLabel = new JLabel("GESTIONE PROFILO", SwingConstants.CENTER);
        profileLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        profileLabel.setFont(new Font("Montserrat", Font.BOLD, 20));
        JButton changeEmail = new JButtonWithBorder("Cambia email", Controller.APP_BLUE);
        JButton changeUsername = new JButtonWithBorder("Cambia username", Controller.APP_BLUE);
        JButton changePassword = new JButtonWithBorder("Cambia password", Controller.APP_BLUE);
        JButton deleteAccount = new JButtonWithBorder("Elimina account", Controller.APP_RED);
        JButton logout = new JButtonWithBorder("Logout", Controller.APP_BLUE);

        // Stile
        for (JButton b : new JButton[]{changeEmail, changeUsername, changePassword, deleteAccount, logout}) {
        	b.setPreferredSize(new Dimension(200, 35));
            b.setMaximumSize(new Dimension(200, 35));
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
    		b.setHorizontalAlignment(SwingConstants.CENTER);
        }
        
        // Bottoni -> funzioni del controller
        changeEmail.addActionListener(e -> controller.openModificaEmailFrame());
        changeUsername.addActionListener(e -> controller.openModificaUsernameFrame());
        changePassword.addActionListener(e -> controller.openModificaPasswordFrame());
        deleteAccount.addActionListener(e -> controller.onEliminaAccountClicked());
        logout.addActionListener(e -> controller.onLogoutClicked());

        
        // Aggiunta bottoni
        contentPanel.add(Box.createVerticalGlue());
        contentPanel.add(profileLabel);
        contentPanel.add(Box.createVerticalStrut(40));
        contentPanel.add(changeEmail);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(changeUsername);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(changePassword);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(deleteAccount);
        contentPanel.add(Box.createVerticalStrut(40));
        contentPanel.add(logout);
        contentPanel.add(Box.createVerticalGlue());    

        // --- SEZIONE DESTRA: DASHBOARD STATISTICHE ---
        JPanel rightPanel = new JPanelWithBorder();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(Color.WHITE);
        JLabel statsLabel = new JLabel("DASHBOARD STATISTICHE", SwingConstants.CENTER);
        statsLabel.setFont(new Font("Montserrat", Font.BOLD, 20));
        rightPanel.add(statsLabel, BorderLayout.NORTH);

        JPanel chartsGrid = new JPanel(new GridLayout(2, 2, 10, 10));
        chartsGrid.setBackground(Color.WHITE);
        chartsGrid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crea e aggiungi i grafici
        chartsGrid.add(createChartPanel(sentOffersBarChart(), "Offerte inviate"));
        chartsGrid.add(createChartPanel(acceptedOffersBarChart(), "Offerta accettate"));
        chartsGrid.add(createChartPanel(offersNumberPieChart(), "Totale delle offerte"));
        chartsGrid.add(createChartPanel(earningsTimeChart(), "Guadagni"));

        rightPanel.add(chartsGrid, BorderLayout.CENTER);

        // --- Aggiungi le sezioni al main panel ---
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);
    }

    // Funzione ausiliaria per creare pannello grafico con titolo
    private JPanel createChartPanel(JFreeChart chart, String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 250));
        chartPanel.setOpaque(false);
        panel.add(chartPanel, BorderLayout.CENTER);
        return panel;
    }

    // --- GRAFICI ---
    private JFreeChart sentOffersBarChart() {
        JFreeChart chart = ChartFactory.createBarChart(
                "Offerte inviate",
                "Tipologia",
                "Numero",
                sentOffers(),
                PlotOrientation.VERTICAL,
                false, false, false
        );
        CategoryPlot plot = chart.getCategoryPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        return chart;
    }

    private JFreeChart acceptedOffersBarChart() {
        JFreeChart chart = ChartFactory.createBarChart(
                "Offerta accettate",
                "Tipologia",
                "Numero",
                acceptedOffers(),
                PlotOrientation.VERTICAL,
                false, false, false
        );
        CategoryPlot plot = chart.getCategoryPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        return chart;
    }

    private JFreeChart offersNumberPieChart() {
        JFreeChart chart = ChartFactory.createPieChart(
                "Totale delle offerte",
                offersNumber(),
                false, true, false
        );
        PiePlot<?> plot = (PiePlot<?>) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}\n{2}"));
        return chart;
    }

    private JFreeChart earningsTimeChart() {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Guadagni",
                "",
                "offerta in denaro",
                earningsDataset(),
                PlotOrientation.VERTICAL,
                false, true, true
        );

        XYPlot plot = chart.getXYPlot();
        NumberAxis domain = (NumberAxis) plot.getDomainAxis();
        domain.setTickLabelsVisible(false);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLACK);
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesPaint(1, Color.GREEN);
        renderer.setSeriesLinesVisible(1, true);
        renderer.setSeriesShapesVisible(1, true);
        renderer.setSeriesShape(2, new Ellipse2D.Double(-5, -5, 10, 10));
        renderer.setSeriesPaint(2, Color.BLUE);
        renderer.setSeriesShapesVisible(2, true);
        renderer.setSeriesLinesVisible(2, false);
        renderer.setSeriesShape(3, new Ellipse2D.Double(-5, -5, 10, 10));
        renderer.setSeriesPaint(3, Color.RED);
        renderer.setSeriesShapesVisible(3, true);
        renderer.setSeriesLinesVisible(3, false);

        plot.setRenderer(renderer);
        return chart;
    }

    // --- DATASET ---
    private CategoryDataset sentOffers() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int numVendite = 0, numScambio = 0, numRegalo = 0;
        for (Offerta o : offerteInviate) {
            Annuncio a = o.getAnnuncio();
            switch (a) {
                case AnnuncioVendita av -> numVendite++;
                case AnnuncioScambio as -> numScambio++;
                case AnnuncioRegalo ar -> numRegalo++;
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
        int numVenditeInv = 0, numScambioInv = 0, numRegaloInv = 0;
        int numVenditeRic = 0, numScambioRic = 0, numRegaloRic = 0;
        String inviate = "Offerte inviate", ricevute = "Offerte ricevute";

        for (Offerta o : offerteRicevute) {
            if (o.getStato().equals(Stato.Accettata)) {
                Annuncio a = o.getAnnuncio();
                switch (a) {
                    case AnnuncioVendita av -> numVenditeRic++;
                    case AnnuncioScambio as -> numScambioRic++;
                    case AnnuncioRegalo ar -> numRegaloRic++;
                    default -> {}
                }
            }
        }
        for (Offerta o : offerteInviate) {
            if (o.getStato().equals(Stato.Accettata)) {
                Annuncio a = o.getAnnuncio();
                switch (a) {
                    case AnnuncioVendita av -> numVenditeInv++;
                    case AnnuncioScambio as -> numScambioInv++;
                    case AnnuncioRegalo ar -> numRegaloInv++;
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
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        dataset.setValue("Inviate", offerteInviate.size());
        dataset.setValue("Ricevute", offerteRicevute.size());
        return dataset;
    }

    private XYDataset earningsDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries allOffers = new XYSeries("Tutte le offerte");
        XYSeries media = new XYSeries("Media delle offerte");
        XYSeries minimo = new XYSeries("l'offerta più bassa");
        XYSeries massimo = new XYSeries("l'offerta più alta");

        double min = 9999.99, max = 0;
        double minX = 0, minY = 0, maxX = 0, maxY = 0, pos = 0;
        int totale = 0;
        boolean hasData = false;

        for (int i = 0; i < offerteRicevute.size(); i++) {
            Offerta offerta = offerteRicevute.get(i);
            if (offerta instanceof OffertaDenaro o && o.getStato().equals(Stato.Accettata)) {
                hasData = true;
                if (o.getOfferta() < min) { min = o.getOfferta(); minX = pos; minY = o.getOfferta(); }
                if (o.getOfferta() > max) { max = o.getOfferta(); maxX = pos; maxY = o.getOfferta(); }
                pos++;
                allOffers.add(i, o.getOfferta());
                totale += o.getOfferta();
                media.add(i, totale / (i + 1));
            }
        }

        if (!hasData) {
            dataset.addSeries(new XYSeries("Tutte le offerte"));
            dataset.addSeries(new XYSeries("Media delle offerte"));
            dataset.addSeries(new XYSeries("l'offerta più bassa"));
            dataset.addSeries(new XYSeries("l'offerta più alta"));
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
