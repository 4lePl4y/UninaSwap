package entities.annuncio;

import java.time.LocalDate;
import java.time.LocalTime;
import entities.enumerazioni.Sede;
import entities.oggetto.Oggetto;
import entities.studente.Studente;

public class AnnuncioRegalo extends Annuncio {
	
	//COSTRUTTORI
	public AnnuncioRegalo(String titolo, Studente autore, Oggetto oggetto, String descrizione, Sede luogo, LocalTime oraIncontro, LocalDate dataPubblicazione) {
		super(titolo, autore, oggetto, descrizione, luogo, oraIncontro, dataPubblicazione);	
	}	

	public AnnuncioRegalo(long id, String titolo, Studente autore, Oggetto oggetto, String descrizione, Sede luogo, LocalTime oraIncontro, LocalDate dataPubblicazione) {
		super(id, titolo, autore, oggetto, descrizione, luogo, oraIncontro, dataPubblicazione);	
	}

	//METODI
	@Override
	public String getTipoAnnuncio() {
		return "REGALO";
	}
	
}
