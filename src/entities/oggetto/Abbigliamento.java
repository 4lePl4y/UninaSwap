package entities.oggetto;

import entities.Studente;

public class Abbigliamento extends Oggetto {
	// ATTRIBUTI
	private String brand;
	private String taglia;

	// COSTRUTTORE
	public Abbigliamento(String descrizione, Studente proprietario, String brand, String taglia) {
		super(descrizione, proprietario);
		this.brand = brand;
		this.taglia = taglia;
	}

	// METODI

}
