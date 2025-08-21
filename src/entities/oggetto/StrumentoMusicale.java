package entities.oggetto;

import entities.studente.Studente;

public class StrumentoMusicale extends Oggetto {
	// ATTRIBUTI
	private String marchio;

	// COSTRUTTORE
	public StrumentoMusicale(String nome, Studente proprietario, String marchio) {
		super(nome, proprietario);
		this.marchio = marchio;
	}

	public StrumentoMusicale(long id, String nome, Studente proprietario, String marchio) {
		super(id, nome, proprietario);
		this.marchio = marchio;
	}

	// METODI
	public String getMarchio() {
		return marchio;
	}
	
	public String toString() {
		return "StrumentoMusicale:"+ nome ;
	}

}
