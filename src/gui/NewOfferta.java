package gui;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controller.Controller;
import entities.annuncio.*;
import entities.offerta.*;
import entities.oggetto.*;
import entities.studente.*;
import gui.preset.JCustomTextArea;
import gui.preset.presetJTextField.JDoubleTextField;
import gui.preset.JButtonWithBorder;

public class NewOfferta extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Controller controller;
    private Annuncio annuncio;
    private Studente autore;
    private JLabel welcomingLabel;
    private JCustomTextArea messaggioArea;
    private JButtonWithBorder submitButton;
    private JDoubleTextField moneyTextField;
    private ArrayList<Oggetto> mieiOggetti;
    private ArrayList<JCheckBox> checkBoxes; 

    public NewOfferta(Controller controller, Annuncio annuncio, Studente autore) {
        this.controller = controller;
        this.annuncio = annuncio;
        this.autore = autore;
        
        this.setTitle("Nuova Offerta");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(new Rectangle(0, 0, 420, 400));
        this.setFocusable(true);
        this.setResizable(false);
        this.setAlwaysOnTop(true);

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
        messaggioArea = new JCustomTextArea("Aggiungi un messaggio alla tua offerta...");
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
        	
        	mieiOggetti = controller.getMieiOggetti();
        	JPanel checkBoxPanel = new JPanel();
        	checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
        	this.checkBoxes = new ArrayList<>();
        	for (Oggetto oggetto : mieiOggetti) {
        	    JCheckBox checkBox = new JCheckBox(oggetto.toString());
        	    checkBoxPanel.add(checkBox);
        	    checkBoxes.add(checkBox);
        	}
        	
        	JScrollPane scrollPane = new JScrollPane(checkBoxPanel);
        	scrollPane.setBounds(0, 20, 200, 100);
        	barterPanel.add(scrollPane);
        	
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
        	
        	moneyTextField = new JDoubleTextField("€");
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
    	String messaggio = messaggioArea.getText().equals("Aggiungi un messaggio alla tua offerta...") ? "" : messaggioArea.getText();
    	Studente studenteLoggato = controller.getStudenteLoggato();
    	Offerta offerta = null;
    	
        if(annuncio instanceof AnnuncioScambio) {
			ArrayList<Oggetto> oggettiSelezionati = getOggettiSelezionati();
			if(oggettiSelezionati.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Devi selezionare almeno un oggetto da scambiare!");
				return;
			}
			offerta = new OffertaScambio(messaggio, studenteLoggato, annuncio, oggettiSelezionati);
		
        } else {
			String moneyText = moneyTextField.getText();
			double money;
			try {
				money = Double.parseDouble(moneyText);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Inserisci un importo valido in denaro!");
				return;
			}
			offerta = new OffertaDenaro(messaggio, studenteLoggato, annuncio, money);
		}
		
        JOptionPane.showMessageDialog(this, "Offerta inviata!");
        dispose();
        controller.creaOfferta(offerta);
    }

	private ArrayList<Oggetto> getOggettiSelezionati() {
		ArrayList<Oggetto> oggettiSelezionati = new ArrayList<>();
		for (int i=0; i<this.checkBoxes.size(); i++) {
			if(checkBoxes.get(i).isSelected())
				oggettiSelezionati.add(mieiOggetti.get(i));
		}
		
		return oggettiSelezionati;
	}
}
