package IHM;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Affichage.Model;
import Main.MainClass;

/***
 * 
 * Cette classe permet d'afficher les informations du modèle actuel
 * tels que le nombre de points, segments et triangles.
 * (Volume ?)
 *
 */
@SuppressWarnings("serial")
public class Infos extends JPanel {

    JLabel text;

    /**
     * Constructeur du panel Infos (en bas de l'ecran)
     */
    public Infos() {

        text = new JLabel(MainClass.model.url.substring(6) + " :   "
        		+ ((Integer) (Model.nbPts)).toString() + " points / "
                + ((Integer) (Model.nbSeg)).toString() + " segments / "
                + ((Integer) (Model.nbTri)).toString() + " triangles");

        add(text);
        text.setForeground(Color.WHITE);
        this.setBackground(Color.black);

    }
}
