package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.Database_Connection;

import dao.Message_Dao;

public class Message_Model {

	private static final String TABLE = "messages";
	
	public int saveOne(Message_Dao message) throws SQLException {

		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;

		try {
			String query = "INSERT INTO " + TABLE + " (nick, email, message, ip, request, sent) VALUES (?, ?, ?, ?, ?, NOW())";
			conn = db.Database_Connection.open();
			st = conn.prepareStatement(query);
			st.setString(1, message.getNick());
			st.setString(2, message.getEmail());
			st.setString(3, message.getMessage());
			st.setString(4, message.getIp());
			st.setBoolean(5, message.getRequest());
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
