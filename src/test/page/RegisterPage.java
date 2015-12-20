package test.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage extends PageObject {

	public RegisterPage(WebDriver driver){
		super(driver, "/Controller?action=addPerson");
	}
	
	public void setFirstName(String firstName){
		fillOutFieldById("firstName", firstName);
	}
	public void setLastName(String lastName){
		fillOutFieldById("lastName", lastName);
	}
	public void setEmail(String email){
		fillOutFieldById("email", email);
	}
	public void setPassword(String password){
		fillOutFieldById("password", password);
	}
	public void setWoonplaats(String woonplaats) {
		fillOutFieldById("woonplaats", woonplaats);
	}
	/**
	 * I don't agree returning the HomePage here
	 * If the form isn't correct we stay at the page,
	 * it's trivial to just create a new OverviewPage-object instead
	 * and check the isCorrectPage method
	 */
	public void confirm() {
		WebElement signUpButton = getDriver().findElement(By.id("signUp"));
		signUpButton.click();
	}
	
	public String getErrorMessage(){
		return getDriver().findElement(By.cssSelector("div.alert-danger ul li")).getText();
	}
}