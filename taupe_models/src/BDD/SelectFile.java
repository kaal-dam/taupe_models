package BDD;


/**
 * 
 * @author toulousd
 * 
 * Classe permettant l'affichage du contenue de la db
 * 
 */

import java.sql.*;

public class SelectFile
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
      while ( rs.next() ) {
         String  name = rs.getString("name");
         String chemin  = rs.getString("chemin");
         String tag1 = rs.getString("tag1");;
         String tag2 = rs.getString("tag2");
         System.out.println( "NAME = " + name );
         System.out.println( "CHEMIN = " + chemin );
         System.out.println(" tag 1= "+ tag1);
         System.out.println(" tag 2= "+ tag2);
         
         System.out.println();
      }
      rs.close();
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Operation done successfully");
  }
}
