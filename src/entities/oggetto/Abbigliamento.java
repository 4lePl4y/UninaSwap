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

	public Abbigliamento(long id, String nome, Studente proprietario, String marchio, String taglia) {
		super(id, nome, proprietario);
		this.marchio = marchio;
		this.taglia = taglia;
	}

	// METODI
	public String getMarchio() {
		return marchio;
	}
	
	public void setMarchio(String marchio) {
		this.marchio=marchio;
	}
	
	public String getTaglia() {
		return taglia;
	}
	
	public void setTaglia(String taglia) {
		this.taglia=taglia;
	}
	
	@Override
	public String toString() {
		return "Abbigliamento: "+ nome;
	}

	@Override
	public String getBasicInfo() {
		return "Marchio: " + marchio + "\nTaglia: " + taglia;
	}
	
	public String getSourceImage() {
		return "src/img/abbigliamento.png";
	}
	
}
