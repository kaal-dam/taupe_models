package IHM;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Main.MainClass;
import Tools.ToolBox;

import javax.swing.JCheckBox;

/**
 * 
 * J'ai toujours pas compris le sens et l'intérêt de cette classe, demander à Sicchio :D
 *
 */
@SuppressWarnings("serial")
public class Scale extends JFrame implements ActionListener {

    JTextField textLong;
    JTextField textLarg;
    JTextField texthaut;
    JCheckBox check;

    /**
     * Contructeur de Scale.
     */
    public Scale() {

        this.setTitle("Scale");

        JLabel largeur = new JLabel("largeur");
        JLabel longueur = new JLabel("longueur");
        JLabel hauteur = new JLabel("hauteur");

        textLong = new JTextField();
        textLarg = new JTextField();
        texthaut = new JTextField();

        check = new JCheckBox("Mise a l'echelle homogene");
        check.setSelected(true);

        JButton valid = new JButton("Valider");
        valid.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame error = new JFrame("error");
                error.setLayout(new GridLayout(2, 1));
                error.setSize(100, 100);
                /* Sicchio tu te calmes sur les erreurs voyons */
                error.add(new JLabel("ERROR ! CONNARD"));


                /**
                 * trouver moyen de trouver la distance max entre les deux
                 * extremitï¿½ du modele en x,y,z et faire: saisieX/distX = coefX
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
                        if (saisieX < 0 || saisieY < 0 || saisieZ < 0) {
                            throw new Exception("saisie de nb negatif");
                        }
                        if (check.isSelected()) {
                            if (saisieX / distX != ToolBox.coefX) {
                                ToolBox.coefX = saisieX / distX;
                                ToolBox.coefY = saisieX / distX;
                                ToolBox.coefZ = saisieX / distX;
                            } else if (saisieY / distY != ToolBox.coefY) {
                                ToolBox.coefX = saisieY / distY;
                                ToolBox.coefY = saisieY / distY;
                                ToolBox.coefZ = saisieY / distY;
                            } else if (saisieZ / distZ != ToolBox.coefZ) {
                                ToolBox.coefX = saisieZ / distZ;
                                ToolBox.coefY = saisieZ / distZ;
                                ToolBox.coefZ = saisieZ / distZ;
                            }
                        } else {
                            ToolBox.coefX = saisieX / distX;
                            ToolBox.coefY = saisieY / distY;
                            ToolBox.coefZ = saisieZ / distZ;
                        }
                    } catch (Exception ex) {
                        error.add(new JLabel(ex.getMessage()));
                        error.setVisible(true);
                    }
                } else {
                }
                dispose();
            }
        });

        JButton cancel = new JButton("annuler");
        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setLayout(new GridLayout(4, 3));
        add(longueur);
        add(textLong);
        add(new JLabel("cm"));
        add(largeur);
        add(textLarg);
        add(new JLabel("cm"));
        add(hauteur);
        add(texthaut);
        add(new JLabel("cm"));
        add(cancel);
        add(valid);
        add(check);
        setSize(200, 100);



        
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		textLong.setText((MainClass.model.xMax - MainClass.model.xMin) * ToolBox.coefX + "");
        textLarg.setText((MainClass.model.yMax - MainClass.model.yMin) * ToolBox.coefY + "");
        texthaut.setText((MainClass.model.zMax - MainClass.model.zMin) * ToolBox.coefZ + "");
		this.setVisible(true);
	}
}
