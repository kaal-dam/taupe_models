package IHM;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.MainClass;

@SuppressWarnings("serial")
public class Infos extends JPanel {

    JLabel text;

    public Infos() {

        text = new JLabel(((Integer) (MainClass.model.nbPts)).toString() + " points / "
                + ((Integer) (MainClass.model.nbSeg)).toString() + " segments / "
                + ((Integer) (MainClass.model.nbTri)).toString() + " triangles");

        add(text);
        text.setForeground(Color.WHITE);
        this.setBackground(Color.black);

    }
}
