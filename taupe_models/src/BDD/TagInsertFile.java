package BDD;

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

public class TagInsertFile extends JFrame {

	JTextField jTextField = new JTextField();
	JButton valid = new JButton("valider");
	JButton add = new JButton("ajouter");
	static File file;
	static String desc;

	public TagInsertFile(File file, String desc) {
		this.setSize(500, 100);
		this.setVisible(true);
		this.setLayout(new FlowLayout());
		this.file = file;
		this.desc = desc;
		this.add(new JLabel("tag:"));
		jTextField.setPreferredSize(new Dimension(100, 20));
		this.add(jTextField);
		this.add(add);
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addBDD(jTextField.getText());
				jTextField.setText("");
			}
		});
		this.add(valid);
		valid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addBDD(jTextField.getText());
				dispose();
			}
		});

		this.setDefaultCloseOperation(1);
		this.setLocationRelativeTo(null);

	}


	static void addBDD(String tag1) {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:model.db");
			c.setAutoCommit(false);
			Statement stmt = c.createStatement();
			String sql = "";
			// ajouter la figure meme si on ne lui met aucun tag
			if (!tag1.equals("")) {
				sql = "INSERT INTO Tags  " + "VALUES ('" + tag1 + "');";
				try {
					stmt.executeUpdate(sql);
				} catch (Exception e) {
				}
				sql = "INSERT INTO Association  " + "VALUES ('"
						+ file.getName() + "', '" + tag1 + "');";
				try {
					stmt.executeUpdate(sql);
				} catch (Exception e) {
					System.out.println("echec pour add asso");
				}
			}
			stmt.close();
			c.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				c.close();
			} catch (Exception e2) {
			}
		}
	}

}
