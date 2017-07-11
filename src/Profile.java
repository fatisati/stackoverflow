
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Profile extends JPanel implements ActionListener{

    Dimension d;
    JButton save;
    JTextField name, username, password, email;
    JLabel lname, luser, lpass, lemail, linterest;
    JCheckBox java, C, SQL, Verilog, VHDL;
    JFrame j;
    Userz u;
    Client c;

    public Profile(Client client,Userz user ) {
	this.c = client;
	this.u = user;
	d = Toolkit.getDefaultToolkit().getScreenSize();
	j = new JFrame();
	setSize(800, 560);
	j.setSize(600, 560);
	setLocation(0, 0);
	j.setLocation(90, 0);
	setLayout(null);
	j.setLayout(null);
	setFocusable(true);
	j.getContentPane().add(this);

	save = new JButton("signup");
	save.setSize(100, 30);
	save.setLocation(220, 330);

	name = new JTextField();
	name.setSize(300, 30);
	name.setLocation(140, 50);

	username = new JTextField();
	username.setSize(300, 30);
	username.setLocation(140, 120);

	password = new JTextField();
	password.setSize(300, 30);
	password.setLocation(140, 190);

	email = new JTextField();
	email.setSize(300, 30);
	email.setLocation(140, 260);

	lname = new JLabel("name");
	lname.setSize(100, 50);
	lname.setLocation(20, 50);

	linterest = new JLabel("Interests:");
	linterest.setSize(150, 30);
	linterest.setLocation(470, 50);

	luser = new JLabel("usrname");
	luser.setSize(100, 50);
	luser.setLocation(20, 120);

	lpass = new JLabel("password");
	lpass.setSize(100, 50);
	lpass.setLocation(20, 190);

	lemail = new JLabel("email");
	lemail.setSize(100, 50);
	lemail.setLocation(20, 260);

	java = new JCheckBox("java");
	java.setSize(100, 30);
	java.setLocation(470, 90);

	C = new JCheckBox("C");
	C.setSize(100, 30);
	C.setLocation(470, 130);

	SQL = new JCheckBox("SQL");
	SQL.setSize(100, 30);
	SQL.setLocation(470, 170);

	Verilog = new JCheckBox("Verilog");
	Verilog.setSize(100, 30);
	Verilog.setLocation(470, 210);

	VHDL = new JCheckBox("VHDL");
	VHDL.setSize(100, 30);
	VHDL.setLocation(470, 250);

	this.add(save);
	this.add(name);
	this.add(username);
	this.add(password);
	this.add(email);
	this.add(luser);
	this.add(lpass);
	this.add(lemail);
	this.add(lname);
	this.add(java);
	this.add(C);
	this.add(SQL);
	this.add(Verilog);
	this.add(VHDL);
	this.add(linterest);
	save.addActionListener(this);
	java.addActionListener(this);
	C.addActionListener(this);
	SQL.addActionListener(this);
	Verilog.addActionListener(this);
	VHDL.addActionListener(this);
	j.getContentPane().add(this);
	setVisible(true);
	j.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
