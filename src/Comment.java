import java.io.Serializable;
import java.util.Date;

import org.bson.Document;

public class Comment implements Serializable {

	String content;
	String username;
	Date date;

	public Comment(String content, String username, Date date) {
		// TODO Auto-generated constructor stub
		this.content = content;
		this.username = username;
		this.date = date;
	}

	public Comment(Document doc) {
		// TODO Auto-generated constructor stub
		this.content = doc.getString("content");
		this.username = doc.getString("username");
		this.date = doc.getDate("date");
	}

	Document toDocument() {

		Document doc = new Document("content", content).append("username", username).append("date", date);
		return doc;
	}
}
