package entities.annuncio;

import java.time.LocalDate;
import java.time.LocalTime;
import entities.enumerazioni.Sede;
import entities.oggetto.Oggetto;
import entities.studente.Studente;

public class AnnuncioScambio extends Annuncio{
	
	public AnnuncioScambio(String titolo, Studente autore, Oggetto oggetto, String descrizione, Sede luogo, LocalTime oraIncontro, LocalDate dataPubblicazione) {
		super(titolo, autore, oggetto, descrizione, luogo, oraIncontro, dataPubblicazione);	
	}
	
	public AnnuncioScambio(long id, String titolo, Studente autore, Oggetto oggetto, String descrizione, Sede luogo, LocalTime oraIncontro, LocalDate dataPubblicazione) {
		super(id, titolo, autore, oggetto, descrizione, luogo, oraIncontro, dataPubblicazione);	
	}
}
