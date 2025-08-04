package entities.studente;
import entities.oggetto.Oggetto;
public class Recensione {
	private int voto;
	private String descrizione;
	private String studenteRecensito;
	private Oggetto oggettoRecensito;
	
	public Recensione(int voto, String descrizione, String studenteRecensito, Oggetto oggettoRecensito) {
		this.voto=voto;
		this.descrizione=descrizione;
		this.studenteRecensito=studenteRecensito;
		this.oggettoRecensito=oggettoRecensito;
	}
	//voto compreso tra 1 e 5 (se vi piace)
	public void setVoto(int voto) {
		if(voto<1 || voto>5) {
			throw new IllegalArgumentException("Il voto deve essere compreso tra 1 e 5.");
		}
		this.voto = voto;
	}
	public int getVoto() {
		return voto;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getStudenteRecensito() {
		return studenteRecensito;
	}
	public void setStudenteRecensito(String studenteRecensito) {
		this.studenteRecensito = studenteRecensito;
	}
	
	public Oggetto getOggettoRecensito() {
		return oggettoRecensito;
	}
	public void setOggettoRecensito(Oggetto oggettoRecensito) {
		this.oggettoRecensito = oggettoRecensito;
	}
	
	@Override
	public String toString() {
		return "Recensione oggetto: " +(oggettoRecensito != null ? oggettoRecensito: "N/A") + "\n" +
				"Voto: " + voto + "\n" +
				"Descrizione: " + descrizione + "\n" +
				"Studente Recensito: " + (studenteRecensito != null ? studenteRecensito: "N/A") + "\n";
	}
}
