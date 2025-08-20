package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.offerta.Offerta;

public class OffertaDAO implements DaoInterface<Offerta> {
	//ATTRIBUTI
	private Connection conn;
	private StudenteDAO studenteDAO = new StudenteDAO(conn);
	private AnnuncioDAO annuncioDAO = new AnnuncioDAO(conn);

	//COSTRUTTORE
	public OffertaDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Offerta retrieveByPK(String id) {
		//TODO: Implementazione per recuperare un'offerta per chiave primaria
		return null;
	}
		
	public void create(Offerta offerta) {
		//TODO: Implementazione per creare una nuova offerta
	}
	
	public void update(Offerta offerta) {
		//TODO: Implementazione per aggiornare un'offerta esistente
	}
	
	public void delete(String id) {
		//TODO: Implementazione per eliminare un'offerta per chiave primaria
	}
	
	public Offerta creaOffertaCorretto(ResultSet rs) throws SQLException {
		String id = rs.getString("id");
		String idStudente = rs.getString("idStudente");
		String idAnnuncio = rs.getString("idAnnuncio");
		double prezzo = rs.getDouble("prezzo");
		String data = rs.getString("data");
		String ora = rs.getString("ora");
		
		return null; 
	}
}
