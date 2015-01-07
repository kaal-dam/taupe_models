package Main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToggleButton;

import Affichage.Affichage;
import Affichage.Model;
import BDD.GTSFileChooser;
import BDD.addTagToTableTag;
import IHM.APropos;
import IHM.Infos;
import IHM.ListeModels;
import IHM.Scale;
import Tools.ToolBox;

public class MainClass {

    public static Affichage aff;
    public static Model model;
    public static Infos info;
    public static int panelH = 600, panelW = 800;
    public static JFrame jf;
    public static ListeModels listmodel;
    public static int aProposOpened = 0;

    public static void main(String[] args) {
        //pour test
    	
    	//vrai main
        jf = new JFrame("Taupes Models");
        listmodel = new ListeModels();
        JMenuBar menuBar = new JMenuBar();
        //je fais du bordel
        JMenuBar menuAff = new JMenuBar();
        
        JToggleButton arrete = new JToggleButton("A",ToolBox.arrete);
        arrete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ToolBox.arrete = !ToolBox.arrete;
			}
		});
        
        JToggleButton point = new JToggleButton("P",ToolBox.point);
        point.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ToolBox.point = !ToolBox.point;
			}
		});
        
        JToggleButton face = new JToggleButton("F",ToolBox.face);
        face.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ToolBox.face = !ToolBox.face;
			}
		});
        
        menuAff.add(arrete);
        menuAff.add(point);
        menuAff.add(face);
        menuBar.add(menuAff);
        
        //fini le bordel
        
        JMenu menuFichier = new JMenu("Fichier");
        JMenuItem importer = new JMenuItem("Importer");
        menuFichier.add(importer);
        
        /* NewTag inutile maintenant, allez dans PlusDInfo */
        JMenuItem newTag = new JMenuItem("NewTag");
        menuFichier.add(newTag);

        menuBar.add(menuFichier);
        
        
        JMenu menuScale = new JMenu("Scale");
        JMenuItem scale = new JMenuItem("Scale");
        menuScale.add(scale);
        
        //menuBar.add(menuScale);
        
        
        /* Redirection vers la doc */
        JMenu menuAide = new JMenu("Aide");
        
        menuBar.add(menuAide);
        
        /* A propos contiendra des infos générales sur le logiciel
         * Nom, noms des membres du groupes, etc... */
        JMenu menuAPropos = new JMenu("A propos");
        JMenuItem aPropos = new JMenuItem("A propos");
        menuAPropos.add(aPropos);
        
        menuBar.add(menuAPropos);
        menuBar.add(menuAff);
        
        jf.setJMenuBar(menuBar);

        importer.addActionListener(new GTSFileChooser());
        newTag.addActionListener(new addTagToTableTag());
        scale.addActionListener(new Scale());
        aPropos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(aProposOpened == 0) {
					new APropos();
					aProposOpened = 1;
				}
			}
        	
        });

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
