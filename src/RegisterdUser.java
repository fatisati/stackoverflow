
public class RegisterdUser extends Client{
	
	String name;
	String username;
	String email;
	String password;
	
	public RegisterdUser(String name, String username, String email, String password,
			String server, int port) {
		// TODO Auto-generated constructor stub
		super(server, port, username);
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
	}
}
