package entities.oggetto;

import java.time.LocalDate;

import entities.studente.Studente;

public class Elettronica extends Oggetto {
	// ATTRIBUTI
	private String marchio;
	private String modello;
	private LocalDate dataUscita;

	// COSTRUTTORE
	public Elettronica(String nome, Studente proprietario, String marchio, String modello, LocalDate dataUscita) {
		super(nome, proprietario);
		this.marchio = marchio;
		this.modello = modello;
		this.dataUscita = dataUscita; 
	}

	// METODI
	public String getMarchio() {
		return marchio;
	}
	
	public String getModello() {
		return modello;
	}
	
	public LocalDate getAnnoUscita() {
		return dataUscita;
	}

}
