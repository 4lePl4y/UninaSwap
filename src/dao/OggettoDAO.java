package dao;

import java.sql.Connection;

import entities.oggetto.Oggetto;

public class OggettoDAO implements DaoInterface<Oggetto> {
	//ATTRIBUTI
	private Connection conn;

	//COSTRUTTORE
	public OggettoDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Oggetto retrieveByPK(String id) {
		// Implementazione per recuperare un oggetto per chiave primaria
		return null;
	}

	@Override
	public void create(Oggetto oggetto) {
		// Implementazione per creare un nuovo oggetto
	}

	@Override
	public void update(Oggetto oggetto) {
		// Implementazione per aggiornare un oggetto esistente
	}

	@Override
	public void delete(String id) {
		// Implementazione per eliminare un oggetto per chiave primaria
	}

}
