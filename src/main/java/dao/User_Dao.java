package dao;

import java.util.Date;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User_Dao {
	
	private int id;
	private String login;
	private String email;
	private String password;
	private Date registered;
	private Date logged;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEncodedPassword() throws NoSuchAlgorithmException {		
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.reset();
		md.update(this.password.getBytes());
		byte[] digest = md.digest();
		BigInteger bigInt = new BigInteger(1, digest);
		return bigInt.toString(16);
	}
	
	public Date getRegistered() {
		return registered;
	}
	
	public void setRegistered(Date registered) {
		this.registered = registered;
	}	
	
	public Date getLogged() {
		return logged;
	}
	
	public void setLogged(Date logged) {
		this.logged = logged;
	}	
}
