package test.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddProductPage extends PageObject {

	public AddProductPage(WebDriver driver){
		super(driver, "/Controller?action=addProduct");
	}
	
	public void setName(String name){
		fillOutFieldById("name", name);
	}
	public void setPrice(String price){
		fillOutFieldById("price", price);
	}
	public void setImgUrl(String imgUrl){
		fillOutFieldById("ImgUrl", imgUrl);
	}

	public void confirm() {
		getElementById("addProduct").click();
	}
	
	public String getErrorMessage(){
		return getDriver().findElement(By.cssSelector("div.alert-danger ul li")).getText();
	}
}