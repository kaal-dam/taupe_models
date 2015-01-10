package IHM;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;

/**
 * ouvre un JColorChooser pour regler la couleur du modele
 * @author _Flo
 *
 */
public class ColorChoice extends JFrame {
	/**
	 * Jcolor chooser
	 */
	private JColorChooser jcc;

	/**
	 * Construis la fenetre contenant le JcolorChooser
	 */
	public ColorChoice() {
		// TODO Auto-generated constructor stub
		this.setLayout(new FlowLayout());
		jcc = new JColorChooser();
		JButton accept = new JButton("accept");
		accept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Tools.ToolBox.defaultColor = jcc.getColor();
				System.out.println(Tools.ToolBox.defaultColor.toString());
			}
		});
		
		this.add(jcc);
		this.add(accept);
		this.setSize(675, 450);
		this.setVisible(true);
	}
}
