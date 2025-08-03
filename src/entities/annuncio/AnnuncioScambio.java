package entities.annuncio;

import java.time.LocalTime;
import entities.enumerazioni.Sede;
import entities.studente.Studente;

public class AnnuncioScambio extends Annuncio{
	
	public AnnuncioScambio(String titolo, Studente autore, String descrizione, Sede luogo, LocalTime ora) {
		super(titolo, autore, descrizione, luogo, ora);
	}
}
