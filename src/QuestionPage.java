
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.print.attribute.standard.Severity;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class QuestionPage extends JPanel implements ActionListener{
	    Dimension d; 
        JFrame j;
        Client c;
        Userz u;
    	JButton search ;
    	JLabel question;
    	String s;
    //	ArrayList<String> questions;
    	ArrayList<JLabel> questions = new ArrayList<JLabel>();
    	ArrayList<JButton> answers = new ArrayList<JButton>();
    	ArrayList<JButton> showanswers = new ArrayList<JButton>();
    	ArrayList<JButton> comment = new ArrayList<JButton>();
    	ArrayList<JButton> up = new ArrayList<JButton>(); 
    	ArrayList<JButton> down = new ArrayList<JButton>();
    	
    	ArrayList<Question>qs;
	public QuestionPage(Client client,Userz user , ArrayList<Question> ans){
		this.qs = ans;
		u = user ;
		c = client;
		d = Toolkit.getDefaultToolkit().getScreenSize();
		j = new JFrame();
		setSize(1150,700);
		j.setSize(1150,700);
		setLocation(0,0);
		j.setLocation(90,0);
		setLayout(null);
		j.setLayout(null);
		setFocusable(true);
		
		for(int i = 0 ; i <ans.size() ; i++){
			questions.add(new JLabel(ans.get(i).content));
			questions.get(i).setSize(800, 30);
			questions.get(i).setLocation(10, 80+(110*i));
			
			answers.add(new JButton("answers"));
			answers.get(i).setSize(100, 30);
			answers.get(i).setLocation(820, 80+(110*i));
			
			showanswers.add(new JButton("showanswers"));
			showanswers.get(i).setSize(100, 30);
			showanswers.get(i).setLocation(700, 80+(110*i));
			
			comment.add(new JButton("comments"));
			comment.get(i).setSize(100, 30);
			comment.get(i).setLocation(820, 120+(110*i));
			
			up.add(new JButton("up"));
			up.get(i).setSize(100, 15);
			up.get(i).setLocation(30, 120+(110*i));
			
			down.add(new JButton("down"));
			down.get(i).setSize(100, 15);
			down.get(i).setLocation(30, 140+(110*i));
			
			this.add(questions.get(i));
			this.add(answers.get(i));
			this.add(showanswers.get(i));
			this.add(up.get(i));
			this.add(down.get(i));
			this.add(comment.get(i));
			answers.get(i).addActionListener(this);
			showanswers.get(i).addActionListener(this);
			up.get(i).addActionListener(this);
			down.get(i).addActionListener(this);
			comment.get(i).addActionListener(this);
		}
//		for(int i = 0 ; i <ans.size() ; i++){
//			System.out.println(answers);
//		}
//	 	search = new JButton("search");
//	 	search.setSize(100, 30);
//	 	search.setLocation(570, 30);
		
//	 	question = new JLabel("");
//	 	question.setSize(550, 30);
//	 	question.setLocation(10, 30);
//	 	
//	    this.add(search);
//	    this.add(question);
//	    search.addActionListener(this);
	    j.getContentPane().add(this);
		j.setVisible(true);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		int i;
		boolean flag = false;
		for(i = 0; i<answers.size(); i++){
			if(arg0.getSource() == answers.get(i)){
				flag = true;
				break;
			}
		}
		
		if(flag){
			new AnswerPage(c, u, qs.get(i));
		}
		
	}

}
