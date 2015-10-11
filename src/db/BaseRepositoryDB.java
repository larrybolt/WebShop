package db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class BaseRepositoryDB {

	private String table;
	private Properties properties;
	protected Connection db;
	protected int last_insert_id;

	public BaseRepositoryDB(String table, InputStream resourceAsStream){
		this.table = table;
    	try {
    		properties = new Properties();
    		// user, password and other properties are set in /WEB-INF/config.xml, 
    		// copy config.example.xml to config.xml and edit values
    		properties.loadFromXML(resourceAsStream);
			Class.forName("org.postgresql.Driver");
			db = DriverManager.getConnection(properties.getProperty("url"), properties);
			// get schema from config and prepend table-var
			this.table = properties.getProperty("schema") + "." + this.table;
			// run a test query, to make sure it works
			// also get the biggest id
			ResultSet result = db.createStatement().executeQuery(String.format(
					"SELECT max(id) as lastid FROM %s", 
					this.table
			));
			result.next();
			this.last_insert_id = result.getInt("lastid");
		} catch (ClassNotFoundException e) {
			System.out.println("PostgreSQL JDBC Driver is missing");
			e.printStackTrace();
			return;
		} catch (SQLException e) {
			System.out.println("SQL error");
			e.printStackTrace();
			throw new RuntimeException(e);
    	} catch (Exception e) {
			System.out.println("WTF error");
			e.printStackTrace();
    	}
	}

	protected String getTable() {
		return table;
	}

	protected void setTable(String table) {
		this.table = table;
	}
}
