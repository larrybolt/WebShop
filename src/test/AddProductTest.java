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

public class AddProductTest {
	private WebDriver driver;
	private AddProductPage page;

	@BeforeClass
    public static void oneTimeSetUp() {
		System.setProperty("webdriver.chrome.driver", "/Users/larrybolt/Downloads/chromedriver");
	}

	@Before
    public void setup() {
		driver = new ChromeDriver();
		page = new AddProductPage(driver);
	}

	@After
	public void clean() {
		driver.quit();
	}

	private void submitForm(String name,String price, String imgUrl) {
		page.loadPage();
		page.setName(name);
		page.setPrice(price);
		page.setImgUrl(imgUrl);
		page.confirm();
	}

	private String generateRandomNumber() {
		return (int)(Math. random() * 1000 + 1)+"";
	}

	@Test
	public void testAddProductCorrect() {
		String name = "Test Product "+generateRandomNumber();
		String price = generateRandomNumber();
		String imgUrl = "https://placehold.it/100/ff0000/ffffff?text="+generateRandomNumber();

		submitForm(name, price, imgUrl);

		OverviewProductPage overviewProductPage = new OverviewProductPage(driver);
		assertTrue("After submitting we should be on the product overview page", overviewProductPage.isCorrectPage());

		// Check on overview if product is added to overview
		overviewProductPage.loadPage();
		boolean foundProduct = false;
		boolean hasProductAllData = false;
		for (WebElement el : overviewProductPage.getPersonRows()) {
			if (el.getText().contains(name)) {
				// we found a row with the name
				foundProduct = true;
				// does it contain all other info too?
				hasProductAllData = true;
				hasProductAllData &= el.getText().contains(price);
				hasProductAllData &= el.getText().contains(imgUrl);
				// no point continuing
				break;
			}
		}
		assertTrue("We should have found the product in the table", foundProduct);
		assertTrue("The row with the product name should have all other info too", hasProductAllData);
	}

	@Test
	public void testAddProductNameEmpty(){
		String name = "";
		String price = generateRandomNumber();
		String imgUrl = "https://placehold.it/100/ff0000/ffffff?text="+generateRandomNumber();

		submitForm(name, price, imgUrl);

		assertEquals("Error should be given", "No name given", page.getErrorMessage());
	}

	@Test
	public void testAddProductPriceEmpty(){
		String name = "Test Product "+generateRandomNumber();
		String price = "";
		String imgUrl = "https://placehold.it/100/ff0000/ffffff?text="+generateRandomNumber();

		submitForm(name, price, imgUrl);

		assertEquals("Error should be given", "No price given", page.getErrorMessage());
	}

	@Test
	public void testAddProductPriceInvalid(){
		String name = "Test Product "+generateRandomNumber();
		String price = "TEXTINSTEADOFVALUE";
		String imgUrl = "https://placehold.it/100/ff0000/ffffff?text="+generateRandomNumber();

		submitForm(name, price, imgUrl);

		assertEquals("Error should be given", "Invalid price", page.getErrorMessage());
	}

	@Test
	public void testAddProductPriceValidDouble(){
		String name = "Test Product valid double as price "+generateRandomNumber();
		String price = "101.10";
		String imgUrl = "https://placehold.it/100/ff0000/ffffff?text="+generateRandomNumber();

		submitForm(name, price, imgUrl);

		assertEquals("Error should be given", "Invalid price", page.getErrorMessage());
	}

	@Test
	public void testAddProductImgUrlEmpty(){
		String name = "Test Product "+generateRandomNumber();
		String price = generateRandomNumber();
		String imgUrl = "";

		submitForm(name, price, imgUrl);

		assertEquals("Error should be given", "Invalid url", page.getErrorMessage());
	}

	@Test
	public void testAddProductImgUrlInvalid(){
		String name = "Test Product "+generateRandomNumber();
		String price = generateRandomNumber();
		String imgUrl = "\\etc\\passwd";

		submitForm(name, price, imgUrl);

		assertEquals("Error should be given", "Invalid url", page.getErrorMessage());
	}
}