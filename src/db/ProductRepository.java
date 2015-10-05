package db;
import java.util.List;
import domain.Product;

public interface ProductRepository {
		
		public Product get(String personId);
		public List<Product> getAll();
		public void add(Product person);
		public void update(Product person);
		public void delete(String productId);
	
}