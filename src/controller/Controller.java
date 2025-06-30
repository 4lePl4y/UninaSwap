package controller;

import java.awt.EventQueue;

import gui.*;

public class Controller {
	//ATTRIBUTI
	Login loginFrame; 
	SignUp signUpFrame; 
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login loginFrame = new Login(this);
					loginFrame.setVisible(true);
					SignUp signUpFrame = new SignUp(this);
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
