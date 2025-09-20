package controller;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import dao.*;
import db.DBConnection;

import entities.annuncio.*;
import entities.enumerazioni.Sede;
import entities.enumerazioni.*;
import entities.offerta.*;
import entities.oggetto.*;
import entities.studente.*;

import gui.Main;
import gui.Login;
import gui.SignUp;
import gui.annuncio.*;
import gui.offerta.*;
import gui.oggetto.*;
import gui.profilo.*;
import exception.*;

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
	ModifyEmail modifyEmailFrame;
	ModifyUsername modifyUsernameFrame;
	ModifyPassword modifyPasswordFrame;
	
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
		long startTime = System.nanoTime();  // start timer
		
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
		
		long endTime = System.nanoTime();  // stop timer
	    System.out.println("Tempo esecuzione: " + (endTime - startTime)/1_000_000 + " ms");
	}

	//**Metodo per la registrazione di un nuovo utente*/
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
	
	//**Apre il frame per modificare i campi di un'offerta inviata*/
	public void onModificaOffertaFrameClicked(Offerta offerta) {
		modifyOffertaFrame = new ModifyOfferta(this, offerta);
		modifyOffertaFrame.setVisible(true);
	}
	
	//*Apre il frame per modificare i campi di un oggetto*/
	public void onModificaOggettoFrameClicked(Oggetto oggetto) {
		modifyOggettoFrame = new ModifyOggetto(this, oggetto);
		modifyOggettoFrame.setVisible(true);
	}
	
	//*Apre il frame per modificare l'email*/
	public void openModificaEmailFrame() {
		modifyEmailFrame = new ModifyEmail(mainFrame, this);
		modifyEmailFrame.setVisible(true);
		
	}
	
	//*Apre il frame per modificare l'username*/
	public void openModificaUsernameFrame() {
		modifyUsernameFrame = new ModifyUsername(mainFrame, this);
		modifyUsernameFrame.setVisible(true);
		
	}
	
	//*Apre il frame per modificare la password*/
	public void openModificaPasswordFrame() {
		modifyPasswordFrame = new ModifyPassword(mainFrame, this);
		modifyPasswordFrame.setVisible(true);
	}
	
	
	//--OBJECTS RELATED METHODS--//
	private Oggetto creaOggetto(String nome) {
		Oggetto oggetto = null;
		String marchio;
		Year annoUscita;
		TipoOggetto tipo = newOggettoFrame.getTipoOggetto();
		switch(tipo) {
		case Abbigliamento:
			marchio = newOggettoFrame.getMarchio();
			String taglia = newOggettoFrame.getTaglia();
			oggetto = new Abbigliamento(nome, studenteLoggato, marchio, taglia);
			break;
			
		case Elettronica:
			marchio = newOggettoFrame.getMarchio();
			String modello = newOggettoFrame.getModello();
			annoUscita = newOggettoFrame.getAnnoUscita();
			oggetto = new Elettronica(nome, studenteLoggato, marchio, modello, annoUscita);
			break;
			
		case StrumentoMusicale:
			marchio = newOggettoFrame.getMarchio();
			oggetto = new StrumentoMusicale(nome, studenteLoggato, marchio);
			break;
			
		case Libro:
			annoUscita = newOggettoFrame.getAnnoUscita();
			String titolo = newOggettoFrame.getTitolo();
			String isbn = newOggettoFrame.getIsbn();
			String autore = newOggettoFrame.getAutore();
			String genere = newOggettoFrame.getGenere();
			oggetto = new Libro(nome, studenteLoggato, titolo, isbn, annoUscita, autore, genere);
			break;
			
		case Misc:
			marchio = newOggettoFrame.getMarchio();
			String categoria = newOggettoFrame.getCategoria();
			oggetto = new Misc(nome, studenteLoggato, marchio, categoria);
		}
		
		return oggetto;
	}
	
	//**Metodo per creare un nuovo oggetto nel database*/
	public void onCreaOggettoClicked() {
		String nome = newOggettoFrame.getNome();
		Oggetto oggetto = creaOggetto(nome);
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
		
		mainFrame.refreshMyObjects();
		mainFrame.refreshMadeOffers();
	}
	
	//--LISTINGS RELATED METHODS--//
	
	private Annuncio creaAnnuncio(String titolo, Oggetto oggettoSelezionato, String descrizione, Sede luogo, LocalTime oraIncontro, LocalDate dataPubblicazione) {
		Annuncio annuncio = null;
		TipoAnnuncio tipo = newAnnuncioFrame.getTipoAnnuncio();
		switch (tipo) {
		case Vendita:
			double prezzo = newAnnuncioFrame.getPrezzo();
			annuncio = new AnnuncioVendita(titolo, studenteLoggato, oggettoSelezionato, descrizione, luogo, oraIncontro, dataPubblicazione, prezzo);
			break;
			
		case Scambio:
			annuncio = new AnnuncioScambio(titolo, studenteLoggato, oggettoSelezionato, descrizione, luogo, oraIncontro, dataPubblicazione);
			break;
			
		case Regalo:
			annuncio = new AnnuncioRegalo(titolo, studenteLoggato, oggettoSelezionato, descrizione, luogo, oraIncontro, dataPubblicazione);
		}
		
		return annuncio;
	}
	
	//**Controlla in fase di creazione di annuncio se esistono annunci con lo stesso oggetto incompatibili tra loro*/
	public boolean areAnnunciConStessoOggetto (Annuncio annuncio) throws InvalidListingException {
		ArrayList<Annuncio> annunciConStessoOggetto = annuncioDAO.retrieveByOggetto(annuncio.getOggetto().getId());
		
		if(annuncio instanceof AnnuncioRegalo) {
			if(!annunciConStessoOggetto.isEmpty())
				throw new InvalidListingException("Non puoi creare un annuncio di regalo per questo oggetto. \nEsiste già un suo annuncio di vendita o di scambio");
		}else{
			for(Annuncio a : annunciConStessoOggetto) {
				if(a instanceof AnnuncioRegalo) 
					throw new InvalidListingException("Non puoi creare un annuncio di vendita o di scambio per questo oggetto. \nEsiste già un suo annuncio di regalo");
			}
		}
		return false;
		
	}
	
	public void onCreaAnnuncioClicked() throws CustomSQLException {
		String titolo = newAnnuncioFrame.getTitolo();
		Oggetto oggettoSelezionato = newAnnuncioFrame.getOggettoSelezionato();
		String descrizione = newAnnuncioFrame.getDescrizione();
		Sede luogo = newAnnuncioFrame.getLuogo();
		LocalTime oraIncontro = newAnnuncioFrame.getOraIncontro();
		LocalDate dataPubblicazione = LocalDate.now();
		
		Annuncio annuncio = creaAnnuncio(titolo, oggettoSelezionato, descrizione, luogo, oraIncontro, dataPubblicazione);

		try {
			annuncioDAO.create(annuncio);	
		} catch (CustomSQLException e) {
			throw e;
		}
		mainFrame.refreshListings();
		return;
	}
	
	//**Metodo per cancellare un annuncio nel database*/
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
		
		mainFrame.refreshListings();
		mainFrame.refreshReceivedOffers();
	}
	
	//--OFFERS RELATED METHODS--//

	//**Metodo per inserire un'offerta nel database*/
	public void onInviaOffertaClicked() throws CustomSQLException {
		Offerta offerta = null;
		Annuncio annuncioDiOfferta = newOffertaFrame.getAnnuncio();
		if(annuncioDiOfferta instanceof AnnuncioScambio as)
			offerta = new OffertaScambio(newOffertaFrame.getMessaggio(), studenteLoggato, as, newOffertaFrame.getOggettiOfferti());
		else
			offerta = new OffertaDenaro(newOffertaFrame.getMessaggio(), studenteLoggato, annuncioDiOfferta, newOffertaFrame.getOfferta());
		
		try {
			offertaDAO.create(offerta);
		} catch (CustomSQLException e) {
			throw e;
		}
		mainFrame.refreshMadeOffers();
		return;
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
		if(offerta instanceof OffertaScambio offertaScambio) {
			offertaScambio.setOggettiOfferti(modifyOffertaFrame.getOggettiOfferti());
			offertaDAO.update(offertaScambio);			
		}else {
			OffertaDenaro offertaDenaro = (OffertaDenaro) offerta;
			offertaDenaro.setOfferta(modifyOffertaFrame.getOfferta());
			offertaDAO.update(offertaDenaro);
		}
		mainFrame.refreshMadeOffers();
	}
	
	//**Metodo per cancellare un'offerta presente nel DB*/
	public void onCancellaOffertaClicked(Offerta offerta) {
		offertaDAO.delete(offerta);
		mainFrame.refreshMadeOffers();
	}
	
	
	//--PROFILO METHODS--//
	public void onModificaEmailClicked(Studente studenteLoggato) {
		try {
			String newEmail = modifyEmailFrame.getEmail();
			if(studenteLoggato.getEmail().equals(newEmail)) 
				throw new NoChangeException("La nuova email non può essere uguale a quella vecchia!");
			
			studenteDAO.updateEmail(studenteLoggato, newEmail);
			studenteLoggato.setEmail(newEmail);
			JOptionPane.showMessageDialog(modifyEmailFrame, "Email modificata!");
			modifyEmailFrame.dispose();		
		} catch(NoChangeException e) {
			JOptionPane.showMessageDialog(modifyEmailFrame, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		} catch(UniqueSQLException e) {
			JOptionPane.showMessageDialog(modifyEmailFrame, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(modifyEmailFrame, "Errore imprevisto durante l’aggiornamento", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void onModificaUsernameClicked(Studente studenteLoggato) {
		try {
			String newUsername = modifyUsernameFrame.getUsername();
			if(studenteLoggato.getUsername().equals(newUsername)) 
				throw new NoChangeException("Il nuovo username non può essere uguale a quello vecchio!");
			
			studenteDAO.updateUsername(studenteLoggato, newUsername);
			studenteLoggato.setUsername(newUsername);
			JOptionPane.showMessageDialog(modifyUsernameFrame, "Username modificato!");
			modifyUsernameFrame.dispose();		
		} catch(NoChangeException e) {
			JOptionPane.showMessageDialog(modifyUsernameFrame, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		} catch(UniqueSQLException e) {
			JOptionPane.showMessageDialog(modifyUsernameFrame, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(modifyUsernameFrame, "Errore imprevisto durante l’aggiornamento", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void onModificaPasswordClicked(Studente studenteLoggato) {
		try {
			String oldPassword = modifyPasswordFrame.getOldPassword();
			if(!studenteLoggato.getPassword().equals(oldPassword))
				throw new NoChangeException("La vecchia password non è corretta!");
			
			String newPassword = modifyPasswordFrame.getNewPassword();
			if (studenteLoggato.getPassword().equals(newPassword)) 
				throw new NoChangeException("La nuova password non può essere uguale a quella vecchia!");
			
			studenteDAO.updatePassword(studenteLoggato, newPassword);
			studenteLoggato.setPassword(newPassword);
			JOptionPane.showMessageDialog(modifyPasswordFrame, "Password modificata!");
			modifyPasswordFrame.dispose();
		} catch (NoChangeException e) {
		JOptionPane.showMessageDialog(modifyPasswordFrame, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(modifyPasswordFrame, "Errore imprevisto durante l’aggiornamento", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	//--REFRESH METHODS--//
	
	//**Metodo stub per aggiornare gli annunci contentuti nel browsePane nel mainFrame*/
	public void refreshBrowse() {
		mainFrame.refreshBrowse();
	}
	
	//**Metodo stub per aggiornare gli annunci contentuti nel browsePane nel mainFrame in base alla ricerca*/
	public void refreshBrowseForResearch() {
		mainFrame.refreshBrowseForResearch();
	}
	
	//**Metodo stub per aggiornare gli annunci contentuti nel listingsPane nel mainFrame*/
	public void refreshListings() {
		mainFrame.refreshListings();
	}
	
	//**Metodo stub per aggiornare tutte le offerte fatte e ricevute contentute nel offersPane nel mainFrame*/
	public void refreshAllOffers() {
		mainFrame.refreshAllOffers();
	}

	//**Metodo stub per aggiornare tutte le offerte ricevute contentute nel offersPane nel mainFrame*/
	public void refreshReceivedOffers() {
		mainFrame.refreshReceivedOffers();
	}

	
	//**Metodo stub per aggiornare gli oggetti contentuti nel MyObjectsPane nel mainFrame*/
	public void refreshMyObjects() {
		mainFrame.refreshMyObjects();
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
	
	public ArrayList<Annuncio> getAltriAnnunciByRicerca(String usernameStudenteLoggato, String research, boolean[] filtersOggetto, boolean[] filtersAnnuncio) {
		if(research.equals("") && allEquals(filtersOggetto) && allEquals(filtersAnnuncio))
			 return getAltriAnnunci(40, usernameStudenteLoggato);
		
		else {
			ArrayList<Annuncio> annunci = annuncioDAO.getAltriAnnunciByRicerca(usernameStudenteLoggato, research, filtersOggetto, filtersAnnuncio);
			return annunci;
		}
	}
	
	private boolean allEquals(boolean[] filters) {
		boolean first = filters[0];
		for (boolean val : filters) {
			if (val != first) 
				return false;
		}
	        
		return true;
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
	public ArrayList<Offerta> getOfferteInviate(String usernameUtenteLoggato) {
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

	//**Metodo per fare il logout e tornare al login frame*/
	public void onLogoutClicked() {
		int scelta = JOptionPane.showConfirmDialog(mainFrame,
                "Sei sicuro di voler effettuare il logout?",
                "Conferma Logout",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE);
		if(scelta == JOptionPane.YES_OPTION) {			
			studenteLoggato = null;
			loginFrame.setVisible(true);
			mainFrame.dispose();
			mainFrame = null;
		}else {
			return;
		}
	}

	public void onEliminaAccountClicked() {
		int scelta = JOptionPane.showConfirmDialog(mainFrame,
				"Sei sicuro di voler eliminare il tuo account? \nL'operazione è irreversibile",
				"Conferma Eliminazione Account",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if(scelta == JOptionPane.YES_OPTION) {			
			studenteDAO.delete(studenteLoggato.getUsername());
			studenteLoggato = null;
			loginFrame.setVisible(true);
			mainFrame.dispose();
			mainFrame = null;
		}else {
			return;
		}
	}

	public Main getMainFrame() {
		if(mainFrame != null)
			return mainFrame;
		else
			return null;
	}
	
}
