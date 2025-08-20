package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.studente.Studente;

public class StudenteDAO implements DaoInterface<Studente> {
	//ATTRIBUTI
	private Connection conn;
	
	//COSTRUTTORE
	public StudenteDAO(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public Studente retrieveByPK(String username) {
		Studente studente = null;
		String query = "SELECT * FROM studente WHERE username = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String usrnm = rs.getString("username");
				String email = rs.getString("email");
				String password = rs.getString("password"); // Non necessario per il recupero
				studente = new Studente(nome, cognome, usrnm, email, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return studente;
	}
	
	public Studente retrieveByEmail(String inEmail) {
		Studente studente = null;
		String query = "SELECT * FROM studente WHERE email = ?;";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, inEmail);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				String usrnm = rs.getString("username");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String email = rs.getString("email");
				String password = rs.getString("password"); // Non necessario per il recupero
				studente = new Studente(nome, cognome, email, usrnm, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return studente;
	}

	@Override
	public void create(Studente studente) {
		String query = "INSERT INTO studente (nome, cognome, email, username, password) VALUES (?, ?, ?, ?, ?);";
		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, studente.getNome());
			pstmt.setString(2, studente.getCognome());
			pstmt.setString(3, studente.getEmail());
			pstmt.setString(4, studente.getUsername());
			pstmt.setString(5, studente.getPassword());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Studente studente) {
		//TODO: Implementare l'aggiornamento dello studente
	}

	@Override
	public void delete(String username) {
		String query = "DELETE FROM studente WHERE username = ?;";
		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, username);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
