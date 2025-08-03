package entities.studente;

import java.util.ArrayList;
import entities.annuncio.Annuncio;
import entities.offerta.Offerta;
import entities.oggetto.Oggetto;

public class Studente {
	private String nome;
	private String cognome;
	private String eMail;
	private String username;
	private ArrayList<Oggetto> oggetti;
	private ArrayList<Annuncio> annunci;
	private ArrayList<Offerta> offerte;
	
	
	public Studente(String nome, String cognome, String eMail, String username) {
		this.nome = nome;
		this.cognome = cognome;
		this.eMail = eMail;
		this.username = username;
		this.oggetti = new ArrayList<Oggetto>();
		this.annunci = new ArrayList<Annuncio>();
		this.offerte = new ArrayList<Offerta>();
	}
}
