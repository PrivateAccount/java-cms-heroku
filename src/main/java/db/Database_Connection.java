package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database_Connection {
	
	public static Connection open() {
		
		Connection dbConnection = null;
		 
		String DB_HOST =  System.getenv("DB_HOST");
		String DB_PORT =  System.getenv("DB_PORT");
		String DB_NAME =  System.getenv("DB_NAME");
		String DB_USER =  System.getenv("DB_USER");
		String DB_PASS =  System.getenv("DB_PASS");

		String DB_CONN = "jdbc:postgresql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?user=" + DB_USER + "&password=" + DB_PASS + "&ssl=true";

		try { 
			Class.forName("org.postgresql.Driver");
		} 
		catch (java.lang.ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
 
		try { 
			dbConnection = DriverManager.getConnection(DB_CONN, DB_USER, DB_PASS);
		} 
		catch (java.sql.SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return dbConnection;
	}
}
