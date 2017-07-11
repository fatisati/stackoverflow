
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Frame extends JFrame implements ActionListener {
	Dimension d;
	JButton search, signin, signup, allquestions;
	JTextField question, username, password;
	JLabel luser, lpass, q;
	JComboBox jb;
	String[] s;
	String[] sg;
	String[] sl;
	Panel p;
	Signin si;
	Signup sp;
	MainPage mp;
	Client c;

	// QuestionPage qp;
	public Frame() {
		c = new Client("127.0.0.1", 8000, "signup");
		c.start();
		c.sendMessage(new Message(Message.KEYWORDS, null));
		while (c.keywords == null) {
		}
		System.out.println("here");
		this.p = p;
		d = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(1150, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(90, 0);
		setResizable(false);

		search = new JButton("search");
		search.setSize(100, 50);
		search.setLocation(570, 50);

		allquestions = new JButton("allquestions");
		allquestions.setSize(150, 50);
		allquestions.setLocation(570, 120);

		signin = new JButton("signin");
		signin.setSize(80, 30);
		signin.setLocation(860, 130);

		signup = new JButton("signup");
		signup.setSize(80, 30);
		signup.setLocation(980, 130);

		question = new JTextField();
		question.setSize(550, 50);
		question.setLocation(10, 50);

		username = new JTextField();
		username.setSize(300, 30);
		username.setLocation(790, 30);

		password = new JTextField();
		password.setSize(300, 30);
		password.setLocation(790, 80);

		luser = new JLabel("usrname");
		luser.setSize(80, 30);
		luser.setLocation(700, 30);

		lpass = new JLabel("password");
		lpass.setSize(80, 30);
		lpass.setLocation(700, 80);

		q = new JLabel("ask your question:");
		q.setSize(150, 20);
		q.setLocation(10, 20);
		sg = new String[c.keywords.length];
		// sl = getarray();
		// this.sg = getarray();
		jb = new JComboBox(sg);
		jb.setSize(200, 20);
		jb.setLocation(10, 120);

		getContentPane().add(search);
		getContentPane().add(allquestions);
		getContentPane().add(signin);
		getContentPane().add(signup);
		getContentPane().add(question);
		getContentPane().add(username);
		getContentPane().add(password);
		getContentPane().add(luser);
		getContentPane().add(lpass);
		getContentPane().add(q);
		getContentPane().add(jb);
		allquestions.addActionListener(this);
		search.addActionListener(this);
		signin.addActionListener(this);
		signup.addActionListener(this);
		jb.addActionListener(this);
		jb.setActionCommand("subject");
		p = new Panel(d, this);
		this.getContentPane().add(p);
		setVisible(true);
	}

	public String[] getarray() {
		return s;
	}

	public void setarray(String[] a) {
		this.s = a;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == search) {
			System.out.println(question.getText());
			c.sendMessage(new Message(Message.SEARCH, question.getText()));
		}

		if (arg0.getSource() == signin) {
			if (username.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Error:you should enter your username");
			}
			if (password.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Error:you should enter your password");
			}
			Userz u = new Userz("", username.getText(), "", password.getText(), null);
			c.sendMessage(new Message(Message.LOGIN, u));

			// boolean flag = true;
			// try {
			// wait(100);
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }

			// try {
			// Thread.sleep(100);
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			//
			// System.out.println("qq");
			// if (c.islogin == false) {
			// JOptionPane.showMessageDialog(null, "wrong username or
			// password");
			// //flag = false;
			// } else{
			// mp = new MainPage(c, u);
			// }
		}
		if (arg0.getSource() == signup) {
			// sp = new Signup(c);
			Userz u = new Userz("", username.getText(), "", password.getText(), null);
			c.sendMessage(new Message(Message.REGISTER, u));
			// qp = new QuestionPage(c, u);
		}

		if (arg0.getActionCommand().equals("subject")) {
		      String selectItem = (String) jb.getSelectedItem();
		      c.sendMessage(new Message(Message.SEARCH, selectItem));

		    }

		if (arg0.getSource() == allquestions) {
			c.sendMessage(new Message(Message.VIEWALL, "allquestions"));
		}
	}

}
