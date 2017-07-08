

import java.io.Serializable;


public class Userz implements Serializable{
	
	String name;
	String username;
	String email;
	String pass;
	public int[] interest ;
	
	public Userz(String name, String username, String email, String pass,int [] i) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.username = username;
		this.email = email;
		this.pass = pass;
		this.interest = i ;
	
	}
}
