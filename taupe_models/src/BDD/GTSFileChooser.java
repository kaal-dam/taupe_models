package BDD;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class GTSFileChooser extends JPanel implements ActionListener {

	private static final long	serialVersionUID	= 1L;
	

	JFileChooser				fc;

	public GTSFileChooser() {
		setSize(600, 600);
		setLayout(new FlowLayout());

		

		FileFilter filter = new FileNameExtensionFilter("GTS File", "gts");
		fc = new JFileChooser();
		fc.setFileFilter(filter);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		
			int result = fc.showDialog(GTSFileChooser.this, "Importer");
			
			

			if (result == JFileChooser.APPROVE_OPTION) {

				try {
					
					File filePath = fc.getSelectedFile();
					String s = "./model/" + filePath.getName();
					File fileDest = new File(s);
					fileDest.createNewFile();
					copyFile(filePath, fileDest);
					new TagInsertFile(new File(s));

				} catch (Exception sql) {
					System.err.println(sql.getClass().getName() + ": "
							+ sql.getMessage());
					System.exit(0);
				}
				finally {

				}

			}

			else if (result == JFileChooser.CANCEL_OPTION) {
				System.out.println("");
			}

			else if (result == JFileChooser.ERROR_OPTION) {
				System.out.println("");
			}
		
	}

	public static boolean copyFile(File source, File dest) {
		try {
			// Declaration et ouverture des flux
			java.io.FileInputStream sourceFile = new java.io.FileInputStream(source);

			try {
				java.io.FileOutputStream destinationFile = null;

				try {
					destinationFile = new FileOutputStream(dest);

					// Lecture par segment de 0.5Mo
					byte buffer[] = new byte[512 * 1024];
					int nbLecture;

					while ((nbLecture = sourceFile.read(buffer)) != -1) {
						destinationFile.write(buffer, 0, nbLecture);
					}
				}
				finally {
					destinationFile.close();
				}
			}
			finally {
				sourceFile.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false; // Erreur
		}

		return true; // Résultat OK
	}

}