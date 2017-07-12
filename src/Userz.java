
import java.io.Serializable;
import java.util.ArrayList;

import org.bson.Document;

public class Userz implements Serializable {

	String name;
	String username;
	String email;
	String password;
	int mark;
	ArrayList<String> interest;

	public Userz(String name, String username, String email, String password, ArrayList<String> interest) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.interest = interest;
		mark = 0;

	}
	
	public Userz(Document doc) {
		// TODO Auto-generated constructor stub
		this.name = doc.getString("name");
		this.username = doc.getString("username");
		this.password = doc.getString("password");
		this.email = doc.getString("email");
		this.interest = (ArrayList<String>) doc.get("interest");
		if(interest == null){
			interest = new ArrayList<>();
		}
		this.mark = doc.getInteger("mark", 0);
	}

	Document toDocument() {
		Document doc = new Document("username", username);
		doc.append("name", name).append("email", email).append("password", password).append("interest", interest).append("mark",
				mark);
		return doc;
	}
}
