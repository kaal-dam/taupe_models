package BDD;

import java.sql.*;
import java.util.List;
import java.util.Vector;

public class FiltreModels {

	Vector<String> list;

	public FiltreModels() {

		list = new Vector<String>();

		Connection c = null;
		Statement stmt = null;

		try {

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:model.db");
			c.setAutoCommit(false);
			String query = "";
			String arg[] = IHM.ListeModels.recherche.getText().split(" ");
			stmt = c.createStatement();
			query = "select distinct chemin from Modeles M, Association A "
					+ "where M.nom = A.nom ";
			for (int i = 0; i < arg.length; i++) {
				if ((i == 0 && arg.length > 1) || !arg[0].equals(""))
					query += "and (";
				if (!arg[i].equals("")) {
					query += "M.nom like \"%" + arg[i].toLowerCase() + "%\" "
							+ "or A.tag like \"%" + arg[i].toLowerCase() + "%\"";
					if (i < arg.length - 1)
						query += " or ";
					if (i == arg.length - 1)
						query += ")";
				}
			}
			query += ";";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				int i = 0;
				if (!list.isEmpty()) {
					while (i < list.size()
							&& list.get(i).compareTo(
									rs.getString("chemin").substring(8)) < 0)
						++i;
				}
				list.add(i, rs.getString("chemin"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		} finally {
			try {
				c.close();
			} catch (Exception e) {
			}
		}

	}

	public Vector<String> getList() {
		return list;
	}

	public String[] getArray() {
		String[] ret = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			ret[i] = list.get(i);
		}
		return ret;
	}

	public String[] getList1(int SelectedValue) {
		String res[];
		List<String> l = list.subList(0, SelectedValue + 1);

		res = new String[l.size()];
		for (int i = 0; i < res.length; ++i)
			res[i] = l.get(i);
		return res;
	}

	public String[] getList2(int SelectedValue) {
		String res[];
		List<String> l = list.subList(SelectedValue + 1, list.size());
		res = new String[l.size()];
		for (int i = 0; i < res.length; ++i)
			res[i] = l.get(i);
		return res;
	}

}
