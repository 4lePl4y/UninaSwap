package entities.annuncio;

import java.time.LocalTime;
import entities.enumerazioni.Sede;

public class AnnuncioScambio extends Annuncio{
	
	public AnnuncioScambio(String titolo, String descrizione, Sede luogo, LocalTime ora) {
		super(titolo, descrizione, luogo, ora);
	}
}
