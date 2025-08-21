package entities.oggetto;

import entities.studente.Studente;

public class Misc extends Oggetto{
	private static final long serialVersionUID = 1L;
	// ATTRIBUTI
	private String marchio;
	private String categoria;

	public Misc(String nome, Studente proprietario, String marchio, String categoria) {
		super(nome, proprietario);
		this.marchio = marchio;
		this.categoria = categoria;
	}

	//METODI
	public String getMarchio() {
		return marchio;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	@Override
	public String toString() {
		return "Misc: "+ nome;
	}
}
