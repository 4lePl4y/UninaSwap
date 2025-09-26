package gui.annuncio;

import controller.Controller;
import entities.enumerazioni.Sede;
import entities.enumerazioni.TipoAnnuncio;
import entities.oggetto.Oggetto;
import exception.CustomSQLException;
import gui.preset.JCustomComboBox;
import gui.preset.JCustomList;
import gui.preset.JCustomScrollPane;
import gui.preset.JCustomList.Mode;
import gui.preset.JWritableTextArea;
import gui.preset.presetJButton.JButtonWithBorder;
import gui.preset.presetJTextField.JCustomTextField;
import gui.preset.presetJTextField.JPriceTextField;

import java.awt.*;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class NewAnnuncio extends JDialog {
    private static final long serialVersionUID = 1L;
    private final Controller controller;

    private JPanel contentPane;
    private JCustomTextField titoloAnnuncioField;
    private JWritableTextArea descrTextArea;
    private JCustomComboBox<TipoAnnuncio> tipoAnnuncioCombo;
    private JCustomList<Oggetto> oggettiEsistentiList;
    private JCustomComboBox<Sede> sedeCombo;
    private JCustomComboBox<String> orarioCombo;
    private JPanel prezzoPanel;
    private JPriceTextField prezzoField;
    private JPanel aggiungiOggettoPanel;
    private JLabel oggettiEsistentiLabel;
	private JPanel oggettoEsistentePanel;

    public NewAnnuncio(Controller controller) {
        super(controller.getMainFrame(), "Nuovo Annuncio", true);
        this.controller = controller;
        this.setSize(550, 550);
        super.setLocationRelativeTo(null);	//	Centra la finestra
        this.setResizable(false);
        this.setFocusable(true);

        contentPane = new JPanel(new BorderLayout(15, 15));
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);

        // ---------- NORTH: Titolo + Descrizione ----------
        JPanel northPanel = new JPanel(new BorderLayout(10, 10));
        northPanel.setOpaque(false);

        // Titolo
        JPanel titoloPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titoloPanel.setOpaque(false);
        JLabel titoloAnnuncioLabel = new JLabel("Titolo:");
        titoloAnnuncioField = new JCustomTextField("Inserisci il titolo dell'annuncio");
        titoloAnnuncioField.setColumns(25);
        titoloPanel.add(titoloAnnuncioLabel);
        titoloPanel.add(titoloAnnuncioField);
        northPanel.add(titoloPanel, BorderLayout.NORTH);

        // Descrizione
        JPanel descrPanel = new JPanel();
        descrPanel.setOpaque(false);
        FlowLayout fl_descrPanel = new FlowLayout(FlowLayout.LEFT, 10, 10);
        fl_descrPanel.setAlignOnBaseline(true);
        descrPanel.setLayout(fl_descrPanel);
       
        JLabel descrLabel = new JLabel("Descrizione:");
        descrTextArea = new JWritableTextArea("Inserisci la descrizione...");
        JScrollPane scrollDescr = new JScrollPane(descrTextArea);
        scrollDescr.setBorder(null);
        scrollDescr.getViewport().setBackground(new Color(255, 255, 255));
        scrollDescr.setBackground(new Color(255, 255, 255));
        scrollDescr.setBackground(new Color(255, 255, 255));
        scrollDescr.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollDescr.setPreferredSize(new Dimension(250, 70));
        scrollDescr.setMaximumSize(new Dimension(250, 70));
        descrPanel.add(descrLabel);
        descrPanel.add(scrollDescr);
        northPanel.add(descrPanel, BorderLayout.CENTER);

        contentPane.add(northPanel, BorderLayout.NORTH);

        // ---------- CENTER: Tipo annuncio + Oggetti + Sede + Orario ----------
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // Tipo Annuncio
        JPanel tipoAnnuncioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tipoAnnuncioPanel.setOpaque(false);
        JLabel tipoAnnuncioLabel = new JLabel("Tipo Annuncio:");
        tipoAnnuncioCombo = new JCustomComboBox<>(TipoAnnuncio.values());
        tipoAnnuncioCombo.setPreferredSize(new Dimension(150, 25));
        tipoAnnuncioCombo.setMaximumSize(new Dimension(150, 25));
        tipoAnnuncioPanel.add(tipoAnnuncioLabel);
        tipoAnnuncioPanel.add(tipoAnnuncioCombo);
        centerPanel.add(tipoAnnuncioPanel);

        // Oggetti
        aggiungiOggettoPanel = new JPanel();
        aggiungiOggettoPanel.setOpaque(false);
        aggiungiOggettoPanel.setLayout(new BoxLayout(aggiungiOggettoPanel, BoxLayout.Y_AXIS));
        
        // Oggetti esistenti
        oggettoEsistentePanel = new JPanel();
        oggettoEsistentePanel.setOpaque(false);
        oggettoEsistentePanel.setLayout(new BorderLayout(0, 0));
        oggettiEsistentiLabel = new JLabel("Scegli dai tuoi oggetti:");
        oggettiEsistentiList = new JCustomList<Oggetto>(controller.getMieiOggetti(), Mode.SINGLE_SELECTION,  500, 60);
        oggettiEsistentiList.setAlignmentX(Component.LEFT_ALIGNMENT);
        oggettoEsistentePanel.add(oggettiEsistentiLabel, BorderLayout.NORTH);
        oggettoEsistentePanel.add(oggettiEsistentiList, BorderLayout.CENTER);
        
        // Crea nuovo oggetto
        JPanel creaOggettoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        creaOggettoPanel.setOpaque(false);
        creaOggettoPanel.add(new JLabel("Oppure"));
        JButtonWithBorder creaOggettoBtn = new JButtonWithBorder("Crea Nuovo Oggetto", Controller.APP_BLUE);
        creaOggettoBtn.addActionListener(e -> onAggiungiNuovoOggettoClicked());
        creaOggettoPanel.add(creaOggettoBtn);
        
        aggiungiOggettoPanel.add(oggettoEsistentePanel);
        aggiungiOggettoPanel.add(creaOggettoPanel);
        centerPanel.add(aggiungiOggettoPanel);
        
        

        // Sede
        JPanel sedePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sedePanel.setOpaque(false);
        JLabel sedeLabel = new JLabel("Luogo d'incontro:");
        sedeCombo = new JCustomComboBox<>(Sede.values());
        sedeCombo.setPreferredSize(new Dimension(150, 25));
        sedeCombo.setMaximumSize(new Dimension(150, 25));
        sedePanel.add(sedeLabel);
        sedePanel.add(sedeCombo);
        centerPanel.add(sedePanel);

        // Orario
        JPanel orarioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        orarioPanel.setOpaque(false);
        JLabel orarioLabel = new JLabel("Orario:");
        orarioCombo = new JCustomComboBox<>(generaOrari());
        orarioCombo.setPreferredSize(new Dimension(70, 25));
        orarioCombo.setMaximumSize(new Dimension(70, 25));
        orarioPanel.add(orarioLabel);
        orarioPanel.add(orarioCombo);
        centerPanel.add(orarioPanel);

        // Prezzo
        prezzoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        prezzoPanel.setOpaque(false);
        JLabel prezzoLabel = new JLabel("Prezzo:");
        prezzoField = new JPriceTextField("€");
        prezzoField.setColumns(10);
        prezzoPanel.add(prezzoLabel);
        prezzoPanel.add(prezzoField);
        prezzoPanel.setVisible(tipoAnnuncioCombo.getSelectedItem() == TipoAnnuncio.Vendita);
        centerPanel.add(prezzoPanel);

        contentPane.add(centerPanel, BorderLayout.CENTER);

        // ---------- SOUTH: Bottone ----------
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel.setOpaque(false);
        JButtonWithBorder creaButton = new JButtonWithBorder("Crea Annuncio", Controller.APP_BLUE);
        creaButton.addActionListener(e -> onCreaAnnuncioClicked());
        southPanel.add(creaButton);

        contentPane.add(southPanel, BorderLayout.SOUTH);

        // Listener per tipo annuncio
        tipoAnnuncioCombo.addActionListener(e -> {
            boolean isVendita = tipoAnnuncioCombo.getSelectedItem() == TipoAnnuncio.Vendita;
            prezzoPanel.setVisible(isVendita);
            contentPane.revalidate();
            contentPane.repaint();
        });
    }


    // METODI GETTER (restano come prima)
    public String getTitolo() {
        return titoloAnnuncioField.getText();
    }

    public Oggetto getOggettoSelezionato() {
        return (Oggetto) oggettiEsistentiList.getSelectedValues().getFirst();
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
        // Assumiamo che JPriceTextField esponga getPrezzo() come prima,
        // altrimenti fallback a parsing
        try {
            return prezzoField.getPrezzo();
        } catch (Exception e) {
            try {
                return Double.parseDouble(prezzoField.getText());
            } catch (Exception ex) {
                return 0.0;
            }
        }
    }

    // AZIONI
    public void onCreaAnnuncioClicked() {
        if (areInputsValid()) {
            try {
                controller.onCreaAnnuncioClicked();
            } catch (CustomSQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Errore imprevisto nella creazione dell'annuncio. Riprova!", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(this, "Errore: " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(this, "Annuncio creato!", "Successo", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
    }

    private boolean areInputsValid() {
        if (titoloAnnuncioField.getText() == null || titoloAnnuncioField.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Inserisci un titolo per l'annuncio!");
            titoloAnnuncioField.requestFocus();
            return false;
        }

        if (oggettiEsistentiList.getSelectedValues() == null) {
            JOptionPane.showMessageDialog(this, "Seleziona un oggetto esistente o crea un nuovo oggetto!");
            oggettiEsistentiList.requestFocus();
            return false;
        }

        if (prezzoPanel.isVisible()) {
            String prezzoTxt = prezzoField.getText();
            if (prezzoTxt == null || prezzoTxt.isBlank()) {
                JOptionPane.showMessageDialog(this, "Inserisci un prezzo per l'annuncio di vendita!");
                prezzoField.requestFocus();
                return false;
            }
            try {
                // prova a convertire in double
                Double.parseDouble(prezzoTxt.replace(',', '.'));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Inserisci un prezzo valido in denaro!");
                prezzoField.requestFocus();
                return false;
            }
        }

        return true;
    }

    public static String[] generaOrari() {
        // 8:00 .. 19:00 con step 15' (19:00 incluso)
        java.util.List<String> orari = new ArrayList<>();
        for (int hour = 8; hour <= 19; hour++) {
            for (int min = 0; min < 60; min += 15) {
                if (hour == 19 && min > 0) break;
                orari.add(String.format("%02d:%02d", hour, min));
            }
        }
        return orari.toArray(new String[0]);
    }

    private void refreshOggettiEsistenti() {
    	oggettoEsistentePanel.remove(oggettiEsistentiList);
    	oggettiEsistentiList = new JCustomList<Oggetto>(controller.getMieiOggetti(), Mode.SINGLE_SELECTION,  500, 60);
        oggettoEsistentePanel.add(oggettiEsistentiList, BorderLayout.CENTER);
        oggettoEsistentePanel.revalidate();
        oggettoEsistentePanel.repaint();
    }
    
    private void onAggiungiNuovoOggettoClicked() {
		controller.onApriOggettoFrameClicked();
		refreshOggettiEsistenti();
	}
    
}
