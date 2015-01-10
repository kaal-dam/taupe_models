package BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;


/**
 * classe de supression généralisé
 * 
 * 
 */
public class Delete {
	/**
	 * supression de certain champs dans la bdd
	 * 
	 * @param table
	 *            table ou aura lieu la suppression
	 * @param field
	 *            champs a tester
	 * @param nom
	 *            ce qui doit etre supprimer
	 * @return
	 */
	public static boolean delete(String table, String field, String nom) {
		Connection c = null;
		String query = "";
		ArrayList<String> list = new ArrayList<String>();
		try {

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:model.db");
			c.setAutoCommit(false);

			query = "DELETE FROM " + table + " WHERE " + field + "='" + nom
					+ "';";
			System.out.println(query);
			boolean ret = c.createStatement().executeUpdate(query) > 0;
			c.commit();
			c.close();
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				c.close();
			} catch (Exception e1) {}
		}
		return false;
	}
}