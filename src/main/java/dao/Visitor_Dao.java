package dao;

import java.util.Date;

public class Visitor_Dao {

	private int id;
	private String visitor_ip;
	private String http_referer;
	private String request_uri;
	private Date visited;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getVisitorIp() {
		return visitor_ip;
	}
	
	public void setVisitorIp(String visitor_ip) {		
		this.visitor_ip = visitor_ip;
	}
	
	public String getHttpReferer() {
		return http_referer;
	}
	
	public void setHttpReferer(String http_referer) {
		this.http_referer = http_referer;
	}
	
	public String getRequestUri() {
		return request_uri;
	}
	
	public void setRequestUri(String request_uri) {
		this.request_uri = request_uri;
	}
	
	public Date getVisited() {
		return visited;
	}
	
	public void setVisited(Date visited) {
		this.visited = visited;
	}
}
