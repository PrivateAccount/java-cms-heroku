package dao;

import java.util.Date;

public class Message_Dao {
	
	private int id;
	private String nick;
	private String email;
	private String message;
	private String ip;
	private boolean request;
	private Date sent;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNick() {
		return nick;
	}
	
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public boolean getRequest() {
		return request;
	}
	
	public void setRequest(boolean request) {
		this.request = request;
	}
	
	public Date getSent() {
		return sent;
	}
	
	public void setSent(Date sent) {
		this.sent = sent;
	}	
}
