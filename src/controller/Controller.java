package controller;

import java.awt.EventQueue;

import gui.*;

public class Controller {
	//ATTRIBUTI
	Login loginFrame; 
	SignUp signUpFrame; 
	
	public static void main(String[] args) {
		Controller controller = new Controller();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login loginFrame = new Login(controller);
					loginFrame.setVisible(true);
					SignUp signUpFrame = new SignUp(controller);
					signUpFrame.setVisible(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//METODI
	public void onRegistratiClicked(){
		loginFrame.setVisible(false);
		signUpFrame.setVisible(true);
	}
	
}
