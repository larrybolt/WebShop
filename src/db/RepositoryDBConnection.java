package db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class RepositoryDBConnection {

	private Connection connection;
	private String schema;
	private static RepositoryDBConnection instance;
	private RepositoryDBConnection(Connection connection, String schema){
		this.connection = connection;
		this.schema = schema;
	}
	public static synchronized RepositoryDBConnection getInstance(InputStream resourceAsStream){
		// if we already have an active connection, return it
		if (RepositoryDBConnection.instance != null) {
			return RepositoryDBConnection.instance;
		}
		// otherwise initiate a new connection to the database
    	try {
    		Properties properties = new Properties();
    		// user, password and other properties are set in /WEB-INF/config.xml, 
    		// copy config.example.xml to config.xml and edit values
    		properties.loadFromXML(resourceAsStream);
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
			String schema = properties.getProperty("schema");
    		RepositoryDBConnection.instance = new RepositoryDBConnection(connection, schema);
			// get schema from config and prepend table-var
		} catch (ClassNotFoundException e) {
			System.out.println("PostgreSQL JDBC Driver is missing");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL error");
			e.printStackTrace();
			throw new RuntimeException(e);
    	} catch (Exception e) {
			System.out.println("WTF error in singleton");
			e.printStackTrace();
    	}
    	return RepositoryDBConnection.instance;
	}
	public Connection getConnection(){
		return this.connection;
	}
	public String getSchema(){
		return this.schema;
	}
}
