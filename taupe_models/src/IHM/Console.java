package IHM;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class Console extends JPanel {

	private JPanel contenu;
	private JTabbedPane console;
	private JTextField saisie;
	private JTextPane affichage;
	
	public Console(int width, int height) {
		
		console = new JTabbedPane();
		
		contenu = new JPanel();
		
		saisie = new JTextField();
		saisie.setSize(saisie.getMaximumSize().width, saisie.getPreferredSize().height);
		contenu.add(saisie, BorderLayout.SOUTH);
		
		affichage = new JTextPane();
		contenu.add(affichage);
		
		contenu.setSize(width, height);
		
		console.addTab("Console", contenu);
		
		this.add(console);
		this.setVisible(true);
	}
	
}
