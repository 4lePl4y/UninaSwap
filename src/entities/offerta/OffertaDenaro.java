package entities.offerta;

import entities.annuncio.Annuncio;
import entities.enumerazioni.Stato;
import entities.studente.Studente;

public class OffertaDenaro extends Offerta {
	private Annuncio annuncio;
	private float offerta;
	
	public OffertaDenaro(Stato stato, Studente offerente, Annuncio annuncio, float offerta) {
		super(stato, offerente);
		this.annuncio = annuncio;
		this.offerta = offerta;
	}
}
