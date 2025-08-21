package entities.oggetto;

import entities.studente.Studente;

public class Abbigliamento extends Oggetto {
	// ATTRIBUTI
	private String marchio;
	private String taglia;

	// COSTRUTTORE
	public Abbigliamento(String nome, Studente proprietario, String marchio, String taglia) {
		super(nome, proprietario);
		this.marchio = marchio;
		this.taglia = taglia;
	}

	// METODI
	public String getMarchio() {
		return marchio;
	}
	
	public String getTaglia() {
		return taglia;
	}
	
	@Override
	public String toString() {
		return "Abbigliamento: "+ nome;
	}
	
}
