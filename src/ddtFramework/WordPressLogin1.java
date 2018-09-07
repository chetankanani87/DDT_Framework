package ddtFramework;

import org.testng.annotations.Test;
import lib.ExcelDataConfig;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class WordPressLogin1 {
	WebDriver driver;

	@Test(dataProvider = "wordpressData")
	public void loginToWordPress(String username, String password) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Chetan\\Softs\\SeleniumSuite\\WebDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://demosite.center/wordpress/wp-login.php");
		driver.findElement(By.id("user_login")).sendKeys(username);
		driver.findElement(By.id("user_pass")).sendKeys(password);
		driver.findElement(By.id("wp-submit")).click();
		Thread.sleep(2000);
		// System.out.println(driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains("Dashboard"), "User is not able to Login - Invalid Credentials.");
		System.out.println("Page Title verified - User is able to login Successfully...!");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@DataProvider(name="wordpressData")
	public Object[][] passData() throws Exception{
		ExcelDataConfig config = new ExcelDataConfig("C:\\Users\\hck\\eclipse-workspace\\DataDrivenTestingFramework\\TestData\\InputData.xlsx");
		int rows = config.getRowCount(0);
		Object[][] data = new Object[rows][2];
		for(int i = 0; i < rows; i++) {
			data[i][0] = config.getData(0, i, 0);
			data[i][1] = config.getData(0, i, 1);
		}
		return data;
	}

}
