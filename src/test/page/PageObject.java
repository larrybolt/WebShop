package test.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class PageObject {

	private WebDriver driver;

	protected static final String BASEURL = "http://localhost:8080/WebShop";
	protected String pageUrl;

	public PageObject(WebDriver driver, String pageUrl){
		setDriver(driver);
		setPageUrl(pageUrl);
	}
	
	public void loadPage(){
		getDriver().get(BASEURL+getPageUrl());
	}

	protected void fillOutFieldById(String name,String value) {
		WebElement field = driver.findElement(By.id(name));
		field.clear();
		field.sendKeys(value);
	}
	
	protected WebElement getElementById(String id) {
		return driver.findElement(By.id(id));
	}
	
	public String getTitle(){
		return getDriver().getTitle();
	}

	public boolean isCorrectPage(){
		String expectedUrl = BASEURL+getPageUrl();
		String currentUrl = getDriver().getCurrentUrl();
		return currentUrl.equals(expectedUrl);
	}

	public WebDriver getDriver() {
		return driver;
	}
	protected void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	protected void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	
	public String getInputValueById(String inputId){
		return getDriver().findElement(By.id(inputId)).getAttribute("value");
	}
}