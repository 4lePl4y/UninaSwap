package entities.oggetto;

import entities.studente.Studente;

public abstract class Oggetto {
	//ATTRIBUTI
	protected long id;
	protected String nome;
	protected Studente proprietario;
	
	//COSTRUTTORE
	public Oggetto(String nome, Studente proprietario) {
		this.nome = nome;
		this.proprietario = proprietario;
	}
	
	public Oggetto(long id, String nome, Studente proprietario) {
		this.id = id;
		this.nome = nome;
		this.proprietario = proprietario;
	}
	
	//METODI
	public long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getUsernameProprietario() {
		return proprietario.getUsername();
	}
	
	public String toString() {
		return "Oggetto: " + nome;
	}
	
}
