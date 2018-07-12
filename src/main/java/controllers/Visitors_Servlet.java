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

import dao.Visitor_Dao;
import dao.User_Dao;
import models.Visitors_Model;
import util.Message;

@WebServlet("/visitors")

public class Visitors_Servlet extends HttpServlet {
	
	private static final String MODULE = "visitors";
	
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
			Visitor_Dao visitor = null;
			Visitors_Model modelObject = new Visitors_Model();
			try {
				visitor = modelObject.getOne(Integer.parseInt(id));
			} 
			catch (SQLException e) {
				e.printStackTrace();
			} 
			request.setAttribute("visitor", visitor);
		}
		else if (action.equals("delete")) {
			Visitor_Dao visitor = null;
			Visitors_Model modelObject = new Visitors_Model();
			try {
				visitor = modelObject.getOne(Integer.parseInt(id));
			} 
			catch (SQLException e) {
				e.printStackTrace();
			} 
			request.setAttribute("visitor", visitor);
		}
		else {
			ArrayList<Visitor_Dao> visitors = null;
			Visitors_Model modelObject = new Visitors_Model();
			String filter = (String) request.getSession().getAttribute("visitorsFilter");
			try {
				visitors = modelObject.getAll(filter);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			} 
			request.setAttribute("data", visitors);
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
		
		if (action.equals("delete")) {
			int result = 0;
			Visitors_Model modelObject = new Visitors_Model();
			try {
				result = modelObject.removeOne(Integer.parseInt(id));
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			if (result == 1) {
				message.show("SUCCESS", "Wizyta została pomyślnie usunięta.");
				response.sendRedirect("/" + MODULE);
			}
			else {
				message.show("ERROR", "Wizyta nie została usunięta.");
				response.sendRedirect("/" + MODULE + "?action=" + action + "&id=" + id);
			}
		}
		else if (action.equals("search")) {
			if (search != null) {
				String filter = request.getParameter("filter");
				request.getSession().setAttribute("visitorsFilter", filter);
			}
			if (clear != null) {
				request.getSession().removeAttribute("visitorsFilter");
			}
			response.sendRedirect("/" + MODULE);
		}
		else {
			response.sendRedirect("/" + MODULE);
		}
	}
}
