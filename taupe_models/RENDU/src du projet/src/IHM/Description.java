package IHM;

import java.awt.Color;
import java.awt.Dimension;
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

/**
 * 
 * Cette classe correspond à la description du modèle actuellement sélectionné.<br>
 * Le JPanel est situé dans le coin supérieur gauche de l'écran.<br>
 * Des informations supplémentaires sont disponibles via un JButton.
 * @see {@link PlusDInfo}
 * 
 *
 */
@SuppressWarnings("serial")
public class Description extends JPanel implements ActionListener {
	
	/**
	 * un jlabel
	 */
	JLabel text;
	
	/**
	 * liste des tag du modele regarder
	 */
	JList<String> listTag;
	/**
	 * frame du menu plus d'infos
	 */
	JFrame frameInfo;
	/**
	 * boutton permettant d'afficher le menu plus d'infos
	 */
	JButton plus;
	/**
	 * nom du modeldont ou souhaite les infos
	 */
	String model;
	/**
	 * fenetre plus d'info a afficher si voulu
	 */
	public static PlusDInfo plusDInfo = null;
	
	/**
	 * Constructeur de Description
	 */
	public Description() {
	
		this.setMaximumSize(new Dimension(150, 100));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBackground(Color.LIGHT_GRAY);
		
	}
	
	/**
	 * 
	 * Rafraichit le JPanel Description, cette méthode est appelée lors d'un clic simple sur un élément de la JList ({@link ListeModels}).
	 * @param model Le model sélectionné
	 */
	public void refreshInfo(final String model) {
		this.model = model;
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
			plus = new JButton("plus d'infos");
			plus.addActionListener(this);
			this.add(plus);
		} catch (Exception e) {
			
		} finally {
			try{c.close();}catch(Exception e1){}
		}
	}

	@Override
	/**
	 * ouvre le menu plus d'info
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == plus && plusDInfo == null) {
			plusDInfo = new PlusDInfo(model);
		}
	}
}
