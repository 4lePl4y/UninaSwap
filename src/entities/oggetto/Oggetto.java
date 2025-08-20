package entities.oggetto;

import entities.studente.Studente;

public abstract class Oggetto {
	//ATTRIBUTI
	protected String nome;
	protected Studente proprietario;
	
	//COSTRUTTORE
	public Oggetto(String nome, Studente proprietario) {
		this.nome = nome;
		this.proprietario = proprietario;
	}
	
	//METODI
	public String getDescrizione() {
		return nome;
	}
	
	public String getUsernameProprietario() {
		return proprietario.getUsername();
	}
}
