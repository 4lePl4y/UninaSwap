package entities;

public class OffertaDenaro extends Offerta {
	private Annuncio annuncio;
	private float offerta;
	
	public OffertaDenaro(Stato stato, Annuncio annuncio, float offerta) {
		super(stato);
		this.annuncio = annuncio;
		this.offerta = offerta;
	}
}
