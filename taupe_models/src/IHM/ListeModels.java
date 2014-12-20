package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import BDD.FiltreModels;
import Main.MainClass;

public class ListeModels extends JPanel implements MouseListener, ActionListener, KeyListener {

	private static final long serialVersionUID = -2678875145334246880L;
	public static JTextField recherche;
    JList liste;
    JPopupMenu jf;
    
    @SuppressWarnings("unchecked")
	public ListeModels() {

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setMaximumSize(this.getPreferredSize());
        this.

        recherche = new JTextField("Recherche par nom/tag");
        recherche.setMaximumSize(new Dimension(200, 25));
        recherche.addActionListener(this);
        recherche.addKeyListener(this);
        add(recherche, BorderLayout.NORTH);

        liste = new JList(new File("./model").list());
        add(liste);
        liste.addMouseListener(this);

        this.setBackground(Color.white);

    }

    public void refreshList(){
    	this.remove(liste);
    	liste = new JList(new File("./model").list());
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
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == recherche) {
			
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getSource() == recherche) {
			if(recherche.getText().length()>20)
				recherche.setText(recherche.getText().substring(0, 20));
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
