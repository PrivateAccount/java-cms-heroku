package controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Page_Dao;
import dao.Message_Dao;
import dao.User_Dao;
import models.Page_Model;
import models.Message_Model;
import util.Message;

@WebServlet("/contact")

public class Contact_Servlet extends HttpServlet {
	
	private static final String MODULE = "contact";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Page_Dao page = null;
		User_Dao user = (User_Dao) request.getSession().getAttribute("sessionUser");
				
		Page_Model modelObject = new Page_Model();
		Message message = new Message(request);
		
		try {
			page = modelObject.getPage("contact");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		
		request.setAttribute("module", "contact");
		request.setAttribute("page", page);
		request.setAttribute("user", user);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/templates/public.jsp");
		
		dispatcher.forward(request, response);
		
		message.hide();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Message message = new Message(request);
		String visitorIp = request.getRemoteAddr();
		String confirm = request.getParameter("confirm");
		
		if (confirm != null) {
			int result = 0;
			Message_Dao contact = new Message_Dao();
			contact.setNick(request.getParameter("nick"));
			contact.setEmail(request.getParameter("email"));
			contact.setMessage(request.getParameter("message"));
			contact.setIp(visitorIp);
			contact.setRequest(true);
			Message_Model modelObject = new Message_Model();
			try {
				result = modelObject.saveOne(contact);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			if (result == 1) {
				message.show("SUCCESS", "Wiadomość została pomyślnie wysłana.");
				response.sendRedirect("/" + MODULE);
			}
			else {
				message.show("ERROR", "Wiadomość nie została wysłana.");
				response.sendRedirect("/" + MODULE);
			}
		}
	}
}
