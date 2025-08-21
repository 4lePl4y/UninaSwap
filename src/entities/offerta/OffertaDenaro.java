package entities.offerta;

import entities.annuncio.Annuncio;
import entities.enumerazioni.Stato;
import entities.studente.Studente;

public class OffertaDenaro extends Offerta {
	private double offerta;
	
	public OffertaDenaro(String messaggio, Studente offerente, Annuncio annuncio, double offerta) {
		super(messaggio, offerente, annuncio);
		this.offerta = offerta;
	}
	
	public OffertaDenaro(long id, Stato stato, String messaggio, Studente offerente, Annuncio annuncio, double offerta) {
		super(id, stato, messaggio, offerente, annuncio);
		this.offerta = offerta;
	}
	
	public double getOfferta() {
		return offerta;
	}
	
	public String toString() {
		return "OffertaDenaro [id=" + getId() + ", stato=" + getStato() + ", messaggio=" + getMessaggio() + ", offerente=" + getOfferente().getUsername() + ", annuncio=" + getAnnuncio().getTitolo() + ", offerta=" + offerta + "]";
	}
	
}
