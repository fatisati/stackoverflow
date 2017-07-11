import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import org.bson.Document;

public class Answer implements Serializable {

	String username;
	String content;
	int mark;
	Date date;
	ArrayList<Comment> comments;
	boolean isBestAnswer;

	public Answer(String content, String username, Date date) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.content = content;
		this.date = date;
		isBestAnswer = false;
		comments = new ArrayList<>();
		mark = 0;
	}

	public Answer(String content, String username, Date date, ArrayList<Comment> comments, int mark,
			boolean isBestAnswer) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.content = content;
		this.date = date;
		this.isBestAnswer = isBestAnswer;
		this.comments = comments;
		this.mark = mark;
	}

	public Answer(Document doc) {
		// TODO Auto-generated constructor stub
		this.content = doc.getString("content");
		this.username = doc.getString("username");
		this.date = doc.getDate("date");
		this.isBestAnswer = doc.getBoolean("isBestAnswer", false);
		this.mark = doc.getInteger("mark", 0);
		comments = new ArrayList<>();

		ArrayList<Document> comdoc = (ArrayList<Document>) doc.get("comments");
		if (comdoc != null) {
			for (Document com : comdoc) {
				comments.add(new Comment(com));
			}
		}
	}

	Document toDocument() {
		Document doc = new Document("content", content);
		doc.append("username", username).append("mark", mark).append("date", date).append("isBestAnswer", isBestAnswer);

		ArrayList<Document> comdocarr = new ArrayList<>();
		for (Comment com : comments) {

			comdocarr.add(com.toDocument());
		}
		doc.append("comments", comdocarr);
		return doc;
	}
}
