package BDD;

import java.sql.*;
import java.util.Vector;

import javax.swing.JList;

public class FiltreModels {
	
	Vector<Object> list;
	
	public FiltreModels() {
		
		list = new Vector();
		
		Connection c = null;
	    Statement stmt = null;
	    
	    try {
	    	
	    	Class.forName("org.sqlite.JDBC");
		    c = DriverManager.getConnection("jdbc:sqlite:model.db");
		    c.setAutoCommit(false);
		    stmt = c.createStatement();
		    String query = "select distinct chemin from Modeles M, Association A " +
		    				"where M.nom = A.nom and " +
		    					"(M.nom like \"%" + IHM.ListeModels.recherche.getText() + "%\" " +
		    						"or A.tag like \"%" + IHM.ListeModels.recherche.getText() + "%\");";
		    
		    ResultSet rs = stmt.executeQuery(query);
		    
		    while(rs.next()){
		    	System.out.println("Chemin : " + rs.getString("chemin").substring(8));
		    	list.add(rs.getString("chemin").substring(8));
		    }
	    	
	    }catch ( Exception e ) {
	        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	        System.exit(0);
	    }finally{
			try{c.close();}catch(Exception e){}
		}

	}
	
	public Vector<Object> getList() {
		return list;
	}
	
}
