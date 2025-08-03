package entities.offerta;

import entities.enumerazioni.Stato;

public abstract class Offerta {
	Stato stato;
	
	public Offerta(Stato stato) {
		this.stato = stato;
	}
}
