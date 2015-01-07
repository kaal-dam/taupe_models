package BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import Main.MainClass;

public class Add {
	public static boolean toTag(String t, String nom) {
		Connection c = null;
		String query = "";
		try {

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:model.db");
			c.setAutoCommit(false);

			query = "INSERT INTO Tags values ('" + t + "');";
			boolean ret1 = c.createStatement().executeUpdate(query) > 0;
			query = "INSERT INTO Association values ('" + nom + "','" + t
					+ "');";
			boolean ret2 = c.createStatement().executeUpdate(query) > 0;
			c.commit();
			c.close();

			return ret1 && ret2;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				c.close();
			} catch (Exception e1) {
			}
		}
		return false;
	}

	public static boolean toModeles(String nom, String path, String desc) {
		Connection c = null;
		String query = "";
		try {

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:model.db");
			c.setAutoCommit(false);

			query = "INSERT INTO Modeles VALUES ('" + nom + "','" + path
					+ "','" + desc + "');";
			boolean ret1 = c.createStatement().executeUpdate(query) > 0;
			c.commit();
			c.close();

			return ret1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				c.close();
			} catch (Exception e1) {
			}
		}
		return false;
	}
}
