package util;

import javax.servlet.http.HttpServletRequest;

public class Message {
	
	private HttpServletRequest request;
	private String result;
	private String description;

	public Message(HttpServletRequest request) {
		this.request = request;
	}
	
	public void show(String result, String description) {
		this.result = result;
		this.description = description;
		this.request.getSession().setAttribute("message", result + "@" + description);
	}
	
	public void hide() {
		this.request.getSession().removeAttribute("message");
	}
	
	public String getResult() {
		return this.result;
	}
	
	public String getDescription() {
		return this.description;
	}
}
