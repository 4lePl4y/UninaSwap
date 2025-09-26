package gui.oggetto;

import controller.Controller;
import entities.enumerazioni.TipoOggetto;
import gui.preset.JCustomComboBox;
import gui.preset.presetJButton.JButtonWithBorder;
import gui.preset.presetJTextField.JCustomTextField;
import gui.preset.presetJTextField.JYearTextField;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.Year;
import java.time.format.DateTimeParseException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class NewOggetto extends JDialog {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<TipoOggetto> tipoOggettoCombo;
    private Controller controller;

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

    public NewOggetto(Controller controller) {
    	super(controller.getMainFrame(), "Nuovo Oggetto", true);
    	this.controller = controller;
    	
        this.setBounds(100, 100, 500, 500);
        this.setResizable(false);
        this.setFocusable(true);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);

        // Tipo Oggetto Panel
        JPanel tipoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel tipoLabel = new JLabel("Tipo Oggetto:");
        tipoOggettoCombo = new JCustomComboBox<>(TipoOggetto.values());
        tipoOggettoCombo.setPreferredSize(new Dimension(170, 25));
        tipoOggettoCombo.setMaximumSize(new Dimension(170, 25));
        tipoOggettoCombo.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        tipoOggettoCombo.addActionListener(e -> aggiornaCampiPerComboBox());
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
        
        aggiornaCampiPerComboBox();

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
        JButtonWithBorder creaButton = new JButtonWithBorder("Crea Oggetto", Controller.APP_BLUE);
        creaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(Box.createVerticalStrut(20));
        creaButton.addActionListener(e -> onCreaOggettoClicked());
        contentPane.add(creaButton);
        
        
    }

    // METODI    
    // Getter 
    public TipoOggetto getTipoOggetto() {
    	return (TipoOggetto) tipoOggettoCombo.getSelectedItem();
    }
    
    public String getNome() {
        return nomeField.getText();
    }

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

    public String getIsbn() {
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

    
    // Altri metodi
    public void onCreaOggettoClicked() {
    	if(areInputsValid()) {
    		controller.onCreaOggettoClicked();
    		JOptionPane.showMessageDialog(this, "Oggetto creato!");
    		this.dispose();
    	}
    	return;
    }

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


	private JPanel creaFieldPanel(String label, JCustomTextField field) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel jlabel = new JLabel(label);
        field.setColumns(20);
        panel.add(jlabel);
        panel.add(field);
        return panel;
    }

    private void aggiornaCampiPerComboBox() {
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
    
}
