package controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.User_Dao;
import models.Admin_Model;
import util.Message;

@WebServlet("/admin")

public class Admin_Servlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User_Dao user = (User_Dao) request.getSession().getAttribute("sessionUser");
		if (user == null) {
			response.sendRedirect("/login");
			return;
		}
		
		int messagesCount = 0;
		int visitorsCount = 0;

		Admin_Model modelObject = new Admin_Model();
		Message message = new Message(request);
		
		try {
			messagesCount = modelObject.getCount("messages");
			visitorsCount = modelObject.getCount("visitors");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("user", user);
		request.setAttribute("messagesCount", messagesCount);
		request.setAttribute("visitorsCount", visitorsCount);
		request.setAttribute("module", "admin");
		request.setAttribute("message", request.getSession().getAttribute("message"));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/templates/admin.jsp");
		
		dispatcher.forward(request, response);
		
		message.hide();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
}
