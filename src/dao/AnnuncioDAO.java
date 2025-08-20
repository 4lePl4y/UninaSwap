package dao;

import entities.annuncio.*;
import entities.oggetto.*;
import entities.studente.Studente;
import entities.enumerazioni.Sede;
import entities.enumerazioni.TipoAnnuncio;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Time;
import java.time.LocalDate;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnnuncioDAO implements DaoInterface<Annuncio> {
	//ATTRIBUTI
	private Connection conn;
	private StudenteDAO studenteDAO = new StudenteDAO(conn);
	private OggettoDAO oggettoDAO = new OggettoDAO(conn);
	
	//COSTRUTTORE
	public AnnuncioDAO(Connection conn) {
		this.conn = conn;
	}
	
	//METODI CRUD
	@Override
	public Annuncio retrieveByPK(String id) {
		Annuncio annuncio = null;
		String query = "SELECT * FROM annuncio WHERE id = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				annuncio = creaAnnuncioCorretto(rs);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return annuncio;
	}
	

	@Override
	public void create(Annuncio annucio) {
		String query = "INSERT INTO annuncio (titolo, descrizione, luogo, oraIncontro, dataPubblicazione, tipoAnnuncio, prezzo, autore, oggetto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, annucio.getTitolo());
			pstmt.setString(2, annucio.getDescrizione());
			pstmt.setObject(3, annucio.getLuogo(), java.sql.Types.OTHER);
			pstmt.setTime(4, Time.valueOf(annucio.getOraIncontro()));
			pstmt.setDate(5, Date.valueOf(LocalDate.now()));
			pstmt.setString(8, annucio.getAutore().getUsername());
			pstmt.setLong(9, annucio.getOggetto().getId());
			
			if (annucio instanceof AnnuncioVendita) {
				pstmt.setObject(6, TipoAnnuncio.Vendita, java.sql.Types.OTHER);
				pstmt.setDouble(7, ((AnnuncioVendita) annucio).getPrezzo());
			} else if (annucio instanceof AnnuncioScambio) {
				pstmt.setObject(6, TipoAnnuncio.Scambio, java.sql.Types.OTHER);
				pstmt.setNull(7, java.sql.Types.DOUBLE);
			} else {
				pstmt.setObject(6, TipoAnnuncio.Regalo, java.sql.Types.OTHER);
				pstmt.setNull(7, java.sql.Types.DOUBLE);
			}
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Annuncio annuncio) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void delete(String id) {
		String query = "DELETE FROM annuncio WHERE id = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	
	//ALTRI METODI
	private Annuncio creaAnnuncioCorretto(ResultSet rs) throws SQLException {
		String titolo = rs.getString("titolo");
		String descrizione = rs.getString("descrizione");
		String luogo = rs.getString("luogo");
		Time oraIncontro = rs.getTime("oraIncontro");
		Date dataPubblicazione = rs.getDate("dataPubblicazione");
		Studente autore = studenteDAO.retrieveByPK(rs.getString("autore"));
		Oggetto oggetto = oggettoDAO.retrieveByPK(rs.getString("oggetto"));
		String tipoAnnuncio = rs.getString("tipoAnnuncio");
		
		Annuncio annuncio = null;
		switch(tipoAnnuncio) {	
			case "Vendita":
				double prezzo = rs.getDouble("prezzo");
				annuncio = new AnnuncioVendita(titolo, autore, oggetto, descrizione, Sede.valueOf(luogo), oraIncontro.toLocalTime(), dataPubblicazione.toLocalDate(), prezzo);
				break;
		
			case "Regalo":
				annuncio = new AnnuncioRegalo(titolo, autore, oggetto, descrizione, Sede.valueOf(luogo), oraIncontro.toLocalTime(), dataPubblicazione.toLocalDate());
				break;
				
			case "Scambio":
				annuncio = new AnnuncioScambio(titolo, autore, oggetto, descrizione, Sede.valueOf(luogo), oraIncontro.toLocalTime(), dataPubblicazione.toLocalDate());
	}
	
		return annuncio;
	}
	

	public ArrayList<Annuncio> getAnnunci(int numeroAnnunci) {
		ArrayList<Annuncio> annunci = new ArrayList<>();
		String query = "SELECT * FROM annuncio LIMIT ?;";
		
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, numeroAnnunci);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Annuncio annuncio = creaAnnuncioCorretto(rs);
				annunci.add(annuncio);					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			 
		
		return annunci;	
	}

}
