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

import dao.User_Dao;
import models.Users_Model;
import util.Message;

@WebServlet("/users")

public class Users_Servlet extends HttpServlet {
	
	private static final String MODULE = "users";
	
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
		
		if (action.equals("new")) {
			User_Dao account = new User_Dao();
			account.setLogin("");
			account.setEmail("");
			account.setPassword("");
			request.setAttribute("account", account);
		}
		else if (action.equals("edit")) {
			User_Dao account = null;
			Users_Model modelObject = new Users_Model();
			try {
				account = modelObject.getOne(Integer.parseInt(id));
			} 
			catch (SQLException e) {
				e.printStackTrace();
			} 
			request.setAttribute("account", account);
		}
		else if (action.equals("password")) {
			User_Dao account = null;
			Users_Model modelObject = new Users_Model();
			try {
				account = modelObject.getOne(Integer.parseInt(id));
			} 
			catch (SQLException e) {
				e.printStackTrace();
			} 
			request.setAttribute("account", account);
		}
		else if (action.equals("delete")) {
			User_Dao account = null;
			Users_Model modelObject = new Users_Model();
			try {
				account = modelObject.getOne(Integer.parseInt(id));
			} 
			catch (SQLException e) {
				e.printStackTrace();
			} 
			request.setAttribute("account", account);
		}
		else {
			ArrayList<User_Dao> accounts = null;
			Users_Model modelObject = new Users_Model();
			String filter = (String) request.getSession().getAttribute("usersFilter");
			try {
				accounts = modelObject.getAll(filter);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			} 
			request.setAttribute("data", accounts);
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
		
		if (action.equals("new")) {
			int result = 0;
			User_Dao account = new User_Dao();
			account.setLogin(request.getParameter("login"));
			account.setEmail(request.getParameter("email"));
			account.setPassword(request.getParameter("password"));
			Users_Model modelObject = new Users_Model();
			try {
				result = modelObject.saveOne(account);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			if (result == 1) {
				message.show("SUCCESS", "Konto użytkownika zostało pomyślnie zapisane.");
				response.sendRedirect("/" + MODULE);
			}
			else {
				message.show("ERROR", "Konto użytkownika nie zostało zapisane.");
				response.sendRedirect("/" + MODULE + "?action=" + action);
			}
		}
		else if (action.equals("edit")) {
			int result = 0;
			User_Dao account = new User_Dao();
			account.setLogin(request.getParameter("login"));
			account.setEmail(request.getParameter("email"));
			account.setPassword(request.getParameter("password"));
			Users_Model modelObject = new Users_Model();
			try {
				result = modelObject.updateOne(Integer.parseInt(id), account);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			if (result == 1) {
				message.show("SUCCESS", "Konto użytkownika zostało pomyślnie zapisane.");
				response.sendRedirect("/" + MODULE);
			}
			else {
				message.show("ERROR", "Konto użytkownika nie zostało zapisane.");
				response.sendRedirect("/" + MODULE + "?action=" + action + "&id=" + id);
			}
		}
		else if (action.equals("password")) {
			int result = 0;
			User_Dao account = new User_Dao();
			account.setLogin(request.getParameter("login"));
			account.setEmail(request.getParameter("email"));
			account.setPassword(request.getParameter("password"));
			Users_Model modelObject = new Users_Model();
			try {
				result = modelObject.updateOne(Integer.parseInt(id), account);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			if (result == 1) {
				message.show("SUCCESS", "Hasło użytkownika zostało pomyślnie zapisane.");
				response.sendRedirect("/" + MODULE);
			}
			else {
				message.show("ERROR", "Hasło użytkownika nie zostało zapisane.");
				response.sendRedirect("/" + MODULE + "?action=" + action + "&id=" + id);
			}
		}
		else if (action.equals("delete")) {
			int result = 0;
			Users_Model modelObject = new Users_Model();
			try {
				result = modelObject.removeOne(Integer.parseInt(id));
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			if (result == 1) {
				message.show("SUCCESS", "Konto użytkownika zostało pomyślnie usunięte.");
				response.sendRedirect("/" + MODULE);
			}
			else {
				message.show("ERROR", "Konto użytkownika nie zostało usunięte.");
				response.sendRedirect("/" + MODULE + "?action=" + action + "&id=" + id);
			}
		}
		else if (action.equals("search")) {
			if (search != null) {
				String filter = request.getParameter("filter");
				request.getSession().setAttribute("usersFilter", filter);
			}
			if (clear != null) {
				request.getSession().removeAttribute("usersFilter");
			}
			response.sendRedirect("/" + MODULE);
		}
		else {
			response.sendRedirect("/" + MODULE);
		}
	}
}
