package gui.create_windows;

import controller.Controller;
import entities.enumerazioni.TipoOggetto;
import entities.oggetto.*;
import entities.studente.Studente;
import gui.preset.JButtonWithBorder;
import gui.preset.presetJTextField.JCustomTextField;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Year;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class NewOggetto extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<TipoOggetto> tipoOggettoCombo;
    private Controller controller;

    // Panels for each field
    private JPanel nomePanel, marchioPanel, tagliaPanel, modelloPanel, annoUscitaPanel;
    private JPanel titoloPanel, isbnPanel, autorePanel, generePanel;
    private JPanel categoriaPanel;

    // Fields
    private JCustomTextField nomeField = new JCustomTextField("Inserisci il nome");
    private JCustomTextField marchioField = new JCustomTextField("Inserisci il marchio");
    private JCustomTextField tagliaField = new JCustomTextField("Inserisci la taglia");
    private JCustomTextField modelloField = new JCustomTextField("Inserisci il modello");
    private JCustomTextField annoUscitaField = new JCustomTextField("Inserisci l'anno di uscita");
    private JCustomTextField titoloField = new JCustomTextField("Inserisci il titolo");
    private JCustomTextField isbnField = new JCustomTextField("Inserisci l'ISBN");
    private JCustomTextField autoreField = new JCustomTextField("Inserisci l'autore");
    private JCustomTextField genereField = new JCustomTextField("Inserisci il genere");
    private JCustomTextField categoriaField = new JCustomTextField("Inserisci la categoria");

    public NewOggetto(Controller controller) {
    	this.controller = controller;
        this.setTitle("Nuovo Oggetto");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(100, 100, 500, 500);
        this.setResizable(false);
        this.setFocusable(true);
        this.setAlwaysOnTop(true);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);

        // Tipo Oggetto
        JPanel tipoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel tipoLabel = new JLabel("Tipo Oggetto:");
        tipoOggettoCombo = new JComboBox<>(TipoOggetto.values());
        tipoOggettoCombo.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        tipoPanel.add(tipoLabel);
        tipoPanel.add(tipoOggettoCombo);
        contentPane.add(tipoPanel);

        // Panels for each field
        nomePanel = creaFieldPanel("Nome:", nomeField);
        marchioPanel = creaFieldPanel("Marchio:", marchioField);
        tagliaPanel = creaFieldPanel("Taglia:", tagliaField);
        modelloPanel = creaFieldPanel("Modello:", modelloField);
        annoUscitaPanel = creaFieldPanel("Anno di uscita:", annoUscitaField);
        titoloPanel = creaFieldPanel("Titolo:", titoloField);
        isbnPanel = creaFieldPanel("ISBN:", isbnField);
        autorePanel = creaFieldPanel("Autore:", autoreField);
        generePanel = creaFieldPanel("Genere:", genereField);
        categoriaPanel = creaFieldPanel("Categoria:", categoriaField);

        // Add all panels (they will be shown/hidden as needed)
        contentPane.add(nomePanel);
        contentPane.add(marchioPanel);
        contentPane.add(tagliaPanel);
        contentPane.add(modelloPanel);
        contentPane.add(annoUscitaPanel);
        contentPane.add(titoloPanel);
        contentPane.add(isbnPanel);
        contentPane.add(autorePanel);
        contentPane.add(generePanel);
        contentPane.add(categoriaPanel);

        // Button
        JButtonWithBorder creaButton = new JButtonWithBorder("Crea Oggetto");
        creaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                TipoOggetto tipo = (TipoOggetto) tipoOggettoCombo.getSelectedItem();
                if (tipo == null) return;

                dispose();
            }
        });
        
        creaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(Box.createVerticalStrut(20));
        creaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				onCreaOggettoClicked();
			}
		});
        contentPane.add(creaButton);

        tipoOggettoCombo.addActionListener(e -> aggiornaCampi());
        aggiornaCampi();
    }

    private JPanel creaFieldPanel(String label, JCustomTextField field) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel jlabel = new JLabel(label);
        field.setColumns(20);
        panel.add(jlabel);
        panel.add(field);
        return panel;
    }

    private void aggiornaCampi() {
        // Hide all panels first
        marchioPanel.setVisible(false);
        tagliaPanel.setVisible(false);
        modelloPanel.setVisible(false);
        annoUscitaPanel.setVisible(false);
        titoloPanel.setVisible(false);
        isbnPanel.setVisible(false);
        autorePanel.setVisible(false);
        generePanel.setVisible(false);
        categoriaPanel.setVisible(false);

        TipoOggetto tipo = (TipoOggetto) tipoOggettoCombo.getSelectedItem();
        if (tipo == null) return;

        switch (tipo) {
            case StrumentoMusicale:
                marchioPanel.setVisible(true);
                break;
            case Abbigliamento:
                marchioPanel.setVisible(true);
                tagliaPanel.setVisible(true);
                break;
            case Elettronica:
                marchioPanel.setVisible(true);
                modelloPanel.setVisible(true);
                annoUscitaPanel.setVisible(true);
                break;
            case Libro:
                titoloPanel.setVisible(true);
                isbnPanel.setVisible(true);
                annoUscitaPanel.setVisible(true);
                autorePanel.setVisible(true);
                generePanel.setVisible(true);
                break;
            case Misc:
            	marchioPanel.setVisible(true);
                categoriaPanel.setVisible(true);   
        }
        
        contentPane.revalidate();
        contentPane.repaint();
    }
    
    public void onCreaOggettoClicked() {
		Oggetto oggetto = null;
		Studente proprietario = controller.getStudenteLoggato();
	    String oNome = nomeField.getText().trim();
	    String oMarchio;
	    String oModello;
	    String oTaglia;
	    Year oAnnoUscita;
	    String oTitolo;
	    String oISBN;
	    String oAutore;
	    String oGenere;	// genere del libro
		String oCategoria;	// categoria dell'oggetto misc
		
	    TipoOggetto tipo = (TipoOggetto) tipoOggettoCombo.getSelectedItem();
	    switch (tipo) {
        case StrumentoMusicale:
        	oMarchio = marchioField.getText().trim();
            oggetto = new StrumentoMusicale(oNome, proprietario ,oMarchio);
            break;
        case Abbigliamento:
        	oMarchio = marchioField.getText().trim();
        	oTaglia = tagliaField.getText().trim();
            oggetto = new Abbigliamento(oNome, proprietario, oMarchio, oTaglia);
            break;
        case Elettronica:
        	oMarchio = marchioField.getText().trim();
        	oModello = modelloField.getText().trim();
        	oAnnoUscita = Year.of(Integer.valueOf(annoUscitaField.getText().trim()));
            oggetto = new Elettronica(oNome, proprietario, oMarchio, oModello, oAnnoUscita);
            break;
        case Libro:
        	oTitolo = titoloField.getText().trim();
        	oISBN = isbnField.getText().trim();
        	oAnnoUscita = Year.of(Integer.valueOf(annoUscitaField.getText().trim()));
        	oAutore = autoreField.getText().trim();
        	oGenere = genereField.getText().trim();
            oggetto = new Libro(oNome, proprietario, oTitolo, oISBN, oAnnoUscita, oAutore, oGenere);
            break;
        case Misc:
        	oMarchio = marchioField.getText().trim();
        	oCategoria = categoriaField.getText().trim();
        	oggetto = new Misc(oNome, proprietario, oMarchio, oCategoria);
			break;   
    }    
	    controller.onCreaOggettoClicked(oggetto);
	}
    
}
