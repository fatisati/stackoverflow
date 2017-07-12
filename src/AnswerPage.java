
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

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
	ArrayList<JButton> anscom = new ArrayList<JButton>();
	ArrayList<JTextArea> showAnsCom = new ArrayList<JTextArea>();

	JScrollPane scroll;
	JTextArea scomment;

	JTextArea showComments;
	JScrollPane showCommentsScroll;

	JButton comment;
	JButton submit;
	boolean isup[], isdown[];

	public AnswerPage(Client client, Userz user, Question q) {

		showComments = new JTextArea();
		showComments.setSize(800, 50);
		showComments.setLocation(10, 70 + (170 * (q.answers.size())));
		showComments.setEditable(false);

		showCommentsScroll = new JScrollPane(showComments);
		showCommentsScroll.setSize(800, 50);
		showCommentsScroll.setLocation(10, 70 + (170 * (q.answers.size())));
		this.add(showCommentsScroll);

		System.out.println("comsize" + q.comments.size());
		for (Comment com : q.comments) {
			showComments.append(com.username + ": " + com.content + " " + com.date + "\n");
		}
		// TODO Auto-generated constructor stub
		isup = new boolean[q.answers.size()];
		isdown = new boolean[q.answers.size()];
		// System.out.println(isup[0]);
		u = user;
		c = client;
		d = Toolkit.getDefaultToolkit().getScreenSize();
		j = new JFrame();
		scroll = new JScrollPane(this);
		scroll.setSize(1150, 700);
		scroll.setLocation(90, 0);
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
		comment.setLocation(820, 170 + (170 * (q.answers.size())));

		this.q = q;
		if (q == null) {
			System.out.println("q");
		}
		if (q.answers == null) {
			System.out.println("answers");
		}
		for (int i = 0; i < q.answers.size(); i++) {
			answers.add(new JLabel(q.answers.get(i).content));
			answers.get(i).setSize(800, 30);
			answers.get(i).setLocation(10, 70 + (70 * i));
			this.add(answers.get(i));

			mark.add(new JLabel(Integer.toString(q.answers.get(i).mark)));
			mark.get(i).setSize(40, 30);
			mark.get(i).setLocation(780, 90 + (70 * i));
			this.add(mark.get(i));

			up.add(new JButton("up"));
			up.get(i).setSize(100, 15);
			up.get(i).setLocation(820, 70 + (70 * i));
			up.get(i).addActionListener(this);
			this.add(up.get(i));

			down.add(new JButton("down"));
			down.get(i).setSize(100, 15);
			down.get(i).setLocation(820, 90 + (70 * i));
			down.get(i).addActionListener(this);
			this.add(down.get(i));

			anscom.add(new JButton("comment"));
			anscom.get(i).setSize(100, 30);
			anscom.get(i).setLocation(930, 70 + (70 * i));
			anscom.get(i).addActionListener(this);
			this.add(anscom.get(i));

			// JTextArea ja = new JTextArea();
			// ja.setSize(800, 25);
			// showComments.setLocation(10, 150 + (70 * i));;
			// showComments.setEditable(false);
			// this.add(ja);
			// showAnsCom.add(ja);
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
		scomment.setLocation(10, 130 + (170 * (q.answers.size())));

		submit = new JButton("submit");
		submit.setSize(100, 30);
		submit.setLocation(820, 130 + (170 * (q.answers.size())));

		scroll = new JScrollPane();

		this.add(questions);
		this.add(comment);
		this.add(scomment);
		this.add(submit);
		// this.add(scroll);

		comment.addActionListener(this);
		submit.addActionListener(this);
		scroll.add(this);
		JPanel panel = new JPanel();
		panel.setSize(1150, 700);
		panel.setLocation(0, 0);
		panel.setLayout(null);
		panel.setVisible(true);
		panel.add(scroll);

		j.add(this);
		scroll.setVisible(true);
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
			boolean flag = false;
			for (Answer ans : q.answers) {
				if (ans.content.equals(scomment.getText())) {
					flag = true;
				}
			}
			if (!flag) {

				if (q.answers == null) {
					q.answers = new ArrayList<>();
				}
				q.answers.add(new Answer(scomment.getText(), u.username, new Date()));
				c.sendMessage(new Message(Message.ANSWER, q));
			}
		}

		for (int k = 0; k < up.size(); k++) {
			if (arg0.getSource() == up.get(k)) {

				if (isdown[k]) {
					q.answers.get(k).mark += 2;
				} else if (!isup[k]) {
					q.answers.get(k).mark++;
				}
				isup[k] = true;
				isdown[k] = false;

			}
			// System.out.println("got it");
			c.sendMessage(new Message(Message.ANSWER, q));
			mark.get(k).setText(Integer.toString(q.answers.get(k).mark));

		}

		for (int k = 0; k < down.size(); k++) {
			if (arg0.getSource() == down.get(k)) {
				if (isup[k]) {
					q.answers.get(k).mark -= 2;
				} else if (!isdown[k]) {
					q.answers.get(k).mark--;
				}
				isdown[k] = true;
				isup[k] = false;

			}
			c.sendMessage(new Message(Message.ANSWER, q));
			mark.get(k).setText(Integer.toString(q.answers.get(k).mark));

		}

		for (int k = 0; k < anscom.size(); k++) {
			
			if (arg0.getSource() == anscom.get(k)) {
				System.out.println("k:"+k);
				if(scomment.getText()!=null){
					q.answers.get(k).comments.add(new Comment(scomment.getText(), u.username, new Date()));
					c.sendMessage(new Message(Message.ANSWER, q));
					System.out.println("message sent");
					System.out.println("size"+q.answers.get(k).comments.size());
				}
				for (Comment com : q.answers.get(k).comments) {
					System.out.println(com.username + ": " + com.content + " " + com.date );

				}
			}
		}

		if (arg0.getSource() == comment)

		{
			Comment com = new Comment(scomment.getText(), u.username, new Date());
			showComments.append(com.username + ": " + com.content + " " + com.date + "\n");
			q.comments.add(com);
			c.sendMessage(new Message(Message.QCOMMENT, q));
		}

	}

}
