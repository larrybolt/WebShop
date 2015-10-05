package domain;
import java.util.List;

import db.ProductRepository;
import db.ProductRepositoryMap;

public class ProductService {
	private ProductRepository ProductRepository = new ProductRepositoryMap();

	public ProductService(){
	}
	
	public Product getProduct(String ProductId) {
		return getProductRepository().get(ProductId);
	}

	public List<Product> getProducts() {
		return getProductRepository().getAll();
	}

	public void addProduct(Product Product) {
		getProductRepository().add(Product);
	}

	public void updateProducts(Product Product) {
		getProductRepository().update(Product);
	}

	public void deleteProduct(String id) {
		getProductRepository().delete(id);
	}

	private ProductRepository getProductRepository() {
		return ProductRepository;
	}

}
