package IHM;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.Projet_modelisasion_S3_test;

@SuppressWarnings("serial")
public class Infos extends JPanel {
	
	JLabel text;
	
	public Infos() {
		
		text = new JLabel(((Integer) (Projet_modelisasion_S3_test.model.nbPts)).toString()+" points / "+
						((Integer) (Projet_modelisasion_S3_test.model.nbSeg)).toString()+" segments / "+
						((Integer) (Projet_modelisasion_S3_test.model.nbTri)).toString()+" triangles");
		
		add(text);
		text.setForeground(Color.white);
		this.setBackground(Color.black);
		
	}

}
