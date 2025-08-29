package gui.modify_windows;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.time.Year;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import entities.oggetto.*;
import gui.preset.JButtonWithBorder;
import gui.preset.presetJTextField.JCustomTextField;

public class ModifyOggetto extends JFrame{
	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Controller controller;
    private Oggetto oggetto;

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

    public ModifyOggetto(Controller controller, Oggetto oggetto) {
    	this.controller = controller;
    	this.oggetto = oggetto;

    	this.setTitle("Modifica Oggetto");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(100, 100, 500, 500);
        this.setResizable(false);
        this.setFocusable(true);
        this.setAlwaysOnTop(true);

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
        creaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				onModificaOggettoClicked();
			}
		});
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
    
    public void onModificaOggettoClicked() {
	    controller.onModificaOggettoClicked(oggetto);
	}
    
    public String getMarchio() {
    	return marchioField.getText().trim();
    }

	public String getTaglia() {
		return tagliaField.getText().trim();
	}
	
	public String getModello() {
		return modelloField.getText().trim();
	}
	
	public Year getAnnoUscita() {
		return Year.of(Integer.valueOf(annoUscitaField.getText().trim()));
	}
	
	public String getTitolo() {
		return titoloField.getText().trim();
	}
    
	public String getISBN() {
		return isbnField.getText().trim();
	}
	
	public String getAutore() {
		return autoreField.getText().trim();
	}
	
	public String getGenere() {
		return genereField.getText().trim();
	}
	
	public String getCategoria() {
		return categoriaField.getText().trim();
	}

	public String getNome() {
		return nomeField.getText().trim();
	}
	
}
