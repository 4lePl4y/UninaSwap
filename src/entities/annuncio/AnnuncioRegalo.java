package entities.annuncio;

import java.time.LocalTime;
import entities.enumerazioni.Sede;

public class AnnuncioRegalo extends Annuncio {
	
	public AnnuncioRegalo(String titolo, String descrizione, Sede luogo, LocalTime ora) {
		super(titolo, descrizione, luogo, ora);
	}

	

}
