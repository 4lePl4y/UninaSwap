package entities.annuncio;

import java.time.LocalTime;

import entities.enumerazioni.Sede;

public abstract class Annuncio {
	protected String titolo;
	protected String descrizione;
	protected Sede luogo;
	protected LocalTime ora;
	
	public Annuncio(String titolo, String descrizione, Sede luogo, LocalTime ora) {
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.luogo = luogo;
		this.ora = ora;
	}

}

