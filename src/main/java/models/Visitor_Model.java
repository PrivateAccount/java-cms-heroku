package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.Database_Connection;

import dao.Visitor_Dao;

public class Visitor_Model {

	private static final String TABLE = "visitors";
	
	public int save(Visitor_Dao visitor) throws SQLException {

		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;

		try {
			String query = "INSERT INTO " + TABLE + " (visitor_ip, http_referer, request_uri, visited) VALUES (?, ?, ?, NOW())";
			conn = db.Database_Connection.open();
			st = conn.prepareStatement(query);
			st.setString(1, visitor.getVisitorIp());
			st.setString(2, visitor.getHttpReferer());
			st.setString(3, visitor.getRequestUri());
			result = st.executeUpdate();
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		finally {
			st.close();
			conn.close();
		}
		
		return result;
	}
}
