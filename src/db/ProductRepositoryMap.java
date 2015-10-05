package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Product;

public class ProductRepositoryMap implements ProductRepository {
	private Map<String, Product> Products = new HashMap<String, Product>();
	
	public ProductRepositoryMap () {
		add(new Product(
				"SneakerBlauwFredPerry", 
				"Sneaker Blauw Fred Perry", 
				86.35, 
				"https://10.assets.torfs.be/products/138495/sneaker-blauw-fred-perry-138495-zij-440x440-1423044002.jpg"
				));
		add(new Product(
				"TaupeSneakerFredPerry", 
				"Taupe Sneaker Fred Perry", 
				68.35, 
				"https://2.assets.torfs.be/products/155137/taupe-sneaker-fred-perry-155137-zij-440x440-1423198801.jpg"
				));
	}
	
	public Product get(String ProductId){
		if(ProductId == null){
			throw new IllegalArgumentException("No id given");
		}
		return Products.get(ProductId);
	}
	
	public List<Product> getAll(){
		return new ArrayList<Product>(Products.values());	
	}

	public void add(Product Product){
		if(Product == null){
			throw new IllegalArgumentException("No Product given");
		}
		if (Products.containsKey(Product.getId())) {
			throw new IllegalArgumentException("User already exists");
		}
		Products.put(Product.getId(), Product);
	}
	
	public void update(Product Product){
		if(Product == null){
			throw new IllegalArgumentException("No Product given");
		}
		Products.put(Product.getId(), Product);
	}
	
	public void delete(String ProductId){
		if(ProductId == null){
			throw new IllegalArgumentException("No id given");
		}
		Products.remove(ProductId);
	}
}
