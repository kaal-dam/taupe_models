package IHM;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import Main.MainClass;

public class ListeModels extends JPanel implements MouseListener {

	private static final long serialVersionUID = -2678875145334246880L;
	JComboBox tags;
    JList liste;
    JPopupMenu jf;
    
    @SuppressWarnings("unchecked")
	public ListeModels() {

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        tags = new JComboBox<String>(new String[]{"<aucun tag>"});
        tags.setMaximumSize(tags.getPreferredSize());
        add(tags);

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

}
