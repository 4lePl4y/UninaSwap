package controller;

import java.awt.EventQueue;
import java.sql.Connection;

import java.util.ArrayList;
import dao.*;
import db.DBConnection;
import entities.annuncio.*;
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
	
	//**Apre il frame per creare un nuovo oggetto*/
	public void onNuovoOggettoClicked() {
		newOggettoFrame = new NewOggetto(this);
		newOggettoFrame.setVisible(true);
	}
	
	//**Metodo stub per inserire un oggetto nel database*/
	public void creaOggetto(Oggetto oggetto) {
		oggettoDAO.create(oggetto);
	}
	
	//**Metodo per invocare il metodo del newAnnuncioFrame per creare un nuovo annuncio*/
	public void onCreaAnnuncioClicked() {
		newAnnuncioFrame.onCreaAnnuncioClicked();
	}
	
	//**Metodo stub per inserire un annuncio nel database*/
	public void creaAnnuncio(Annuncio annuncio) {
		annuncioDAO.create(annuncio);
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
	
	
	//**Prende un numero "numeroAnnunci" di annunci dal database*/
	public ArrayList<Annuncio> getAltriAnnunci(int numeroAnnunci, String usernameStudenteLoggato) {
		ArrayList<Annuncio> annunci = annuncioDAO.getAltriAnnunci(numeroAnnunci, usernameStudenteLoggato); 
		return annunci; 
	}
	
	//**Prende gli annunci fatto da un utente dal database*/
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
	public ArrayList<Offerta> getMieOfferte(String usernameUtenteLoggato) {
		ArrayList<Offerta> offerte = offertaDAO.retrieveByOfferente(usernameUtenteLoggato);
		return offerte;
	}
	
	/**Prende le offerte ricevute ad un utente dal database*/
	public ArrayList<Offerta> getMieOfferteRicevute(String usernameUtenteLoggato) {
		ArrayList<Offerta> offerte = offertaDAO.retrieveByRicevente(usernameUtenteLoggato);
		return offerte;
	}
	
	//**Metodo stub per inserire un'offerta nel database*/
	public void creaOfferta(Offerta offerta) {
		offertaDAO.create(offerta);
	}
	
	//**Metodo per recuperare lo studente loggato dal main frame*/
	public Studente getStudenteLoggato() {
		return studenteLoggato;
	}

	
	
}
