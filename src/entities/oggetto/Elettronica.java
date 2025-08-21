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

	// METODI
	public String getMarchio() {
		return marchio;
	}
	
	public String getModello() {
		return modello;
	}
	
	public Year getAnnoUscita() {
		return annoUscita;
	}
	
	@Override
	public String toString() {
		return "Elettronica: "+ nome;
	}

}
