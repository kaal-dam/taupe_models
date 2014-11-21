package BDD;

import java.sql.*;

public class DropTable
{
  public static void main( String args[] )
  {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:model.db");
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      String sql = "DROP TABLE Modeles";
      stmt.executeUpdate(sql);
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Table dropped successfully");
  }
}