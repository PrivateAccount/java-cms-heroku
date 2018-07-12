package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.Database_Connection;

import dao.Message_Dao;

public class Messages_Model {

	private static final String TABLE = "messages";
	
	public Message_Dao getOne(int id) throws SQLException {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		Message_Dao message = new Message_Dao();

		try {
			String query = "SELECT * FROM " + TABLE + " WHERE id = ?";
			conn = db.Database_Connection.open();
			st = conn.prepareStatement(query);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				message.setId(rs.getInt("id"));
				message.setNick(rs.getString("nick"));
				message.setEmail(rs.getString("email"));
				message.setMessage(rs.getString("message"));
				message.setIp(rs.getString("ip"));
				message.setRequest(rs.getBoolean("request"));
				message.setSent(rs.getTimestamp("sent"));
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
		
		return message;
	}
	
	public ArrayList<Message_Dao> getAll(String filter) throws SQLException {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		ArrayList<Message_Dao> messages = new ArrayList<Message_Dao>();

		try {
			String condition = filter != null ? " WHERE nick LIKE '%" + filter + "%' OR email LIKE '%" + filter + "%' OR message LIKE '%" + filter + "%' OR ip LIKE '%" + filter + "%'" : "";
			String query = "SELECT * FROM " + TABLE + condition + " ORDER BY id DESC LIMIT 1000";
			conn = db.Database_Connection.open();
			st = conn.prepareStatement(query);
			rs = st.executeQuery();
			while (rs.next()) {
				Message_Dao message = new Message_Dao();
				message.setId(rs.getInt("id"));
				message.setNick(rs.getString("nick"));
				message.setEmail(rs.getString("email"));
				message.setMessage(rs.getString("message"));
				message.setIp(rs.getString("ip"));
				message.setRequest(rs.getBoolean("request"));
				message.setSent(rs.getTimestamp("sent"));
				messages.add(message);
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
		
		return messages;
	}
	
	public int updateOne(int id, Message_Dao message) throws SQLException {

		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;

		try {
			String query = "UPDATE " + TABLE + " SET request = ? WHERE id = ?";
			conn = db.Database_Connection.open();
			st = conn.prepareStatement(query);
			st.setBoolean(1, message.getRequest());
			st.setInt(2, id);
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
	
	public int removeOne(int id) throws SQLException {

		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;

		try {
			String query = "DELETE FROM " + TABLE + " WHERE id = ?";
			conn = db.Database_Connection.open();
			st = conn.prepareStatement(query);
			st.setInt(1, id);
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
	
	public int removeAll() throws SQLException {

		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;

		try {
			String query = "DELETE FROM " + TABLE + " WHERE request = ?";
			conn = db.Database_Connection.open();
			st = conn.prepareStatement(query);
			st.setBoolean(1, true);
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
