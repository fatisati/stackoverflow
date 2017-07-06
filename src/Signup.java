import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Signup extends JPanel implements ActionListener{
	Dimension d;
	JButton save;
	JTextField name,username,password,email;
	JLabel lname,luser,lpass,lemail;
	JFrame j;
	
	public Signup(){
		d = Toolkit.getDefaultToolkit().getScreenSize();
		j = new JFrame();
		setSize(1000,720);
		j.setSize(1015,760);
		setLocation(0,0);
		j.setLocation(90,0);
		setLayout(null);
		j.setLayout(null);
		setFocusable(true);
		j.getContentPane().add(this);
		
		save = new JButton("signin");
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
		
		luser = new JLabel("usrname");
		luser.setSize(100, 50);
		luser.setLocation(20, 120);
		
		lpass = new JLabel("password");
		lpass.setSize(100, 50);
		lpass.setLocation(20, 190);
		
		lemail = new JLabel("email");
		lemail.setSize(100, 50);
		lemail.setLocation(20, 260);
		
	    this.add(save);
	    this.add(name);
	    this.add(username);
	    this.add(password);
	    this.add(email);
	    this.add(luser);
	    this.add(lpass);
	    this.add(lemail);
	    this.add(lname);
	    save.addActionListener(this);
	    setVisible(true);
		j.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}
	

}
