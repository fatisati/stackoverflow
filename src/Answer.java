import java.io.Serializable;
import java.util.ArrayList;

public class Answer implements Serializable{
	
	String username;
	String content;
	int mark;
	ArrayList<String> comments;

	public Answer(String content, String username) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.content = content;
		comments = new ArrayList<>();
		mark = 0;
	}
}
