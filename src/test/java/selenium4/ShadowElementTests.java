package selenium4;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ShadowElementTests {

	WebDriver driver;

	@BeforeClass
	public void setUp() {

		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("http://localhost:3000/");

	}

	@Test(priority = 1)
	public void addItemToday() throws InterruptedException {
		Thread.sleep(3000);
		WebElement todayShadow = expandRootElement(driver.findElement(By.tagName("todo-app")));
		WebElement todayAddItem = expandRootElement(todayShadow.findElement(By.tagName("add-item")));
		todayAddItem.findElement(By.tagName("textarea")).sendKeys("Selenium Conf Today@2020");
		Thread.sleep(3000);
		todayAddItem.findElement(By.tagName("textarea")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		todayAddItem.findElement(By.tagName("textarea")).clear();
		todayAddItem.findElement(By.tagName("textarea")).sendKeys("Selenium Conf Today@Bengaluru");
		Thread.sleep(3000);
		todayAddItem.findElement(By.tagName("textarea")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
	}
	
	@Test(priority = 2)
	public void addItemLater() throws InterruptedException {
		WebElement todayShadow = expandRootElement(driver.findElement(By.tagName("todo-app")));
		WebElement todayAddItem = expandRootElement(todayShadow.findElement(By.tagName("add-item")));
		todayAddItem.findElement(By.cssSelector("button#later")).click();
		Thread.sleep(3000);
		todayAddItem.findElement(By.tagName("textarea")).clear();
		todayAddItem.findElement(By.tagName("textarea")).sendKeys("Selenium Conf Later@2020");
		Thread.sleep(3000);
		todayAddItem.findElement(By.tagName("textarea")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		todayAddItem.findElement(By.tagName("textarea")).clear();
		todayAddItem.findElement(By.tagName("textarea")).sendKeys("Selenium Conf Later@Bengaluru");
		Thread.sleep(3000);
		todayAddItem.findElement(By.tagName("textarea")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
	}

	@Test(priority = 3, enabled = false)
	public void deleteItemLater() throws InterruptedException {
		WebElement todayShadow = expandRootElement(driver.findElement(By.tagName("todo-app")));
		WebElement listItems = expandRootElement(todayShadow.findElement(By.tagName("list-items")));
		WebElement toDoItem = expandRootElement(listItems.findElement(By.tagName("todo-item")));
		toDoItem.findElement(By.cssSelector("div.list-item div.other-actions button.delete i.far.fa-trash-alt")).click();	// button.delete 
		Thread.sleep(3000);
	}
	
	//Returns shadow-root element - passing element as a arguments[0]
	public WebElement expandRootElement(WebElement element) {
		WebElement ele = (WebElement) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].shadowRoot",element);
		return ele;
	}

	@AfterClass
	public void tearDown() {
		if(driver != null) {
			driver.close();
			driver.quit();
		}
	}
}

