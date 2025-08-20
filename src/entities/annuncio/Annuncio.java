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
	protected String messaggio;
	protected Sede luogo;
	protected LocalTime oraIncontro;
	protected LocalDate dataPubblicazione;
	
	public Annuncio(String titolo, Studente autore, Oggetto oggetto, String messaggio, Sede luogo, LocalTime oraIncontro, LocalDate dataPubblicazione) {
		this.titolo = titolo;
		this.autore = autore;
		this.oggetto = oggetto;
		this.messaggio = messaggio;
		this.luogo = luogo;
		this.oraIncontro = oraIncontro;
		this.dataPubblicazione = dataPubblicazione;
	}

	public String getTitolo() {
		return titolo;
	}
	
	public Studente getAutore() {
		return autore;
	}
	
	public String getMessaggio() {
		return messaggio;
	}
	
	public Sede getLuogo() {
		return luogo;
	}
	
	public LocalTime getOraIncontro() {
		return oraIncontro;
	}
	
	public LocalDate getDataPubblicazione() {
		return dataPubblicazione;
	}
}

