package entities.offerta;

import entities.enumerazioni.Stato;
import entities.studente.Studente;

public abstract class Offerta {
	Stato stato;
	private Studente offerente;
	
	public Offerta(Stato stato, Studente offerente) {
		this.stato = stato;
		this.offerente = offerente;
	}
}
