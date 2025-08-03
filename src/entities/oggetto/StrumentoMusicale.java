package entities.oggetto;

import entities.studente.Studente;

public class StrumentoMusicale extends Oggetto {
	// ATTRIBUTI
	private String brand;

	// COSTRUTTORE
	public StrumentoMusicale(String descrizione, Studente proprietario, String brand) {
		super(descrizione, proprietario);
		this.brand = brand;
	}

	// METODI
	
	

}
