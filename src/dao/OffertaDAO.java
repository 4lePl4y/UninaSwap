package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
				offerte.add(creaOffertaCorretto(rs));
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
		
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, offerta.getMessaggio());
			pstmt.setString(2, offerta.getOfferente().getUsername());
			pstmt.setString(3, String.valueOf(offerta.getAnnuncio().getId()));
			if(!(offerta instanceof OffertaScambio))
				pstmt.setDouble(4, ((OffertaDenaro)offerta).getOfferta());
			pstmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Offerta offerta) {
		//TODO: Implementazione per aggiornare un'offerta esistente
	}
	
	public void delete(Offerta offerta) {
		String query = ""; 
		if (offerta instanceof OffertaScambio)
			query = "DELETE FROM offerta_scambio WHERE id = ?;";
		else
			query = "DELETE FROM offerta_denaro WHERE id = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, String.valueOf(offerta.getId()));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Offerta creaOffertaCorretto(ResultSet rs) throws SQLException {
		long idOfferta = rs.getLong("id_offerta");
		Stato stato = Stato.valueOf(rs.getString("stato"));
		double offerta = rs.getDouble("offerta");
		String messaggio = rs.getString("messaggio");
		Studente offerente = studenteDAO.retrieveByPK(rs.getString("offerente"));
		Annuncio annuncio = annuncioDAO.retrieveByPK(rs.getString("idAnnuncio"));
		TipoAnnuncio tipoAnnuncio = TipoAnnuncio.valueOf(rs.getString("tipoAnnuncio"));
		
		Offerta offertaCorretta=null; 
		
		switch (tipoAnnuncio) {
			case Scambio:
				ArrayList<Oggetto> oggettiScambio = oggettoDAO.retrieveOggettiScambio(idOfferta);
				offertaCorretta = new OffertaScambio(idOfferta, stato, messaggio, offerente, annuncio, oggettiScambio);
				break;
			case Vendita:
				offertaCorretta = new OffertaDenaro(idOfferta, stato, messaggio, offerente, annuncio, offerta);
				break;
			case Regalo:
				offertaCorretta = new OffertaDenaro(idOfferta, stato, messaggio, offerente, annuncio, offerta);
				break;
		}
		return offertaCorretta;
	}
}
