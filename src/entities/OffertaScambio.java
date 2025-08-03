package entities;

import java.util.ArrayList;

public class OffertaScambio extends Offerta {
	private Annuncio annuncio;
	private ArrayList<Oggetto> oggettiOfferti = new ArrayList<Oggetto>();
	
	public OffertaScambio(Stato stato, Annuncio annuncio, ArrayList<Oggetto> oggettiOfferti) {
		super(stato);
		this.annuncio = annuncio;
		this.oggettiOfferti = oggettiOfferti;
	}
}
