package db;

import java.sql.Statement;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.Product;

public class ProductRepositoryDB implements ProductRepository {
	
	private Connection db;
	private String table = "products";
	Properties properties;
	private int last_insert_id;

	public ProductRepositoryDB(InputStream resourceAsStream) {
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
			System.out.println("last id:"+ last_insert_id);
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

	public Product get(int id){
		try {
			PreparedStatement statement = db.prepareStatement(
					String.format("SELECT * FROM %s WHERE id = ?", this.table)
			);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			result.next();
			return new Product(
					id, 
					result.getString("name"), 
					result.getDouble("price"), 
					result.getString("imgurl")
			);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public List<Product> getAll(){
		List<Product> products = new ArrayList<>();
		try {
			Statement statement = db.createStatement();
			ResultSet results = statement.executeQuery(String.format("SELECT * FROM %s", this.table));
			while(results.next()){
				products.add(
				  new Product(
					results.getInt("id"), 
					results.getString("name"), 
					results.getDouble("price"), 
					results.getString("imgurl") 
				  )
				);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return products;
	}

	public void add(Product product){
		if(product == null){
			throw new IllegalArgumentException("No Product given");
		}
		try {
			PreparedStatement statement = db.prepareStatement(
					String.format("INSERT INTO %s (id, name, price, imgurl) VALUES (?,?,?,?) RETURNING id;", this.table)
			);
			statement.setInt(1, product.getId());
			statement.setString(2, product.getName());
			statement.setDouble(3, product.getPrice());
			statement.setString(4, product.getImgUrl());
			ResultSet result = statement.executeQuery();
			result.next();
			this.last_insert_id = result.getInt("id");
			System.out.println("last id:"+ last_insert_id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void update(Product product){
		if(product == null){
			throw new IllegalArgumentException("No Product given");
		}
		try {
			PreparedStatement statement = db.prepareStatement(
					String.format("UPDATE %s SET name=?, price=?, imgurl=? WHERE id = ?", this.table)
			);
			statement.setString(1, product.getName());
			statement.setDouble(2, product.getPrice());
			statement.setString(3, product.getImgUrl());
			statement.setInt(4, product.getId());
			int rowsAffected = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void delete(int id){
		try {
			PreparedStatement statement = db.prepareStatement(
					String.format("DELETE FROM %s WHERE id = ?", this.table)
			);
			statement.setInt(1, id);
			int rowsAffected = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public int generateNewId() {
		return this.last_insert_id+1;
	}
}
