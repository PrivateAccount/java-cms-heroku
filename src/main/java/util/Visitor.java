package util;

import java.sql.SQLException;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

import dao.Visitor_Dao;
import models.Visitor_Model;

@WebListener

public class Visitor implements ServletRequestListener {
	
    public void requestInitialized(ServletRequestEvent servletRequestEvent)  { 

    	HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
    	
    	String visitorIp = request.getRemoteAddr();
    	String requestUri = request.getRequestURI();
    	String httpReferer = request.getHeader("Referer");

		Visitor_Dao visitor = new Visitor_Dao();
		
		visitor.setHttpReferer(httpReferer);
		visitor.setVisitorIp(visitorIp);
		visitor.setRequestUri(requestUri);
		
		Visitor_Model modelObject = new Visitor_Model();
		
		try {
			if (requestUri.indexOf(".") == -1) {
				int result = modelObject.save(visitor);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
    public void requestDestroyed(ServletRequestEvent servletRequestEvent)  { 

    }
}
