package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * Cette classe permet d'afficher des infos supplémentaires sur le model sélectionné (tags).<br>
 * Elle permet également de modifier la description et la liste des tags.<br>
 * Une seule JFrame PlusDInfo peut être ouverte à la fois.
 *
 */
@SuppressWarnings("serial")
public class PlusDInfo extends JFrame implements ActionListener, WindowListener{
	
	JList<String> listTag;
	JButton delete;
	JButton add;
	JTextField tag;
	final JPanel pan2;
	final String model;

	/**
	 * Constructeur de PlusDInfo
	 * @param model Model sélectionné
	 */
	public PlusDInfo(String model){
		
		this.model = model;
		this.setTitle("Plus d'infos sur " + model);
		this.addWindowListener(this);
		JTextField description = null;
		
		tag = new JTextField();
		add = new JButton("add tag");
		add.addActionListener(this);
		
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
		pan1.add(new JLabel("Nom: " + model));
		pan1.add(new JLabel(""));
		pan1.add(new JLabel("Description:"));
		pan1.add(description);
		//panel tag 
		pan2 = new JPanel();
		pan2.setLayout(new FlowLayout());
		listTag = new JList<String>(BDD.Select.getTagof(model));
		pan2.add(listTag);
		delete = new JButton("Supprimer Tag");
		delete.addActionListener(this);
		pan2.add(delete);
		
		//panel d'ajout de tag
		JPanel pan3 = new JPanel(new GridLayout(1,2));
		tag.setMinimumSize(new Dimension(25, 50));
		tag.setMaximumSize(new Dimension(25, 50));
		pan3.add(tag);
		pan3.add(add);
		
		
		//on ajoute les pan
		this.setSize(300, 200);
		this.setLayout(new GridLayout(3,1));
		this.add(pan1);
		this.add(pan2, BorderLayout.NORTH);
		this.add(pan3);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == delete) {
			pan2.remove(listTag);
			BDD.Delete.delete("tags","tag", listTag.getSelectedValue());
			BDD.Delete.delete("association","tag", listTag.getSelectedValue());
			listTag.setListData(BDD.Select.getTagof(model));
			pan2.add(listTag);
			repaint();
		}
		if(e.getSource() == add){
			if(!tag.getText().equals(" ") && !tag.getText().equals("")){
				BDD.Add.toTag(tag.getText(), model);
				listTag.setListData(BDD.Select.getTagof(model));
				tag.setBackground(Color.white);
				repaint();
			}else{
				tag.setBackground(Color.RED);
			}
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		/* Permet d'interdire l'ouverte de plusieurs JFrame PlusDInfo */
		IHM.Description.plusDInfo = null;
		Connection c = null;
		Statement stmnt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:model.db");
			c.setAutoCommit(false);
			stmnt = c.createStatement();
		} catch(Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				c.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
