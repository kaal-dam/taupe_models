package BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class CreateTable {
	public static void main(String args[]) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:model.db");
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE Modeles "
					+ "(NAME           TEXT    NOT NULL, "
					+ " CHEMIN			TEXT	NOT NULL," + " TAG1 TEXT NOT NULL,"
					+ " TAG2 TEXT NOT NULL)";
			stmt.executeUpdate(sql);
			stmt.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());

		}
		finally {
			try {
				c.close();
			} catch (Exception e2) {}
		}
		System.out.println("Table created successfully");
	}
}