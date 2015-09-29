import java.sql.*;
import java.util.Properties;

public class Test {

	public void main(String[] args){
		Properties properties = new Properties();
		String url = "jdbc:postgresql://gegevensbanken.khleuven.be:51415/webontwerp";
		properties.setProperty("user", "r0296766");
		properties.setProperty("password", "");
		properties.setProperty("ssl", "true");
		properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
		Connection connection;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, properties);
			//Statement statement = connection.createStatement();
			Statement q;
			//q =  connection.prepareStatement("select * from test;");
			//ResultSet result = q.execute
			ResultSet r =  connection.createStatement().executeQuery("select * from test");
			r.last();
			System.out.println("results "+r.getRow());
			r.first();
			while (r.next()) {
				System.out.println("name: "+r.getString("name"));
			}
			
			
		} catch (SQLException e) {
			//throw new DbException(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			//throw new DbException(e.getMessage(), e);
		}
	}
}
