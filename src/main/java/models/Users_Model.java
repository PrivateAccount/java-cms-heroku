package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import db.Database_Connection;

import dao.User_Dao;

public class Users_Model {

	private static final String TABLE = "users";
	
	public User_Dao getOne(int id) throws SQLException {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		User_Dao user = new User_Dao();

		try {
			String query = "SELECT * FROM " + TABLE + " WHERE id = ?";
			conn = db.Database_Connection.open();
			st = conn.prepareStatement(query);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setRegistered(rs.getTimestamp("registered"));
				user.setLogged(rs.getTimestamp("logged"));
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
		
		return user;
	}
	
	public ArrayList<User_Dao> getAll(String filter) throws SQLException {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		ArrayList<User_Dao> users = new ArrayList<User_Dao>();

		try {
			String condition = filter != null ? " WHERE login LIKE '%" + filter + "%' OR email LIKE '%" + filter + "%'" : "";
			String query = "SELECT * FROM " + TABLE + condition + " ORDER BY id";
			conn = db.Database_Connection.open();
			st = conn.prepareStatement(query);
			rs = st.executeQuery();
			while (rs.next()) {
				User_Dao user = new User_Dao();
				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setRegistered(rs.getTimestamp("registered"));
				user.setLogged(rs.getTimestamp("logged"));
				users.add(user);
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
		
		return users;
	}
	
	public int saveOne(User_Dao user) throws SQLException {

		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;

		try {
			String query = "INSERT INTO " + TABLE + " (login, email, password, registered, logged) VALUES (?, ?, ?, NOW(), NOW())";
			conn = db.Database_Connection.open();
			st = conn.prepareStatement(query);
			st.setString(1, user.getLogin());
			st.setString(2, user.getEmail());
			st.setString(3, user.getEncodedPassword());
			result = st.executeUpdate();
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		catch (NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
		} 
		finally {
			st.close();
			conn.close();
		}
		
		return result;
	}
	
	public int updateOne(int id, User_Dao user) throws SQLException {

		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;

		try {
			String query = "UPDATE " + TABLE + " SET login = ?, email = ?, password = ? WHERE id = ?";
			conn = db.Database_Connection.open();
			st = conn.prepareStatement(query);
			st.setString(1, user.getLogin());
			st.setString(2, user.getEmail());
			st.setString(3, user.getEncodedPassword());
			st.setInt(4, id);
			result = st.executeUpdate();
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		catch (NoSuchAlgorithmException e) {
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
}
