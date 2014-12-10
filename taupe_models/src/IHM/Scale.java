package IHM;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import Main.MainClass;

public class Scale extends JMenuItem {
	JFrame jf = new JFrame();
	JTextField textLong;
	JTextField textLarg;
	JTextField texthaut;

	public Scale() {

		this.setText("Scale");

		JLabel largeur = new JLabel("largeur");
		JLabel longueur = new JLabel("longueur");
		JLabel hauteur = new JLabel("hauteur");
		JLabel unite = new JLabel("cm");

		textLong = new JTextField();
		textLarg = new JTextField();
		texthaut = new JTextField();

		JButton valid = new JButton("valider");
		valid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame error = new JFrame("error");
				error.add(new JLabel("ERROR ! CONNARD"));
				
				
				/*
				 * trouver moyen de trouver la distance max entre les deux
				 * extremité du modele en x,y,z et faire: saisieX/distX = coefX
				 * a refaire pour Y,Z
				 */
				float distX = MainClass.model.xMax - MainClass.model.xMin;
				float distY = MainClass.model.yMax - MainClass.model.yMin;
				float distZ = MainClass.model.zMax - MainClass.model.zMin;

				if (!textLong.getText().equals("")
						&& !textLarg.getText().equals("")
						&& !texthaut.getText().equals("")) {
					float saisieX = 0;
					float saisieY = 0;
					float saisieZ = 0;
					
					try {
						saisieX = Float.valueOf(textLong.getText());
						saisieY = Float.valueOf(textLarg.getText());
						saisieZ = Float.valueOf(texthaut.getText());
					} catch (Exception ex) {
						error.setVisible(true);
					}
				} else {
				}

				jf.setVisible(false);
			}
		});

		JButton cancel = new JButton("annuler");
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jf.setVisible(false);
			}
		});

		jf.setLayout(new GridLayout(4, 3));
		jf.add(longueur);
		jf.add(textLong);
		jf.add(new JLabel("cm"));
		jf.add(largeur);
		jf.add(textLarg);
		jf.add(new JLabel("cm"));
		jf.add(hauteur);
		jf.add(texthaut);
		jf.add(new JLabel("cm"));
		jf.add(cancel);
		jf.add(valid);
		jf.setSize(200, 100);
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jf.setVisible(true);
			}
		});
	}

}
