package gui;

import controller.Controller;
import entities.annuncio.*;
import entities.enumerazioni.Sede;
import entities.enumerazioni.TipoAnnuncio;
import entities.oggetto.Oggetto;
import entities.studente.Studente;
import gui.preset.JButtonWithBorder;
import gui.preset.JCustomTextArea;
import gui.preset.presetJTextField.JCustomTextField;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class NewAnnuncio extends JFrame {
    private static final long serialVersionUID = 1L;
    private Controller controller;
    
    private JPanel contentPane;
    private JCustomTextField titoloAnnuncioField;
    private JCustomTextArea descrPane;
    private JComboBox<TipoAnnuncio> tipoAnnuncioCombo;
    private JComboBox<Oggetto> oggettiEsistentiCombo;
    private  JComboBox<Sede> sedeCombo;
    private JComboBox<String> orarioCombo;
    private JCustomTextField prezzoField;

    public NewAnnuncio(Controller controller) {
        this.controller = controller;
    	
    	this.setTitle("Nuovo Annuncio");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(100, 100, 600, 500);
        this.setResizable(false);
        this.setFocusable(true);
        this.setAlwaysOnTop(true);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);
        
        // Titolo Annuncio
        JPanel titoloAnnuncioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titoloAnnuncioLabel = new JLabel("Titolo:");
        titoloAnnuncioField = new JCustomTextField("Inserisci il titolo dell'annuncio");
        titoloAnnuncioField.setColumns(20);
        titoloAnnuncioPanel.add(titoloAnnuncioLabel);
        titoloAnnuncioPanel.add(titoloAnnuncioField);
        contentPane.add(titoloAnnuncioPanel);

        // Descrizione
        JPanel descrPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel descrLabel = new JLabel("Descrizione:");
        descrPane = new JCustomTextArea("Inserisci la descrizione...");
        descrPane.setPreferredSize(new Dimension(300, 80));
        descrPanel.add(descrLabel);
        descrPanel.add(descrPane);
        contentPane.add(descrPanel);

        // Tipo Annuncio
        JPanel tipoAnnuncioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel tipoAnnuncioLabel = new JLabel("Tipo Annuncio:");
        tipoAnnuncioCombo = new JComboBox<>(TipoAnnuncio.values());
        tipoAnnuncioPanel.add(tipoAnnuncioLabel);
        tipoAnnuncioPanel.add(tipoAnnuncioCombo);
        contentPane.add(tipoAnnuncioPanel);
        
        // Aggiungi oggetto esistente o crea nuovo oggetto
        JPanel aggiungiOggettoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel oggettiEsistentiLabel = new JLabel("Scegli dai tuoi oggetti:");
        ArrayList<Oggetto> mieiOggetti = controller.getMieiOggetti();
        Oggetto[] oggettiArray = new Oggetto[mieiOggetti.size() + 1];
        oggettiArray[0] = null; 
        for(int i = 0; i < mieiOggetti.size(); i++) {
			oggettiArray[i + 1] = mieiOggetti.get(i);
		}
        oggettiEsistentiCombo = new JComboBox<>(oggettiArray);
        aggiungiOggettoPanel.add(oggettiEsistentiLabel);
        aggiungiOggettoPanel.add(oggettiEsistentiCombo);
        
        JLabel optionLabel = new JLabel("Oppure");
        aggiungiOggettoPanel.add(optionLabel);
        
        JButtonWithBorder creaOggettoBtn = new JButtonWithBorder("Crea Nuovo Oggetto");
        creaOggettoBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.onCreaOggettoFromAnnuncioClicked();
			}
        });
        aggiungiOggettoPanel.add(creaOggettoBtn);
        contentPane.add(aggiungiOggettoPanel);

        // Sede
        JPanel sedePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel sedeLabel = new JLabel("Luogo d'incontro:");
        sedeCombo = new JComboBox<>(Sede.values());
        sedePanel.add(sedeLabel);
        sedePanel.add(sedeCombo);
        contentPane.add(sedePanel);

        // Orario
        JPanel orarioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel orarioLabel = new JLabel("Orario:");
        orarioCombo = new JComboBox<>(generaOrari());
        orarioPanel.add(orarioLabel);
        orarioPanel.add(orarioCombo);
        contentPane.add(orarioPanel);

        // Prezzo (solo per Vendita)
        JPanel prezzoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel prezzoLabel = new JLabel("Prezzo:");
        prezzoField = new JCustomTextField("€");
        prezzoField.setColumns(10);
        prezzoPanel.add(prezzoLabel);
        prezzoPanel.add(prezzoField);
        prezzoPanel.setVisible(tipoAnnuncioCombo.getSelectedItem() == TipoAnnuncio.Vendita);
        contentPane.add(prezzoPanel);

        // Listeners
        tipoAnnuncioCombo.addActionListener(e -> {
            boolean isVendita = tipoAnnuncioCombo.getSelectedItem() == TipoAnnuncio.Vendita;
            prezzoPanel.setVisible(isVendita);
            contentPane.revalidate();
            contentPane.repaint();
        });


        // Bottone crea annuncio
        JButtonWithBorder creaBtn = new JButtonWithBorder("Crea Annuncio");
        creaBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        creaBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.onCreaAnnuncioClicked();
			}
		});
        contentPane.add(Box.createVerticalStrut(20));
        contentPane.add(creaBtn);
    }
    
    
    // METODI
    private String[] generaOrari() {
        String[] orari = new String[45];
        int idx = 0;
        for (int hour = 8; hour <= 19; hour++) {
            for (int min = 0; min < 60; min += 15) {
                if (hour == 19 && min > 0) break;
                String orario = String.format("%02d:%02d", hour, min);
                orari[idx++] = orario;
            }
        }
        return orari;
    }


	public void onCreaAnnuncioClicked() {
		String titolo = this.titoloAnnuncioField.getText();
		Studente autore = this.controller.getStudenteLoggato();
		Oggetto oggettoSelezionato = (Oggetto) this.oggettiEsistentiCombo.getSelectedItem();
		String descrizione = this.descrPane.getText();
		Sede luogo = (Sede) this.sedeCombo.getSelectedItem();
		LocalTime oraIncontro = LocalTime.parse((String) this.orarioCombo.getSelectedItem());
		LocalDate dataPubblicazione = LocalDate.now();
		
		TipoAnnuncio tipo = (TipoAnnuncio) this.tipoAnnuncioCombo.getSelectedItem();
		Annuncio annuncio = null;
		switch (tipo) {
			case Vendita: 
				double prezzo = Double.valueOf(prezzoField.getText());
				annuncio = new AnnuncioVendita(titolo, autore, oggettoSelezionato, descrizione, luogo, oraIncontro, dataPubblicazione, prezzo);
				break;
				
			case Scambio:
				annuncio = new AnnuncioScambio(titolo, autore, oggettoSelezionato, descrizione, luogo, oraIncontro, dataPubblicazione);
				break;
				
			case Regalo:
				annuncio = new AnnuncioRegalo(titolo, autore, oggettoSelezionato, descrizione, luogo, oraIncontro, dataPubblicazione);
				break;
		}
		
		controller.creaAnnuncio(annuncio);
		JOptionPane.showMessageDialog(this, "Annucio creato!");
        dispose();
	}
    
    
    //TODO check dei textfield
}
