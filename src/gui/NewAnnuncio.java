package gui;

import controller.Controller;
import entities.enumerazioni.Sede;
import entities.enumerazioni.TipoAnnuncio;
import entities.enumerazioni.TipoOggetto;

import gui.preset.JButtonWithBorder;
import gui.preset.JCustomTextArea;
import gui.preset.presetJTextField.JCustomTextField;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class NewAnnuncio extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public NewAnnuncio(Controller controller) {
        this.setTitle("Nuovo Annuncio");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(100, 100, 500, 500);
        this.setResizable(false);
        this.setFocusable(true);
        this.setAlwaysOnTop(true);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);

        // Tipo Annuncio
        JPanel tipoAnnuncioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel tipoAnnuncioLabel = new JLabel("Tipo Annuncio:");
        JComboBox<TipoAnnuncio> tipoAnnuncioCombo = new JComboBox<>(TipoAnnuncio.values());
        tipoAnnuncioPanel.add(tipoAnnuncioLabel);
        tipoAnnuncioPanel.add(tipoAnnuncioCombo);
        contentPane.add(tipoAnnuncioPanel);
        
        // Tipo Oggetto
        JPanel tipoOggettoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel tipoOggettoLabel = new JLabel("Tipo Oggetto:");
        JComboBox<TipoOggetto> tipoOggettoCombo = new JComboBox<>(TipoOggetto.values());
        tipoOggettoPanel.add(tipoOggettoLabel);
        tipoOggettoPanel.add(tipoOggettoCombo);
        contentPane.add(tipoOggettoPanel);


        // Titolo
        JPanel titoloPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titoloLabel = new JLabel("Titolo:");
        JCustomTextField titoloField = new JCustomTextField("Inserisci il titolo");
        titoloField.setColumns(20);
        titoloPanel.add(titoloLabel);
        titoloPanel.add(titoloField);
        contentPane.add(titoloPanel);

        // Descrizione
        JPanel descrPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel descrLabel = new JLabel("Descrizione:");
        JTextArea descrPane = new JCustomTextArea("Inserisci la descrizione...");
        descrPane.setPreferredSize(new Dimension(300, 80));
        descrPanel.add(descrLabel);
        descrPanel.add(descrPane);
        contentPane.add(descrPanel);

        // Sede
        JPanel sedePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel sedeLabel = new JLabel("Luogo d'incontro:");
        JComboBox<Sede> sedeCombo = new JComboBox<>(Sede.values());
        sedePanel.add(sedeLabel);
        sedePanel.add(sedeCombo);
        contentPane.add(sedePanel);

        // Orario
        JPanel orarioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel orarioLabel = new JLabel("Orario:");
        JComboBox<String> orarioCombo = new JComboBox<String>(generaOrari());
        orarioPanel.add(orarioLabel);
        orarioPanel.add(orarioCombo);
        contentPane.add(orarioPanel);

        // Prezzo (solo per Vendita)
        JPanel prezzoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel prezzoLabel = new JLabel("Prezzo:");
        JCustomTextField prezzoField = new JCustomTextField("€");
        prezzoField.setColumns(10);
        prezzoPanel.add(prezzoLabel);
        prezzoPanel.add(prezzoField);
        prezzoPanel.setVisible(tipoAnnuncioCombo.getSelectedItem().equals("Vendita"));
        contentPane.add(prezzoPanel);

        tipoAnnuncioCombo.addActionListener(e -> {
            boolean isVendita = tipoAnnuncioCombo.getSelectedItem().equals("Vendita");
            prezzoPanel.setVisible(isVendita);
            contentPane.revalidate();
            contentPane.repaint();
        });

        // Bottone crea annuncio
        JButtonWithBorder creaBtn = new JButtonWithBorder("Crea Annuncio");
        creaBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(Box.createVerticalStrut(20));
        contentPane.add(creaBtn);
    }

    private String[] generaOrari() {
        String[] orari = new String[45];
        int idx = 0;
        for (int hour = 8; hour <= 19; hour++) {
            for (int min = 0; min < 60; min += 15) {
                if (hour == 19 && min > 0) break;
                String orario = String.format("%2d:%02d", hour, min);
                orari[idx++] = orario;
            }
        }
        
        return orari;
    }
}
