package gui.annuncio;

import controller.Controller;
import entities.annuncio.*;
import entities.enumerazioni.Sede;
import gui.preset.JButtonWithBorder;
import gui.preset.JWritableTextArea;
import gui.preset.presetJTextField.JCustomTextField;
import gui.preset.presetJTextField.JPriceTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.LocalTime;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ModifyAnnuncio extends JDialog {
    private static final long serialVersionUID = 1L;
    private Controller controller;
    private Annuncio annuncio;
    
    private JPanel contentPane;
    private JCustomTextField titoloAnnuncioField;
    private JWritableTextArea descrTextArea;
    private  JComboBox<Sede> sedeCombo;
    private JComboBox<String> orarioCombo;
    private JPanel prezzoPanel;
    private JPriceTextField prezzoField;

    public ModifyAnnuncio(Controller controller, Annuncio annuncio) {
    	super(controller.getMainFrame(), "Modifica Annuncio", true);
    	this.controller = controller;
        this.annuncio = annuncio;

        this.setBounds(100, 100, 600, 500);
        this.setResizable(false);
        this.setFocusable(true);

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
        descrTextArea = new JWritableTextArea("Inserisci la descrizione...");
        descrTextArea.setPreferredSize(new Dimension(300, 80));
        if(!annuncio.getDescrizione().isBlank()) {
        	descrTextArea.setText(annuncio.getDescrizione());
            descrTextArea.setForeground(Color.BLACK);
        }
        descrPanel.add(descrLabel);
        descrPanel.add(descrTextArea);
        contentPane.add(descrPanel);

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
        prezzoField = new JPriceTextField("€");
        prezzoField.setText(annuncio instanceof AnnuncioVendita ? String.valueOf(((AnnuncioVendita) annuncio).getPrezzo()) : "€");
        prezzoField.setForeground(Color.BLACK);
        prezzoField.setColumns(10);
        prezzoPanel.add(prezzoLabel);
        prezzoPanel.add(prezzoField);
        prezzoPanel.setVisible(annuncio instanceof AnnuncioVendita);
        contentPane.add(prezzoPanel);


        // Bottone modifica annuncio
        JButtonWithBorder modificaButton = new JButtonWithBorder("Modifica Annuncio");
        modificaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        modificaButton.addActionListener(e -> onModificaAnnuncioClicked());
        contentPane.add(Box.createVerticalStrut(20));
        contentPane.add(modificaButton);
    }
    
    
    // METODI
    public void onModificaAnnuncioClicked() {
    	if(areInputsValid()) {
    		controller.onModificaAnnuncioClicked(annuncio);
    		JOptionPane.showMessageDialog(this, "Annucio modificato!");
    		this.dispose();
    	}
		return;
	}


	private boolean areInputsValid() {
		if(titoloAnnuncioField.getText().isBlank()) {
			JOptionPane.showMessageDialog(this, "Inserisci un titolo per l'annuncio!");
			return false;
		}
		
		if(prezzoPanel.isVisible()) {
			if(prezzoField.getText().isBlank()) {
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
			
	public String getTitolo() {
		return titoloAnnuncioField.getText();
	}

	public String getDescrizione() {
		return descrTextArea.getText();
	}
	
	public Sede getSede() {
		return (Sede) this.sedeCombo.getSelectedItem();
	}
	
	public LocalTime getOraIncontro() {
		return LocalTime.parse((String) this.orarioCombo.getSelectedItem());
	}
	
	public double getPrezzo() {
		return Double.valueOf(prezzoField.getText());
	}
}
