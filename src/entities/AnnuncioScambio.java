package entities;

import java.time.LocalTime;

public class AnnuncioScambio extends Annuncio{
	
	public AnnuncioScambio(String titolo, String descrizione, Sede luogo, LocalTime ora) {
		super(titolo, descrizione, luogo, ora);
	}
}
