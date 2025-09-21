package gui.preset.presetJPanel.presetJCard; 

import controller.Controller;
import entities.enumerazioni.Stato;
import entities.offerta.*; 
import entities.oggetto.Oggetto; 
import entities.studente.Studente;
import gui.preset.JDisplayTextArea;
import gui.preset.presetJButton.JButtonWithBorder;
import gui.preset.presetJLabel.JInteractiveLabel; 
import javax.swing.JLabel; 
import javax.swing.JList; 
import javax.swing.JScrollPane;
import javax.swing.JButton; 
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JOfferCard extends JCard { 
	private static final long serialVersionUID = 1L; 
	private Offerta offerta; 
	private Studente offerente; 
	private JButton acceptButton; 
	private JButton declineButton; 
	private JButton deleteButton;
	private JButtonWithBorder modifyButton; 
	
	public JOfferCard(Offerta offerta, Controller controller) { 
		super(controller); 
		this.offerta = offerta; 
		this.offerente = offerta.getOfferente(); 
		this.setBounds(0, 0, 280, 450); 
		this.setLayout(null);
		
		JLabel welcomingLabel = new JLabel("Dettagli dell'offerta: "); 
		welcomingLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15)); 
		welcomingLabel.setBounds(10, 10, 260, 18); add(welcomingLabel); 
		
		JLabel listingTitleLabel = new JLabel("Annuncio: "+offerta.getAnnuncio().getTitolo()); 
		listingTitleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15)); 
		listingTitleLabel.setBounds(10, 38, 260, 18); add(listingTitleLabel); 
		
		JDisplayTextArea listingDescriptionTextArea = new JDisplayTextArea("Descrizione annuncio: " + offerta.getAnnuncio().getDescrizione());
		JScrollPane listingDescriptionScrollPane = new JScrollPane(listingDescriptionTextArea);
		listingDescriptionScrollPane.setBounds(10, 66, 260, 54);
		listingDescriptionScrollPane.setBorder(null);
		
		if(!(offerente.getUsername().equals(controller.getStudenteLoggato().getUsername()))) { 
			JLabel authorLabel = new JInteractiveLabel("Offerente: " + offerente.getUsername()); 
			authorLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15)); 
			authorLabel.setBounds(10, 66, 260, 18); 
			listingDescriptionTextArea.setBounds(10, 94, 260, 54); add(authorLabel); 
		} 
		add(listingDescriptionTextArea); 
		
		if(offerta instanceof OffertaScambio os) { 
			JLabel offeredObjectsLabel = new JLabel("Oggetti offerti in scambio: "); 
			offeredObjectsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15)); 
			offeredObjectsLabel.setBounds(10, 148, 260, 18); 
			add(offeredObjectsLabel); 
		
			JList<Oggetto> list = new JList<>(os.getOggettiOfferti().toArray(new Oggetto[0]));
            JScrollPane sp = new JScrollPane(list);
            sp.setBounds(10, 166, 260, 60);
            sp.setBorder(null);
            add(sp);
            
		}else{ 
			JLabel moneyLabel = new JLabel("Denaro offerto: " + ((OffertaDenaro)offerta).getOfferta()); 
			moneyLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15)); 
			moneyLabel.setBounds(10, 148, 260, 18); add(moneyLabel); 
		} 
		

		JDisplayTextArea messageTextArea = new JDisplayTextArea("Messaggio: " + offerta.getMessaggio());
		JScrollPane messageScrollPane = new JScrollPane(messageTextArea);
		messageScrollPane.setBounds(10, 212, 260, 80);
		messageScrollPane.setBorder(null);

		add(messageScrollPane);

		JLabel statusLabel = new JLabel("Stato offerta: " + offerta.getStato()); 
		statusLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15)); 
		statusLabel.setBounds(10, 350, 260, 18); add(statusLabel); 
		
		if(offerta.getStato().equals(Stato.InAttesa)) {
			if(!(offerente.getUsername().equals(controller.getStudenteLoggato().getUsername())))
				addAcceptNDeclineButtons();
			else
				addModifyNDeleteButton();
		}
			
	}
	
	public void onAccettaOffertaClicked() {
		controller.onAccettaOffertaClicked(offerta);
	}
	
	public void onRifiutaOffertaClicked() {
		controller.onRifiutaOffertaClicked(offerta);
	}

	public void onModificaOffertaFrameClicked() {
		controller.onModificaOffertaFrameClicked(offerta);
	}
	
	public void onCancellaOffertaClicked() {
		controller.onCancellaOffertaClicked(offerta);
	}
	
	
	private void addAcceptNDeclineButtons() {
		acceptButton = new JButtonWithBorder("Accetta"); 
		acceptButton.setBounds(30, 410, 100, 30); 
		acceptButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				onAccettaOffertaClicked();
			}
		});
		add(acceptButton); 
		
		declineButton = new JButtonWithBorder("Rifiuta"); 
		declineButton.setBounds(155, 410, 100, 30); 
		declineButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				onRifiutaOffertaClicked();
			}
		});
		add(declineButton);
	}
	
	private void addModifyNDeleteButton() {
		modifyButton = new JButtonWithBorder("Modifica"); 
		modifyButton.setBounds(30, 410, 100, 30); 
		modifyButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				onModificaOffertaFrameClicked();
			}
		});
		add(modifyButton);
		
		deleteButton = new JButtonWithBorder("Ritira"); 
		deleteButton.setBounds(155, 410, 100, 30); 
		deleteButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				onCancellaOffertaClicked();
			}
		});
		add(deleteButton);
	}
		
}
