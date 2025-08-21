package controller;

import java.awt.EventQueue;
import java.sql.Connection;

import java.util.ArrayList;
import java.time.LocalTime;
import java.time.LocalDate;

import dao.*;
import db.DBConnection;
import entities.annuncio.*;
import entities.enumerazioni.*;
import entities.offerta.*;
import entities.oggetto.*;
import entities.studente.*;
import gui.*;

public class Controller {
	//ATTRIBUTI
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
	public void onGoToSignUpClicked(){
		signUpFrame = new SignUp(this);
		signUpFrame.setVisible(true);
		loginFrame.setVisible(false);
	}

	public void onReturnToLoginClicked() {
		loginFrame.setVisible(true);
		signUpFrame.setVisible(false);
	}
	
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
			mainFrame = new Main(this, chkStudente);
			mainFrame.setVisible(true);
			loginFrame.setVisible(false);
		}
		
	}

	public void onRegisterClicked() {
		signUpFrame.resetWarningLabel();
		if(!signUpFrame.areInputsValid()) {
			return;
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
	

	public void onFaiOffertaClicked(Annuncio annuncio, Studente autore) {
		newOffertaFrame = new NewOfferta(this, annuncio, autore);
		newOffertaFrame.setVisible(true);
	}
	
	public void onNuovoAnnuncioClicked() {
		newAnnuncioFrame = new NewAnnuncio(this);
		newAnnuncioFrame.setVisible(true);
	}
	
	public void onNuovoOggettoClicked() {
		newOggettoFrame = new NewOggetto(this);
		newOggettoFrame.setVisible(true);
	}
	
	public void onCreaAnnuncioClicked() {
		newAnnuncioFrame.onCreaAnnuncioClicked();
	}
	
	public void creaAnnuncio(Annuncio annuncio) {
		annuncioDAO.create(annuncio);
	}
	
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
	
	public void onCreaOggettoFromAnnuncioClicked() {
		newOggettoFrame = new NewOggetto(this);
		newOggettoFrame.setVisible(true);
	}

	
	public ArrayList<Annuncio> getAnnunci(int numeroAnnunci) {
		ArrayList<Annuncio> annunci = annuncioDAO.getAnnunci(numeroAnnunci); 
		return annunci; 
	}
	
	public ArrayList<Annuncio> getMieiAnnunci(String usernameUtenteLoggato) {
		ArrayList<Annuncio> annunci = annuncioDAO.retrieveByAutore(usernameUtenteLoggato); 
		return annunci; 
		
	}
	
	
	
	/**Prende gli oggetti dal database di un utente loggato*/
	public ArrayList<Oggetto> getMieiOggetti(String usernameUtenteLoggato) {
		ArrayList<Oggetto> oggetti = oggettoDAO.retrieveByUsername(usernameUtenteLoggato); // 
		return oggetti;
	}
	
	/**Prende gli oggetti dal main frame, quindi sono già caricati in memoria*/
	public ArrayList<Oggetto> getMieiOggetti(){
		ArrayList<Oggetto> oggetti = mainFrame.getMieiOggetti();
		return oggetti;
	}

	public ArrayList<Offerta> getMieOfferte(String usernameUtenteLoggato) {
		ArrayList<Offerta> offerte = offertaDAO.retrieveByOfferente(usernameUtenteLoggato);
		return offerte;
	}
	
	public ArrayList<Offerta> getMieOfferteRicevute(String usernameUtenteLoggato) {
		ArrayList<Offerta> offerte = offertaDAO.retrieveByRicevente(usernameUtenteLoggato);
		return offerte;
	}

	

	
	
	public void creaOfferta(Offerta offerta) {
		offertaDAO.create(offerta);
	}
	
	public Studente getStudenteLoggato() {
		return mainFrame.getStudenteLoggato();
	}

	
	
}
