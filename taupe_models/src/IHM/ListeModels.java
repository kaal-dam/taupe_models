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

import Main.MainClass;

public class ListeModels extends JPanel implements MouseListener, ActionListener, KeyListener {

	private static final long serialVersionUID = -2678875145334246880L;
	public static JTextField recherche;
    JList<Object> liste;
    JPopupMenu jf;
    
    public ListeModels() {

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setMaximumSize(this.getSize());
        this.setMinimumSize(this.getSize());
        

        recherche = new JTextField("");
        recherche.setSize(150, 25);
        recherche.setMaximumSize(new Dimension(150, 25));
        recherche.addActionListener(this);
        recherche.addKeyListener(this);
        add(recherche, BorderLayout.NORTH);

        liste = new JList<Object>(new File("./model").list());
        add(liste);
        liste.addMouseListener(this);
        liste.setMaximumSize(this.getSize());
        liste.setMinimumSize(this.getSize());

        this.setSize(150, 600);
        this.setPreferredSize(this.getSize());
        this.setBackground(Color.white);

    }

    public void refreshList(){
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
        	System.out.println(e.getSource().getClass());
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
			/*if(recherche.getText().length()>20)
				recherche.setText(recherche.getText().substring(0, 20));*/
	}
		

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getSource() == recherche){
			refreshList();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
