package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.Database_Connection;

import dao.Page_Dao;

public class Page_Model {

	private static final String TABLE = "pages";
	
	public Page_Dao getPage(String index) throws SQLException {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		Page_Dao page = new Page_Dao();

		try {
			String query = "SELECT * FROM " + TABLE + " WHERE index = ?";
			conn = db.Database_Connection.open();
			st = conn.prepareStatement(query);
			st.setString(1, index);
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
	
	public Page_Dao getArticle(int index) throws SQLException {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		Page_Dao article = new Page_Dao();

		try {
			String query = "SELECT * FROM " + TABLE + " WHERE id = ?";
			conn = db.Database_Connection.open();
			st = conn.prepareStatement(query);
			st.setInt(1, index);
			rs = st.executeQuery();
			if (rs.next()) {				
				article.setId(rs.getInt("id"));
				article.setIndex(rs.getString("index"));
				article.setTitle(rs.getString("title"));
				article.setImage(rs.getString("image"));
				article.setIntro(rs.getString("intro"));
				article.setContent(rs.getString("content"));
				article.setModified(rs.getTimestamp("modified"));
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
		
		return article;
	}
	
	public ArrayList<Page_Dao> getArticles() throws SQLException {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		ArrayList<Page_Dao> articles = new ArrayList<Page_Dao>();

		try {
			String query = "SELECT * FROM " + TABLE + " WHERE index LIKE 'article-%' ORDER BY id DESC";
			conn = db.Database_Connection.open();
			st = conn.prepareStatement(query);
			rs = st.executeQuery();
			while (rs.next()) {
				Page_Dao article = new Page_Dao();
				article.setId(rs.getInt("id"));
				article.setIndex(rs.getString("index"));
				article.setTitle(rs.getString("title"));
				article.setImage(rs.getString("image"));
				article.setIntro(rs.getString("intro"));
				article.setContent(rs.getString("content"));
				article.setModified(rs.getTimestamp("modified"));
				articles.add(article);
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
		
		return articles;
	}
	
	public ArrayList<Page_Dao> getManuals() throws SQLException {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		ArrayList<Page_Dao> manuals = new ArrayList<Page_Dao>();

		try {
			String query = "SELECT * FROM " + TABLE + " WHERE index LIKE 'manual-%' ORDER BY id";
			conn = db.Database_Connection.open();
			st = conn.prepareStatement(query);
			rs = st.executeQuery();
			while (rs.next()) {
				Page_Dao manual = new Page_Dao();
				manual.setId(rs.getInt("id"));
				manual.setIndex(rs.getString("index"));
				manual.setTitle(rs.getString("title"));
				manual.setImage(rs.getString("image"));
				manual.setIntro(rs.getString("intro"));
				manual.setContent(rs.getString("content"));
				manual.setModified(rs.getTimestamp("modified"));
				manuals.add(manual);
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
		
		return manuals;
	}
}
