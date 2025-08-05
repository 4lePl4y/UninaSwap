package entities.studente;

import java.util.ArrayList;
import entities.annuncio.Annuncio;
import entities.offerta.Offerta;
import entities.oggetto.Oggetto;

public class Studente {
	private String username;
	private String password;
	private String nome;
	private String cognome;
	private String eMail;
	private ArrayList<Oggetto> oggetti;
	private ArrayList<Annuncio> annunci;
	private ArrayList<Offerta> offerte;
	
	
	public Studente(String nome, String cognome, String username, String eMail, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.eMail = eMail;
		this.password = password;
		this.oggetti = new ArrayList<Oggetto>();
		this.annunci = new ArrayList<Annuncio>();
		this.offerte = new ArrayList<Offerta>();
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getEmail() {
		return eMail;
	}
	
	public void setEmail(String eMail) {
		this.eMail = eMail;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
