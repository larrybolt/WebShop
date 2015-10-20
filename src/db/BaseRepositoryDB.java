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
    		// we cache the db-connection
			db = RepositoryDBConnection.getInstance(resourceAsStream).getConnection();
			// get schema from config and prepend table-var
			this.table = RepositoryDBConnection.getInstance(resourceAsStream).getSchema() + "." + this.table;
			// run a test query, to make sure it works
			// also get the biggest id
			ResultSet result = db.createStatement().executeQuery(String.format(
					"SELECT max(id) as lastid FROM %s", 
					this.table
			));
			result.next();
			this.last_insert_id = result.getInt("lastid");
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
