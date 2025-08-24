package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import entities.annuncio.Annuncio;
import entities.annuncio.AnnuncioScambio;
import entities.offerta.Offerta;
import entities.offerta.OffertaDenaro;
import entities.offerta.OffertaScambio;
import entities.oggetto.Oggetto;
import entities.studente.Studente;
import gui.preset.JButtonWithBorder;
import gui.preset.JWritableTextArea;
import gui.preset.presetJTextField.JDoubleTextField;

public class ModifyOfferta extends JFrame{
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private Offerta offerta;
	private Annuncio annuncio;
	private Studente autore;
	private JPanel contentPane;
	private JLabel welcomingLabel;
	private JWritableTextArea messaggioArea;
	private ArrayList<Oggetto> mieiOggetti;
	private ArrayList<JCheckBox> checkBoxes;
	private JDoubleTextField moneyTextField;
	private JButtonWithBorder modifyButton;
	private ArrayList<Oggetto> oggettiSelezionati;
	
	public ModifyOfferta(Controller controller, Offerta offerta) {
		this.controller = controller;
        this.offerta = offerta;
        this.annuncio = offerta.getAnnuncio();
        this.autore = annuncio.getAutore();
        
        this.setTitle("Modifica Offerta");
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
        welcomingLabel.setText("Modifica la tua offerta per: " + annuncio.getTitolo() + " di " + autore.getUsername());
        welcomingLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        contentPane.add(welcomingLabel);

        // Description area
        messaggioArea = new JWritableTextArea("Inserisci un nuovo messaggio per la tua offerta...");
        messaggioArea.setText(offerta.getMessaggio());
        messaggioArea.setForeground(Color.BLACK);
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
        	
        	JLabel moneyLabel = new JLabel("Modifica la tua precedente offerta: ");
        	moneyLabel.setBounds(0, 0, 230, 23);
        	moneyPanel.add(moneyLabel);
        	
        	moneyTextField = new JDoubleTextField("€");
        	moneyTextField.setText(String.valueOf(((OffertaDenaro)offerta).getOfferta()));
        	moneyTextField.setForeground(Color.BLACK);
        	moneyTextField.setBounds(200, 2, 96, 19);
        	moneyPanel.add(moneyTextField);
        	moneyTextField.setColumns(10);			
        }

        // Submit button
        modifyButton = new JButtonWithBorder("Modifica Offerta");
        modifyButton.setBounds(130, 296, 160, 28);
        modifyButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        modifyButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				onModificaOffertaClicked();
			}
		});
        contentPane.add(modifyButton);
        
    }

    public void onModificaOffertaClicked() {
    	if(!areInputsValid())
    		return;
    	controller.onModificaOffertaClicked(this.offerta);
    	JOptionPane.showMessageDialog(this, "Offerta modificata!");
        dispose();
    }

	private ArrayList<Oggetto> getOggettiSelezionati() {
		ArrayList<Oggetto> oggettiSelezionati = new ArrayList<>();
		for (int i=0; i<this.checkBoxes.size(); i++) {
			if(checkBoxes.get(i).isSelected())
				oggettiSelezionati.add(mieiOggetti.get(i));
		}
		
		return oggettiSelezionati;
	}
    
	private boolean areInputsValid() {
        if(annuncio instanceof AnnuncioScambio) {
			oggettiSelezionati = getOggettiSelezionati();
			if(oggettiSelezionati.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Devi selezionare almeno un oggetto da scambiare!");
				return false;
			}
			((OffertaScambio)this.offerta).setOggettiOfferti(oggettiSelezionati);
        } else {
			String moneyText = moneyTextField.getText();
			double money;
			try {
				money = Double.parseDouble(moneyText);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Inserisci un importo valido in denaro!");
				return false ;
			}
		}
        return true;
	}
	
	public String getMessaggio() {
		return messaggioArea.getText().equals("Aggiungi un messaggio alla tua offerta...") ? "" : messaggioArea.getText();
	}
	
	public double getOfferta() {
		return Double.valueOf(moneyTextField.getText());
	}
	
	public ArrayList<Oggetto> getOggettiOfferti(){
		return oggettiSelezionati;
	}
}
	

