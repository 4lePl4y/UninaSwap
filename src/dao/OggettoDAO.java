package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.oggetto.*;
import entities.studente.Studente;

public class OggettoDAO implements DaoInterface<Oggetto> {
	//ATTRIBUTI
	private Connection conn;
	private StudenteDAO studenteDAO = new StudenteDAO(conn);

	//COSTRUTTORE
	public OggettoDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Oggetto retrieveByPK(String id) {
		Oggetto oggetto = null;
		String query = "SELECT * FROM oggetto WHERE id = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				oggetto = creaOggettoCorretto(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return oggetto;
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
	
	private Oggetto creaOggettoCorretto(ResultSet rs) throws SQLException {
		Oggetto oggetto = null;
		String descrizione = rs.getString("descrizione");
		String tipoOggetto = rs.getString("tipoOggetto");
		String marchio = rs.getString("marchio");
		String taglia = rs.getString("taglia");
		String modello = rs.getString("modello");
		Date annoUscita = rs.getDate("annoUscita");
		String titolo = rs.getString("titolo"); 
		String ISBN = rs.getString("ISBN"); 
		String autore = rs.getString("autore"); 
		String genere = rs.getString("genere");
		String categoria = rs.getString("categoria");
		Studente proprietario = studenteDAO.retrieveByPK(rs.getString("proprietario"));
		
		switch (tipoOggetto) {
			case "StrumentoMusicale":
				oggetto = new StrumentoMusicale(descrizione, proprietario, marchio);
				break;
			case "Abbigliamento":
				oggetto = new Abbigliamento(descrizione, proprietario, marchio, taglia);
				break;
			case "Elettronica":
				oggetto = new Elettronica(descrizione, proprietario, marchio, modello, annoUscita.toLocalDate());
				break;
			case "Libro":
				oggetto = new Libro(descrizione, proprietario, titolo, ISBN, annoUscita.toLocalDate(), autore, genere);
				break;
			case "Misc":
				oggetto = new Misc(descrizione, proprietario, marchio, categoria);
				break;
		}
		
		return oggetto;
	}

}
