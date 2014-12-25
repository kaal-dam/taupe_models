package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
		info.setMaximumSize(new Dimension(150, 75));
		info.setSize(150, 200);
		add(info, BorderLayout.NORTH);

		recherche = new JTextField("recherche..");
		recherche.setSize(150, 25);
		recherche.setMaximumSize(new Dimension(150, 25));
		recherche.addKeyListener(this);
		add(recherche, BorderLayout.WEST);

		liste = new JList<Object>(new File("./model").list());
		add(liste, BorderLayout.SOUTH);
		liste.addMouseListener(this);

		this.setSize(150, 600);
		this.setPreferredSize(this.getSize());
		this.setBackground(Color.white);
		refreshInfo("x_wing.gts");

	}

	public void refreshInfo(String model) {
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
			info.add(new JLabel("description:" + desc));
		} catch (Exception e) {
			
		} finally {
			try{c.close();}catch(Exception e1){}
		}
	}

	public void refreshList() {
		this.remove(liste);
    	liste = new JList<Object>(new BDD.FiltreModels().getList());
    	liste.addMouseListener(this);
        add(liste);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println(liste.getSelectedValue().toString());
		if (e.getSource() == liste) {
			if (e.getClickCount() == 2) {
				MainClass.loadModel("model/" + liste.getSelectedValue());
			}
		}
		//refreshInfo(liste.getSelectedValue().toString());
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
