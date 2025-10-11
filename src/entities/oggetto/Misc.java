package entities.oggetto;

import entities.studente.Studente;

public class Misc extends Oggetto{
	// ATTRIBUTI
	private String marchio;
	private String categoria;

	public Misc(String nome, Studente proprietario, String marchio, String categoria) {
		super(nome, proprietario);
		this.marchio = marchio;
		this.categoria = categoria;
	}

	public Misc(long id, String nome, Studente proprietario, String marchio, String categoria) {
		super(id, nome, proprietario);
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

	public void setMarchio(String marchio) {
		this.marchio = marchio;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public String toString() {
		return "Varie: "+ nome;
	}

	@Override
	public String getBasicInfo() {		
		return "Categoria: " + categoria + "\nMarchio: " + marchio;
	}
	
	public String getSourceImage() {
		return "src/img/misc.png";
	}
}
