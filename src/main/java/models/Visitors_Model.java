package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.Database_Connection;

import dao.Visitor_Dao;

public class Visitors_Model {

	private static final String TABLE = "visitors";
	
	public Visitor_Dao getOne(int id) throws SQLException {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		Visitor_Dao visitor = new Visitor_Dao();

		try {
			String query = "SELECT * FROM " + TABLE + " WHERE id = ?";
			conn = db.Database_Connection.open();
			st = conn.prepareStatement(query);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				visitor.setId(rs.getInt("id"));
				visitor.setVisitorIp(rs.getString("visitor_ip"));
				visitor.setHttpReferer(rs.getString("http_referer"));
				visitor.setRequestUri(rs.getString("request_uri"));
				visitor.setVisited(rs.getTimestamp("visited"));
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
		
		return visitor;
	}
	
	public ArrayList<Visitor_Dao> getAll(String filter) throws SQLException {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		ArrayList<Visitor_Dao> visitors = new ArrayList<Visitor_Dao>();

		try {
			String condition = filter != null ? " WHERE visitor_ip LIKE '%" + filter + "%' OR http_referer LIKE '%" + filter + "%' OR request_uri LIKE '%" + filter + "%'" : "";
			String query = "SELECT * FROM " + TABLE + condition + " ORDER BY id DESC LIMIT 1000";
			conn = db.Database_Connection.open();
			st = conn.prepareStatement(query);
			rs = st.executeQuery();
			while (rs.next()) {
				Visitor_Dao visitor = new Visitor_Dao();
				visitor.setId(rs.getInt("id"));
				visitor.setVisitorIp(rs.getString("visitor_ip"));
				visitor.setHttpReferer(rs.getString("http_referer"));
				visitor.setRequestUri(rs.getString("request_uri"));
				visitor.setVisited(rs.getTimestamp("visited"));
				visitors.add(visitor);
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
		
		return visitors;
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
