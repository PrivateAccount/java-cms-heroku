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
import models.Pages_Model;
import util.Message;

@WebServlet("/pages")

public class Pages_Servlet extends HttpServlet {
	
	private static final String MODULE = "pages";
	
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
			Page_Dao page = new Page_Dao();
			request.setAttribute("site", page);
		}
		else if (action.equals("edit")) {
			Page_Dao page = null;
			Pages_Model modelObject = new Pages_Model();
			try {
				page = modelObject.getOne(Integer.parseInt(id));
			} 
			catch (SQLException e) {
				e.printStackTrace();
			} 
			request.setAttribute("site", page);
		}
		else if (action.equals("delete")) {
			Page_Dao page = null;
			Pages_Model modelObject = new Pages_Model();
			try {
				page = modelObject.getOne(Integer.parseInt(id));
			} 
			catch (SQLException e) {
				e.printStackTrace();
			} 
			request.setAttribute("site", page);
		}
		else {
			ArrayList<Page_Dao> pages = null;
			Pages_Model modelObject = new Pages_Model();
			String filter = (String) request.getSession().getAttribute("pagesFilter");
			try {
				pages = modelObject.getAll(filter);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			} 
			request.setAttribute("data", pages);
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
			Page_Dao page = new Page_Dao();
			page.setIndex(request.getParameter("index"));
			page.setTitle(request.getParameter("title"));
			page.setImage(request.getParameter("image"));
			page.setIntro(request.getParameter("intro"));
			page.setContent(request.getParameter("content"));
			Pages_Model modelObject = new Pages_Model();
			try {
				result = modelObject.saveOne(page);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			if (result == 1) {
				message.show("SUCCESS", "Strona została pomyślnie zapisana.");
				response.sendRedirect("/" + MODULE);
			}
			else {
				message.show("ERROR", "Strona nie została zapisana.");
				response.sendRedirect("/" + MODULE + "?action=" + action);
			}
		}
		else if (action.equals("edit")) {
			int result = 0;
			Page_Dao page = new Page_Dao();
			page.setIndex(request.getParameter("index"));
			page.setTitle(request.getParameter("title"));
			page.setImage(request.getParameter("image"));
			page.setIntro(request.getParameter("intro"));
			page.setContent(request.getParameter("content"));
			Pages_Model modelObject = new Pages_Model();
			try {
				result = modelObject.updateOne(Integer.parseInt(id), page);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			if (result == 1) {
				message.show("SUCCESS", "Strona została pomyślnie zapisana.");
				response.sendRedirect("/" + MODULE);
			}
			else {
				message.show("ERROR", "Strona nie została zapisana.");
				response.sendRedirect("/" + MODULE + "?action=" + action + "&id=" + id);
			}
		}
		else if (action.equals("delete")) {
			int result = 0;
			Pages_Model modelObject = new Pages_Model();
			try {
				result = modelObject.removeOne(Integer.parseInt(id));
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			if (result == 1) {
				message.show("SUCCESS", "Strona została pomyślnie usunięta.");
				response.sendRedirect("/" + MODULE);
			}
			else {
				message.show("ERROR", "Strona nie została usunięta.");
				response.sendRedirect("/" + MODULE + "?action=" + action + "&id=" + id);
			}
		}
		else if (action.equals("search")) {
			if (search != null) {
				String filter = request.getParameter("filter");
				request.getSession().setAttribute("pagesFilter", filter);
			}
			if (clear != null) {
				request.getSession().removeAttribute("pagesFilter");
			}
			response.sendRedirect("/" + MODULE);
		}
		else {
			response.sendRedirect("/" + MODULE);
		}
	}
}
