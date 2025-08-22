package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;

import entities.enumerazioni.TipoOggetto;
import entities.oggetto.*;
import entities.studente.Studente;

public class OggettoDAO implements DaoInterface<Oggetto> {
	//ATTRIBUTI
	private Connection conn;
	private StudenteDAO studenteDAO;

	//COSTRUTTORE
	public OggettoDAO(Connection conn) {
		this.conn = conn;
		this.studenteDAO = new StudenteDAO(conn);
	}

	@Override
	public Oggetto retrieveByPK(String id) {
		Oggetto oggetto = null;
		String query = "SELECT * FROM oggetto WHERE id = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setLong(1, Long.valueOf(id));
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
				oggetto = creaOggettoCorretto(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return oggetto;
	}
	
	public ArrayList<Oggetto> retrieveByUsername(String username) {
		ArrayList<Oggetto> oggetti = new ArrayList<>();
		String query = "SELECT * FROM oggetto WHERE proprietario = ?;";
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
	
	public ArrayList<Oggetto> retrieveOggettiScambio(long idOffertaScambio) {
		ArrayList<Oggetto> oggetti = new ArrayList<>();
		String query = "SELECT * FROM oggetto_per_scambio AS ops JOIN oggetto AS o on ops.\"idOggetto\" = o.id WHERE \"idOffertaScambio\"= ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setLong(1, idOffertaScambio);
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
		String query = "INSERT INTO oggetto (nome, \"tipoOggetto\", marchio, taglia, modello, \"annoUscita\", titolo, \"ISBN\", autore, genere, proprietario, categoria) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, oggetto.getNome());
			pstmt.setString(11, oggetto.getUsernameProprietario());
			if (oggetto instanceof Abbigliamento) {
				pstmt.setObject(2, TipoOggetto.Abbigliamento, java.sql.Types.OTHER);
				pstmt.setString(3, ((Abbigliamento) oggetto).getMarchio());
				pstmt.setString(4, ((Abbigliamento) oggetto).getTaglia());
				pstmt.setNull(5, java.sql.Types.VARCHAR); // Modello non applicabile
				pstmt.setNull(6, java.sql.Types.INTEGER); // Anno di uscita non applicabile
				pstmt.setNull(7, java.sql.Types.VARCHAR); // Titolo non applicabile
				pstmt.setNull(8, java.sql.Types.VARCHAR); // ISBN non applicabile
				pstmt.setNull(9, java.sql.Types.VARCHAR); // Autore non applicabile
				pstmt.setNull(10, java.sql.Types.VARCHAR); // Genere non applicabile
				pstmt.setNull(12, java.sql.Types.VARCHAR); // Categoria non applicabile
			}else if (oggetto instanceof Elettronica) {
				pstmt.setObject(2, TipoOggetto.Elettronica, java.sql.Types.OTHER);
				pstmt.setString(3, ((Elettronica) oggetto).getMarchio());
				pstmt.setNull(4, java.sql.Types.VARCHAR); // Taglia non applicabile
				pstmt.setString(5, ((Elettronica) oggetto).getModello());
				pstmt.setInt(6, ((Elettronica) oggetto).getAnnoUscita().getValue());
				pstmt.setNull(7, java.sql.Types.VARCHAR); // Titolo non applicabile
				pstmt.setNull(8, java.sql.Types.VARCHAR); // ISBN non applicabile
				pstmt.setNull(9, java.sql.Types.VARCHAR); // Autore non applicabile
				pstmt.setNull(10, java.sql.Types.VARCHAR); // Genere non applicabile
				pstmt.setNull(12, java.sql.Types.VARCHAR); // Categoria non applicabile
			}else if (oggetto instanceof Libro) {
				pstmt.setObject(2, TipoOggetto.Libro, java.sql.Types.OTHER);
				pstmt.setInt(6, ((Libro) oggetto).getAnnoUscita().getValue());
				pstmt.setString(7, ((Libro) oggetto).getTitolo());
				pstmt.setString(8, ((Libro) oggetto).getISBN());
				pstmt.setString(9, ((Libro) oggetto).getAutore());
				pstmt.setString(10, ((Libro) oggetto).getGenere());
				pstmt.setNull(3, java.sql.Types.VARCHAR); // Marchio non applicabile
				pstmt.setNull(4, java.sql.Types.VARCHAR); // Taglia non applicabile
				pstmt.setNull(5, java.sql.Types.VARCHAR); // Modello non applicabile
				pstmt.setNull(12, java.sql.Types.VARCHAR); // Categoria non applicabile
			}else if (oggetto instanceof StrumentoMusicale) {
				pstmt.setObject(2, TipoOggetto.StrumentoMusicale, java.sql.Types.OTHER);
				pstmt.setString(3, ((StrumentoMusicale) oggetto).getMarchio());
				pstmt.setNull(4, java.sql.Types.VARCHAR); // Taglia non applicabile
				pstmt.setNull(5, java.sql.Types.VARCHAR); // Modello non applicabile
				pstmt.setNull(6, java.sql.Types.INTEGER); // Anno di uscita non applicabile
				pstmt.setNull(7, java.sql.Types.VARCHAR); // Titolo non applicabile
				pstmt.setNull(8, java.sql.Types.VARCHAR); // ISBN non applicabile
				pstmt.setNull(9, java.sql.Types.VARCHAR); // Autore non applicabile
				pstmt.setNull(10, java.sql.Types.VARCHAR); // Genere non applicabile
				pstmt.setNull(12, java.sql.Types.VARCHAR); // Categoria non applicabile
			} else {
				pstmt.setObject(2, TipoOggetto.Misc, java.sql.Types.OTHER);
				pstmt.setString(3, ((Misc) oggetto).getMarchio());
				pstmt.setNull(4, java.sql.Types.VARCHAR); // Taglia non applicabile
				pstmt.setNull(5, java.sql.Types.VARCHAR); // Modello non applicabile
				pstmt.setNull(6, java.sql.Types.INTEGER); // Anno di uscita non applicabile
				pstmt.setNull(7, java.sql.Types.VARCHAR); // Titolo non applicabile
				pstmt.setNull(8, java.sql.Types.VARCHAR); // ISBN non applicabile
				pstmt.setNull(9, java.sql.Types.VARCHAR); // Autore non applicabile
				pstmt.setNull(10, java.sql.Types.VARCHAR); // Genere non applicabile
				pstmt.setString(12, ((Misc) oggetto).getCategoria());
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
		Long id = rs.getLong("id");
		String nome = rs.getString("nome");
		String tipoOggetto = rs.getString("tipoOggetto");
		String marchio = rs.getString("marchio");
		String taglia = rs.getString("taglia");
		String modello = rs.getString("modello");
		Year annoUscita =  Year.of(rs.getInt("annoUscita"));
		String titolo = rs.getString("titolo"); 
		String ISBN = rs.getString("ISBN"); 
		String autore = rs.getString("autore"); 
		String genere = rs.getString("genere");
		String categoria = rs.getString("categoria");
		Studente proprietario = studenteDAO.retrieveByPK(rs.getString("proprietario"));
		
		switch (tipoOggetto) {
			case "StrumentoMusicale":
				oggetto = new StrumentoMusicale(id, nome, proprietario, marchio);
				break;
			case "Abbigliamento":
				oggetto = new Abbigliamento(id, nome, proprietario, marchio, taglia);
				break;
			case "Elettronica":
				oggetto = new Elettronica(id, nome, proprietario, marchio, modello, annoUscita);
				break;
			case "Libro":
				oggetto = new Libro(id, nome, proprietario, titolo, ISBN, annoUscita, autore, genere);
				break;
			case "Misc":
				oggetto = new Misc(id, nome, proprietario, marchio, categoria);
				break;
		}
		return oggetto;
	}

}
