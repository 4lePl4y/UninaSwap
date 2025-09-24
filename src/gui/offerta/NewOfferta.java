package gui.offerta;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controller.Controller;
import entities.annuncio.*;
import entities.offerta.OffertaScambio;
import entities.oggetto.*;
import entities.studente.*;
import exception.CustomSQLException;
import gui.preset.JCustomList;
import gui.preset.JWritableTextArea;
import gui.preset.presetJButton.JButtonWithBorder;
import gui.preset.presetJTextField.JPriceTextField;

public class NewOfferta extends JDialog {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Controller controller;
    private Annuncio annuncio;
    private JLabel welcomingLabel;
    private JWritableTextArea messaggioArea;
    private JButtonWithBorder submitButton;
    private JPriceTextField moneyTextField;
	private JCustomList<Oggetto> objectsListPanel;
	private ArrayList<Oggetto> oggettiSelezionati;

    public NewOfferta(Controller controller, Annuncio annuncio, Studente autore) {
    	super(controller.getMainFrame(), "Nuova Offerta", true);
        this.controller = controller;
        this.annuncio = annuncio;

        this.setBounds(new Rectangle(0, 0, 420, 400));
        this.setFocusable(true);
        this.setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Welcoming label
        welcomingLabel = new JLabel();
        welcomingLabel.setBounds(10, 10, 386, 23);
        welcomingLabel.setText("Fai un'offerta per: " + annuncio.getTitolo() + " di " + autore.getUsername());
        welcomingLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        contentPane.add(welcomingLabel);

        // Description area
        messaggioArea = new JWritableTextArea("Aggiungi un messaggio alla tua offerta...");
        messaggioArea.setBounds(10, 43, 386, 75);
        messaggioArea.setLineWrap(true);
        messaggioArea.setWrapStyleWord(true);
        messaggioArea.setMaximumSize(new Dimension(400, 80));
        contentPane.add(messaggioArea);

        if(annuncio instanceof AnnuncioScambio) {		
        	JPanel barterPanel = new JPanel();
        	barterPanel.setBounds(10, 144, 386, 150);
        	contentPane.add(barterPanel);
        	barterPanel.setLayout(null);
        	
        	JLabel chooseObjectLabel = new JLabel("Scegli gli oggetti da scambiare: ");
        	chooseObjectLabel.setBounds(0, 0, 200, 21);
        	barterPanel.add(chooseObjectLabel);
        	
        	objectsListPanel = new JCustomList<Oggetto>(controller.getMieiOggetti(), JCustomList.Mode.MULTIPLE_SELECTION, 205, 80);
        	objectsListPanel.setBounds(0, 20, 205, 80);
        	barterPanel.add(objectsListPanel);        	
        	
        	JLabel optionLabel = new JLabel("oppure");
        	optionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        	optionLabel.setBounds(260, 30, 58, 21);
        	barterPanel.add(optionLabel);
        	
        	JButton addObjectButton = new JButtonWithBorder("Aggiungi un nuovo oggetto +");
        	addObjectButton.setBounds(210, 60, 250, 30);
        	addObjectButton.addActionListener(e -> {
				controller.onApriOggettoFrameClicked();
			});
        	barterPanel.add(addObjectButton);			  
        	
        } else {       	
        	JPanel moneyPanel = new JPanel();
        	moneyPanel.setBounds(10, 144, 386, 28);
        	contentPane.add(moneyPanel);
        	moneyPanel.setLayout(null);
        	
        	JLabel moneyLabel = new JLabel("Aggiungi la tua offerta in denaro: ");
        	moneyLabel.setBounds(0, 0, 230, 23);
        	moneyPanel.add(moneyLabel);
        	
        	moneyTextField = new JPriceTextField("€");
        	moneyTextField.setBounds(200, 2, 96, 19);
        	moneyPanel.add(moneyTextField);
        	moneyTextField.setColumns(10);			
        }

        // Submit button
        submitButton = new JButtonWithBorder("Fai Offerta");
        submitButton.setBounds(139, 296, 133, 28);
        submitButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        submitButton.addActionListener(e -> onInviaOffertaClicked());
        contentPane.add(submitButton);
        
    }

    public void onInviaOffertaClicked() {
    	if(areInputsValid()) { 
    		try {
    			controller.onInviaOffertaClicked();
	    	} catch (CustomSQLException e) {
	    		JOptionPane.showMessageDialog(this, e.getMessage());
	    		return;
	    	} catch (SQLException e) {
	    		JOptionPane.showMessageDialog(this, "Errore durante l'invio dell'offerta. Riprova!");
	    		return;
	    	}
    	this.dispose();
    	JOptionPane.showMessageDialog(this, "Offerta inviata!");
    	}
    	
    	return;
    }

	private ArrayList<Oggetto> getOggettiSelezionati() {
		return objectsListPanel.getSelectedValues();
	}
	
	  boolean areInputsValid() {
        if(annuncio instanceof AnnuncioScambio) {
        	oggettiSelezionati = getOggettiSelezionati();
			if(oggettiSelezionati.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Devi selezionare almeno un oggetto da scambiare!");
				return false;
			}
        } else {
			String moneyText = moneyTextField.getText();
			try {
				//TODO: Verificare se l'offerta proposta è <= del prezzo dell'annuncio
				Double.parseDouble(moneyText);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Inserisci un importo valido in denaro!");
				return false;
			}
		}
        return true;
	}
	
	public String getMessaggio() {
		return messaggioArea.getText();
	}
	
	public Annuncio getAnnuncio() {
		return annuncio;
	}
	
	public double getOfferta() {
		return Double.valueOf(moneyTextField.getText());
	}
	
	public ArrayList<Oggetto> getOggettiOfferti() {
		return oggettiSelezionati;
	}
	
}
