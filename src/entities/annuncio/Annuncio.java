package entities.annuncio;

import java.time.LocalDate;
import java.time.LocalTime;

import entities.enumerazioni.Sede;
import entities.oggetto.Oggetto;
import entities.studente.Studente;

public abstract class Annuncio {
	protected long id;
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
	
	public Annuncio(long id, String titolo, Studente autore, Oggetto oggetto, String descrizione, Sede luogo, LocalTime oraIncontro, LocalDate dataPubblicazione) {
		this.id = id;
		this.titolo = titolo;
		this.autore = autore;
		this.oggetto = oggetto;
		this.descrizione = descrizione;
		this.luogo = luogo;
		this.oraIncontro = oraIncontro;
		this.dataPubblicazione = LocalDate.now();
	}
	

	public long getId() {
		return id;
	}
	
	public String getTitolo() {
		return titolo;
	}
	
	public Studente getAutore() {
		return autore;
	}
	
	public Oggetto getOggetto() {
		return oggetto;
	}
	
	public String getDescrizione() {
		return descrizione;
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
	
	public String toString() {
		return "Annuncio [id=" + id + ", titolo=" + titolo + ", autore=" + autore.getUsername() + ", oggetto=" + oggetto.getNome() + 
				", descrizione=" + descrizione + ", luogo=" + luogo + ", oraIncontro=" + oraIncontro + ", dataPubblicazione=" + dataPubblicazione + "]";
	}
	
}

