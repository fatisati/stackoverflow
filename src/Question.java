import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable{
	
	String content;
	ArrayList<String> keywords;
	Userz writer;
	ArrayList<String>answers;
	
	public Question(String content, String keyword, Userz writer) {
		// TODO Auto-generated constructor stub
		keywords = new ArrayList<>();
		String str[]=keyword.split(",");
		for(int i=0;i<str.length; i++){
			keywords.add(str[i]);
		}
		this.content = content;
		this.writer = writer;
		answers = new ArrayList<>();
	}
	
	public Question(String content, ArrayList<String>keywords){
		this.content = content;
		this.keywords = keywords;
		answers = new ArrayList<>();
	}
}

