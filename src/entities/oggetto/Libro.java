package entities.oggetto;

import java.time.Year;

import entities.studente.Studente;

public class Libro extends Oggetto {
	// ATTRIBUTI
	private String titolo;
	private String ISBN;
	private Year annoUscita;
	private String autore;
	private String genere;

	// COSTRUTTORE
	public Libro(String nome, Studente proprietario, String titolo, String ISBN, Year annoUscita, String autore, String genere) {
		super(nome, proprietario);
		this.titolo = titolo;
		this.ISBN = ISBN;
		this.annoUscita = annoUscita;
		this.autore = autore;
		this.genere = genere;
		
	}

	public Libro(long id, String nome, Studente proprietario, String titolo, String ISBN, Year annoUscita, String autore, String genere) {
		super(id, nome, proprietario);
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
	
	public Year getAnnoUscita() {
		return annoUscita;
	}
	
	public String getAutore() {
		return autore;
	}
	
	public String getGenere() {
		return genere;
	}
	

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public void setAnnoUscita(Year annoUscita) {
		this.annoUscita = annoUscita;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}
	
	@Override
	public String toString() {
		return "Libro: "+ nome;
	}

	@Override
	public String getBasicInfo() {
		return "Titolo: " + titolo + "\nAutore: " + autore + "\nAnno di uscita: " + annoUscita + 
				"\nGenere: " + genere + "\nISBN: " + ISBN;
	}
	
	public String getSourceImage() {
		return "src/img/libro.jpg";
	}

}
