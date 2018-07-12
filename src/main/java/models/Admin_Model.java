package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.Database_Connection;

public class Admin_Model {

	public int getCount(String table) throws SQLException {

		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			String query = null;
			if (table.equals("visitors")) {
				query = "SELECT COUNT(*) FROM (SELECT DISTINCT visitor_ip FROM " + table + ") AS counter";
			}
			else if (table.equals("messages")) {
				query = "SELECT COUNT(*) FROM " + table + " WHERE request = TRUE";
			}
			conn = db.Database_Connection.open();
			st = conn.prepareStatement(query);
			rs = st.executeQuery();
			if (rs.next()) {				
				result = rs.getInt("count");
			}
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		finally {
			rs.close();
			st.close();
			conn.close();
		}
		
		return result;
	}
}
