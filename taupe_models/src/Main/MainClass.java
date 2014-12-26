package Main;

import java.awt.BorderLayout;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Affichage.Affichage;
import Affichage.Model;
import BDD.GTSFileChooser;
import BDD.addTagToTableTag;
import IHM.Infos;
import IHM.ListeModels;
import IHM.Scale;

public class MainClass {

    public static Affichage aff;
    public static Model model;
    public static Infos info;
    public static int panelH = 600, panelW = 800;
    public static JFrame jf;
    public static ListeModels listmodel;

    public static void main(String[] args) {
        //pour test
    	
    	//vrai main
        jf = new JFrame("Taupes Models");
        listmodel = new ListeModels();
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFichier = new JMenu("Fichier");
        JMenuItem importer = new JMenuItem("Importer");
        menuFichier.add(importer);
        JMenuItem newTag = new JMenuItem("NewTag");
        menuFichier.add(newTag);

        menuBar.add(menuFichier);
        
        
        JMenu menuScale = new JMenu("Scale");
        JMenuItem scale = new JMenuItem("Scale");
        menuScale.add(scale);
        
        menuBar.add(menuScale);
        
        
        JMenu menuOptions = new JMenu("Options");
        
        menuBar.add(menuOptions);
        
        /* Redirection vers la doc */
        JMenu menuAide = new JMenu("Aide");
        
        menuBar.add(menuAide);
        
        /* A propos contiendra des infos générales sur le logiciel
         * Nom, noms des membres du groupes, etc... */
        JMenu menuAPropos = new JMenu("A propos");
        
        menuBar.add(menuAPropos);
        
        jf.setJMenuBar(menuBar);

        importer.addActionListener(new GTSFileChooser());
        newTag.addActionListener(new addTagToTableTag());
        scale.addActionListener(new Scale());

        jf.add(listmodel, BorderLayout.WEST);

        loadModel("model/x_wing.gts");

        jf.add(aff);
        jf.setVisible(true);
        jf.setSize(800, 600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void loadModel(Object object) {
        model = new Model("" + object);
        Collections.sort(model.triangle);
        aff = new Affichage(model, panelW, panelH);
        if(info != null)
            jf.remove(info);
        info = new Infos();
        jf.add(info, BorderLayout.SOUTH);
    }
}
