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
					+"(NOM			TEXT NOT NULL	PRIMARY KEY,"
					+"CHEMIN           TEXT    NOT NULL)";
					/*+"PHOTO			TEXT,"
					+"DESCRIPTION	TEXT)";*/
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE Tags "
					+"(TAG			TEXT NOT NULL	PRIMARY KEY)";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE Association "
					+"(NOM			TEXT NOT NULL,"
					+"TAG           TEXT    NOT NULL,"
					+"PRIMARY KEY(nom,tag),"
					+"FOREIGN KEY (nom) REFERENCES Modeles(nom),"
					+"FOREIGN KEY (tag) REFERENCES Tags(tag))";
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