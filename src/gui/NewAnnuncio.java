package gui;

import gui.preset.JButtonWithBorder;
import gui.preset.presetJTextField.JCustomTextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import entities.enumerazioni.Sede;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

public class NewAnnuncio extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public NewAnnuncio(Controller controller) {
        setTitle("Nuovo Annuncio");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        setLocationRelativeTo(null); // Center the frame on the screen
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);

        // Tipo Annuncio
        JPanel tipoAnnuncio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel tipoAnnuncioLabel = new JLabel("Tipo Annuncio:");
        String[] tipi = {"Vendita", "Scambio", "Regalo"};
        JComboBox<String> tipoAnnuncioCombo = new JComboBox<>(tipi);
        tipoAnnuncio.add(tipoAnnuncioLabel);
        tipoAnnuncio.add(tipoAnnuncioCombo);
        contentPane.add(tipoAnnuncio);

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
        JTextArea descrPane = new JTextArea("Inserisci la descrizione");
        descrPane.setPreferredSize(new Dimension(300, 80));
        descrPanel.add(descrLabel);
        descrPanel.add(descrPane);
        contentPane.add(descrPanel);

        // Sede
        JPanel sedePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel sedeLabel = new JLabel("Luogo incontro:");
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
        ArrayList<String> orari = new ArrayList<String>();
        for (int h = 8; h <= 19; h++) {
            for (int m = 0; m < 60; m += 15) {
                if (h == 19 && m > 0) break;
                orari.add(String.format("%2d:%02d", h, m));
            }
        }
        return orari.toArray(new String[0]);
    }
}
