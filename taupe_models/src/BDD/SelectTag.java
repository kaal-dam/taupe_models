package BDD;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectTag {
	List<String> list = new ArrayList<String>();
	
	public List<String> GetTag(String str) {
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:model.db");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      
	      
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM tag;" );
	      
	      
	      
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
