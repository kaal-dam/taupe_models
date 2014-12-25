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
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import BDD.SelectTag;
import Main.MainClass;

public class ListeModels extends JPanel implements MouseListener, KeyListener {

	private static final long serialVersionUID = -2678875145334246880L;
	public static JTextField recherche;
	public JList<Object> liste, liste2;
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

	public void refreshList(int nbListes) {

		List<String> tags = null;
		int selectedIndex = 0;

		if (liste.getSelectedValue() != null) {
			tags = SelectTag.GetTags(liste.getSelectedValue().toString());
			if (liste.getSelectedIndex() == liste.getModel().getSize() - 1)
				nbListes = 1;
			else {
				nbListes = 2;
				selectedIndex = liste.getSelectedIndex();
			}
		}

		else if (liste2 != null && liste2.getSelectedValue() != null) {
			tags = SelectTag.GetTags(liste2.getSelectedValue().toString());
			if (liste2.getSelectedIndex() == liste2.getModel().getSize() - 1)
				nbListes = 1;
			else {
				nbListes = 2;
				selectedIndex = liste.getModel().getSize()
						+ liste2.getSelectedIndex();
			}
		}

		this.removeAll();
		add(recherche);

		if (nbListes == 1) {
			liste = new JList<Object>(new BDD.FiltreModels().getList());
			liste.addMouseListener(this);
			add(liste);
			if (tags != null) {
				for (int i = 0; i < tags.size(); ++i) {
					add(new JLabel(tags.get(i))).setForeground(Color.gray);
				}
			}
		}

		if (nbListes == 2) {
			liste = new JList<Object>(
					new BDD.FiltreModels().getList1(selectedIndex));
			liste.addMouseListener(this);
			liste2 = new JList<Object>(
					new BDD.FiltreModels().getList2(selectedIndex));
			liste2.addMouseListener(this);
			add(liste);
			if (tags != null) {
				for (int i = 0; i < tags.size(); ++i) {
					add(new JLabel(tags.get(i))).setForeground(Color.gray);
				}
			}
			add(liste2);
		}

		recherche.requestFocusInWindow();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println(liste.getSelectedValue().toString());
		if (e.getSource() == liste) {
			if (e.getClickCount() == 1) {
				if (liste2 == null)
					refreshList(1);
				else
					refreshList(2);
			}
			if (e.getClickCount() == 2) {
				MainClass.loadModel("model/" + liste.getSelectedValue());
			}
		}
		if (e.getSource() == liste2) {
			if (e.getClickCount() == 1) {
				if (liste2 == null)
					refreshList(1);
				else
					refreshList(2);
			}
			if (e.getClickCount() == 2) {
				MainClass.loadModel("model/" + liste2.getSelectedValue());
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
			if (liste2 == null)
				refreshList(1);
			else
				refreshList(2);
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
