package gui.main_components.profile_pane;

import controller.Controller;
import entities.enumerazioni.Stato;
import entities.offerta.*;
import gui.preset.JButtonWithBorder;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class JProfilePane extends JPanel {

    private static final long serialVersionUID = 1L;

    public JProfilePane(Controller controller, ArrayList<Offerta> offerteInviate) {
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
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        scrollPane.setViewportView(contentPanel);

        // ==========================
        // Pannello statistiche
        // ==========================
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBackground(Color.LIGHT_GRAY); // solo per test
        contentPanel.add(statsPanel);

        // Computazione delle statistiche
       

        // Computazione min, max, avg per vendite accettate
        

        // Aggiunta testo report
        JTextArea reportText = new JTextArea();
        reportText.setEditable(false);
        reportText.setFont(new Font("SansSerif", Font.PLAIN, 14));
        reportText.setText("Report Sintetico Offerte:\n\n");

        reportText.append("Numero totale di offerte inviate:\n");

        reportText.append("\nNumero di offerte accettate per tipologia:\n");

        reportText.append("\nPer annunci di vendita accettati:\n");
        

        statsPanel.add(new JScrollPane(reportText));

        // Creazione grafico con JFreeChart (bar chart per totali e accettate per tipologia)
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        JFreeChart barChart = ChartFactory.createBarChart(
                "Distribuzione Offerte per Tipologia",
                "Tipologia",
                "Numero",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(600, 400));
        chartPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statsPanel.add(Box.createVerticalStrut(20));
        statsPanel.add(chartPanel);

        // ==========================
        // Pannello impostazioni con FlowLayout
        // ==========================
        JPanel settingsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        settingsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- Sezione Gestione Credenziali ---
        JPanel credentialsPanel = new JPanel();
        credentialsPanel.setLayout(new BoxLayout(credentialsPanel, BoxLayout.Y_AXIS));
        credentialsPanel.setBorder(BorderFactory.createTitledBorder("Gestione credenziali"));

        JButtonWithBorder changePasswordButton = new JButtonWithBorder("Cambia password");
        JButtonWithBorder changeEmailButton = new JButtonWithBorder("Cambia email / username");
        JButtonWithBorder editPersonalDataButton = new JButtonWithBorder("Modifica dati personali");

        credentialsPanel.add(changePasswordButton);
        credentialsPanel.add(Box.createVerticalStrut(10));
        credentialsPanel.add(changeEmailButton);
        credentialsPanel.add(Box.createVerticalStrut(10));
        credentialsPanel.add(editPersonalDataButton);

        // --- Sezione Azioni Importanti ---
        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));
        actionsPanel.setBorder(BorderFactory.createTitledBorder("Azioni importanti"));

        JButtonWithBorder logoutButton = new JButtonWithBorder("Logout");
        JButtonWithBorder deleteButton = new JButtonWithBorder("Elimina account");

        actionsPanel.add(logoutButton);
        actionsPanel.add(Box.createVerticalStrut(10));
        actionsPanel.add(deleteButton);

        // Aggiunta pannelli al FlowLayout (affiancati se larghi, sotto se stretti)
        settingsPanel.add(credentialsPanel);
        settingsPanel.add(actionsPanel);

        // Aggiungiamo il settingsPanel al contentPanel (scrollabile)
        contentPanel.add(Box.createVerticalStrut(30));
        contentPanel.add(settingsPanel);
    }
}