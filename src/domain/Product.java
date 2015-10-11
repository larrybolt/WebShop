package domain;

public class Product {
	private int id;
	private String name;
	private double price;
	private String imgUrl;
	
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Product(int id, String name, double price, String imgUrl){
		this(name, price, imgUrl);
		this.setId(id);
	}
	public Product(String name, double price, String imgUrl){
		this.setName(name);
		this.setPrice(price);
		this.setImgUrl(imgUrl);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}