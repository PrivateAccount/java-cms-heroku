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

import dao.Message_Dao;
import dao.User_Dao;
import models.Messages_Model;
import util.Message;

@WebServlet("/messages")

public class Messages_Servlet extends HttpServlet {
	
	private static final String MODULE = "messages";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User_Dao user = (User_Dao) request.getSession().getAttribute("sessionUser");
		if (user == null) {
			response.sendRedirect("/login");
			return;
		}
		
		String id = request.getParameter("id");
		String action = request.getParameter("action");
		
		id = id == null ? "0" : id;
		action = action == null ? "list" : action;
		
		Message message = new Message(request);
		
		if (action.equals("view")) {
			Message_Dao contact = null;
			Messages_Model modelObject = new Messages_Model();
			try {
				contact = modelObject.getOne(Integer.parseInt(id));
			} 
			catch (SQLException e) {
				e.printStackTrace();
			} 
			request.setAttribute("message", contact);
		}
		else if (action.equals("delete")) {
			Message_Dao contact = null;
			Messages_Model modelObject = new Messages_Model();
			try {
				contact = modelObject.getOne(Integer.parseInt(id));
			} 
			catch (SQLException e) {
				e.printStackTrace();
			} 
			request.setAttribute("message", contact);
		}
		else {
			ArrayList<Message_Dao> contacts = null;
			Messages_Model modelObject = new Messages_Model();
			String filter = (String) request.getSession().getAttribute("contactsFilter");
			try {
				contacts = modelObject.getAll(filter);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			} 
			request.setAttribute("data", contacts);
			request.setAttribute("filter", filter);
		}
		
		request.setAttribute("module", MODULE);
		request.setAttribute("action", action);
		request.setAttribute("user", user);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/templates/admin.jsp");
		
		dispatcher.forward(request, response);
		
		message.hide();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User_Dao user = (User_Dao) request.getSession().getAttribute("sessionUser");
		if (user == null) {
			response.sendRedirect("/login");
			return;
		}
		
		String id = request.getParameter("id");
		String action = request.getParameter("action");
		String confirm = request.getParameter("confirm");
		String cancel = request.getParameter("cancel");
		String search = request.getParameter("search");
		String clear = request.getParameter("clear");
		
		id = id == null ? "0" : id;
		action = action == null ? "list" : action;
		
		if (cancel != null) {
			response.sendRedirect("/" + MODULE);
			return;
		}

		Message message = new Message(request);
		
		if (action.equals("view")) {
			int result = 0;
			Message_Dao contact = new Message_Dao();
			contact.setRequest(false);
			Messages_Model modelObject = new Messages_Model();
			try {
				result = modelObject.updateOne(Integer.parseInt(id), contact);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			if (result == 1) {
				message.show("SUCCESS", "Wiadomość została pomyślnie zatwierdzona.");
				response.sendRedirect("/" + MODULE);
			}
			else {
				message.show("ERROR", "Wiadomość nie została zatwierdzona.");
				response.sendRedirect("/" + MODULE + "?action=" + action + "&id=" + id);
			}
		}
		else if (action.equals("delete")) {
			int result = 0;
			Messages_Model modelObject = new Messages_Model();
			try {
				result = modelObject.removeOne(Integer.parseInt(id));
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			if (result == 1) {
				message.show("SUCCESS", "Wiadomość została pomyślnie usunięta.");
				response.sendRedirect("/" + MODULE);
			}
			else {
				message.show("ERROR", "Wiadomość nie została usunięta.");
				response.sendRedirect("/" + MODULE + "?action=" + action + "&id=" + id);
			}
		}
		else if (action.equals("remove")) {
			int result = 0;
			Messages_Model modelObject = new Messages_Model();
			try {
				result = modelObject.removeAll();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			if (result > 0) {
				message.show("SUCCESS", "Wiadomości zostały pomyślnie usunięte.");
				response.sendRedirect("/" + MODULE);
			}
			else {
				message.show("ERROR", "Wiadomości nie zostały usunięte.");
				response.sendRedirect("/" + MODULE + "?action=" + action);
			}
		}
		else if (action.equals("search")) {
			if (search != null) {
				String filter = request.getParameter("filter");
				request.getSession().setAttribute("contactsFilter", filter);
			}
			if (clear != null) {
				request.getSession().removeAttribute("contactsFilter");
			}
			response.sendRedirect("/" + MODULE);
		}
		else {
			response.sendRedirect("/" + MODULE);
		}
	}
}
