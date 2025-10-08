package gui.offerta;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;

import controller.Controller;
import exception.CustomSQLException;
import entities.annuncio.*;
import entities.oggetto.*;
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

    public NewOfferta(Controller controller, Annuncio annuncio) {
    	super(controller.getMainFrame(), "Nuova Offerta", true);
        this.controller = controller;
        this.annuncio = annuncio;
        this.setSize(530, 400);
        super.setLocationRelativeTo(null);
        this.setFocusable(true);
        this.setResizable(false);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(255, 255, 255));

        // Welcoming label
        welcomingLabel = new JLabel();
        welcomingLabel.setBounds(10, 10, 386, 30);
        welcomingLabel.setHorizontalAlignment(SwingConstants.LEFT);
        welcomingLabel.setText("Fai un'offerta per: " + annuncio.getTitolo() + " di " + annuncio.getAutore().getUsername());
        welcomingLabel.setFont(new Font("Montserrat", Font.PLAIN, 15));
        contentPane.add(welcomingLabel);

        // Description area
        messaggioArea = new JWritableTextArea("Aggiungi un messaggio alla tua offerta...");
        messaggioArea.setBounds(10, 43, 390, 75);
        messaggioArea.setLineWrap(true);
        messaggioArea.setWrapStyleWord(true);
        messaggioArea.setMaximumSize(new Dimension(400, 80));
        contentPane.add(messaggioArea);

        // Submit button
        submitButton = new JButtonWithBorder("Fai Offerta", Controller.APP_BLUE);
        submitButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        submitButton.addActionListener(e -> onInviaOffertaClicked());
        contentPane.add(submitButton);

        if(annuncio instanceof AnnuncioScambio) {		
        	JPanel barterPanel = new JPanel();
        	barterPanel.setBounds(10, 130, 500, 150);
        	contentPane.add(barterPanel);
        	barterPanel.setLayout(null);
        	barterPanel.setOpaque(false);
        	
        	JLabel chooseObjectLabel = new JLabel("Scegli gli oggetti da scambiare: ");
        	chooseObjectLabel.setBounds(0, 0, 200, 30);
        	barterPanel.add(chooseObjectLabel);
        	
        	objectsListPanel = new JCustomList<Oggetto>(controller.getMieiOggetti(), JCustomList.Mode.MULTIPLE_SELECTION, 500, 80);
        	objectsListPanel.setBounds(0, 30, 500, 80);
        	barterPanel.add(objectsListPanel);        	
        	
        	JLabel optionLabel = new JLabel("oppure");
        	optionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        	optionLabel.setBounds(0, 120, 60, 30);
        	barterPanel.add(optionLabel);
        	
        	JButton addObjectButton = new JButtonWithBorder("Aggiungi un nuovo oggetto +", Controller.APP_BLUE);
        	addObjectButton.setBounds(100, 120, 250, 30);
        	addObjectButton.addActionListener(e -> {
				controller.onApriOggettoFrameClicked();
			});
        	barterPanel.add(addObjectButton);		
        	
        	submitButton.setBounds(180, 320, 133, 28);
        	
        } else {       	
        	JPanel moneyPanel = new JPanel();
        	moneyPanel.setBounds(10, 144, 386, 30);
        	contentPane.add(moneyPanel);
        	moneyPanel.setLayout(null);
        	moneyPanel.setOpaque(false);
        	
        	JLabel moneyLabel = new JLabel("Aggiungi la tua offerta in denaro: ");
        	moneyLabel.setBounds(0, 0, 230, 23);
        	moneyPanel.add(moneyLabel);
        	
        	moneyTextField = new JPriceTextField("€");
        	moneyTextField.setBounds(200, 2, 96, 25);
        	moneyPanel.add(moneyTextField);
        	moneyTextField.setColumns(10);			

        	submitButton.setBounds(130, 200, 133, 30);
        	this.setSize(425, 280);
        }

        
    }
    
    // METODI 
    // getter
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
	
	private ArrayList<Oggetto> getOggettiSelezionati() {
		return objectsListPanel.getSelectedValues();
	}

	// altri metodi
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
	
	private boolean areInputsValid() {
        if(annuncio instanceof AnnuncioScambio) {
        	oggettiSelezionati = getOggettiSelezionati();
			if(oggettiSelezionati == null) {
				JOptionPane.showMessageDialog(this, "Devi selezionare almeno un oggetto da scambiare!");
				return false;
			}
        } else {
			String moneyText = moneyTextField.getText();
			try {
				Double.parseDouble(moneyText);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Inserisci un importo valido in denaro!");
				return false;
			}
		}
        return true;
	}
	

	
}
