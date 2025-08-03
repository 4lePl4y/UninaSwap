package entities.annuncio;

import java.time.LocalTime;
import entities.enumerazioni.Sede;
import entities.studente.Studente;

public class AnnuncioVendita extends Annuncio {
	private float offerta;

	public AnnuncioVendita(String titolo, Studente autore, String descrizione, Sede luogo, LocalTime ora, float offerta) {
		super(titolo, autore, descrizione, luogo, ora);
		this.offerta = offerta;
	}

}
