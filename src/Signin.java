

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Signin extends JPanel implements ActionListener {
	Dimension d;
	JFrame j;
	JButton signup,signin;
	JTextField username,password;
	JLabel luser,lpass;
	Signup sp;
	
	public Signin(){
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
		
		signin = new JButton("signin");
		signin.setSize(100, 30);
		signin.setLocation(180, 150);

		signup = new JButton("signup");
		signup.setSize(100, 30);
		signup.setLocation(300, 150);
	
		username = new JTextField();
		username.setSize(300, 30);
		username.setLocation(140, 50);
		
		password = new JTextField();
		password.setSize(300, 30);
		password.setLocation(140, 100);
		
		luser = new JLabel("usrname");
		luser.setSize(100, 50);
		luser.setLocation(20, 50);
		
		
		lpass = new JLabel("password");
		lpass.setSize(100, 50);
		lpass.setLocation(20, 100);
		
	    this.add(signin);
	    this.add(signup);
	    this.add(username);
	    this.add(password);
	    this.add(luser);
	    this.add(lpass);
	    signin.addActionListener(this);
	    signup.addActionListener(this);
	    setVisible(true);
		j.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==signin){
			System.exit(0);
		}
		
		if(arg0.getSource()==signup){
	        sp = new Signup();
		}
	}

}
