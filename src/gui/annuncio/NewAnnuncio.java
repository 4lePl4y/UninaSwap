package gui.annuncio;

import controller.Controller;
import entities.enumerazioni.Sede;
import entities.enumerazioni.TipoAnnuncio;
import entities.oggetto.Oggetto;
import gui.preset.JButtonWithBorder;
import gui.preset.JWritableTextArea;
import gui.preset.presetJTextField.JCustomTextField;
import gui.preset.presetJTextField.JPriceTextField;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class NewAnnuncio extends JFrame {
    private static final long serialVersionUID = 1L;
    private Controller controller;
    
    private JPanel contentPane;
    private JCustomTextField titoloAnnuncioField;
    private JWritableTextArea descrTextArea;
    private JComboBox<TipoAnnuncio> tipoAnnuncioCombo;
    private JComboBox<Oggetto> oggettiEsistentiCombo;
    private JComboBox<Sede> sedeCombo;
    private JComboBox<String> orarioCombo;
    private JPanel prezzoPanel;
    private JPriceTextField prezzoField;
    private JPanel aggiungiOggettoPanel;
    private JLabel oggettiEsistentiLabel;
    

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
        descrTextArea = new JWritableTextArea("Inserisci la descrizione...");
        descrTextArea.setPreferredSize(new Dimension(300, 80));
        descrPanel.add(descrLabel);
        descrPanel.add(descrTextArea);
        contentPane.add(descrPanel);

        // Tipo Annuncio
        JPanel tipoAnnuncioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel tipoAnnuncioLabel = new JLabel("Tipo Annuncio:");
        tipoAnnuncioCombo = new JComboBox<>(TipoAnnuncio.values());
        tipoAnnuncioPanel.add(tipoAnnuncioLabel);
        tipoAnnuncioPanel.add(tipoAnnuncioCombo);
        contentPane.add(tipoAnnuncioPanel);
        
        // Aggiungi oggetto esistente o crea nuovo oggetto
        aggiungiOggettoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        oggettiEsistentiLabel = new JLabel("Scegli dai tuoi oggetti:");
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
				controller.onApriOggettoFrameClicked();
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
        prezzoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel prezzoLabel = new JLabel("Prezzo:");
        prezzoField = new JPriceTextField("€");
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
        JButtonWithBorder creaButton = new JButtonWithBorder("Crea Annuncio");
        creaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        creaButton.addActionListener(e -> onCreaAnnuncioClicked());
        contentPane.add(Box.createVerticalStrut(20));
        contentPane.add(creaButton);
    }
    
    
    // METODI

    // Getter
    public String getTitolo() {
    	return titoloAnnuncioField.getText();    	
    }
    
    public Oggetto getOggettoSelezionato() {
		return (Oggetto) oggettiEsistentiCombo.getSelectedItem();
	}
    
    public String getDescrizione() {
		return descrTextArea.getText();
    }
    
    public Sede getLuogo() {
    	return (Sede) sedeCombo.getSelectedItem();
    }
    
    public LocalTime getOraIncontro() {
		return LocalTime.parse((String) orarioCombo.getSelectedItem());
	}
    
    public TipoAnnuncio getTipoAnnuncio() {
		return (TipoAnnuncio) tipoAnnuncioCombo.getSelectedItem();
	}
    
    public double getPrezzo() {
    	return prezzoField.getPrezzo();
    }
	
    
    // Altri metodi
    public void onCreaAnnuncioClicked() {
		if(areInputsValid()) { 
			controller.onCreaAnnuncioClicked();
			
		}
		return;	
	}
    
	private boolean areInputsValid() {
		if(titoloAnnuncioField.getText().isBlank()) {
			JOptionPane.showMessageDialog(this, "Inserisci un titolo per l'annuncio!");
			return false;
		}
		
		if(oggettiEsistentiCombo.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(this, "Seleziona un oggetto esistente o crea un nuovo oggetto!");
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
	
	
	public static String[] generaOrari() {
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
	
	//**Metodo per aggiornare il menu a tendina degli oggetti esistenti */
	public void refreshOggettiEsistenti() {
		/*
		 * FIXME: L'ultimo oggetto dell'arraylist non è detto che sia l'ultimo oggetto creato, quindi il menu a tendina si aggiorna male. 
		 * Infatti se si prova a creare oggetti dal frame per creare gli annunci, non sempre l'aggiunta va a buon fine
		 * Possibile soluzione è usare una Lista 
		*/
		int lastPositionIndex = controller.getMieiOggetti().size() - 1;
		oggettiEsistentiCombo.addItem(controller.getMieiOggetti().get(lastPositionIndex));
	}
			

}
