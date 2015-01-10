package IHM;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
/**
 * fenetre affichant les informations sur le logiciel
 * @author _Flo
 *
 */
public class APropos extends JFrame implements WindowListener{
	
	/**
	 * permet de savoir si la fenetre est deja ouverte
	 */
	public static int opened = 0;
	
	/**
	 * constructeur de la fenetre
	 */
	public APropos() {

		this.setSize(800, 600);
		this.setTitle("A propos");
		this.add(new JLabel(new ImageIcon("./taupe_model_a_propos.png")));
		this.addWindowListener(this);
		this.setResizable(false);
		this.setVisible(true);
		
	}

	@Override
	/**
	 * NC
	 */
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * remet a 0 aProposOpened pour pouvoir le reouvrir 
	 */
	public void windowClosing(WindowEvent e) {
		Main.MainClass.aProposOpened = 0;	
	}

	@Override
	/**
	 * NC
	 */
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	/**
	 * NC
	 */
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * NC
	 */
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * NC
	 */
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * NC
	 */
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
