
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AnswerPage extends JPanel implements ActionListener {
	Dimension d;
	JFrame j;
	Client c;
	Userz u;
	Question q;
	JLabel questions;
	ArrayList<JLabel> answers = new ArrayList<JLabel>();
	ArrayList<JLabel> mark = new ArrayList<JLabel>();
	ArrayList<JButton> up = new ArrayList<JButton>();
	ArrayList<JButton> down = new ArrayList<JButton>();
	JScrollPane scroll;
	JTextArea scomment;
	JButton comment;
	JButton submit;

	public AnswerPage(Client client, Userz user, Question q) {
		// TODO Auto-generated constructor stub
		u = user;
		c = client;
		d = Toolkit.getDefaultToolkit().getScreenSize();
		j = new JFrame();
		setSize(1150, 700);
		j.setSize(1150, 700);
		setLocation(0, 0);
		j.setLocation(90, 0);
		setLayout(null);
		j.setLayout(null);
		setFocusable(true);

		questions = new JLabel(q.content);
		questions.setSize(500, 30);
		questions.setLocation(10, 20);

		comment = new JButton("comment");
		comment.setSize(100, 30);
		comment.setLocation(550, 20);

		this.q = q;
		if (q == null) {
			System.out.println("q");
		}
		if (q.answers == null) {
			System.out.println("answers");
		}
		for (int i = 0; i < q.answers.size(); i++) {
			answers.add(new JLabel(q.answers.get(i)));
			answers.get(i).setSize(800, 30);
			answers.get(i).setLocation(10, 70 + (70 * i));
			this.add(answers.get(i));

			// mark.add(new JLabel(q.answers.get(i).mark));
			// mark.get(i).setSize(40, 30);
			// mark.get(i).setLocation(780, 90+(70*i));
			// this.add(answers.get(i));

			up.add(new JButton("up"));
			up.get(i).setSize(100, 15);
			up.get(i).setLocation(820, 70 + (70 * i));
			this.add(up.get(i));

			down.add(new JButton("down"));
			down.get(i).setSize(100, 15);
			down.get(i).setLocation(820, 90 + (70 * i));
			this.add(down.get(i));
			// comment.add(new JTextArea());
			// comments.get(i).setSize(800, 100);
			// comments.get(i).setLocation(10, 110+(160*i));
			// scrolls.add( new JScrollPane(comments.get(i)));
			// this.add(comments.get(i));
			// this.add(scrolls.get(i));
			// for(int j= 0 ; j<q.answers.get(i).comments.size();j++){
			// comments.get(j).append(q.answers.get(i).comments.get(j));
			// }
		}

		scomment = new JTextArea();
		scomment.setSize(800, 100);
		scomment.setLocation(10, 70 + (170 * (q.answers.size())));

		submit = new JButton("submit");
		submit.setSize(100, 30);
		submit.setLocation(820, 100 + (170 * (q.answers.size())));

		scroll = new JScrollPane();

		this.add(questions);
		this.add(comment);
		this.add(scomment);
		this.add(submit);
		// this.add(scroll);

		comment.addActionListener(this);
		submit.addActionListener(this);
		scroll.add(this);

		j.add(this);
		setVisible(true);
		// scroll.setVisible(true);

		j.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == submit) {
			if (q == null) {
				System.out.println("hey this q is null");
			}
			// System.out.println("hi");
			q.answers.add(scomment.getText());
			c.sendMessage(new Message(Message.ANSWER, q));
			// System.out.println("what");
		}

	}

}