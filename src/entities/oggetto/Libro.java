package entities.oggetto;

import java.time.LocalDate;

import entities.studente.Studente;

public class Libro extends Oggetto {
	// ATTRIBUTI
	private String titolo;
	private String ISBN;
	private LocalDate annoUscita;
	private String autore;
	private String genere;

	// COSTRUTTORE
	public Libro(String nome, Studente proprietario, String titolo, String ISBN, LocalDate annoUscita, String autore, String genere) {
		super(nome, proprietario);
		this.titolo = titolo;
		this.ISBN = ISBN;
		this.annoUscita = annoUscita;
		this.autore = autore;
		this.genere = genere;
		
	}

	// METODI
	public String getTitolo() {
		return titolo;
	}
	
	public String getISBN() {
		return ISBN;
	}
	
	public LocalDate getAnnoUscita() {
		return annoUscita;
	}
	
	public String getAutore() {
		return autore;
	}
	
	public String getGenere() {
		return genere;
	}
	

}
