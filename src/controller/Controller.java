package controller;

import java.awt.EventQueue;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.*;
import db.DBConnection;
import entities.annuncio.*;
import entities.enumerazioni.Sede;
import entities.enumerazioni.TipoAnnuncio;
import entities.offerta.*;
import entities.oggetto.*;
import entities.studente.*;
import gui.*;

public class Controller {
	//ATTRIBUTI
	Studente studenteLoggato;
	
	//FRAME
	Login loginFrame; 
	SignUp signUpFrame; 
	Main mainFrame;
	NewOfferta newOffertaFrame;
	NewAnnuncio newAnnuncioFrame;
	NewOggetto newOggettoFrame;
	ModifyAnnuncio modifyAnnuncioFrame;
	ModifyOfferta modifyOffertaFrame;
	ModifyOggetto modifyOggettoFrame;
	
	//DAO
	AnnuncioDAO annuncioDAO;
	OffertaDAO offertaDAO;
	StudenteDAO studenteDAO;
	OggettoDAO oggettoDAO;

	
	
	public static void main(String[] args) {
		Controller controller = new Controller();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connection conn = null;
					DBConnection dbConnection = DBConnection.getDBConnection();
					conn = dbConnection.getConnection();
					System.out.println("Connessione OK!");
					 	 					
					controller.studenteDAO = new StudenteDAO(conn);
					controller.oggettoDAO = new OggettoDAO(conn);
					controller.annuncioDAO = new AnnuncioDAO(conn);
					controller.offertaDAO = new OffertaDAO(conn);
					
					controller.loginFrame = new Login(controller);
					controller.loginFrame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//METODI
	//**Metodo per la navigazione dal LoginFrame al SignUpFrame*/
	public void onGoToSignUpClicked(){
		signUpFrame = new SignUp(this);
		signUpFrame.setVisible(true);
		loginFrame.setVisible(false);
	}

	//**Metodo per la navigazione dal SignUpFrame al LoginFrame*/
	public void onReturnToLoginClicked() {
		loginFrame.setVisible(true);
		signUpFrame.setVisible(false);
	}
	
	//**Metodo per l'autenticazione dell'utente*/
	public void onLoginClicked() {
		loginFrame.resetWarningLabel();
		String username = loginFrame.getUsername();
		String password = loginFrame.getPassword();
		Studente chkStudente = studenteDAO.retrieveByPK(username);
		if(chkStudente == null) {
			loginFrame.setWarningLabel("Attenzione! Username errato");
			
			return;
		}else if(chkStudente != null && !chkStudente.getPassword().equals(password)) {
			loginFrame.setWarningLabel("Attenzione! Password errata");
			return; 
		} else {
			studenteLoggato = chkStudente;
			mainFrame = new Main(this);
			mainFrame.setVisible(true);
			loginFrame.setVisible(false);
		}
		
	}

	//**Metodo per la registrazione di un nuovo utente*/
	public void onRegisterClicked() {
		signUpFrame.resetWarningLabel();
		if(!signUpFrame.areInputsValid()) {
			return; // Se i campi non sono validi, non procedere
		}
		
		String newName = signUpFrame.getName();
		String newSurname = signUpFrame.getSurname();
		String newUsername = signUpFrame.getUsername();
		String newEmail = signUpFrame.getEmail();
		String newPassword = signUpFrame.getPassword();

		Studente chkStudente = studenteDAO.retrieveByPK(newUsername);
		if(chkStudente != null) {
			signUpFrame.setWarningLabel("Attenzione! Username già in uso");
			signUpFrame.setUsername("Username");
			return;
		}
		
		chkStudente = studenteDAO.retrieveByEmail(newEmail);
		if(chkStudente != null) {
			signUpFrame.setWarningLabel("Attenzione! Email già in uso");
			signUpFrame.setEmail("Email");
			return;
		}
		
		chkStudente = new Studente(newName, newSurname, newUsername, newEmail, newPassword); 
		studenteDAO.create(chkStudente);
		loginFrame.setVisible(true);
		signUpFrame.setVisible(false);
	}

	//--NAVIGABILTY RELATED METHODS--//
	
	//**Apre il frame per fare una nuova offerta*/
	public void onFaiOffertaClicked(Annuncio annuncio, Studente autore) {
		newOffertaFrame = new NewOfferta(this, annuncio, autore);
		newOffertaFrame.setVisible(true);
	}
	
	//**Apre il frame per creare un nuovo annuncio*/
	public void onNuovoAnnuncioClicked() {
		newAnnuncioFrame = new NewAnnuncio(this);
		newAnnuncioFrame.setVisible(true);
	}
	
	//**Apre il frame per modificare un annuncio esistente*/
	public void onModificaAnnuncioFrameClicked(Annuncio annuncio) {
		modifyAnnuncioFrame = new ModifyAnnuncio(this, annuncio);
		modifyAnnuncioFrame.setVisible(true);
	}
	
	//**Apre il frame per creare un nuovo oggetto*/
	public void onNuovoOggettoClicked() {
		newOggettoFrame = new NewOggetto(this);
		newOggettoFrame.setVisible(true);
	}
	
	//**Metodo per modificare i campi di un'offerta presente nel DB*/
	public void onModificaOffertaFrameClicked(Offerta offerta) {
		modifyOffertaFrame = new ModifyOfferta(this, offerta);
		modifyOffertaFrame.setVisible(true);
	}
	
	public void onModificaOggettoFrameClicked(Oggetto oggetto) {
		modifyOggettoFrame = new ModifyOggetto(this, oggetto);
		modifyOggettoFrame.setVisible(true);
	}
	
	//--OBJECTS RELATED METHODS--//
	
	//**Metodo per creare un nuovo oggetto nel database*/
	public void onCreaOggettoClicked(Oggetto oggetto) {
		//TODO: spostare gran parte del contenuto del metodo onCreaOggettoClicked di NewOggetto nel metodo omonimo del controller
		oggettoDAO.create(oggetto);
		mainFrame.refreshMyObjects();
		if((newAnnuncioFrame != null) && (newAnnuncioFrame.isVisible()))
			newAnnuncioFrame.refreshOggettiEsistenti();
	}
	
	public void onCancellaOggettoClicked(Oggetto oggetto) {
		oggettoDAO.delete(String.valueOf(oggetto.getId()));
		mainFrame.refreshMyObjects();
		mainFrame.refreshAllOffers();
		mainFrame.refreshListings();
	}
	
	public void onModificaOggettoClicked(Oggetto oggetto) {
		oggetto.setNome(modifyOggettoFrame.getNome());
		switch (oggetto) {
        	case StrumentoMusicale sm -> {sm.setMarchio(modifyOggettoFrame.getMarchio());}
        	case Abbigliamento a -> {
        		a.setMarchio(modifyOggettoFrame.getMarchio());
        		a.setTaglia(modifyOggettoFrame.getTaglia());
        	}
        	case Elettronica e -> {
        		e.setMarchio(modifyOggettoFrame.getMarchio());
        		e.setModello(modifyOggettoFrame.getModello());
        		e.setAnnoUscita(modifyOggettoFrame.getAnnoUscita());
        	}
        	case Libro l ->{
        		l.setTitolo(modifyOggettoFrame.getTitolo());
        		l.setISBN(modifyOggettoFrame.getISBN());
        		l.setAnnoUscita(modifyOggettoFrame.getAnnoUscita());
        		l.setAutore(modifyOggettoFrame.getAutore());
        		l.setGenere(modifyOggettoFrame.getGenere());	
        	}
        	case Misc m -> {
        		m.setMarchio(modifyOggettoFrame.getMarchio());
        		m.setCategoria(modifyOggettoFrame.getCategoria());
        	}
        	default -> {}
		}
		oggettoDAO.update(oggetto);
		JOptionPane.showMessageDialog(modifyOggettoFrame, "Oggetto modificato!");
		modifyOggettoFrame.dispose();
		
		mainFrame.refreshMyObjects();
		mainFrame.refreshMadeOffers();
	}
	
	//--LISTINGS RELATED METHODS--//
	
	public void onCreaAnnuncioClicked(Annuncio annuncio) {
		//TODO: spostare gran parte del contenuto del metodo onCreaAnnuncioClicked di NewAnnuncio nel metodo omonimo del controller
		annuncioDAO.create(annuncio);
		mainFrame.refreshListings();
	}
	
	public void onCancellaAnnuncioClicked(Annuncio annuncio) {
		annuncioDAO.delete(String.valueOf(annuncio.getId()));
		mainFrame.refreshListings();
		mainFrame.refreshReceivedOffers();
	}
	
	//**Metodo per modificare un annuncio nel database*/
	public void onModificaAnnuncioClicked(Annuncio annuncio) {
		annuncio.setTitolo(modifyAnnuncioFrame.getTitolo());
		annuncio.setDescrizione(modifyAnnuncioFrame.getDescrizione());
		annuncio.setSede(modifyAnnuncioFrame.getSede());
		annuncio.setOraIncontro(modifyAnnuncioFrame.getOraIncontro());
		
		if(annuncio instanceof AnnuncioVendita av) {
			av.setPrezzo(modifyAnnuncioFrame.getPrezzo());
			annuncioDAO.update(av);
		}else if (annuncio instanceof AnnuncioScambio as)
			annuncioDAO.update(as);
		else
			annuncioDAO.update(((AnnuncioRegalo)annuncio));
		
		JOptionPane.showMessageDialog(modifyAnnuncioFrame, "Annucio modificato!");
		modifyAnnuncioFrame.dispose();
		
		mainFrame.refreshListings();
		mainFrame.refreshReceivedOffers();
	}
	
	//--OFFERS RELATED METHODS--//

	//**Metodo per inserire un'offerta nel database*/
	public void onInviaOffertaClicked() {
		Offerta offerta = null;
		Annuncio annuncioDiOfferta = newOffertaFrame.getAnnuncio();
		if(annuncioDiOfferta instanceof AnnuncioScambio as){
			offerta = new OffertaScambio(newOffertaFrame.getMessaggio(), studenteLoggato, as, newOffertaFrame.getOggettiOfferti());
			offertaDAO.create(offerta);
		}else{
			offerta = new OffertaDenaro(newOffertaFrame.getMessaggio(), studenteLoggato, annuncioDiOfferta, newOffertaFrame.getOfferta());
			offertaDAO.create(offerta);
		}
		mainFrame.refreshMadeOffers();
	}
	
	//**Metodo per modificare lo stato di un'offerta presente nel DB rendendola accettata*/
	public void onAccettaOffertaClicked(Offerta offerta) {
		offertaDAO.updateOffertaAccettata(offerta);
		mainFrame.refreshReceivedOffers();
	}
	
	//**Metodo per modificare lo stato di un'offerta presente nel DB rendendola rifiutata*/
	public void onRifiutaOffertaClicked(Offerta offerta) {
		offertaDAO.updateOffertaRifiutata(offerta);
		mainFrame.refreshReceivedOffers();
	}
	
	public void onModificaOffertaClicked(Offerta offerta) {
		offerta.setMessaggio(modifyOffertaFrame.getMessaggio());
		if(offerta instanceof OffertaScambio of) {
			of.setOggettiOfferti(modifyOffertaFrame.getOggettiOfferti());
			offertaDAO.update(of);			
		}else {
			((OffertaDenaro)offerta).setOfferta(modifyOffertaFrame.getOfferta());
			offertaDAO.update(((OffertaDenaro)offerta));
		}
		mainFrame.refreshMadeOffers();
	}
	
	//**Metodo per cancellare un'offerta presente nel DB*/
	public void onCancellaOffertaClicked(Offerta offerta) {
		offertaDAO.delete(offerta);
		mainFrame.refreshMadeOffers();
	}
	
	//--REFRESH METHODS--//
	
	//**Metodo stub per aggiornare gli annunci contentuti nel browsePane nel mainFrame*/
	public void refreshBrowse() {
		mainFrame.refreshBrowse();
	}
	
	//**Metodo stub per aggiornare gli annunci contentuti nel listingsPane nel mainFrame*/
	public void refreshListings() {
		mainFrame.refreshListings();
	}
	
	//**Metodo stub per aggiornare gli oggetti contentuti nel MyObjectsPane nel mainFrame*/
	public void refreshMyObjects() {
		mainFrame.refreshMyObjects();
	}
	
	//**Controlla in fase di creazione di annuncio se esistono annunci con lo stesso oggetto incompatibili tra loro*/
	public boolean areAnnunciConStessoOggetto(Annuncio annuncio) {
		ArrayList<Annuncio> annunciConStessoOggetto = annuncioDAO.retrieveByOggetto(annuncio.getOggetto().getId());
		if(annuncio instanceof AnnuncioRegalo) {
			if(!annunciConStessoOggetto.isEmpty())
				return true;//ci sono annunci di scambio o vendita con lo stesso oggetto
		}else{
			for(Annuncio a : annunciConStessoOggetto) {
				if(a instanceof AnnuncioRegalo) 
					return true; //c'è un annuncio di regalo con lo stesso oggetto
			}
		}
		return false;
	}
	
	//**Apre il frame per creare un nuovo oggetto in un ambiente diverso dal myObjectPane*/
	public void onApriOggettoFrameClicked() {
		newOggettoFrame = new NewOggetto(this);
		newOggettoFrame.setVisible(true);
	}
	
	
	//**Prende un numero "numeroAnnunci" di annunci diversi dagli annunci dell'utente loggato dal database*/
	public ArrayList<Annuncio> getAltriAnnunci(int numeroAnnunci, String usernameStudenteLoggato) {
		ArrayList<Annuncio> annunci = annuncioDAO.getAltriAnnunci(numeroAnnunci, usernameStudenteLoggato); 
		return annunci; 
	}
	
	//**Prende gli annunci fatti da un utente dal database*/
	public ArrayList<Annuncio> getMieiAnnunci(String usernameUtenteLoggato) {
		ArrayList<Annuncio> annunci = annuncioDAO.retrieveByAutore(usernameUtenteLoggato); 
		return annunci; 
		
	}
	
	/**Prende gli oggetti dal database di un utente*/
	public ArrayList<Oggetto> getMieiOggetti(String usernameUtenteLoggato) {
		ArrayList<Oggetto> oggetti = oggettoDAO.retrieveByUsername(usernameUtenteLoggato); 
		return oggetti;
	}
	
	/**Prende gli oggetti dal main frame dell'utente già loggato, quindi gli oggetti sono già caricati in memoria*/
	public ArrayList<Oggetto> getMieiOggetti(){
		ArrayList<Oggetto> oggetti = mainFrame.getMieiOggetti();
		return oggetti;
	}

	/**Prende le offerte fatte da un utente dal database*/
	public ArrayList<Offerta> getOfferteFatte(String usernameUtenteLoggato) {
		ArrayList<Offerta> offerte = offertaDAO.retrieveByOfferente(usernameUtenteLoggato);
		return offerte;
	}
	
	/**Prende le offerte ricevute ad un utente dal database*/
	public ArrayList<Offerta> getOfferteRicevute(String usernameUtenteLoggato) {
		ArrayList<Offerta> offerte = offertaDAO.retrieveByRicevente(usernameUtenteLoggato);
		return offerte;
	}
	
	
	//**Metodo per recuperare lo studente loggato dal main frame*/
	public Studente getStudenteLoggato() {
		return studenteLoggato;
	}

	
}
