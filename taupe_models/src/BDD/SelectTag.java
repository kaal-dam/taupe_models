package BDD;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectTag {
	static List<String> list;
	
	/* Tous les tags */
	public List<String> GetAllTags() {
		
		list = new ArrayList<String>();
		
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:model.db");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      
	      
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM tags;" );
	      
	      
	      
	      while ( rs.next() ) {
	         
	         String tag  = rs.getString("tag");
	         list.add(tag);
	      }
	      rs.close();
	      
	    } catch ( Exception e ) {
	        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	        System.exit(0);
	    } finally{
			try{c.close();}catch(Exception e2){}
		}
		
		return list;
		
	}
	
	/* Tous les tags d'un model */
	public static List<String> GetTags(String nom) {
		
		list = new ArrayList<String>();
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:model.db");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      
	      
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM association where nom = \"" + nom + "\";" );
	      
	      
	      
	      while ( rs.next() ) {
	         
	         String tag  = rs.getString("tag");
	         list.add(tag);
	      }
	      rs.close();
	      
	    } catch ( Exception e ) {
	        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	        System.exit(0);
	    } finally{
			try{c.close();}catch(Exception e2){}
		}
		
		return list;
		
	}
}
