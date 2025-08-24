package dao;

import entities.annuncio.*;
import entities.oggetto.*;
import entities.studente.Studente;
import entities.enumerazioni.Sede;
import entities.enumerazioni.TipoAnnuncio;
import entities.enumerazioni.TipoOggetto;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Time;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnnuncioDAO implements DaoInterface<Annuncio> {
	//ATTRIBUTI
	private Connection conn;
	private StudenteDAO studenteDAO;
	private OggettoDAO oggettoDAO;
	
	//COSTRUTTORE
	public AnnuncioDAO(Connection conn) {
		this.conn = conn;
		this.studenteDAO = new StudenteDAO(conn);
		this.oggettoDAO = new OggettoDAO(conn);
		
	}
	
	//METODI CRUD
	@Override
	public Annuncio retrieveByPK(String id) {
		Annuncio annuncio = null;
		String query = "SELECT * FROM annuncio WHERE id = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setLong(1, Long.valueOf(id));
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				annuncio = creaAnnuncioCorretto(rs);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return annuncio;
	}
	
	public ArrayList<Annuncio> retrieveByAutore(String usernameAutore) {
		ArrayList<Annuncio> annunci = new ArrayList<>();
		String query = "SELECT * FROM annuncio WHERE autore = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, usernameAutore);
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
	
	
	public ArrayList<Annuncio> retrieveByOggetto(long idOggetto) {
		ArrayList<Annuncio> annunci = new ArrayList<>();
		String query = "SELECT * FROM annuncio WHERE \"idOggetto\" = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setLong(1, idOggetto);
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
	

	@Override
	public void create(Annuncio annuncio) {
		String query = "INSERT INTO annuncio (titolo, descrizione, luogo, \"oraIncontro\", \"dataPubblicazione\", \"tipoAnnuncio\", prezzo, autore, \"idOggetto\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, annuncio.getTitolo());
			pstmt.setString(2, annuncio.getDescrizione());
			pstmt.setObject(3, annuncio.getLuogo(), java.sql.Types.OTHER);
			pstmt.setTime(4, Time.valueOf(annuncio.getOraIncontro()));
			pstmt.setDate(5, Date.valueOf(annuncio.getDataPubblicazione()));
			pstmt.setString(8, annuncio.getAutore().getUsername());
			pstmt.setLong(9, annuncio.getOggetto().getId());
			
			if (annuncio instanceof AnnuncioVendita) {
				pstmt.setObject(6, TipoAnnuncio.Vendita, java.sql.Types.OTHER);
				pstmt.setDouble(7, ((AnnuncioVendita) annuncio).getPrezzo());
			} else if (annuncio instanceof AnnuncioScambio) {
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
		String query = "UPDATE annuncio "
		    + "SET titolo = ?, descrizione = ?, luogo = ?, \"oraIncontro\" = ?, \"tipoAnnuncio\" = ?, prezzo = ? WHERE id = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, annuncio.getTitolo());
			pstmt.setString(2, annuncio.getDescrizione());
			pstmt.setObject(3, annuncio.getLuogo(), java.sql.Types.OTHER);
			pstmt.setTime(4, Time.valueOf(annuncio.getOraIncontro()));
			pstmt.setLong(7, annuncio.getId());
			if (annuncio instanceof AnnuncioVendita) {
				pstmt.setObject(5, TipoAnnuncio.Vendita, java.sql.Types.OTHER);
				pstmt.setDouble(6, ((AnnuncioVendita) annuncio).getPrezzo());
			} else if (annuncio instanceof AnnuncioScambio) {
				pstmt.setObject(5, TipoAnnuncio.Scambio, java.sql.Types.OTHER);
				pstmt.setNull(6, java.sql.Types.DOUBLE);
			} else {
				pstmt.setObject(5, TipoAnnuncio.Regalo, java.sql.Types.OTHER);
				pstmt.setNull(6, java.sql.Types.DOUBLE);
			}
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String id) {
		String query = "DELETE FROM annuncio WHERE id = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setLong(1, Long.valueOf(id));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	
	//ALTRI METODI
	private Annuncio creaAnnuncioCorretto(ResultSet rs) throws SQLException {
		long id = rs.getLong("id");
		String titolo = rs.getString("titolo");
		String descrizione = rs.getString("descrizione");
		String luogo = rs.getString("luogo");
		Time oraIncontro = rs.getTime("oraIncontro");
		Date dataPubblicazione = rs.getDate("dataPubblicazione");
		Studente autore = studenteDAO.retrieveByPK(rs.getString("autore"));
		Oggetto oggetto = oggettoDAO.retrieveByPK(rs.getString("idOggetto"));
		String tipoAnnuncio = rs.getString("tipoAnnuncio");
		
		Annuncio annuncio = null;
		switch(tipoAnnuncio) {	
			case "Vendita":
				double prezzo = rs.getDouble("prezzo");
				annuncio = new AnnuncioVendita(id, titolo, autore, oggetto, descrizione, Sede.valueOf(luogo), oraIncontro.toLocalTime(), dataPubblicazione.toLocalDate(), prezzo);
				break;
		
			case "Regalo":
				annuncio = new AnnuncioRegalo(id, titolo, autore, oggetto, descrizione, Sede.valueOf(luogo), oraIncontro.toLocalTime(), dataPubblicazione.toLocalDate());
				break;
				
			case "Scambio":
				annuncio = new AnnuncioScambio(id, titolo, autore, oggetto, descrizione, Sede.valueOf(luogo), oraIncontro.toLocalTime(), dataPubblicazione.toLocalDate());
	}
	
		return annuncio;
	}
	

	public ArrayList<Annuncio> getAltriAnnunci(int numeroAnnunci, String username) {
		ArrayList<Annuncio> annunci = new ArrayList<>();
		String query = "SELECT * FROM annuncio WHERE autore <> ? LIMIT ?;";
		
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, username);
			pstmt.setInt(2, numeroAnnunci);
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
	
	
	public ArrayList<Annuncio> getAltriAnnunciByRicerca(String username, String research, boolean[] filters) {
		ArrayList<Annuncio> annunci = new ArrayList<>();
		String query = "SELECT * FROM annuncio AS a JOIN oggetto AS o ON a.\"idOggetto\" = o.id "
				+ " WHERE a.autore <> ? AND (a.titolo ILIKE ? OR a.descrizione ILIKE ?) ";
		
		int activePositions = 3;
		for(int i = 0; i < filters.length; i++) {
			if(filters[i]) 
				activePositions++;
		}
		
		if(activePositions > 3 && activePositions <= 7) {
			query += "AND o.\"tipoOggetto\" IN (";			
			for(int i = 0; i < activePositions-3; i++) {
					query += "?, ";	
			}
			
			query = query.substring(0, query.length() - 2);
			query += ");";
		}
		
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, username);
			pstmt.setString(2, "%" + research + "%");
			pstmt.setString(3, "%" + research + "%");
			
			
			if(activePositions > 3 && activePositions <= 7) {
				int index = 4;
				for(int i = 0; i < filters.length; i++) {
					if(filters[i]) {
						switch(i) {
							case 0:
								pstmt.setObject(index, TipoOggetto.Abbigliamento, java.sql.Types.OTHER);
								break;
							case 1:
								pstmt.setObject(index, TipoOggetto.Elettronica, java.sql.Types.OTHER);
								break;
							case 2:
								pstmt.setObject(index, TipoOggetto.Libro, java.sql.Types.OTHER);
								break;
							case 3:
								pstmt.setObject(index, TipoOggetto.StrumentoMusicale, java.sql.Types.OTHER);
								break;
							case 4:
								pstmt.setObject(index, TipoOggetto.Misc, java.sql.Types.OTHER);
								break;
						}
						index++;
					}
				}
			}
			
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
