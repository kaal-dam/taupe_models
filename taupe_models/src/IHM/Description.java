package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Description extends JPanel {
	
	JLabel text;
	
	JList<String> listTag;
	JFrame frameInfo;
	
	Connection c = null;
	Statement stmnt = null;
	
	public Description() {
	
		this.setMaximumSize(new Dimension(150, 75));
		this.setSize(150, 200);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(Color.LIGHT_GRAY);
		
	}
	
	public void refreshInfo(final String model) {
		this.removeAll();
		Connection c = null;
		Statement stmnt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:model.db");
			c.setAutoCommit(false);
			stmnt = c.createStatement();
			
			String query = "SELECT * FROM Modeles WHERE nom = '" + model + "';";
			ResultSet rs = stmnt.executeQuery(query);
			this.add(new JLabel("nom : " + rs.getString("nom")));
			String desc = rs.getString("description");
			if(desc.length() > 50)
				desc = desc.substring(0, 50) + "...";
			this.add(new JLabel("description:"));
			JTextField description = new JTextField();
			description.setText(desc);
			description.setEditable(false);
			this.add(description);
			JButton plus = new JButton("plus d'infos");
			plus.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					plusDInfo(model);
				}
			});
			this.add(plus);
		} catch (Exception e) {
			
		} finally {
			try{c.close();}catch(Exception e1){}
		}
	}
	
	public void plusDInfo(final String model){
		
		frameInfo = new JFrame("plus d'infos sur " + model);
		JTextField description = null;
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:model.db");
			c.setAutoCommit(false);
			String query = "SELECT * FROM Modeles WHERE nom = '" + model + "';";
			ResultSet rs = c.createStatement().executeQuery(query);
			String desc = rs.getString("description");
			description = new JTextField(desc);
			description.setEditable(false);
		} catch (Exception e) {
			
		} finally {
			try{c.close();}catch(Exception e1){}
		}
		//panel nom + desc
		JPanel pan1 = new JPanel();
		pan1.setLayout(new GridLayout(2,2));
		pan1.add(new JLabel("nom: " + model));
		pan1.add(new JLabel(""));
		pan1.add(new JLabel("description:"));
		pan1.add(description);
		//panel tag 
		final JPanel pan2 = new JPanel();
		pan2.setLayout(new FlowLayout());
		listTag = new JList<String>(BDD.Select.getTagof(model));
		pan2.add(listTag);
		JButton delete = new JButton("supprimer");
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pan2.remove(listTag);
				BDD.Delete.delete("tags","tag", listTag.getSelectedValue());
				BDD.Delete.delete("association","tag", listTag.getSelectedValue());
				listTag = new JList<String>(BDD.Select.getTagof(model));
				pan2.add(listTag);
				frameInfo.repaint();
			}
		});
		pan2.add(delete);
		
		//on ajoute les pan
		frameInfo.setSize(300, 200);
		frameInfo.setLayout(new GridLayout(2,1));
		frameInfo.add(pan1);
		frameInfo.add(pan2);
		frameInfo.setVisible(true);
	}

}
