package entities.offerta;

import java.util.ArrayList;
import entities.annuncio.Annuncio;
import entities.oggetto.Oggetto;
import entities.studente.Studente;
import entities.enumerazioni.Stato;

public class OffertaScambio extends Offerta {
	private ArrayList<Oggetto> oggettiOfferti = new ArrayList<Oggetto>();
	
	public OffertaScambio(String messaggio, Studente offerente, Annuncio annuncio, ArrayList<Oggetto> oggettiOfferti) {
		super(messaggio, offerente, annuncio);
		this.oggettiOfferti = oggettiOfferti;
	}

	public OffertaScambio(long id, Stato stato, String messaggio, Studente offerente, Annuncio annuncio, ArrayList<Oggetto> oggettiOfferti) {
		super(id, stato, messaggio, offerente, annuncio);
		this.oggettiOfferti = oggettiOfferti;
	}
	
	public ArrayList<Oggetto> getOggettiOfferti() {
		return oggettiOfferti;
	}
	
	public void setOggettiOfferti(ArrayList<Oggetto> oggettiOfferti){
		this.oggettiOfferti=oggettiOfferti;
	}
	
	public String toString() {
		 String output = "OffertaScambio [id=" + getId() + ", stato=" + getStato() + ", messaggio=" + getMessaggio() + ", offerente=" + getOfferente().getUsername() + ", annuncio=" + getAnnuncio().getTitolo();
		for(Oggetto oggetto : oggettiOfferti) {
			output += ", oggettiOfferti=" + oggetto.getNome();
		}
		return output + "]";
	}
	
	public String getSourceImage() {
		return "src/img/offerta_scambio.png";
	}
}
