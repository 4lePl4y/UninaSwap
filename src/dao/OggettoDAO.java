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
			riempiCampiGiusti(oggetto, pstmt);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Oggetto oggetto) {
		String query = "UPDATE oggetto SET nome = ?, \"tipoOggetto\" = ?, marchio = ?, taglia = ?, modello = ?, \"annoUscita\" = ?, titolo = ?, \"ISBN\" = ?, autore = ?, genere = ?, proprietario = ?, categoria= ? WHERE id = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(query)){
			riempiCampiGiusti(oggetto, pstmt);
			pstmt.setLong(13, oggetto.getId());
			pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String id) {
		String query = "DELETE FROM oggetto WHERE id = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setLong(1, Long.valueOf(id));
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
	
	//**Metodo per riempire tutti i campi della tupla nel DB a seconda del tipo di oggetto (l'id viene tralasciato)*/
	private void riempiCampiGiusti(Oggetto oggetto, PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, oggetto.getNome());
		pstmt.setString(11, oggetto.getUsernameProprietario());
		switch(oggetto) { 
			case Abbigliamento a -> {
				pstmt.setObject(2, TipoOggetto.Abbigliamento, java.sql.Types.OTHER);
				pstmt.setString(3, a.getMarchio());
				pstmt.setString(4, a.getTaglia());
				pstmt.setNull(5, java.sql.Types.VARCHAR); // Modello non applicabile
				pstmt.setNull(6, java.sql.Types.INTEGER); // Anno di uscita non applicabile
				pstmt.setNull(7, java.sql.Types.VARCHAR); // Titolo non applicabile
				pstmt.setNull(8, java.sql.Types.VARCHAR); // ISBN non applicabile
				pstmt.setNull(9, java.sql.Types.VARCHAR); // Autore non applicabile
				pstmt.setNull(10, java.sql.Types.VARCHAR); // Genere non applicabile
				pstmt.setNull(12, java.sql.Types.VARCHAR); // Categoria non applicabile
			}
			case Elettronica e -> {
				pstmt.setObject(2, TipoOggetto.Elettronica, java.sql.Types.OTHER);
				pstmt.setString(3, e.getMarchio());
				pstmt.setNull(4, java.sql.Types.VARCHAR); // Taglia non applicabile
				pstmt.setString(5, e.getModello());
				pstmt.setInt(6, e.getAnnoUscita().getValue());
				pstmt.setNull(7, java.sql.Types.VARCHAR); // Titolo non applicabile
				pstmt.setNull(8, java.sql.Types.VARCHAR); // ISBN non applicabile
				pstmt.setNull(9, java.sql.Types.VARCHAR); // Autore non applicabile
				pstmt.setNull(10, java.sql.Types.VARCHAR); // Genere non applicabile
				pstmt.setNull(12, java.sql.Types.VARCHAR); // Categoria non applicabile
			}
			case Libro l -> {
				pstmt.setObject(2, TipoOggetto.Libro, java.sql.Types.OTHER);
				pstmt.setInt(6, l.getAnnoUscita().getValue());
				pstmt.setString(7, l.getTitolo());
				pstmt.setString(8, l.getISBN());
				pstmt.setString(9, l.getAutore());
				pstmt.setString(10, l.getGenere());
				pstmt.setNull(3, java.sql.Types.VARCHAR); // Marchio non applicabile
				pstmt.setNull(4, java.sql.Types.VARCHAR); // Taglia non applicabile
				pstmt.setNull(5, java.sql.Types.VARCHAR); // Modello non applicabile
				pstmt.setNull(12, java.sql.Types.VARCHAR); // Categoria non applicabile
			}
			case StrumentoMusicale sm -> {
				pstmt.setObject(2, TipoOggetto.StrumentoMusicale, java.sql.Types.OTHER);
				pstmt.setString(3, sm.getMarchio());
				pstmt.setNull(4, java.sql.Types.VARCHAR); // Taglia non applicabile
				pstmt.setNull(5, java.sql.Types.VARCHAR); // Modello non applicabile
				pstmt.setNull(6, java.sql.Types.INTEGER); // Anno di uscita non applicabile
				pstmt.setNull(7, java.sql.Types.VARCHAR); // Titolo non applicabile
				pstmt.setNull(8, java.sql.Types.VARCHAR); // ISBN non applicabile
				pstmt.setNull(9, java.sql.Types.VARCHAR); // Autore non applicabile
				pstmt.setNull(10, java.sql.Types.VARCHAR); // Genere non applicabile
				pstmt.setNull(12, java.sql.Types.VARCHAR); // Categoria non applicabile
			}
			case Misc m -> {
				pstmt.setObject(2, TipoOggetto.Misc, java.sql.Types.OTHER);
				pstmt.setString(3, m.getMarchio());
				pstmt.setNull(4, java.sql.Types.VARCHAR); // Taglia non applicabile
				pstmt.setNull(5, java.sql.Types.VARCHAR); // Modello non applicabile
				pstmt.setNull(6, java.sql.Types.INTEGER); // Anno di uscita non applicabile
				pstmt.setNull(7, java.sql.Types.VARCHAR); // Titolo non applicabile
				pstmt.setNull(8, java.sql.Types.VARCHAR); // ISBN non applicabile
				pstmt.setNull(9, java.sql.Types.VARCHAR); // Autore non applicabile
				pstmt.setNull(10, java.sql.Types.VARCHAR); // Genere non applicabile
				pstmt.setString(12, m.getCategoria());
			}
			default -> {}
		}
	}
}
