

import java.io.Serializable;


public class Userz implements Serializable{
	
	String user;
	String username;
	String email;
	String pass;
	public int[] interest ;
	
	public Userz(String user, String username, String email, String pass,int [] i) {
		// TODO Auto-generated constructor stub
		this.user = user;
		this.username = username;
		this.email = email;
		this.pass = pass;
		this.interest = i ;
	
	}
}
