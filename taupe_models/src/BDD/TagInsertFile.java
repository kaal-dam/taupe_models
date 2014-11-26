package BDD;

/* pour l'instant l'utilisateur entre les tag à la main 
 * 
 * 
 * 
 */


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TagInsertFile extends JFrame{
	
	JTextField j1=new JTextField();
	JTextField j2=new JTextField();		
	JButton b1=new JButton("valider");
	static File f;
	
	public TagInsertFile(File f){
		this.setSize(500,100);
		this.setVisible(true);
		this.setLayout(new FlowLayout());
		this.f=f;
		this.add(new JLabel("tag1"));
		j1.setPreferredSize(new Dimension(100,20));
		this.add(j1);
		this.add(new JLabel("tag2"));
		j2.setPreferredSize(new Dimension(100,20));
		this.add(j2);
		this.add(b1);
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addBDD(j1.getText(),j2.getText());
				dispose();
			}
		});
		
		this.setDefaultCloseOperation(1);
		this.setLocationRelativeTo(null);
		
	}

	
	
	static void addBDD(String tag1,String tag2){
		Connection c =null;		
		try{
			Class.forName("org.sqlite.JDBC");  
			
			
			c = DriverManager.getConnection("jdbc:sqlite:model.db");
			c.setAutoCommit(false);
			Statement stmt = c.createStatement();
		    String sql = "INSERT INTO Modeles  " +
		                   "VALUES ('"+f.getName()+"', '"+f+"', '"+tag1+"', '"+tag2+"');"; 
		    stmt.executeUpdate(sql);
		    stmt.close();
		    c.commit();
		    
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{c.close();}catch(Exception e2){}
		}
	}
	
	
}
