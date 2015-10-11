package db;

import java.sql.Statement;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import domain.Product;

public class ProductRepositoryDB extends BaseRepositoryDB implements ProductRepository {
	
	public ProductRepositoryDB(InputStream resourceAsStream) {
		// config loading and opening connection is handled by BaseRepositoryDB
		super("products", resourceAsStream);
	}

	public Product get(int id){
		try {
			PreparedStatement statement = db.prepareStatement(
					String.format("SELECT * FROM %s WHERE id = ?", this.getTable())
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
			ResultSet results = statement.executeQuery(String.format("SELECT * FROM %s", this.getTable()));
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
					String.format("INSERT INTO %s (id, name, price, imgurl) VALUES (?,?,?,?) RETURNING id;", this.getTable())
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
					String.format("UPDATE %s SET name=?, price=?, imgurl=? WHERE id = ?", this.getTable())
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
					String.format("DELETE FROM %s WHERE id = ?", this.getTable())
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
