package IHM;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class Scaling extends JMenuItem {
	/**
	 * permet d'ouvrir une fenetre qui permettra de setter la taille global du
	 * modele -settera une variable de scale dans le main ou dans la toolbox...
	 * 
	 * +----------------------+ | | | longeur [_______]cm | | largeur
	 * [_______]cm | | hauteur [_______]cm | +----------------------+
	 */

	

	public Scaling() {
		super(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame f = new JFrame();
				f.setSize(200, 150);

				JTextField longueur = new JTextField("0");
				JTextField largeur = new JTextField("0");
				JTextField hauteur = new JTextField("0");
				JButton valid = new JButton("ok");

				f.setLayout(new GridLayout(4, 2));

				f.add(new JLabel("longeur:"));
				f.add(longueur);
				f.add(new JLabel("cm"));
				f.add(new JLabel("largeur:"));
				f.add(largeur);
				f.add(new JLabel("cm"));
				f.add(new JLabel("hauteur:"));
				f.add(hauteur);
				f.add(new JLabel("cm"));
				f.add(valid);

				f.setVisible(true);
			}
		});
		this.setText("scale");
		

	}
}
