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
import models.User_Model;
import util.Message;

@WebServlet("/login")

public class Login_Servlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User_Dao user = (User_Dao) request.getSession().getAttribute("sessionUser");
		if (user != null) {
			response.sendRedirect("/admin");
			return;
		}
		
		request.setAttribute("module", "login");
		request.setAttribute("page", "login form");
		request.setAttribute("message", request.getSession().getAttribute("message"));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/templates/admin.jsp");
		
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int result = 0;
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		User_Dao user = null;
		User_Model modelObject = new User_Model();
		Message message = new Message(request);
		
		try {
			user = modelObject.getUser(login, password);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		if (user.getId() > 0) {
			try {
				result = modelObject.updateLogged(user);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			message.show("SUCCESS", "Witaj! Zostałeś pomyślnie zalogowany do serwisu.");
			request.getSession().setAttribute("sessionUser", user);
			response.sendRedirect("/admin");
		}
		else {
			message.show("ERROR", "Login lub hasło są nieprawidłowe.");
			response.sendRedirect("/login");
		}
	}
}
