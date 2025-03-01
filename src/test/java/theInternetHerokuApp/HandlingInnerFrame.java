package theInternetHerokuApp;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HandlingInnerFrame {
	public static WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://ui.vision/demo/webtest/frames/");
		driver.manage().window().maximize();
	}

	@Test
	public void innerFrames() {
		
		// Close the popUp window
		driver.findElement(By.xpath("//div[@aria-label='Close']//*[name()='svg']")).click();

		// Switch to Inner Frame
		WebElement textAreaFrame = driver.findElement(By.xpath("//iframe[@id='mce_0_ifr']"));
		driver.switchTo().frame(textAreaFrame);
		// Accessing text in frame
		String text = driver.findElement(By.xpath("//body[@class=\"mce-content-body mce-content-readonly\"]//p")).getText();

		Assert.assertEquals(text, "Your content goes here.");

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
