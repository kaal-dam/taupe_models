package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;

import Affichage.Model;
import Main.MainClass;

public class ListeModels extends JPanel {
	
	JComboBox tags;
	JList liste;
	
	public ListeModels() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			
		tags = new JComboBox(new String[]{"<aucun tag>"});
		tags.setMaximumSize(tags.getPreferredSize());
		add(tags);
		
		liste = new JList(new File("./model").list());
		add(liste);
		
		this.setBackground(Color.white);
		
	}

}
