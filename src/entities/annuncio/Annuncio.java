package entities.annuncio;

import java.time.LocalTime;

import entities.enumerazioni.Sede;
import entities.studente.Studente;

public abstract class Annuncio {
	protected Studente autore;
	protected String titolo;
	protected String descrizione;
	protected Sede luogo;
	protected LocalTime ora;
	
	public Annuncio(String titolo, Studente autore, String descrizione, Sede luogo, LocalTime ora) {
		this.titolo = titolo;
		this.autore = autore;
		this.descrizione = descrizione;
		this.luogo = luogo;
		this.ora = ora;
	}

}

