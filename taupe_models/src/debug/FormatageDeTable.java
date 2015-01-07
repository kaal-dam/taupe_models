package debug;

import java.sql.*;


public class FormatageDeTable {
	static String query = "";
	
	public static void main(String[] args){
		//lookALL();
		Connection c = null;
		try{
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:model.db");
		c.setAutoCommit(false);
		
		c.createStatement().executeUpdate("insert into tags values ('lol');");
		c.commit();
		lookALL();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{c.close();}catch(Exception e){}
		}
	}
	
	public static void formatAll(){
		Connection c = null;
		try{
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:model.db");
		c.setAutoCommit(false);
		
		query = "delete from modeles;";
		c.createStatement().executeUpdate(query);
		System.out.println("modele format");
		
		query = "delete from tags;";
		c.createStatement().executeUpdate(query);
		System.out.println("tags format");
		
		query = "delete from association;";
		c.createStatement().executeUpdate(query);
		System.out.println("asso. format");
		
		System.out.println("TABLE MODELES");
		query = "select * from modeles;";
		ResultSet rs = c.createStatement().executeQuery(query);
		ResultSetMetaData rm = rs.getMetaData();
		for(int i = 1; i < rm.getColumnCount(); i++){
			System.out.print(rm.getColumnLabel(i) + "|");
		}
		while(rs.next()){
			for(int i = 1; i < rm.getColumnCount(); i++){
				System.out.print(rs.getString(rm.getColumnLabel(i)) + "|");
			}
		}
		
		System.out.println("\nTABLE TAGS");
		query = "select * from tags;";
		rs = c.createStatement().executeQuery(query);
		rm = rs.getMetaData();
		for(int i = 1; i < rm.getColumnCount(); i++){
			System.out.print(rm.getColumnLabel(i) + "|");
		}
		while(rs.next()){
			for(int i = 1; i < rm.getColumnCount(); i++){
				System.out.print(rs.getString(rm.getColumnLabel(i)) + "|");
			}
		}
		
		System.out.println("\nTABLE ASSO.");
		query = "select * from association;";
		rs = c.createStatement().executeQuery(query);
		rm = rs.getMetaData();
		for(int i = 1; i < rm.getColumnCount(); i++){
			System.out.print(rm.getColumnLabel(i) + "|");
		}
		while(rs.next()){
			for(int i = 1; i < rm.getColumnCount(); i++){
				System.out.print(rs.getString(rm.getColumnLabel(i)) + "|");
			}
		}
		
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(query);
		}finally{
			try{c.close();}catch(Exception e){}
		}
	}

	public static void lookALL(){
		Connection c = null;
		try{
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:model.db");
		c.setAutoCommit(false);
		
		System.out.println("TABLE MODELES");
		query = "select * from modeles;";
		ResultSet rs = c.createStatement().executeQuery(query);
		ResultSetMetaData rm = rs.getMetaData();
		for(int i = 1; i <= rm.getColumnCount(); i++){
			System.out.print(rm.getColumnLabel(i) + "|");
		}
		System.out.println("");
		while(rs.next()){
			for(int i = 1; i <= rm.getColumnCount(); i++){
				System.out.print(rs.getString(i) + "|");
			}
		}
		
		System.out.println("\nTABLE TAGS");
		query = "select * from tags;";
		rs = c.createStatement().executeQuery(query);
		rm = rs.getMetaData();
		for(int i = 1; i <= rm.getColumnCount(); i++){
			System.out.print(rm.getColumnLabel(i) + "|");
		}
		System.out.println("");
		while(rs.next()){
			System.out.println(rs.getString("tag"));
		}
		
		System.out.println("\nTABLE ASSO.");
		query = "select * from association;";
		rs = c.createStatement().executeQuery(query);
		rm = rs.getMetaData();
		for(int i = 1; i <= rm.getColumnCount(); i++){
			System.out.print(rm.getColumnLabel(i) + "|");
		}
		System.out.println("");
		while(rs.next()){
			for(int i = 1; i <= rm.getColumnCount(); i++){
				System.out.print(rs.getString(i) + "|");
			}
			System.out.println("");
		}
		
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(query);
		}finally{
			try{c.close();}catch(Exception e){}
		}
	}
}
