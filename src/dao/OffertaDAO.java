package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.annuncio.*;
import entities.enumerazioni.*;
import entities.offerta.*;
import entities.oggetto.Oggetto;
import entities.studente.Studente;

public class OffertaDAO implements DaoInterface<Offerta> {
	//ATTRIBUTI
	private Connection conn;
	private StudenteDAO studenteDAO;
	private AnnuncioDAO annuncioDAO;
	private OggettoDAO oggettoDAO;

	//COSTRUTTORE
	public OffertaDAO(Connection conn) {
		this.conn = conn;
		this.studenteDAO = new StudenteDAO(conn);
		this.annuncioDAO = new AnnuncioDAO(conn);
		this.oggettoDAO = new OggettoDAO(conn);
	}
	
	public ArrayList<Offerta> retrieveByOfferente(String usernameOfferente) {
		ArrayList<Offerta> offerte = new ArrayList<>();
		String query = "SELECT * FROM offerte_unificate WHERE offerente = ? ;";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, usernameOfferente);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				offerte.add(creaOffertaCorretta(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return offerte;
	}
	
	public ArrayList<Offerta> retrieveByRicevente(String usernameRicevente) {
		ArrayList<Offerta> offerte = new ArrayList<>();
		String query = "SELECT * FROM offerte_unificate WHERE \"idAnnuncio\" IN (SELECT id FROM annuncio WHERE autore = ?);";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, usernameRicevente);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				offerte.add(creaOffertaCorretta(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return offerte;
	}
	
	public void create(Offerta offerta) {
		String query = "";
		if(offerta instanceof OffertaScambio)
			query = "INSERT INTO offerta_scambio (messaggio, offerente, \"idAnnuncio\") VALUES (?, ?, ?);";			
		else
			query = "INSERT INTO offerta_denaro (messaggio, offerente, \"idAnnuncio\", offerta ) VALUES (?, ?, ?, ?);";
		
		try(PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, offerta.getMessaggio());
			pstmt.setString(2, offerta.getOfferente().getUsername());
			pstmt.setLong(3, offerta.getAnnuncio().getId());
			if(!(offerta instanceof OffertaScambio)) {
				pstmt.setDouble(4, ((OffertaDenaro)offerta).getOfferta());
				pstmt.executeUpdate();
			}else {
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				rs.next();
				Long idOfferta = rs.getLong("id");
				ArrayList<Oggetto> oggettiOfferti = ((OffertaScambio)offerta).getOggettiOfferti();
				query = "INSERT INTO oggetto_per_scambio (\"idOggetto\", \"idOffertaScambio\") VALUES ";
				for(int i=0; i<oggettiOfferti.size(); i++) {
					query += "(?, ?),";
				}
				query = query.substring(0, query.length() - 1); // Rimuove l'ultima virgola
				query += ";";
				try(PreparedStatement pstmtOggetto = conn.prepareStatement(query)) {
					for(int i=0, p=1, s=2; i<oggettiOfferti.size(); i++, p+=2, s+=2) {
						pstmtOggetto.setLong(p, oggettiOfferti.get(i).getId());
						pstmtOggetto.setLong(s, idOfferta);
					}
					pstmtOggetto.executeUpdate();
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Offerta offerta) {
		String query = "";
		if (offerta instanceof OffertaScambio) {
			query = "UPDATE offerta_scambio SET messaggio = ? WHERE id = ?;";
			try(PreparedStatement pstmt = conn.prepareStatement(query)) {
				pstmt.setString(1, offerta.getMessaggio());
				pstmt.setLong(2, offerta.getId());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			query = "DELETE FROM oggetto_per_scambio WHERE \"idOffertaScambio\" = ?";
			try(PreparedStatement pstmt = conn.prepareStatement(query)){
				pstmt.setLong(1, offerta.getId());
				pstmt.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			Long idOfferta = offerta.getId();
			ArrayList<Oggetto> oggettiOfferti = ((OffertaScambio) offerta).getOggettiOfferti();
			query = "INSERT INTO oggetto_per_scambio (\"idOggetto\", \"idOffertaScambio\") VALUES";
			for(int i=0; i<oggettiOfferti.size(); i++) {
				query += "(?, ?),";
			}
			query = query.substring(0, query.length() - 1); // Rimuove l'ultima virgola
			query += ";";
			try(PreparedStatement pstmt = conn.prepareStatement(query)){
				for(int i=0, p=1, s=2; i<oggettiOfferti.size(); i++, p+=2, s+=2) {
					pstmt.setLong(p, oggettiOfferti.get(i).getId());
					pstmt.setLong(s, idOfferta);
				}
				pstmt.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			query = "UPDATE offerta_denaro SET messaggio = ?, offerta = ? WHERE id = ?;";
			try(PreparedStatement pstmt = conn.prepareStatement(query)) {
				pstmt.setString(1, offerta.getMessaggio());
				pstmt.setDouble(2, ((OffertaDenaro)offerta).getOfferta());
				pstmt.setLong(3, offerta.getId());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void updateOffertaRifiutata(Offerta offerta) {
		String query = "";
		if (offerta instanceof OffertaScambio)
			query = "UPDATE offerta_scambio SET stato = \'Rifiutata\' WHERE id = ?;";
		else
			query = "UPDATE offerta_denaro SET stato = \'Rifiutata\' WHERE id = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setLong(1, offerta.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateOffertaAccettata(Offerta offerta) {
		String query = "";
		if (offerta instanceof OffertaScambio)
			query = "UPDATE offerta_scambio SET stato = \'Accettata\' WHERE id = ?;";
		else
			query = "UPDATE offerta_denaro SET stato = \'Accettata\' WHERE id = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setLong(1, offerta.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Offerta offerta) {
		String query = ""; 
		if (offerta instanceof OffertaScambio)
			query = "DELETE FROM offerta_scambio WHERE id = ?;";
		else
			query = "DELETE FROM offerta_denaro WHERE id = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setLong(1, offerta.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Offerta creaOffertaCorretta(ResultSet rs) throws SQLException {
		long id = rs.getLong("id_offerta");
		Stato stato = Stato.valueOf(rs.getString("stato"));
		double offerta = rs.getDouble("offerta");
		String messaggio = rs.getString("messaggio");
		Studente offerente = studenteDAO.retrieveByPK(rs.getString("offerente"));
		Annuncio annuncio = annuncioDAO.retrieveByPK(rs.getString("idAnnuncio"));
		TipoAnnuncio tipoAnnuncio = TipoAnnuncio.valueOf(rs.getString("tipoAnnuncio"));
		
		Offerta offertaCorretta=null; 
		
		switch (tipoAnnuncio) {
			case Scambio:
				ArrayList<Oggetto> oggettiScambio = oggettoDAO.retrieveOggettiScambio(id);
				offertaCorretta = new OffertaScambio(id, stato, messaggio, offerente, annuncio, oggettiScambio);
				break;
			case Vendita:
				offertaCorretta = new OffertaDenaro(id, stato, messaggio, offerente, annuncio, offerta);
				break;
			case Regalo:
				offertaCorretta = new OffertaDenaro(id, stato, messaggio, offerente, annuncio, offerta);
				break;
		}
		return offertaCorretta;
	}
}
