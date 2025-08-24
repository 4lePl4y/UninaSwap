package gui;

import controller.Controller;
import entities.annuncio.*;
import entities.enumerazioni.Sede;
import entities.enumerazioni.TipoAnnuncio;
import entities.oggetto.Oggetto;
import entities.studente.Studente;
import gui.preset.JButtonWithBorder;
import gui.preset.JWritableTextArea;
import gui.preset.presetJTextField.JCustomTextField;
import gui.preset.presetJTextField.JDoubleTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ModifyAnnuncio extends JFrame {
    private static final long serialVersionUID = 1L;
    private Controller controller;
    private Annuncio annuncio;
    
    private JPanel contentPane;
    private JCustomTextField titoloAnnuncioField;
    private JWritableTextArea descrPane;
    private JComboBox<TipoAnnuncio> tipoAnnuncioCombo;
    private JComboBox<Oggetto> oggettiEsistentiCombo;
    private  JComboBox<Sede> sedeCombo;
    private JComboBox<String> orarioCombo;
    private JPanel prezzoPanel;
    private JDoubleTextField prezzoField;

    public ModifyAnnuncio(Controller controller, Annuncio annuncio) {
        this.controller = controller;
        this.annuncio = annuncio;
        
        
    	
    	this.setTitle("Modifica Annuncio");
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
        titoloAnnuncioField.setText(annuncio.getTitolo());
        titoloAnnuncioField.setForeground(Color.BLACK);
        titoloAnnuncioField.setColumns(20);
        titoloAnnuncioPanel.add(titoloAnnuncioLabel);
        titoloAnnuncioPanel.add(titoloAnnuncioField);
        contentPane.add(titoloAnnuncioPanel);

        // Descrizione
        JPanel descrPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel descrLabel = new JLabel("Descrizione:");
        descrPane = new JWritableTextArea("Inserisci la descrizione...");
        descrPane.setText(annuncio.getDescrizione());
        descrPane.setForeground(Color.BLACK);
        descrPane.setPreferredSize(new Dimension(300, 80));
        descrPanel.add(descrLabel);
        descrPanel.add(descrPane);
        contentPane.add(descrPanel);

        // Tipo Annuncio
        TipoAnnuncio tipo;
        if(annuncio instanceof AnnuncioVendita) {
        	tipo = TipoAnnuncio.Vendita;
        } else if (annuncio instanceof AnnuncioScambio) {
			tipo = TipoAnnuncio.Scambio;
		} else {
			tipo = TipoAnnuncio.Regalo;
		}
        JPanel tipoAnnuncioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel tipoAnnuncioLabel = new JLabel("Tipo Annuncio:");
        tipoAnnuncioCombo = new JComboBox<>(TipoAnnuncio.values());
        tipoAnnuncioCombo.setSelectedItem(tipo);
        tipoAnnuncioPanel.add(tipoAnnuncioLabel);
        tipoAnnuncioPanel.add(tipoAnnuncioCombo);
        contentPane.add(tipoAnnuncioPanel);
        
        // Sede
        JPanel sedePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel sedeLabel = new JLabel("Luogo d'incontro:");
        sedeCombo = new JComboBox<>(Sede.values());
        sedeCombo.setSelectedItem(annuncio.getLuogo());
        sedePanel.add(sedeLabel);
        sedePanel.add(sedeCombo);
        contentPane.add(sedePanel);

        // Orario
        JPanel orarioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel orarioLabel = new JLabel("Orario:");
        orarioCombo = new JComboBox<>(NewAnnuncio.generaOrari());
        orarioCombo.setSelectedItem(annuncio.getOraIncontro().toString());
        orarioPanel.add(orarioLabel);
        orarioPanel.add(orarioCombo);
        contentPane.add(orarioPanel);

        // Prezzo (solo per Vendita)
        prezzoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel prezzoLabel = new JLabel("Prezzo:");
        prezzoField = new JDoubleTextField("€");
        prezzoField.setText(annuncio instanceof AnnuncioVendita ? String.valueOf(((AnnuncioVendita) annuncio).getPrezzo()) : "€");
        prezzoField.setForeground(Color.BLACK);
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
        JButtonWithBorder creaBtn = new JButtonWithBorder("Modifica Annuncio");
        creaBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        creaBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.onModificaAnnuncioClicked();
			}
		});
        contentPane.add(Box.createVerticalStrut(20));
        contentPane.add(creaBtn);
    }
    
    
    // METODI
	public Annuncio creaAnnuncio() {
		if(!areInputsValid()) {
			return null;
		}
		
		long id = this.annuncio.getId();
		String titolo = this.titoloAnnuncioField.getText();
		Studente autore = this.controller.getStudenteLoggato();
		Oggetto oggettoSelezionato = this.annuncio.getOggetto();
		String descrizione = this.descrPane.getText();
		descrizione = descrizione.equals("Inserisci la descrizione...") ? "" : descrizione;
		Sede luogo = (Sede) this.sedeCombo.getSelectedItem();
		LocalTime oraIncontro = LocalTime.parse((String) this.orarioCombo.getSelectedItem());
		LocalDate dataPubblicazione = this.annuncio.getDataPubblicazione();
		
		TipoAnnuncio tipo = (TipoAnnuncio) this.tipoAnnuncioCombo.getSelectedItem();
		Annuncio annuncio = null;
		switch (tipo) {
			case Vendita: 
				double prezzo = Double.valueOf(prezzoField.getText());
				annuncio = new AnnuncioVendita(id, titolo, autore, oggettoSelezionato, descrizione, luogo, oraIncontro, dataPubblicazione, prezzo);
				break;
				
			case Scambio:
				annuncio = new AnnuncioScambio(id, titolo, autore, oggettoSelezionato, descrizione, luogo, oraIncontro, dataPubblicazione);
				break;
				
			case Regalo:
				annuncio = new AnnuncioRegalo(id, titolo, autore, oggettoSelezionato, descrizione, luogo, oraIncontro, dataPubblicazione);
				break;
		}
		if(controller.areAnnunciConStessoOggetto(annuncio)) {
			if(annuncio instanceof AnnuncioRegalo) {
				JOptionPane.showMessageDialog(this, "Non puoi creare un annuncio di regalo per questo oggetto. \nEsiste già un suo annuncio di vendita o di scambio");
				return null;
			} else {
				JOptionPane.showMessageDialog(this, "Non puoi creare un annuncio di vendita o di scambio per questo oggetto. \nEsiste già un suo annuncio di regalo");
				return null; 
			}
		}
		
		JOptionPane.showMessageDialog(this, "Annucio modificato!");
        dispose();
        return annuncio;
	}


	private boolean areInputsValid() {
		if(titoloAnnuncioField.getText().isEmpty() || titoloAnnuncioField.getText().equals("Inserisci il titolo dell'annuncio")) {
			JOptionPane.showMessageDialog(this, "Inserisci un titolo per l'annuncio!");
			return false;
		}
		
		
		if(prezzoPanel.isVisible()) {
			if(prezzoField.getText().isEmpty() || prezzoField.getText().equals("€")) {
				JOptionPane.showMessageDialog(this, "Inserisci un prezzo per l'annuncio di vendita!");
				return false;
			}
			
			try {
				Double.parseDouble(prezzoField.getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Inserisci un prezzo valido in denaro!");
				return false;
			}
		}
		
		return true;
	}
	
	//**Metodo per aggiornare il menu a tendina degli oggetti esistenti */
	public void refreshOggettiEsistenti() {
		int lastPositionIndex = controller.getMieiOggetti().size() - 1;
		oggettiEsistentiCombo.addItem(controller.getMieiOggetti().get(lastPositionIndex));
	}
			

}
