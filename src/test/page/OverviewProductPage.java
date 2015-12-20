package test.page;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OverviewProductPage extends PageObject {

	public OverviewProductPage(WebDriver driver) {
		super(driver, "/Controller?action=products");
	}
	
	public ArrayList<WebElement> getPersonRows(){
		return (ArrayList<WebElement>) getDriver().findElements(By.cssSelector("table tr"));
	}
}