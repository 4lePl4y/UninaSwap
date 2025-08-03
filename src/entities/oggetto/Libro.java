package entities.oggetto;

import java.time.LocalDate;

import entities.studente.Studente;

public class Libro extends Oggetto {
	// ATTRIBUTI
	private String titolo;
	private String ISBN;
	private LocalDate dataPubblicazione;
	private String autore;
	private String genere;

	// COSTRUTTORE
	public Libro(String descrizione, Studente proprietario, String titolo, String ISBN, LocalDate dataPubblicazione, String autore, String genere) {
		super(descrizione, proprietario);
		this.titolo = titolo;
		this.ISBN = ISBN;
		this.dataPubblicazione = dataPubblicazione;
		this.autore = autore;
		this.genere = genere;
		
	}

	// METODI


}
