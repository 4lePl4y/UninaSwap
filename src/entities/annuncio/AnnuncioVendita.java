package entities.annuncio;

import java.time.LocalDate;
import java.time.LocalTime;
import entities.enumerazioni.Sede;
import entities.oggetto.Oggetto;
import entities.studente.Studente;

public class AnnuncioVendita extends Annuncio {
	private double prezzo;
	
	public AnnuncioVendita(String titolo, Studente autore, Oggetto oggetto, String descrizione, Sede luogo, LocalTime oraIncontro, LocalDate dataPubblicazione, double prezzo) {
		super(titolo, autore, oggetto, descrizione, luogo, oraIncontro, dataPubblicazione);	
		this.prezzo = prezzo;
	}
	
	public AnnuncioVendita(long id,String titolo, Studente autore, Oggetto oggetto, String descrizione, Sede luogo, LocalTime oraIncontro, LocalDate dataPubblicazione, double prezzo) {
		super(id, titolo, autore, oggetto, descrizione, luogo, oraIncontro, dataPubblicazione);	
		this.prezzo = prezzo;
	}
	
	public double getPrezzo() {
		return prezzo;
	}
	
	public void setPrezzo(double prezzo) {
		this.prezzo=prezzo;
	}
}
