package entities.annuncio;

import java.time.LocalTime;
import entities.enumerazioni.Sede;

public class AnnuncioVendita extends Annuncio {
	private float offerta;

	public AnnuncioVendita(String titolo, String descrizione, Sede luogo, LocalTime ora, float offerta) {
		super(titolo, descrizione, luogo, ora);
		this.offerta = offerta;
	}

}
