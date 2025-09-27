package gui.offerta;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import entities.annuncio.Annuncio;
import entities.annuncio.AnnuncioScambio;
import entities.offerta.Offerta;
import entities.offerta.OffertaDenaro;
import entities.oggetto.Oggetto;
import entities.studente.Studente;
import gui.preset.JCustomList;
import gui.preset.JWritableTextArea;
import gui.preset.presetJButton.JButtonWithBorder;
import gui.preset.presetJTextField.JPriceTextField;

public class ModifyOfferta extends JDialog{
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private Offerta offerta;
	private Annuncio annuncio;
	private Studente autore;
	
	private JPanel contentPane;
	private JLabel welcomingLabel;
	private JWritableTextArea messaggioArea;
	private JPriceTextField moneyTextField;
	private JButtonWithBorder modifyButton;
	private ArrayList<Oggetto> oggettiSelezionati;
	private JCustomList<Oggetto> objectsListPanel;
	
	public ModifyOfferta(Controller controller, Offerta offerta) {	
		super(controller.getMainFrame(), "Modifica Offerta", true);
		this.controller = controller;
        this.offerta = offerta;
        this.annuncio = offerta.getAnnuncio();
        this.autore = annuncio.getAutore();
        this.setSize(530, 370);
        this.setFocusable(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(new Color(255, 255, 255));

        // Welcoming label
        welcomingLabel = new JLabel();
        welcomingLabel.setBounds(10, 10, 386, 30);
        welcomingLabel.setText("Modifica la tua offerta per: " + annuncio.getTitolo() + " di " + autore.getUsername());
        welcomingLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        contentPane.add(welcomingLabel);

        // Description area
        messaggioArea = new JWritableTextArea("Inserisci un nuovo messaggio per la tua offerta...");
        messaggioArea.setBounds(10, 43, 390, 75);
        messaggioArea.setMaximumSize(new Dimension(400, 80));
        if(!offerta.getMessaggio().isBlank()) {
        	messaggioArea.setText(offerta.getMessaggio());
        	messaggioArea.setForeground(Color.BLACK);
        }
        contentPane.add(messaggioArea);
        
        // Submit button
        modifyButton = new JButtonWithBorder("Modifica Offerta", Controller.APP_BLUE);
        modifyButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        modifyButton.addActionListener(e -> onModificaOffertaClicked());
        contentPane.add(modifyButton);
        
        // Fields area
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
        	addObjectButton.setBounds(100, 115, 250, 30);
        	addObjectButton.addActionListener(e -> {
				controller.onApriOggettoFrameClicked();
			});
        	barterPanel.add(addObjectButton);			        
        	
        	modifyButton.setBounds(170, 290, 150, 30);
        	
        } else {       	
        	JPanel moneyPanel = new JPanel();
        	moneyPanel.setBounds(10, 144, 386, 30);
        	moneyPanel.setLayout(null);
        	moneyPanel.setOpaque(false);
        	contentPane.add(moneyPanel);
        	
        	JLabel moneyLabel = new JLabel("Modifica la tua precedente offerta: ");
        	moneyLabel.setBounds(0, 0, 230, 23);
        	moneyPanel.add(moneyLabel);
        	
        	moneyTextField = new JPriceTextField("€");
        	moneyTextField.setText(String.valueOf( ((OffertaDenaro)offerta).getOfferta()) );
        	moneyTextField.setForeground(Color.BLACK);
        	moneyTextField.setBounds(200, 2, 96, 25);
        	moneyPanel.add(moneyTextField);
        	moneyTextField.setColumns(10);			
        	
        	modifyButton.setBounds(130, 190, 150, 30);
        	this.setSize(425, 280);
        }

        
    }
	
	// METODI

	// Getter
	public String getMessaggio() {
		return messaggioArea.getText();
	}
	
	public double getOfferta() {
		return moneyTextField.getPrezzo();
	}
	
	public ArrayList<Oggetto> getOggettiOfferti(){
		return this.oggettiSelezionati;
	}
	
	// Altri metodi
    public void onModificaOffertaClicked() {
    	if(areInputsValid()) {
    		controller.onModificaOffertaClicked(this.offerta);
    		JOptionPane.showMessageDialog(this, "Offerta modificata!");
    		this.dispose();
    	}
    	return;
    }

    //TODO: Identico al metodo di newOfferta
	private boolean areInputsValid() {
        if(annuncio instanceof AnnuncioScambio) {
        	this.oggettiSelezionati = getOggettiSelezionati();
			if(this.oggettiSelezionati.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Devi selezionare almeno un oggetto da scambiare!");
				return false;
			}

        } else {
        	try {
        		//TODO: Verificare se l'offerta proposta è <= del prezzo dell'annuncio
        		Double.parseDouble(moneyTextField.getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Inserisci un importo valido in denaro!");
				return false ;
			}
		}

        return true;
	}
	
	private ArrayList<Oggetto> getOggettiSelezionati() {
		return objectsListPanel.getSelectedValues();
	}
	
	
}
	

