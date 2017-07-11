
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainPage extends JPanel implements ActionListener {
	Dimension d;
	JFrame j;
	JButton search, submit, allquestions, setting, java, C, SQL, Verilog, VHDL;
	JTextField question, tags, result;
	JTextArea squestion;
	JComboBox interests;
	JComboBox keyword;
	private int b;
	private int m = 80;
	private int i;
	private int n;
	private Userz u;
	private Client c;
	String[] sl;
	String[] si;
	ArrayList<String> interest = new ArrayList<String>();

	public MainPage(Client client, Userz user) {
		u = user;
		c = client;
		interest = u.interest;
		d = Toolkit.getDefaultToolkit().getScreenSize();
		j = new JFrame();
		setSize(1150, 700);
		j.setSize(1150, 700);
		setLocation(0, 0);
		j.setLocation(90, 0);
		setLayout(null);
		j.setLayout(null);
		setFocusable(true);

		search = new JButton("search");
		search.setSize(100, 30);
		search.setLocation(570, 30);

		allquestions = new JButton("seequestions");
		allquestions.setSize(150, 50);
		allquestions.setLocation(550, 120);

		submit = new JButton("submit");
		submit.setSize(100, 30);
		submit.setLocation(840, 500);

		setting = new JButton("setting");
		setting.setSize(100, 30);
		setting.setLocation(50, 500);

		question = new JTextField();
		question.setSize(550, 30);
		question.setLocation(10, 30);

		tags = new JTextField();
		tags.setSize(400, 30);
		tags.setLocation(690, 450);

		result = new JTextField();
		result.setSize(400, 30);
		result.setLocation(690, 550);

		squestion = new JTextArea();
		squestion.setSize(400, 400);
		squestion.setLocation(690, 30);

		C = new JButton("C");
		C.setSize(100, 30);
		C.setLocation(680, 120);

		SQL = new JButton("SQL");
		SQL.setSize(100, 30);
		SQL.setLocation(680, 160);

		Verilog = new JButton("Verilog");
		Verilog.setSize(100, 30);
		Verilog.setLocation(680, 200);

		VHDL = new JButton("VHDL");
		VHDL.setSize(100, 30);
		VHDL.setLocation(680, 240);

		this.add(submit);
		this.add(search);
		this.add(question);
		this.add(squestion);
		this.add(tags);
		this.add(result);
		this.add(setting);
		sl = new String[interest.size()];
		si = new String[c.keywords.length];
		si = c.keywords;
		if (u.interest != null) {
			for (int i = 0; i < interest.size(); i++) {
				if (interest.get(i).equals("java")) {
					sl[i] = "java";
					// java = new JButton("java");
					// java.setSize(100, 30);
					// java.setLocation(680, m);
					// m = m + 40;
					// this.add(java);
					// java.addActionListener(this);
				}
				if (interest.get(i).equals("C")) {
					sl[i] = "C";
					// C = new JButton("C");
					// C.setSize(100, 30);
					// C.setLocation(680, m);
					// m = m + 40;
					// this.add(C);
					// C.addActionListener(this);
				}
				if (interest.get(i).equals("SQL")) {
					sl[i] = "SQL";
					// SQL = new JButton("SQL");
					// SQL.setSize(100, 30);
					// SQL.setLocation(680, m);
					// m = m + 40;
					// this.add(SQL);
					// SQL.addActionListener(this);
				}
				if (interest.get(i).equals("Verilog")) {
					sl[i] = "Verilog";
					// Verilog = new JButton("Verilog");
					// Verilog.setSize(100, 30);
					// Verilog.setLocation(680, m);
					// m = m + 40;
					// this.add(Verilog);
					// Verilog.addActionListener(this);
				}
				if (interest.get(i).equals("VHDL")) {
					sl[i] = "VHDL";
					// VHDL = new JButton("VHDL");
					// VHDL.setSize(100, 30);
					// VHDL.setLocation(680, m);
					// m = m + 40;
					// this.add(VHDL);
					// VHDL.addActionListener(this);
				}
			}
		}
		interests = new JComboBox(sl);
		interests.setSize(200, 20);
		interests.setLocation(10, 80);

		keyword = new JComboBox(si);
		keyword.setSize(200, 20);
		keyword.setLocation(230, 80);

		this.add(interests);
		this.add(keyword);
		this.add(allquestions);
		search.addActionListener(this);
		allquestions.addActionListener(this);
		submit.addActionListener(this);
		interests.addActionListener(this);
		interests.setActionCommand("jb");
		keyword.addActionListener(this);
		keyword.setActionCommand("ji");
		j.getContentPane().add(this);
		setVisible(true);
		j.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == submit) {

			Question q = new Question(squestion.getText(), tags.getText(), u, new Date());
			c.sendMessage(new Message(Message.ADD, q));
		}

		if (arg0.getSource() == search) {

			c.sendMessage(new Message(Message.SEARCH, question.getText()));
			// System.out.println("msg sent");
		}

		if (arg0.getSource() == allquestions) {
			c.sendMessage(new Message(Message.SEARCH, "allquestions"));
			// System.out.println("msg sent");
		}

		if (arg0.getActionCommand().equals("interests")) {
			String selectItem = (String) interests.getSelectedItem();
			c.sendMessage(new Message(Message.SEARCH, selectItem));
		}
		if (arg0.getActionCommand().equals("keyword")) {
			String selectItem = (String) keyword.getSelectedItem();
			c.sendMessage(new Message(Message.SEARCH, selectItem));
			System.out.println("hhhh");
		}

	}

	// @Override
	// public void actionPerformed(ActionEvent arg0) {
	// // TODO Auto-generated method stub
	// if (arg0.getSource() == submit) {
	//
	// Question q = new Question(ja.getText(), question.getText(), u);
	// c.sendMessage(new Message(Message.ADD, q));
	// }
	//
	// if (arg0.getSource() == search) {
	//
	// c.sendMessage(new Message(Message.SEARCH, ""));
	// System.out.println("msg sent");
	// }

}
