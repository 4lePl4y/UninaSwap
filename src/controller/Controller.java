package controller;

import java.awt.EventQueue;
import java.sql.Connection;


import dao.*;
import db.DBConnection;
import entities.studente.*;
import gui.*;

public class Controller {
	//ATTRIBUTI
	Login loginFrame; 
	SignUp signUpFrame; 
	Main mainFrame;
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
					controller.signUpFrame = new SignUp(controller);
					controller.signUpFrame.setVisible(false);
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
	
}
