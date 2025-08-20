package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.enumerazioni.TipoOggetto;
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
	
	public ArrayList<Oggetto> retrieveByNome(String username) {
		ArrayList<Oggetto> oggetti = new ArrayList<>();
		String query = "SELECT * FROM oggetto WHERE nome = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
				oggetti.add(creaOggettoCorretto(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return oggetti;
	}

	@Override
	public void create(Oggetto oggetto) {
		String query = "INSERT INTO oggetto (nome, tipoOggetto, marchio, taglia, modello, annoUscita, titolo, ISBN, autore, genere, categoria, proprietario) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, oggetto.getDescrizione());
			pstmt.setString(12, oggetto.getUsernameProprietario());
			if (oggetto instanceof Abbigliamento) {
				pstmt.setString(2, TipoOggetto.Abbigliamento.toString());
				pstmt.setString(3, ((Abbigliamento) oggetto).getMarchio());
				pstmt.setString(4, ((Abbigliamento) oggetto).getTaglia());
				pstmt.setNull(5, java.sql.Types.VARCHAR); // Modello non applicabile
				pstmt.setNull(6, java.sql.Types.DATE); // Anno di uscita non applicabile
				pstmt.setNull(7, java.sql.Types.VARCHAR); // Titolo non applicabile
				pstmt.setNull(8, java.sql.Types.VARCHAR); // ISBN non applicabile
				pstmt.setNull(9, java.sql.Types.VARCHAR); // Autore non applicabile
				pstmt.setNull(10, java.sql.Types.VARCHAR); // Genere non applicabile
				pstmt.setNull(11, java.sql.Types.VARCHAR); // Categoria non applicabile
			}else if (oggetto instanceof Elettronica) {
				pstmt.setString(2, TipoOggetto.Elettronica.toString());
				pstmt.setString(3, ((Elettronica) oggetto).getMarchio());
				pstmt.setNull(4, java.sql.Types.VARCHAR); // Taglia non applicabile
				pstmt.setString(5, ((Elettronica) oggetto).getModello());
				pstmt.setDate(6, Date.valueOf(((Elettronica) oggetto).getAnnoUscita()));
				pstmt.setNull(7, java.sql.Types.VARCHAR); // Titolo non applicabile
				pstmt.setNull(8, java.sql.Types.VARCHAR); // ISBN non applicabile
				pstmt.setNull(9, java.sql.Types.VARCHAR); // Autore non applicabile
				pstmt.setNull(10, java.sql.Types.VARCHAR); // Genere non applicabile
				pstmt.setNull(11, java.sql.Types.VARCHAR); // Categoria non applicabile
			}else if (oggetto instanceof Libro) {
				pstmt.setString(2, TipoOggetto.Libro.toString());
				pstmt.setDate(6, Date.valueOf(((Libro) oggetto).getAnnoUscita()));
				pstmt.setString(7, ((Libro) oggetto).getTitolo());
				pstmt.setString(8, ((Libro) oggetto).getISBN());
				pstmt.setString(9, ((Libro) oggetto).getAutore());
				pstmt.setString(10, ((Libro) oggetto).getGenere());
				pstmt.setNull(3, java.sql.Types.VARCHAR); // Marchio non applicabile
				pstmt.setNull(4, java.sql.Types.VARCHAR); // Taglia non applicabile
				pstmt.setNull(5, java.sql.Types.VARCHAR); // Modello non applicabile
				pstmt.setNull(11, java.sql.Types.VARCHAR); // Categoria non applicabile
			}else if (oggetto instanceof StrumentoMusicale) {
				pstmt.setString(2, TipoOggetto.StrumentoMusicale.toString());
				pstmt.setString(3, ((StrumentoMusicale) oggetto).getMarchio());
				pstmt.setNull(4, java.sql.Types.VARCHAR); // Taglia non applicabile
				pstmt.setNull(5, java.sql.Types.VARCHAR); // Modello non applicabile
				pstmt.setNull(6, java.sql.Types.DATE); // Anno di uscita non applicabile
				pstmt.setNull(7, java.sql.Types.VARCHAR); // Titolo non applicabile
				pstmt.setNull(8, java.sql.Types.VARCHAR); // ISBN non applicabile
				pstmt.setNull(9, java.sql.Types.VARCHAR); // Autore non applicabile
				pstmt.setNull(10, java.sql.Types.VARCHAR); // Genere non applicabile
				pstmt.setNull(11, java.sql.Types.VARCHAR); // Categoria non applicabile
			} else {
				pstmt.setString(2, TipoOggetto.Misc.toString());
				pstmt.setString(3, ((Misc) oggetto).getMarchio());
				pstmt.setString(11, ((Misc) oggetto).getCategoria());
				pstmt.setNull(4, java.sql.Types.VARCHAR); // Taglia non applicabile
				pstmt.setNull(5, java.sql.Types.VARCHAR); // Modello non applicabile
				pstmt.setNull(6, java.sql.Types.DATE); // Anno di uscita non applicabile
				pstmt.setNull(7, java.sql.Types.VARCHAR); // Titolo non applicabile
				pstmt.setNull(8, java.sql.Types.VARCHAR); // ISBN non applicabile
				pstmt.setNull(9, java.sql.Types.VARCHAR); // Autore non applicabile
				pstmt.setNull(10, java.sql.Types.VARCHAR); // Genere non applicabile
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Oggetto oggetto) {
		//TODO: Implementazione per aggiornare un oggetto esistente
	}

	@Override
	public void delete(String id) {
		String query = "DELETE FROM oggetto WHERE id = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Oggetto creaOggettoCorretto(ResultSet rs) throws SQLException {
		Oggetto oggetto = null;
		String nome = rs.getString("nome");
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
				oggetto = new StrumentoMusicale(nome, proprietario, marchio);
				break;
			case "Abbigliamento":
				oggetto = new Abbigliamento(nome, proprietario, marchio, taglia);
				break;
			case "Elettronica":
				oggetto = new Elettronica(nome, proprietario, marchio, modello, annoUscita.toLocalDate());
				break;
			case "Libro":
				oggetto = new Libro(nome, proprietario, titolo, ISBN, annoUscita.toLocalDate(), autore, genere);
				break;
			case "Misc":
				oggetto = new Misc(nome, proprietario, marchio, categoria);
				break;
		}
		
		return oggetto;
	}

}
