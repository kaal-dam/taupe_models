package BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Delete {
	public static boolean delete(String table, String field, String nom){
		Connection c = null;
		String query ="";
		ArrayList<String> list = new ArrayList<String>();
		try{
			
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:model.db");
			c.setAutoCommit(false);
			
			query = "delete from " + table + " where "+ field + "='" + nom + "';";
			boolean ret = c.createStatement().executeUpdate(query) > 0;
			c.close();
			
			return ret;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{c.close();}catch(Exception e1){}
		}
		return false;
	}
}