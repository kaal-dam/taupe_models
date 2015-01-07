package BDD;


/**
 * 
 * @author toulousd
 * 
 * Classe permettant l'affichage du contenue de la db
 * 
 */

import java.sql.*;

public class SelectALL
{
  public static void main( String args[] )
  {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:model.db");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT * FROM Modeles;" );
      System.out.println("Modeles:");
      while ( rs.next() ) {
         String  name = rs.getString("nom");
         String chemin  = rs.getString("chemin");
         System.out.println( "NAME = " + name );
         System.out.println( "CHEMIN = " + chemin );
         
         System.out.println();
      }
      rs.close();
      rs = stmt.executeQuery( "SELECT * FROM Tags;" );
      System.out.println("Tags:");
      while ( rs.next() ) {
         String  tag = rs.getString("tag");
         System.out.println( "TAG = " + tag );
         
         System.out.println();
      }
      rs = stmt.executeQuery( "SELECT * FROM Association;" );
      System.out.println("Association:");
      while ( rs.next() ) {
         String  name = rs.getString("nom");
         String tag  = rs.getString("tag");
         System.out.println( "NAME = " + name );
         System.out.println( "TAG = " + tag );
         
         System.out.println();
      }
      rs.close();
      rs = stmt.executeQuery( "SELECT nom FROM Association WHERE tag='lapin';" );
      while ( rs.next() ) {
         String  name = rs.getString("nom");
         System.out.println( "NAME = " + name );
         
         System.out.println();
      }
      rs.close();
      rs.close();
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }finally{
		try{c.close();}catch(Exception e2){}
	}
    System.out.println("Operation done successfully");
  }
}
