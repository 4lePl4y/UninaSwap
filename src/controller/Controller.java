package controller;

import java.awt.EventQueue;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import dao.*;
import db.DBConnection;
import entities.annuncio.Annuncio;
import entities.annuncio.AnnuncioRegalo;
import entities.annuncio.AnnuncioVendita;
import entities.enumerazioni.Sede;
import entities.oggetto.Oggetto;
import entities.studente.*;
import gui.*;

public class Controller {
	//ATTRIBUTI
	Login loginFrame; 
	SignUp signUpFrame; 
	Main mainFrame;
	NewOfferta newOffertaFrame;
	NewAnnuncio newAnnuncioFrame;
	NewOggetto newOggettoFrame;
	StudenteDAO studenteDAO;
	
	public static void main(String[] args) {
		Controller controller = new Controller();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connection conn = null;
					DBConnection dbConnection = DBConnection.getDBConnection();
					conn = dbConnection.getConnection();
					System.out.println("Connessione OK!");
					 	 					
					controller.loginFrame = new Login(controller);
					controller.loginFrame.setVisible(true);
					controller.mainFrame = new Main(controller);
					controller.mainFrame.setVisible(false);
					controller.studenteDAO = new StudenteDAO(conn);
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
//		loginFrame.resetWarningLabel();
//		String username = loginFrame.getUsername();
//		String password = loginFrame.getPassword();
//		Studente chkStudente = studenteDAO.retrieveByPK(username);
//		if(chkStudente == null) {
//			loginFrame.setWarningLabel("Attenzione! Username errato");
//			
//			return;
//		}else if(chkStudente != null && !chkStudente.getPassword().equals(password)) {
//			loginFrame.setWarningLabel("Attenzione! Password errata");
//			return; 
//		} else {
			mainFrame.setVisible(true);
			loginFrame.setVisible(false);
//		}
		
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
		newOffertaFrame = new NewOfferta(this);
		newOffertaFrame.setVisible(true);
		newOffertaFrame.setAnnuncio(annuncio);
		newOffertaFrame.setStudente(autore);
	}
	
	public void onNuovoAnnuncioClicked() {
		newAnnuncioFrame = new NewAnnuncio(this);
		newAnnuncioFrame.setVisible(true);
	}
	
	public void onNuovoOggettoClicked() {
		newOggettoFrame = new NewOggetto(this);
		newOggettoFrame.setVisible(true);
	}

	
	public ArrayList<Annuncio> getAnnunci() {
		//TODO: Questo metodo chiama getAnnunci(int numeroAnnunci) presente in AnnuncioDAO.java per ottenere un certo numero di annunci dal database.
		//Il codice qui sotto è un placeholder che crea una lista di annunci fittizi per il caricamento iniziale dell'applicazione.
		
		ArrayList<Annuncio> annunci = new ArrayList<>();
		Studente studente = null; 
		Oggetto oggetto = null;
		for(int i=0; i<30; i++) {
			annunci.add(new AnnuncioVendita("titolo", studente, oggetto, "descrizione", Sede.MonteSantAngelo, LocalTime.now(), LocalDate.now(), i*10 )); // Placeholder for the first card, if needed
			annunci.add(new AnnuncioRegalo("titolo", studente, oggetto, "descrizione", Sede.MonteSantAngelo, LocalTime.now(), LocalDate.now())); // Placeholder for the first card, if needed		
		}
		return annunci; 
	}

	
	
}
