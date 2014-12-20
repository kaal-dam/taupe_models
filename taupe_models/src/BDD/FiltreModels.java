package BDD;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class FiltreModels {
	
	List<String> list = new ArrayList<String>();
	
	public FiltreModels() {
		
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
		    	System.out.println("Chemin : " + rs.getString("chemin"));
		    	list.add(rs.getString("chemin"));
		    }
	    	
	    }catch ( Exception e ) {
	        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	        System.exit(0);
	    }finally{
			try{c.close();}catch(Exception e){}
		}

	}
}
