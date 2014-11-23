package Main;


import java.io.File;
import java.util.Collections;

import javax.swing.JFrame;

import Affichage.Affichage;
import Affichage.Model;

public class Projet_modelisasion_S3_test{

    /**
     * @param args the command line arguments
     */
	
	public static Affichage aff;
	public static Model model;
    public static void main(String[] args) {
        //pour test
        JFrame jf = new JFrame("test de projection d'un cube");
        
        model = new Model("x_wing.gts");
        Collections.sort(model.triangle);
        aff = new Affichage(model);
        jf.add(aff);
        jf.setVisible(true);
        jf.setSize(800, 600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }
}
