package db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class RepositoryDBConnection {

	private static Connection connection;
	private RepositoryDBConnection(InputStream resourceAsStream){

	}
	public static synchronized Connection getConnection(InputStream resourceAsStream){
		// if we already have an active connection, return it
		if (RepositoryDBConnection.connection != null) {
			return RepositoryDBConnection.connection;
		}
		// otherwise initiate a new connection to the database
    	try {
    		Properties properties = new Properties();
    		// user, password and other properties are set in /WEB-INF/config.xml, 
    		// copy config.example.xml to config.xml and edit values
    		properties.loadFromXML(resourceAsStream);
			Class.forName("org.postgresql.Driver");
			RepositoryDBConnection.connection = DriverManager.getConnection(properties.getProperty("url"), properties);
			return RepositoryDBConnection.connection;
			// get schema from config and prepend table-var
		} catch (ClassNotFoundException e) {
			System.out.println("PostgreSQL JDBC Driver is missing");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL error");
			e.printStackTrace();
			throw new RuntimeException(e);
    	} catch (Exception e) {
			System.out.println("WTF error");
			e.printStackTrace();
    	}
    	return null;
	}
}
