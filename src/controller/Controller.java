package controller;
//
import java.awt.EventQueue;

import gui.*;

public class Controller {
	//ATTRIBUTI
	Login loginFrame; 
	SignUp signUpFrame; 
	Main mainFrame;
	
	public static void main(String[] args) {
		Controller controller = new Controller();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					controller.loginFrame = new Login(controller);
					controller.loginFrame.setVisible(true);
					controller.signUpFrame = new SignUp(controller);
					controller.signUpFrame.setVisible(false);
					controller.mainFrame = new Main(controller);
					controller.mainFrame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//METODI
	public void onRegisterClicked(){
		signUpFrame.setVisible(true);
		loginFrame.setVisible(false);
	}

	public void onReturnToLoginClicked() {
		loginFrame.setVisible(true);
		signUpFrame.setVisible(false);
	}
	
	public void onLoginClicked() {
		mainFrame.setVisible(true);
		loginFrame.setVisible(false);
	}
	
}
