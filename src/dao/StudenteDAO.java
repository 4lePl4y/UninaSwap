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
	public Studente retrieve(String username) {
		String query = "SELECT * FROM studenti WHERE username = " + username + ";";
		try(PreparedStatement pstmt = conn.prepareStatement(query)) {
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				String usrnm = rs.getString("username");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String email = rs.getString("email");
				String password = rs.getString("password"); // Non necessario per il recupero
				Studente studente = new Studente(nome, cognome, email, usrnm, password);
				return studente;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void create(Studente studente) {
		String query = "INSERT INTO studenti (nome, cognome, email, username, password) VALUES (?, ?, ?, ?, ?);";
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
		// Logica per aggiornare uno studente nel database
	}

	@Override
	public void delete(String username) {
		String query = "DELETE FROM studenti WHERE username = " + username + ";";
		try (PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
