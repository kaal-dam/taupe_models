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


/**
 * Classe d'ajout de tag a un modele lors de sa creation
 * 
 * @author damien, tanre
 * 
 */
public class TagInsertFile extends JFrame {

	JTextField		j1		= new JTextField();
	JButton			valid	= new JButton("valider");
	JButton			add		= new JButton("ajouter");
	static File		file;
	static String	desc;

	public TagInsertFile(File file, String desc) {
		this.setSize(500, 100);
		setVisible(true);
		setLayout(new FlowLayout());
		this.file = file;
		this.desc = desc;
		this.add(new JLabel("tag1"));
		j1.setPreferredSize(new Dimension(100, 20));
		this.add(j1);
		this.add(add);
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addBDD(j1.getText());
				j1.setText("");
			}
		});
		this.add(valid);
		valid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addBDD(j1.getText());
				dispose();
			}
		});

		setDefaultCloseOperation(1);
		setLocationRelativeTo(null);

	}

	/**
	 * Ajoute le tag a la table des tag si le tag n'existait pas et cree une
	 * association tag model
	 * 
	 * @param tag1
	 *            tag a ajouter
	 */
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
				} catch (Exception e) {}
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
		}
		finally {
			try {
				c.close();
			} catch (Exception e2) {}
		}
	}
}
