import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import org.bson.Document;

public class Question implements Serializable {

	String content;
	ArrayList<String> keywords;
	String username;
	Date date;
	int mark;
	// Userz writer;
	ArrayList<Answer> answers;
	ArrayList<Comment> comments;

	public Question(String content, String keyword, Userz writer, Date date) {
		// TODO Auto-generated constructor stub
		keywords = new ArrayList<>();
		String str[] = keyword.split(",");
		for (int i = 0; i < str.length; i++) {
			keywords.add(str[i]);
		}
		this.content = content;
		this.date = date;
		this.username = writer.username;
		mark = 0;
		answers = new ArrayList<>();
		comments = new ArrayList<>();
	}

	public Question(String content, ArrayList<String> keywords, String username, Date date, int mark,
			ArrayList<Answer> answers, ArrayList<Comment> comments) {
		this.content = content;
		this.keywords = keywords;
		this.username = username;
		this.date = date;
		this.mark = mark;
		this.answers = answers;
		this.comments = comments;
	}

	public Question(Document doc) {
		// TODO Auto-generated constructor stub
		this.content = doc.getString("content");
		this.username = doc.getString("username");
		this.date = doc.getDate("date");

		this.mark = doc.getInteger("mark", 0);
		comments = new ArrayList<>();
		answers = new ArrayList<>();

		ArrayList<Document> ansdoc = (ArrayList<Document>) doc.get("answers");
		if (ansdoc != null) {
			for (Document ans : ansdoc) {
				answers.add(new Answer(ans));
			}
		}

		ArrayList<Document> comdoc = (ArrayList<Document>) doc.get("comments");
		if (comdoc != null) {
			for (Document com : comdoc) {
				comments.add(new Comment(com));
			}
		}
	}

	Document toDocument() {

		Document doc = new Document("content", content);
		doc.append("keywords", keywords).append("username", username).append("date", date).append("mark", mark);

		ArrayList<Document> ansdoc = new ArrayList<>();
		for (Answer ans : answers) {
			ansdoc.add(ans.toDocument());
		}

		ArrayList<Document> comdoc = new ArrayList<>();
		for (Comment com : comments) {
			comdoc.add(com.toDocument());
		}

		doc.append("answers", ansdoc).append("comments", comdoc);
		return doc;

	}
}
