package entities;

import java.time.LocalTime;

public class AnnuncioVendita extends Annuncio {
	private float offerta;

	public AnnuncioVendita(String titolo, String descrizione, Sede luogo, LocalTime ora, float offerta) {
		super(titolo, descrizione, luogo, ora);
		this.offerta = offerta;
	}

}
