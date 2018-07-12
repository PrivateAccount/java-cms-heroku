package controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Message;

@WebServlet("/logout")

public class Logout_Servlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Message message = new Message(request);
		message.show("SUCCESS", "Do zobaczenia! Zostałeś pomyślnie wylogowany z serwisu.");
		request.getSession().removeAttribute("sessionUser");
		response.sendRedirect("/login");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
}
