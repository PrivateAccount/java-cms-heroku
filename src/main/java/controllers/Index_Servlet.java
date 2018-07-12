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
import dao.User_Dao;
import models.Page_Model;
import util.Message;

@WebServlet("/index")

public class Index_Servlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Page_Dao page = null;
		ArrayList<Page_Dao> articles = null;
		User_Dao user = (User_Dao) request.getSession().getAttribute("sessionUser");
		
		Page_Model modelObject = new Page_Model();
		Message message = new Message(request);
		
		try {
			page = modelObject.getPage("index");
			articles = modelObject.getArticles();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		
		request.setAttribute("module", "index");
		request.setAttribute("page", page);
		request.setAttribute("articles", articles);
		request.setAttribute("user", user);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/templates/public.jsp");
		
		dispatcher.forward(request, response);
		
		message.hide();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
}
