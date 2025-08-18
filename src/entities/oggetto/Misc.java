package entities.oggetto;

import entities.studente.Studente;

public class Misc extends Oggetto{
	private static final long serialVersionUID = 1L;
	// ATTRIBUTI
	private String marchio;
	private String categoria;

	public Misc(String descrizione, Studente proprietario, String marchio, String categoria) {
		super(descrizione, proprietario);
		this.marchio = marchio;
		this.categoria = categoria;
	}

}
