package BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 * Classe de selection en bdd
 * 
 * @author tanre
 */
public class Select {
	/**
	 * Selection en bdd
	 * 
	 * @param table
	 *            table ou effectuer la recherche
	 * @param field
	 *            champ a rechercher
	 * @return true si reussi false sinon
	 */
	public static String[] select(String table, String field) {
		Connection c = null;
		String query = "";
		ArrayList<String> list = new ArrayList<String>();
		try {

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:model.db");
			c.setAutoCommit(false);

			query = "select " + field + " from " + table + ";";
			ResultSet rs = c.createStatement().executeQuery(query);
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			c.close();
			String[] ret = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				ret[i] = list.get(i);
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				c.close();
			} catch (Exception e1) {}
		}
		return null;
	}

	/**
	 * Recuperation des tag d'un modele
	 * 
	 * @param model
	 *            modele dont on veut recuperer les champs
	 * @return un tableau des tag du modele si reussi null sinon
	 */
	public static String[] getTagof(String model) {
		Connection c = null;
		String query = "";
		ArrayList<String> list = new ArrayList<String>();

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:model.db");
			c.setAutoCommit(false);
			query = "select tag from association where nom='" + model + "';";
			ResultSet rs = c.createStatement().executeQuery(query);
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			c.close();
			String[] ret = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				ret[i] = list.get(i);
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				c.close();
			} catch (Exception e1) {}
		}
		return null;
	}
}
