package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import Main.MainClass;

/**
 * 
 * Cette classe correspond au panel latéral gauche qui affiche la liste des models disponibles
 * ainsi que des informations supplémentaires sur le modèle actuellement sélectionner.<br>
 * Recherche par nom / tag disponible via le JTextField.<br>
 * Les infos supplémentaires sont disponibles via le JPanel {@link Description} au dessus de la liste.
 * 
 */
public class ListeModels extends JPanel implements MouseListener, KeyListener {

	private static final long serialVersionUID = -2678875145334246880L;
	public static JTextField recherche;
	public JList<Object> liste;
	JPopupMenu jf;
	Description info;
	public JList<String> listTag;
	public JFrame frameInfo;

	/**
	 * Constructeur de ListeModels
	 */
	public ListeModels() {

		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setMaximumSize(this.getSize());
		this.setMinimumSize(this.getSize());

		info = new Description();
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
		info.refreshInfo("x_wing.gts");

	}

	/**
	 * Rafraichit la liste des modèles en fonction de ce qui est écrit dans la barre de recherche (JTextField).<br>
	 * Cette méthode est appelée lors de l'évènement KeyReleased (si la source est le JTextField).
	 */
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
		if (e.getSource() == liste) {
			if (e.getClickCount() == 2) {
				MainClass.loadModel("model/" + liste.getSelectedValue());
			}
		}
		info.refreshInfo(liste.getSelectedValue().toString());
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
		if (e.getSource() == recherche)
			refreshList();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
