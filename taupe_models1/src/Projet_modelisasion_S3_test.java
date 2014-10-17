

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JFrame;

public class Projet_modelisasion_S3_test{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //pour test
        JFrame jf = new JFrame("test de projection d'un cube");
        
        Model model = Analyseur.Analyse("library/model");
        Collections.sort(model.triangle);
        Affichage aff = new Affichage(model);
        jf.add(aff);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
