package entities.oggetto;

import java.time.Year;

import entities.studente.Studente;

public class Elettronica extends Oggetto {
	// ATTRIBUTI
	private String marchio;
	private String modello;
	private Year annoUscita;

	// COSTRUTTORE
	public Elettronica(String nome, Studente proprietario, String marchio, String modello, Year annoUscita) {
		super(nome, proprietario);
		this.marchio = marchio;
		this.modello = modello;
		this.annoUscita = annoUscita; 
	}

	public Elettronica(long id, String nome, Studente proprietario, String marchio, String modello, Year annoUscita) {
		super(id, nome, proprietario);
		this.marchio = marchio;
		this.modello = modello;
		this.annoUscita = annoUscita; 
	}

	// METODI
	public String getMarchio() {
		return marchio;
	}
	
	public void setMarchio(String marchio) {
		this.marchio=marchio;
	}
	
	public String getModello() {
		return modello;
	}
	
	public void setModello(String modello) {
		this.modello=modello;
	}
	
	public Year getAnnoUscita() {
		return annoUscita;
	}
	
	public void setAnnoUscita(Year annoUscita) {
		this.annoUscita=annoUscita;
	}
	
	@Override
	public String toString() {
		return "Elettronica: "+ nome;
	}

	@Override
	public String getBasicInfo() {
		return "Marchio: " + marchio + "\nModello: " + modello + "\nAnno di uscita: " + annoUscita;
	}

}
