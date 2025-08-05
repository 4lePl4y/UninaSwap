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
		loginFrame.wrongUsrnmLabel.setVisible(false);
		loginFrame.wrongPwdLabel.setVisible(false);
		String username = loginFrame.getUsername();
		String password = loginFrame.getPassword();
		Studente chkStudente = studenteDAO.retrieve(username);
		if(chkStudente == null) {
			loginFrame.wrongUsrnmLabel.setVisible(true);
			loginFrame.userTxtField.setText("");
			return;
		}else if(chkStudente != null && !chkStudente.getPassword().equals(password)) {
			loginFrame.wrongPwdLabel.setVisible(true);
			loginFrame.pswTxtField.setText("");
			return; 
		} else {
			mainFrame.setVisible(true);
			loginFrame.setVisible(false);
		}
		
	}

	public void onRegisterClicked(String name, String surname, String username, String email, String password, String confirmPassword) {
		if (password.equals(confirmPassword)) {
			Studente newStudente = new Studente(name, surname, username, email, password);
			studenteDAO.create(newStudente);
			loginFrame.setVisible(true);
			signUpFrame.setVisible(false);
			
		} else {
			System.out.println("Le password non corrispondono.");
		}
		
	}
	
}
