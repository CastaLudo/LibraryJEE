package fr.afpa.jee.libraryJEE.DBconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoConnect {
	
	static String url = "jdbc:mysql://localhost:3306/library";
	static String user = "root";
	static String password = "toor";
	static Connection connection = null;
	
	public static Connection DaoConnecting() {
		//Connection connection = null;
		try {
			//chargement du driver
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		try {
			connection = DriverManager.getConnection(url, user, password);
		}
		catch ( SQLException e ) {
		    /* Gérer les éventuelles erreurs ici */
		} 
		return connection;
	}
	
	public static void DaoDisconnect(Connection connection) {
		try {
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
