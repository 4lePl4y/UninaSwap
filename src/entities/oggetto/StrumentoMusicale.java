package entities.oggetto;

import entities.studente.Studente;

public class StrumentoMusicale extends Oggetto {
	// ATTRIBUTI
	private String marchio;

	// COSTRUTTORE
	public StrumentoMusicale(String descrizione, Studente proprietario, String marchio) {
		super(descrizione, proprietario);
		this.marchio = marchio;
	}

	// METODI
	
	

}
