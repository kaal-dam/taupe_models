package Main;


import java.awt.BorderLayout;

import java.io.File;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Affichage.Affichage;
import Affichage.Model;
import BDD.GTSFileChooser;
import BDD.addTagToTableTag;


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
        JMenuItem newTag = new JMenuItem("NewTag");
        menuFichier.add(newTag);
        
        
        menuBar.add(menuFichier);
        jf.setJMenuBar(menuBar);
        
        importer.addActionListener(new GTSFileChooser());
        newTag.addActionListener(new addTagToTableTag());
        
        
        String[] listeModels = new File("./model").list();
        
        JList JListeModels = new JList(listeModels);
        
        
        
        jf.add(JListeModels, BorderLayout.WEST);
        
        jf.add(new Console(jf.WIDTH, jf.HEIGHT/4), BorderLayout.SOUTH);
        
        model = new Model("model/x_wing.gts");
        Collections.sort(model.triangle);
        aff = new Affichage(model);
        jf.add(aff);
        jf.setVisible(true);
        jf.setSize(800, 600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }
}
