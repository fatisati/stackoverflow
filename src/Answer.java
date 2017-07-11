import java.io.Serializable;
import java.util.ArrayList;

public class Answer implements Serializable{
	String answer;
	ArrayList<String> comments;

	public Answer(String answer) {
		// TODO Auto-generated constructor stub
		this.answer = answer;
		comments = new ArrayList<>();
	}
}
