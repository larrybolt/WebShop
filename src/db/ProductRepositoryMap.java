package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Product;

public class ProductRepositoryMap implements ProductRepository {
	private Map<Integer, Product> Products = new HashMap<Integer, Product>();
	
	public ProductRepositoryMap () {
		add(new Product(
				1,
				"Sneaker Blauw Fred Perry",
				86.35, 
				"https://10.assets.torfs.be/products/138495/sneaker-blauw-fred-perry-138495-zij-440x440-1423044002.jpg"
				));
		add(new Product(
				2,
				"Taupe Sneaker Fred Perry",
				68.35, 
				"https://2.assets.torfs.be/products/155137/taupe-sneaker-fred-perry-155137-zij-440x440-1423198801.jpg"
				));
		add(new Product(
				3,
				"Cognac Enkellaars Tommy Hilfiger",
				180.00, 
				"https://www.torfs.be/cognac-tommy-hilfiger-enkellaars-147514"
				));
		
	}
	
	public Product get(int id){
		return Products.get(id);
	}
	
	public List<Product> getAll(){
		return new ArrayList<Product>(Products.values());	
	}

	public void add(Product product){
		if(product == null){
			throw new IllegalArgumentException("No Product given");
		}
		if (Products.containsKey(product.getId())) {
			throw new IllegalArgumentException("Product already exists");
		}
		Products.put(product.getId(), product);
	}
	
	public void update(Product product){
		if(product == null){
			throw new IllegalArgumentException("No Product given");
		}
		Products.put(product.getId(), product);
	}
	
	public void delete(int id){
		Products.remove(id);
	}

	@Override
	public int generateNewId() {
		return this.Products.size()+1;
	}
}
