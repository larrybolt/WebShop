package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import test.page.*;

public class RegisterTest {
	private WebDriver driver;
	private RegisterPage page;
	
	@BeforeClass
    public static void oneTimeSetUp() {
		System.setProperty("webdriver.chrome.driver", "/Users/larrybolt/Downloads/chromedriver");
	}

	@Before
    public void setup() {
		driver = new ChromeDriver();
		page = new RegisterPage(driver);
	}
	
	@After
	public void clean() {
		driver.quit();
	}
	
	private String generateRandomEmail(String component) {
		// generate random email adres in order to run test more than once
		int random = (int)(Math. random() * 1000 + 1);
		return random+component;
	}
	
	private void submitForm(String firstName,String lastName, String email, String password, String woonplaats) {
		page.loadPage();
		page.setFirstName(firstName);
		page.setLastName(lastName);
		page.setEmail(email);
		page.setPassword(password);
		page.setWoonplaats(woonplaats);
		page.confirm();
	}

	@Test
	public void testRegisterCorrect() {
		// generate a random email address
		String randomEmail = generateRandomEmail("jan.janssens@hotmail.com");
		// other person details
		String firstName = "Jan";
		String lastName = "Janssens";
		String password = "password";
		String woonplaats = "Leuven";
		// submit form, after submission we should be at the homepage
		submitForm(firstName, lastName, randomEmail, password, woonplaats);
		HomePage homePage = new HomePage(driver);
		// check if the returned page is the homepage
		assertTrue("After submitting we should be on the homePage", homePage.isCorrectPage());
		
		// Check on overview if person is added to overview
		OverviewPersonPage overviewPersonPage = new OverviewPersonPage(driver);
		overviewPersonPage.loadPage();
		boolean foundPerson = false;
		boolean hasPersonAllData = false;
		for (WebElement el : overviewPersonPage.getPersonRows()) {
			if (el.getText().contains(randomEmail)) {
				// we found a row with the email
				foundPerson = true;
				// does it contain all other info too?
				hasPersonAllData = true;
				hasPersonAllData &= el.getText().contains(firstName);
				hasPersonAllData &= el.getText().contains(lastName);
				hasPersonAllData &= el.getText().contains(woonplaats);
				// no point continuing
				break;
			}
		}
		assertTrue("We should have found the person in the table", foundPerson);
		assertTrue("The row with the persons email should have all other info too", hasPersonAllData);
	}
	
	@Test
	public void testRegisterFielsRemainFilledIn(){
		// generate a random email address
		String randomEmail = generateRandomEmail("jan.janssens@hotmail.com");
		// other person details
		String firstName = "Jan";
		String lastName = "Janssens";
		String password = ""; // we pass an empty password to make sure the form stays
		String woonplaats = "Leuven";

		submitForm(firstName, lastName, randomEmail, password, woonplaats);

		assertEquals("Firstname should remain filled in",  firstName,   page.getInputValueById("firstName"));
		assertEquals("Lastname should remain filled in",   lastName,    page.getInputValueById("lastName"));
		assertEquals("Email should remain filled in",      randomEmail, page.getInputValueById("email"));
		assertEquals("Woonplaats should remain filled in", woonplaats,  page.getInputValueById("woonplaats"));
	}

	@Test
	public void testRegisterLastNameEmpty(){
		// generate a random email address
		String randomEmail = generateRandomEmail("jan.janssens@hotmail.com");
		// other person details
		String firstName = "Jan";
		String lastName = "";
		String password = "password";
		String woonplaats = "Leuven";

		submitForm(firstName, lastName, randomEmail, password, woonplaats);

		assertEquals("Error should be given", "No last name given", page.getErrorMessage());
	}

	@Test
	public void testRegisterEmailEmpty(){
		// generate a random email address
		String randomEmail = "";
		// other person details
		String firstName = "Jan";
		String lastName = "Janssens";
		String password = "password";
		String woonplaats = "Leuven";

		submitForm(firstName, lastName, randomEmail, password, woonplaats);
		assertEquals("Error should be given", "No id given", page.getErrorMessage());
	}

	@Test
	public void testRegisterPasswordEmpty(){
		// generate a random email address
		String randomEmail = generateRandomEmail("jan.janssens@hotmail.com");
		// other person details
		String firstName = "Jan";
		String lastName = "Janssens";
		String password = "";
		String woonplaats = "Leuven";

		submitForm(firstName, lastName, randomEmail, password, woonplaats);
		
		assertEquals("Error should be given", "No password given", page.getErrorMessage());
	}
	
	@Test
	public void testRegisterUserAlreadyExists(){
		// generate a random email address
		String randomEmail = generateRandomEmail("jan.janssens@hotmail.com");
		// other person details
		String firstName = "Jan";
		String lastName = "Janssens";
		String password = "password";
		String woonplaats = "Leuven";

		// this should be successful
		submitForm(firstName, lastName, randomEmail, password, woonplaats);
		
		// this should be fail
		submitForm(firstName, lastName, randomEmail, password, woonplaats);
		assertEquals("Error should be given", "Email already used", page.getErrorMessage());
	}
}