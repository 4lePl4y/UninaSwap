package entities.annuncio;

import java.time.LocalDate;
import java.time.LocalTime;

import entities.enumerazioni.Sede;
import entities.oggetto.Oggetto;
import entities.studente.Studente;

public abstract class Annuncio {
	protected String titolo;
	protected Studente autore;
	protected Oggetto oggetto;
	protected String descrizione;
	protected Sede luogo;
	protected LocalTime oraIncontro;
	protected LocalDate dataPubblicazione;
	
	public Annuncio(String titolo, Studente autore, Oggetto oggetto, String descrizione, Sede luogo, LocalTime oraIncontro, LocalDate dataPubblicazione) {
		this.titolo = titolo;
		this.autore = autore;
		this.oggetto = oggetto;
		this.descrizione = descrizione;
		this.luogo = luogo;
		this.oraIncontro = oraIncontro;
		this.dataPubblicazione = dataPubblicazione;
	}

}

