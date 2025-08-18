package entities.oggetto;

import entities.studente.Studente;

public class Abbigliamento extends Oggetto {
	// ATTRIBUTI
	private String marchio;
	private String taglia;

	// COSTRUTTORE
	public Abbigliamento(String descrizione, Studente proprietario, String marchio, String taglia) {
		super(descrizione, proprietario);
		this.marchio = marchio;
		this.taglia = taglia;
	}

	// METODI

}
