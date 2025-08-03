package entities.oggetto;

import java.time.LocalDate;

import entities.studente.Studente;

public class Elettronica extends Oggetto {
	// ATTRIBUTI
	private String marchio;
	private String modello;
	private LocalDate dataUscita;

	// COSTRUTTORE
	public Elettronica(String descrizione, Studente proprietario, String marchio, String modello, LocalDate dataUscita) {
		super(descrizione, proprietario);
		this.marchio = marchio;
		this.modello = modello;
		this.dataUscita = dataUscita; 
	}

	// METODI


}
