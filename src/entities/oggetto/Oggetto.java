package entities.oggetto;

import entities.studente.Studente;

public abstract class Oggetto {
	//ATTRIBUTI
	protected String descrizione;
	protected Studente proprietario;
	
	//COSTRUTTORE
	public Oggetto(String descrizione, Studente proprietario) {
		this.descrizione = descrizione;
		this.proprietario = proprietario;
	}
	
	//METODI
	
}
