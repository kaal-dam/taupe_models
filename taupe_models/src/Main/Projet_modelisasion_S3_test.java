package Main;


import java.awt.BorderLayout;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Affichage.Affichage;
import Affichage.Model;
import IHM.Console;

public class Projet_modelisasion_S3_test{

    /**
     * @param args the command line arguments
     */
	
	public static Affichage aff;
	public static Model model;
    public static void main(String[] args) {
        //pour test
        JFrame jf = new JFrame("Taupes Models");
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFichier = new JMenu("Fichier");
        JMenuItem ouvrir = new JMenuItem("Ouvrir");
        menuFichier.add(ouvrir);
        JMenuItem importer = new JMenuItem("Importer");
        menuFichier.add(importer);
        menuBar.add(menuFichier);
        jf.setJMenuBar(menuBar);
        
        jf.add(new Console(jf.WIDTH, jf.HEIGHT/4), BorderLayout.SOUTH);
        
        model = new Model("x_wing.gts");
        Collections.sort(model.triangle);
        aff = new Affichage(model);
        jf.add(aff);
        jf.setVisible(true);
        jf.setSize(800, 600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }
}
