package entities.offerta;

import entities.annuncio.Annuncio;
import entities.enumerazioni.Stato;
import entities.studente.Studente;

public abstract class Offerta {
	protected long id=0;
	Stato stato;
	private String messaggio;
	private Studente offerente;
	private Annuncio annuncio;
	
	public Offerta(String messaggio, Studente offerente, Annuncio annuncio) {
		this.messaggio = messaggio;
		this.offerente = offerente;
		this.annuncio = annuncio;
	}
	
	public Offerta(long id, Stato stato, String messaggio, Studente offerente, Annuncio annuncio) {
		this.id = id;
		this.stato = stato;
		this.messaggio = messaggio;
		this.offerente = offerente;
		this.annuncio = annuncio;
	}
	
	public long getId() {
		return id;
	}
	
	public Stato getStato() {
		return stato;
	}
	
	public String getMessaggio() {
		if(messaggio==null || messaggio.isEmpty())
			return "Nessun messaggio";
		else
			return messaggio;
	}
	
	public void setMessaggio(String messaggio) {
		this.messaggio=messaggio;
	}

	public Studente getOfferente() {
		return offerente;
	}
	
	public Annuncio getAnnuncio() {
		return annuncio;
	}
	
	public String toString() {
		return "Offerta [id=" + id + ", stato=" + stato + ", messaggio=" + messaggio + ", offerente=" + offerente.getUsername() + ", annuncio=" + annuncio.getTitolo() + "]";
	}
	
	public abstract String getSourceImage();

}
