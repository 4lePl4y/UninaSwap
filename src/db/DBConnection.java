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
		 if(conn == null || conn.isClosed()) {
			 conn = DriverManager.getConnection(
			  "jdbc:postgresql://aws-0-eu-central-2.pooler.supabase.com:5432/postgres?user=postgres.ukdoakobkjfbrymxtulr&password=UninaSwap"
			 );

	
		 }
		 
		 return conn;
	 }



}
