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
        jf = new JFrame("Taupes Models");
        listmodel = new ListeModels();
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFichier = new JMenu("Fichier");
        JMenuItem ouvrir = new JMenuItem("Ouvrir");
        menuFichier.add(ouvrir);
        JMenuItem importer = new JMenuItem("Importer");
        menuFichier.add(importer);
        JMenuItem newTag = new JMenuItem("NewTag");
        menuFichier.add(newTag);

        menuBar.add(menuFichier);
        menuBar.add(new Scale());
        jf.setJMenuBar(menuBar);

        importer.addActionListener(new GTSFileChooser());
        newTag.addActionListener(new addTagToTableTag());

        jf.add(listmodel, BorderLayout.WEST);

        loadModel("model/fluttershy.gts");

        jf.add(info, BorderLayout.SOUTH);

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
