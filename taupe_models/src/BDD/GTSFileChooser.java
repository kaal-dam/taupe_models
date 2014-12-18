package BDD;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GTSFileChooser extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    JFileChooser fc;
    File filePath;
    JFrame jf;

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
                filePath = fc.getSelectedFile();
                Main.MainClass.loadModel(filePath.getName());
                /**
                 * si on est sur de vouloir add
                 */
                jf = new JFrame("accepter le model ?");
                jf.setLayout(new GridLayout(2,1));
                
                JButton valid = new JButton("ajouter");
                valid.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nomFile = "./model/" + filePath.getName();
                        File fileDest = new File(nomFile);
                        try {
                            fileDest.createNewFile();
                            copyFile(filePath, fileDest);
                            new TagInsertFile(new File(nomFile));
                        } catch (IOException patate) {
                            patate.printStackTrace();
                        }finally{
                            jf.dispose();
                        }
                    }
                });
                JButton annul = new JButton("annuler");
                annul.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jf.dispose();
                    }
                });
                
                
                JPanel pan1 = new JPanel();
                pan1.setLayout(new FlowLayout());
                pan1.add(new JLabel("ajouter ce model a la bdd ?"));
                
                JPanel pan2 = new JPanel(new FlowLayout());
                pan2.add(annul);
                pan2.add(valid);
                
                jf.add(pan1);
                jf.add(pan2);
                jf.setSize(300, 100);
                jf.setVisible(true);

            } catch (Exception sql) {
                System.err.println(sql.getClass().getName() + ": "
                        + sql.getMessage());
                System.exit(0);
            } finally {

            }

        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("");
        } else if (result == JFileChooser.ERROR_OPTION) {
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
                } finally {
                    destinationFile.close();
                }
            } finally {
                sourceFile.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Erreur
        }

        return true; // Résultat OK
    }

}
