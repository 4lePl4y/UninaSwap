package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static DBConnection dbcon = null;
	private Connection conn = null;
	
	private DBConnection() {};
	
	public static DBConnection getDBConnection() {  
		if (dbcon == null) {
			dbcon = new DBConnection();
		}
	        
		return dbcon;
	}
	 
	 public Connection getConnection() throws SQLException {
		 try {
			 if(conn == null || conn.isClosed()) 
				 conn = DriverManager.getConnection("jdbc:postgresql://aws-0-eu-central-2.pooler.supabase.com:5432/postgres", "postgres.ukdoakobkjfbrymxtulr", "UninaSwap");
		 }
		 
		 catch (SQLException  e) {
			 System.out.println("Errore di connessione al database: " + e.getMessage());
			 throw e; 
		 }
		 
		 return conn;
	 }
	 
}
