package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.Database_Connection;

import dao.Page_Dao;

public class Pages_Model {

	private static final String TABLE = "pages";
	
	public Page_Dao getOne(int id) throws SQLException {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		Page_Dao page = new Page_Dao();

		try {
			String query = "SELECT * FROM " + TABLE + " WHERE id = ?";
			conn = db.Database_Connection.open();
			st = conn.prepareStatement(query);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				page.setId(rs.getInt("id"));
				page.setIndex(rs.getString("index"));
				page.setTitle(rs.getString("title"));
				page.setImage(rs.getString("image"));
				page.setIntro(rs.getString("intro"));
				page.setContent(rs.getString("content"));
				page.setModified(rs.getTimestamp("modified"));
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
		
		return page;
	}
	
	public ArrayList<Page_Dao> getAll(String filter) throws SQLException {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		ArrayList<Page_Dao> pages = new ArrayList<Page_Dao>();

		try {
			String condition = filter != null ? " WHERE index LIKE '%" + filter + "%' OR title LIKE '%" + filter + "%' OR intro LIKE '%" + filter + "%' OR content LIKE '%" + filter + "%'" : "";
			String query = "SELECT * FROM " + TABLE + condition + " ORDER BY id";
			conn = db.Database_Connection.open();
			st = conn.prepareStatement(query);
			rs = st.executeQuery();
			while (rs.next()) {
				Page_Dao page = new Page_Dao();
				page.setId(rs.getInt("id"));
				page.setIndex(rs.getString("index"));
				page.setTitle(rs.getString("title"));
				page.setImage(rs.getString("image"));
				page.setIntro(rs.getString("intro"));
				String content = rs.getString("content");
				page.setContent(content.replaceAll("\\<.*?\\>", ""));
				page.setModified(rs.getTimestamp("modified"));
				pages.add(page);
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
		
		return pages;
	}
	
	public int saveOne(Page_Dao page) throws SQLException {

		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;

		try {
			String query = "INSERT INTO " + TABLE + " (index, title, image, intro, content, modified) VALUES (?, ?, ?, ?, ?, NOW())";
			conn = db.Database_Connection.open();
			st = conn.prepareStatement(query);
			st.setString(1, page.getIndex());
			st.setString(2, page.getTitle());
			st.setString(3, page.getImage());
			st.setString(4, page.getIntro());
			st.setString(5, page.getContent());
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
	
	public int updateOne(int id, Page_Dao page) throws SQLException {

		int result = 0;
		Connection conn = null;
		PreparedStatement st = null;

		try {
			String query = "UPDATE " + TABLE + " SET index = ?, title = ?, image = ?, intro = ?, content = ?, modified = NOW() WHERE id = ?";
			conn = db.Database_Connection.open();
			st = conn.prepareStatement(query);
			st.setString(1, page.getIndex());
			st.setString(2, page.getTitle());
			st.setString(3, page.getImage());
			st.setString(4, page.getIntro());
			st.setString(5, page.getContent());
			st.setInt(6, id);
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
}
