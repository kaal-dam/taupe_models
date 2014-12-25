package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import Main.MainClass;

public class ListeModels extends JPanel implements MouseListener, KeyListener {

	private static final long serialVersionUID = -2678875145334246880L;
	public static JTextField recherche;
	public JList<Object> liste;
	JPopupMenu jf;
	public JPanel info;

	public ListeModels() {

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setMaximumSize(this.getSize());
		this.setMinimumSize(this.getSize());

		info = new JPanel();
		info.setLayout(new FlowLayout());
		info.setMaximumSize(new Dimension(150, 100));
		info.setSize(150, 200);
		add(info, BorderLayout.NORTH);

		recherche = new JTextField("recherche..");
		recherche.setSize(150, 25);
		recherche.setMaximumSize(new Dimension(150, 25));
		recherche.addKeyListener(this);
		add(recherche, BorderLayout.WEST);

		liste = new JList<Object>(BDD.Select.select("modeles", "nom"));
		add(liste, BorderLayout.SOUTH);
		liste.addMouseListener(this);

		this.setSize(150, 600);
		this.setPreferredSize(this.getSize());
		this.setBackground(Color.white);
		refreshInfo("x_wing.gts");

	}

	public void refreshInfo(final String model) {
		info.removeAll();
		Connection c = null;
		Statement stmnt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:model.db");
			c.setAutoCommit(false);
			stmnt = c.createStatement();
			
			String query = "SELECT * FROM Modeles WHERE nom = '" + model + "';";
			ResultSet rs = stmnt.executeQuery(query);
			info.add(new JLabel("nom :" + rs.getString("nom")));
			String desc = rs.getString("description");
			if(desc.length() > 50)
				desc = desc.substring(0, 50) + "...";
			info.add(new JLabel("description:"));
			JTextField description = new JTextField();
			description.setText(desc);
			description.setEditable(false);
			info.add(description);
			JButton plus = new JButton("plus d'infos");
			plus.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					plusDInfo(model);
				}
			});
			info.add(plus);
		} catch (Exception e) {
			
		} finally {
			try{c.close();}catch(Exception e1){}
		}
	}
	
	public void plusDInfo(String model){
		JFrame jf = new JFrame("plus d'infos sur " + model);
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
		JPanel pan2 = new JPanel();
		pan2.setLayout(new FlowLayout());
		JList<String> list = new JList<String>(BDD.Select.getTagof(model));
		pan2.add(list);
		
		//on ajoute les pan
		jf.setSize(300, 200);
		jf.setLayout(new GridLayout(2,1));
		jf.add(pan1);
		jf.add(pan2);
		jf.setVisible(true);
	}

	public void refreshList() {
		this.remove(liste);
    	liste = new JList<Object>(BDD.Select.select("modeles", "chemin"));
    	liste.addMouseListener(this);
        add(liste);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == liste) {
			if (e.getClickCount() == 2) {
				MainClass.loadModel("model/" + liste.getSelectedValue());
			}
		}
		refreshInfo(liste.getSelectedValue().toString());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == recherche) {
				refreshList();
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
