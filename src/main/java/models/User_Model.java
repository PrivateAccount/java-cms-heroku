package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.NoSuchAlgorithmException;

import db.Database_Connection;

import dao.User_Dao;

public class User_Model {

	private static final String TABLE = "users";
	
	public User_Dao getUser(String login, String password) throws SQLException {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		User_Dao user = new User_Dao();
		
		user.setPassword(password);

		try {
			String encodedPassword = user.getEncodedPassword();
			String query = "SELECT * FROM " + TABLE + " WHERE login = ? AND password = ? ORDER BY id DESC LIMIT 1";
			conn = db.Database_Connection.open();
			st = conn.prepareStatement(query);
			st.setString(1, login);
			st.setString(2, encodedPassword);
			rs = st.executeQuery();
			if (rs.next()) {				
				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setEmail(rs.getString("email"));
				user.setRegistered(rs.getTimestamp("registered"));
				user.setLogged(rs.getTimestamp("logged"));
			}
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		catch (NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		} 
		finally {
			rs.close();
			st.close();
			conn.close();
		}
		
		return user;
	}
	
	public int updateLogged(User_Dao user) throws SQLException {

		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;

		try {
			String query = "UPDATE " + TABLE + " SET logged = NOW() WHERE id = ?";
			conn = db.Database_Connection.open();
			st = conn.prepareStatement(query);
			st.setInt(1, user.getId());
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
