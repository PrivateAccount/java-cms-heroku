package controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import dao.Page_Dao;
import models.Page_Model;

@WebServlet("/sitemap.xml")

public class Sitemap_Generator extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Page_Dao index = null;
		Page_Dao contact = null;
		Page_Dao manual = null;
		ArrayList<Page_Dao> articles = null;
				
		Page_Model modelObject = new Page_Model();
		
		try {
			index = modelObject.getPage("index");
			contact = modelObject.getPage("contact");
			manual = modelObject.getPage("manual");
			articles = modelObject.getArticles();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		
		request.setAttribute("index", index);
		request.setAttribute("contact", contact);
		request.setAttribute("manual", manual);
		request.setAttribute("articles", articles);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/sitemap.jsp");
		
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
}
