import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainPage extends JPanel implements ActionListener{
Dimension d;
JFrame j;
JButton search , save , java , C , SQL , Verilog , VHDL;
JTextField question;
private int b;
private int m = 80;
private int i;
private int n;
private Userz u ;
private Client c ;
public String [] interest = new String [5];
public MainPage(Client client,Userz user ){
	u = user ;
	c = client;
	d = Toolkit.getDefaultToolkit().getScreenSize();
	j = new JFrame();
	setSize(800,560);
	j.setSize(800,560);
	setLocation(0,0);
	j.setLocation(90,0);
	setLayout(null);
	j.setLayout(null);
	setFocusable(true);
	
 	search = new JButton("search");
 	search.setSize(100, 30);
 	search.setLocation(570, 30);
 	
	save = new JButton("save");
	save.setSize(100, 30);
	save.setLocation(680,30);
	
 	question = new JTextField();
 	question.setSize(550, 30);
 	question.setLocation(10, 30);
 	
	C = new JButton("C");
	C.setSize(100, 30);
	C.setLocation(680,120);
	
 	SQL = new JButton("SQL");
 	SQL.setSize(100, 30);
 	SQL.setLocation(680,160);
 	
	Verilog = new JButton("Verilog");
	Verilog.setSize(100, 30);
	Verilog.setLocation(680,200);
	
	VHDL = new JButton("VHDL");
	VHDL.setSize(100, 30);
	VHDL.setLocation(680,240);
	
    this.add(save);
    this.add(search);
    this.add(question);
    if(u.interest!= null){
    for(i = 0  ; i<5 ; i++){
    	b = u.interest[i];
	if(b==1){
       if(i==0){
    	 	java = new JButton("java");
    	 	java.setSize(100, 30);
    	 	java.setLocation(680,m);
    	 	m = m + 40;
    	 	this.add(java);
    	    java.addActionListener(this);
       }
       if(i==1){
    		C = new JButton("C");
    		C.setSize(100, 30);
    		C.setLocation(680,m);
    		m = m + 40;
    		this.add(C);
    	    C.addActionListener(this);
       }
       if(i==2){
    	 	SQL = new JButton("SQL");
    	 	SQL.setSize(100, 30);
    	 	SQL.setLocation(680,m);
    	 	m = m + 40;
    	 	this.add(SQL);
    	    SQL.addActionListener(this);
       }
       if(i==3){
    		Verilog = new JButton("Verilog");
    		Verilog.setSize(100, 30);
    		Verilog.setLocation(680,m);
    		m = m + 40;
    		this.add(Verilog);
    	    Verilog.addActionListener(this);
       }
       if(i==4){
    		VHDL = new JButton("VHDL");
    		VHDL.setSize(100, 30);
    		VHDL.setLocation(680,m);
    		m = m + 40;
    		this.add(VHDL);
    	    VHDL.addActionListener(this);
       }
	}
    }
    }
    
    search.addActionListener(this);
    save.addActionListener(this);
	j.getContentPane().add(this);
	setVisible(true);
	j.setVisible(true);
}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
