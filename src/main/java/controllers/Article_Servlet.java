package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.lang.NumberFormatException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Page_Dao;
import dao.User_Dao;
import models.Page_Model;
import util.Message;

@WebServlet("/article/*")

public class Article_Servlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Page_Dao article = null;
		User_Dao user = (User_Dao) request.getSession().getAttribute("sessionUser");
				
		Page_Model modelObject = new Page_Model();
		Message message = new Message(request);
		
		String path = request.getPathInfo();
		String id = path != null ? path.substring(1) : "0";
		
		try {
			article = modelObject.getArticle(Integer.parseInt(id));
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("module", "article");
		request.setAttribute("article", article);
		request.setAttribute("user", user);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/templates/public.jsp");
		
		dispatcher.forward(request, response);
		
		message.hide();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
}
