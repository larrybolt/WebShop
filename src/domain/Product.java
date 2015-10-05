package domain;

public class Product {
	private String id;
	private String description;
	private double price;
	private String imgUrl;
	
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Product(String id, String description, double price, String imgUrl){
		this.setId(id);
		this.setDescription(description);
		this.setPrice(price);
		this.setImgUrl(imgUrl);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
