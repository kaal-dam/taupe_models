package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;

import Affichage.Model;
import Main.MainClass;

public class ListeModels extends JPanel implements MouseListener {

    JComboBox tags;
    JList liste;

    public ListeModels() {

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        tags = new JComboBox(new String[]{"<aucun tag>"});
        tags.setMaximumSize(tags.getPreferredSize());
        add(tags);

        liste = new JList(new File("./model").list());
        add(liste);
        liste.addMouseListener(this);

        this.setBackground(Color.white);

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
