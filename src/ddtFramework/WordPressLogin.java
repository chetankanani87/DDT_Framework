package ddtFramework;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class WordPressLogin {
	
	WebDriver driver;
	
	@Test(dataProvider="wordpressData")
	public void loginToWordPress(String username, String password) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Chetan\\Softs\\SeleniumSuite\\WebDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		
		driver.get("http://demosite.center/wordpress/wp-login.php");
		driver.findElement(By.id("user_login")).sendKeys(username);
		driver.findElement(By.id("user_pass")).sendKeys(password);
		driver.findElement(By.id("wp-submit")).click();
		Thread.sleep(2000);
		
		//System.out.println(driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains("Dashboard"), "User is not able to Login - Invalid Credentials.");
		System.out.println("Page Title verified - User is able to login Successfully...!");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@DataProvider(name="wordpressData")
	public Object[][] passData(){
		Object[][] data = new Object[3][2];
		data[0][0] = "admin1";
		data[0][1] = "demo1";
		data[1][0] = "admin";
		data[1][1] = "demo123";
		data[2][0] = "admin2";
		data[2][1] = "demo1234";
		return data;
	}

}
