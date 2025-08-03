package entities.offerta;

import java.util.ArrayList;
import entities.annuncio.Annuncio;
import entities.oggetto.Oggetto;
import entities.studente.Studente;
import entities.enumerazioni.Stato;

public class OffertaScambio extends Offerta {
	private Annuncio annuncio;
	private ArrayList<Oggetto> oggettiOfferti = new ArrayList<Oggetto>();
	
	public OffertaScambio(Stato stato, Studente offerente,Annuncio annuncio, ArrayList<Oggetto> oggettiOfferti) {
		super(stato, offerente);
		this.annuncio = annuncio;
		this.oggettiOfferti = oggettiOfferti;
	}
}
