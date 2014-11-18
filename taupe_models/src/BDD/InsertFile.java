package BDD;



import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;


public class InsertFile extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	JButton bt1;
	JButton bt2;
	JFileChooser fc;

	public InsertFile() {
		setSize(600, 600);
		setLayout(new FlowLayout());
		
		bt1 = new JButton("Save File FileChooser");

		bt1.addActionListener(this);
		

		add(bt1);
		

		fc = new JFileChooser();

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == bt1) {
			int result = fc.showSaveDialog(InsertFile.this);

			if (result == JFileChooser.APPROVE_OPTION) {
				System.out.println("");
				Connection con=null;
				try {
					
					

					File filePath = fc.getSelectedFile();	
					String s="~/model/"+filePath.getName();
					File fileDest = new File(s);
					fileDest.createNewFile();
					copyFile(filePath,fileDest);
					
					con = DriverManager.getConnection("~/model.db","","");

					

				
				} catch (Exception sql){
					System.out.println(sql);
				} finally{
					try{con.close();}catch(Exception sql2){};
				}
				
			}
			

			else if (result == JFileChooser.CANCEL_OPTION) {
				System.out.println("");
			}

			else if (result == JFileChooser.ERROR_OPTION) {
				System.out.println("");
			}
		}
	}
	
	public static boolean copyFile(File source, File dest){
		try{
			// Declaration et ouverture des flux
			java.io.FileInputStream sourceFile = new java.io.FileInputStream(source);
	 
			try{
				java.io.FileOutputStream destinationFile = null;
	 
				try{
					destinationFile = new FileOutputStream(dest);
	 
					// Lecture par segment de 0.5Mo 
					byte buffer[] = new byte[512 * 1024];
					int nbLecture;
	 
					while ((nbLecture = sourceFile.read(buffer)) != -1){
						destinationFile.write(buffer, 0, nbLecture);
					}
				} finally {
					destinationFile.close();
				}
			} finally {
				sourceFile.close();
			}
		} catch (IOException e){
			e.printStackTrace();
			return false; // Erreur
		}
	 
		return true; // Résultat OK  
	}

}