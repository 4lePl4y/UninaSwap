package gui.oggetto;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.time.Year;
import java.time.format.DateTimeParseException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import entities.oggetto.*;
import gui.preset.JButtonWithBorder;
import gui.preset.presetJTextField.JCustomTextField;
import gui.preset.presetJTextField.JYearTextField;

public class ModifyOggetto extends JDialog{
	// ATTRIBUTI
	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Controller controller;
    private Oggetto oggetto;

    // Panels for each field
    private JPanel nomePanel, marchioPanel, tagliaPanel, modelloPanel, annoUscitaPanel;
    private JPanel titoloPanel, isbnPanel, autorePanel, generePanel;
    private JPanel categoriaPanel;

    // Fields
    private JCustomTextField nomeField = new JCustomTextField("Inserisci il nome", "nome");
    private JCustomTextField marchioField = new JCustomTextField("Inserisci il marchio", "marchio");
    private JCustomTextField tagliaField = new JCustomTextField("Inserisci la taglia", "taglia");
    private JCustomTextField modelloField = new JCustomTextField("Inserisci il modello", "modello");
    private JYearTextField annoUscitaField = new JYearTextField("Inserisci l'anno di uscita", "anno d'uscita");
    private JCustomTextField titoloField = new JCustomTextField("Inserisci il titolo", "titolo");
    private JCustomTextField isbnField = new JCustomTextField("Inserisci l'ISBN", "ISBN");
    private JCustomTextField autoreField = new JCustomTextField("Inserisci l'autore", "autore");
    private JCustomTextField genereField = new JCustomTextField("Inserisci il genere", "genere");
    private JCustomTextField categoriaField = new JCustomTextField("Inserisci la categoria", "categoria");

    // COSTRUTTORE
    public ModifyOggetto(Controller controller, Oggetto oggetto) {
    	super(controller.getMainFrame(), "Modifica oggetto", true);
    	this.controller = controller;
    	this.oggetto = oggetto;
        this.setBounds(100, 100, 500, 500);
        this.setResizable(false);
        this.setFocusable(true);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);
        
        nomeField.setText(oggetto.getNome());
        nomeField.setForeground(Color.BLACK);
        switch(oggetto) {
        	case StrumentoMusicale sm -> {marchioField.setText(oggetto.getNome());}
        	case Abbigliamento a -> {
        		marchioField.setText(a.getMarchio());
        		marchioField.setForeground(Color.BLACK);
        		tagliaField.setText(a.getTaglia()); 
        		tagliaField.setForeground(Color.BLACK);
        	}
        	case Elettronica e ->{
        		marchioField.setText(e.getMarchio());
        		marchioField.setForeground(Color.BLACK);
        		modelloField.setText(e.getModello()); 
        		modelloField.setForeground(Color.BLACK);
        		annoUscitaField.setText(String.valueOf(e.getAnnoUscita()));
        		annoUscitaField.setForeground(Color.BLACK);
        	}
        	case Libro l ->{
        		titoloField.setText(l.getTitolo());
        		titoloField.setForeground(Color.BLACK);
        		isbnField.setText(l.getISBN());
        		isbnField.setForeground(Color.BLACK);
        		annoUscitaField.setText(String.valueOf(l.getAnnoUscita()));
        		annoUscitaField.setForeground(Color.BLACK);
        		autoreField.setText(l.getAutore());
        		autoreField.setForeground(Color.BLACK);
        		genereField.setText(l.getGenere());
        		genereField.setForeground(Color.BLACK);
        	}
        	case Misc m -> {
        		marchioField.setText(m.getMarchio());
        		marchioField.setForeground(Color.BLACK);
        		categoriaField.setText(m.getCategoria());
        		categoriaField.setForeground(Color.BLACK);
        	}
        	default -> {}
        } 

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

        aggiornaCampi();

        // Button
        JButtonWithBorder creaButton = new JButtonWithBorder("Modifica Oggetto");
        creaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(Box.createVerticalStrut(20));
        creaButton.addActionListener(e -> onModificaOggettoClicked());
        contentPane.add(creaButton);

    }

    private JPanel creaFieldPanel(String label, JCustomTextField field) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel jlabel = new JLabel(label);
        field.setColumns(20);
        panel.add(jlabel);
        panel.add(field);
        return panel;
    }

    // METODI
    // Getter
    public String getMarchio() {
    	return marchioField.getText();
    }

	public String getTaglia() {
		return tagliaField.getText();
	}
	
	public String getModello() {
		return modelloField.getText();
	}
	
	public Year getAnnoUscita() {
		return annoUscitaField.getAnno();
	}
	
	public String getTitolo() {
		return titoloField.getText();
	}
    
	public String getISBN() {
		return isbnField.getText();
	}
	
	public String getAutore() {
		return autoreField.getText();
	}
	
	public String getGenere() {
		return genereField.getText();
	}
	
	public String getCategoria() {
		return categoriaField.getText();
	}

	public String getNome() {
		return nomeField.getText();
	}
    
    
	//Altri metodi
    public void onModificaOggettoClicked() {
    	if(areInputsValid()) {
    		controller.onModificaOggettoClicked(oggetto);
    		JOptionPane.showMessageDialog(this, "Oggetto modificato!");
    		this.dispose();
    	}
    	return;
	}
    
    //TODO Identico al metodo di NewOggetto; Alternativa rispetto al metodo usato in newAnnuncio perché aveva molti field (chiedo parare)
    private boolean areInputsValid() {
    	JCustomTextField[] fields = {
    			nomeField, marchioField, tagliaField, modelloField, annoUscitaField, titoloField, isbnField, autoreField, genereField, categoriaField
    			};
    	for(JCustomTextField textField: fields) {
    		if(textField.isShowing() && textField.getText().isBlank()) {
    			JOptionPane.showMessageDialog(this, "Inserire " + textField.getName() + "!");
    			return false;
    		}
    	}
    	
    	if (annoUscitaField.isShowing()) {
    		try {
    			Year anno = getAnnoUscita();
    			if( anno.isAfter(Year.now()) || anno.isBefore(Year.of(1900)) ){
    				JOptionPane.showMessageDialog(this, "Inserire un anno valido");
    				return false;
    			}
    		} catch(DateTimeParseException e) {
    			JOptionPane.showMessageDialog(this, "Inserire un anno valido");
    			return false;
    		}
    	}
    	
    	return true;
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

        switch (oggetto) {
            case StrumentoMusicale sm -> {marchioPanel.setVisible(true);}
            case Abbigliamento a -> {
                marchioPanel.setVisible(true);
                tagliaPanel.setVisible(true);
            }
            case Elettronica e -> {
                marchioPanel.setVisible(true);
                modelloPanel.setVisible(true);
                annoUscitaPanel.setVisible(true);
            }
            case Libro l -> {            	
            	titoloPanel.setVisible(true);
            	isbnPanel.setVisible(true);
            	annoUscitaPanel.setVisible(true);
            	autorePanel.setVisible(true);
            	generePanel.setVisible(true);
            }
            case Misc m -> {
            	marchioPanel.setVisible(true);
            	categoriaPanel.setVisible(true);               	
            }
            default -> {}
        }
        
        contentPane.revalidate();
        contentPane.repaint();
    }
	
}
