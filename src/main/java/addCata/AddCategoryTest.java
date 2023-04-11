package addCata;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddCategoryTest {

	private WebDriver driver;

	@BeforeClass
	public void init() {
		// setup for system property
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(co);
		// delete cookies
		driver.manage().deleteAllCookies();
		// go to website
		driver.get("http://techfios.com/test/101/");

		driver.manage().window().maximize();
	}

	@Test
	public void testAddCategory() {

		// Enter the category name
		WebElement categoryNameInput = driver.findElement(By.xpath("//*[@id=\"extra\"]/input[1]"));
		categoryNameInput.sendKeys("Test Category");

		// Click the "Save" button
		WebElement addCatagoryButton = driver.findElement(By.xpath("//*[@id=\"extra\"]/input[2]"));
		addCatagoryButton.click();

	}

	@Test
	public void testAddDuplicateCategory() {
		// Navigate to the add category page
		driver.get("http://techfios.com/test/101/");

		// Enter a category name that already exists
		String categoryName = "Test Category";
		WebElement categoryNameField = driver.findElement(By.xpath("//*[@id=\"extra\"]/input[1]"));
		categoryNameField.sendKeys(categoryName);

		// Click the Add button
		WebElement addButton = driver.findElement(By.xpath("//*[@id=\"extra\"]/input[2]"));
		addButton.click();

		// Verify that the correct error message is displayed
		WebElement errorMessage = driver.findElement(By.xpath("/html/body/span[1]"));
		Assert.assertEquals(errorMessage.getText(),
				"The category you want to add already exists: " + categoryName + ".");
	}

	@Test
	public void testMonthDropdownOptions() {
		// Navigate to the page with the Due Date dropdown
		driver.get("http://techfios.com/test/101/");

		

		// Find the month dropdown element within the Due Date dropdown
		WebElement monthDropdown = driver.findElement(By.xpath("//*[@id=\"extra\"]/select[3]"));

		// Get the list of options in the month dropdown
		List<WebElement> monthOptions = monthDropdown.findElements(By.tagName("select"));

		// Verify that the month dropdown has all the months (jan, feb, mar ...)
		Assert.assertEquals(monthOptions.size(), 12);
		Assert.assertTrue(monthOptions.get(0).getText().equals("jan"));
		Assert.assertTrue(monthOptions.get(1).getText().equals("feb"));
		Assert.assertTrue(monthOptions.get(2).getText().equals("mar"));
		Assert.assertTrue(monthOptions.get(3).getText().equals("apr"));
		Assert.assertTrue(monthOptions.get(4).getText().equals("may"));
		Assert.assertTrue(monthOptions.get(5).getText().equals("jun"));
		Assert.assertTrue(monthOptions.get(6).getText().equals("jul"));
		Assert.assertTrue(monthOptions.get(7).getText().equals("aug"));
		Assert.assertTrue(monthOptions.get(8).getText().equals("sep"));
		Assert.assertTrue(monthOptions.get(9).getText().equals("oct"));
		Assert.assertTrue(monthOptions.get(10).getText().equals("nov"));
		Assert.assertTrue(monthOptions.get(11).getText().equals("dec"));
	}

	
}
